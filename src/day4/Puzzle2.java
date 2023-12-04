package day4;

import java.util.*;
import java.util.stream.Collectors;

import static utils.Utils.readInputFile;

public class Puzzle2 {
    private record CardRecord(int cardNum, int[] winningValues, Set<Integer> givenValues) {
    }

    public static void main(String[] args) {
        List<String> list = readInputFile();
        List<CardRecord> parsedList = parseList(list);

        Map<Integer, Integer> cache = new HashMap<>();
        int sum = 0;
        for (int i = 1; i <= parsedList.size(); i++) {
            sum += magicShitInTree(parsedList, i, cache);
        }

        System.out.println(sum);
    }

    private static int magicShitInTree(List<CardRecord> inputList, int cardNum, Map<Integer, Integer> cache) {
        if (cache.containsKey(cardNum)) {
            return cache.get(cardNum);
        }

        int totalBranchSum = 0;
        int branches = cardWorth(inputList, cardNum);

        for (int i = 1; i <= branches; i++) {
            totalBranchSum += magicShitInTree(inputList, cardNum + i, cache);
        }

        totalBranchSum++;

        cache.put(cardNum, totalBranchSum);
        return totalBranchSum;
    }

    private static List<CardRecord> parseList(List<String> inputList) {
        List<CardRecord> out = new ArrayList<>();
        for (String currentLine : inputList) {
            String[] cardNumSplit = currentLine.split(": ");

            int card = Integer.parseInt(cardNumSplit[0].substring(4, 8).trim());

            String[] winningValuesSplit = cardNumSplit[1].split(" \\| ");

            int[] winningValues = Arrays.stream(winningValuesSplit[0].split(" ")).filter(x -> !x.isEmpty()).mapToInt(Integer::parseInt).toArray();
            Set<Integer> givenValues = Arrays.stream(winningValuesSplit[1].split(" ")).filter(x -> !x.isEmpty()).map(Integer::valueOf).collect(Collectors.toSet());

            CardRecord CurrentCardRecord = new CardRecord(card, winningValues, givenValues);
            out.add(CurrentCardRecord);
        }
        return out;
    }

    private static int cardWorth(List<CardRecord> inputList, int cardNum) {
        CardRecord currentCardRecord = inputList.get(cardNum - 1);

        int cardValue = 0;
        for (int winningValue : currentCardRecord.winningValues) {
            if (currentCardRecord.givenValues.contains(winningValue)) {
                cardValue++;
            }
        }

        return cardValue;
    }
}
