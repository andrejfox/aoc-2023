package day2;

import java.util.*;

import static utils.Utils.readInputFile;

public class Puzzle1 {
    public static void main(String[] args) {
        //create an arr with our inputs
        List<String> inputList = readInputFile();

        //initialize a map of arrayLists of maps{ color, numberOfBoxes }
        Map<Integer, List<Map<String, Integer>>> gamesMap = new HashMap<>();

        //get map of arrayLists of maps{ color, numberOfBoxes }
        for (String input : inputList) {
            String[] currentSplitString = input.split(":");

            //get outer map key
            String gameID = currentSplitString[0];
            int gameStringIDLength = gameID.length();
            String gameIDStringNum = gameID.substring(5, gameStringIDLength);

            String[] subsetsArr = currentSplitString[1].split(";");

            //get arrayLists of maps{ color, numberOfBoxes }
            List<Map<String, Integer>> subsets = new ArrayList<>();
            for (String subsetsArrElement : subsetsArr) {
                String[] currentSubsetArrElementArr = subsetsArrElement.trim().split(",");

                //get map{ color, numberOfBoxes }
                Map<String, Integer> mapOfColoredBoxes = new HashMap<>();
                for (String currentSubsetArrElementArrElement : currentSubsetArrElementArr) {
                    String[] currentSubsetArrElementArrElementArr = currentSubsetArrElementArrElement.trim().split(" ");

                    //get color and numberOfBoxes
                    String color = currentSubsetArrElementArrElementArr[1];
                    Integer numberOfBoxes = Integer.parseInt(currentSubsetArrElementArrElementArr[0]);

                    mapOfColoredBoxes.put(color, numberOfBoxes);
                }

                subsets.add(mapOfColoredBoxes);
            }

            gamesMap.put(Integer.parseInt(gameIDStringNum), subsets);
        }

        //calculate the sum of possible keys
        int sum = getSumOfPossibleGames(gamesMap);
        System.out.println(sum);
    }

    private static int getSumOfPossibleGames(Map<Integer, List<Map<String, Integer>>> gamesMap) {
        int sum = 0;
        for (var entry : gamesMap.entrySet()) {
            List<Map<String, Integer>> currentEntryValue = entry.getValue();

            //is the current entry possible?
            boolean isPossible = true;
            boxesEntriesFor : for (Map<String, Integer> boxesEntries : currentEntryValue) {
                for (var boxEntry : boxesEntries.entrySet()) {
                    //possibility checks
                    if (boxEntry.getKey().equals("red") && boxEntry.getValue() > 12) {
                        isPossible = false;
                        break boxesEntriesFor;
                    } else if (boxEntry.getKey().equals("green") && boxEntry.getValue() > 13) {
                        isPossible = false;
                        break boxesEntriesFor;
                    } else if (boxEntry.getKey().equals("blue") && boxEntry.getValue() > 14) {
                        isPossible = false;
                        break boxesEntriesFor;
                    }
                }
            }

            //is yes add the ID number of the game to sum
            if (isPossible) {
                sum += entry.getKey();
            }
        }
        return sum;
    }
}
