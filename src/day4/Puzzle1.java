package day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static utils.Utils.readInputFile;

public class Puzzle1 {
    private record CardRecord(int cardNum, int[] winningValues, Set<Integer> givenValues) {
    }

    public static void main(String[] args) {
        List<String> list = readInputFile();
        List<CardRecord> parsedList = parseList(list);
        List<Integer> cardsWorth = cardsWorth(parsedList);

        int cardsWorthTotal = 0;
        for (int inputCard : cardsWorth) {
            cardsWorthTotal += inputCard;
        }

        System.out.println(cardsWorthTotal);
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

    private static List<Integer> cardsWorth(List<CardRecord> inputList) {
        List<Integer> out = new ArrayList<>();
        for (CardRecord currentCardRecord : inputList) {
            int cardValue = 1;
            for (int winningValue : currentCardRecord.winningValues) {
                if (currentCardRecord.givenValues.contains(winningValue)) {
                    cardValue *= 2;
                }
            }
            out.add(cardValue / 2);
        }
        return out;
    }
}
