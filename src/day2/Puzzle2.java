package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle2 {
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
        int sum = getSumOfPowersOfSets(gamesMap);
        System.out.println(sum);
    }

    private static int getSumOfPowersOfSets(Map<Integer, List<Map<String, Integer>>> gamesMap) {
        int sum = 0;
        for (var entry : gamesMap.entrySet()) {
            List<Map<String, Integer>> currentEntryValue = entry.getValue();

            int minRed = 0;
            int minGreen = 0;
            int minBlue = 0;

            for (Map<String, Integer> boxesEntries : currentEntryValue) {
                for (var boxEntry : boxesEntries.entrySet()) {
                    switch (boxEntry.getKey()) {
                        case "red" -> {
                            if (minRed == 0) {
                                minRed = boxEntry.getValue();
                            } else if (minRed < boxEntry.getValue()) {
                                minRed = boxEntry.getValue();
                            }
                        }

                        case "green" -> {
                            if (minGreen == 0) {
                                minGreen = boxEntry.getValue();
                            } else if (minGreen < boxEntry.getValue()) {
                                minGreen = boxEntry.getValue();
                            }
                        }

                        case "blue" -> {
                            if (minBlue == 0) {
                                minBlue = boxEntry.getValue();
                            } else if (minBlue < boxEntry.getValue()) {
                                minBlue = boxEntry.getValue();
                            }
                        }
                    }
                }
            }

            int pow = minRed * minGreen * minBlue;
            System.out.println(entry.getKey() + " | " + pow);
            sum += pow;
        }
        return sum;
    }

    private static List<String> readInputFile() {
        try {
            return Files.readAllLines(Path.of("./src/input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
