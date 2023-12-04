package day3;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.readInputFile;

public class Puzzle2 {

    private record NumWithGearLocation(int num, int line, int row){}
    public static void main(String[] args) {
        //create an arr with our inputs
        List<String> list = readInputFile();

        //check if all rows are the same length
        if (!sameRowLengthsChecker(list)) {
            System.out.println("Invalid input file.");
            return;
        }

        List<NumWithGearLocation> NumWithGearLocationList = getNumbersNextToGears(list);

        List<Integer> numbs = new ArrayList<>();
        List<int[]> ignoredGears = new ArrayList<>();
        for (int i = 0; i < NumWithGearLocationList.size(); i++) {
            NumWithGearLocation currentNumWithGearLocation = NumWithGearLocationList.get(i);
            int[] currentGear = {currentNumWithGearLocation.line, currentNumWithGearLocation.row};
            for (NumWithGearLocation comparingNumWithGearLocation : NumWithGearLocationList) {
                if (currentNumWithGearLocation.line == comparingNumWithGearLocation.line &&
                    currentNumWithGearLocation.row == comparingNumWithGearLocation.row &&
                    currentNumWithGearLocation != comparingNumWithGearLocation &&
                    IsIgnored(ignoredGears, currentGear)) {
                        ignoredGears.add(currentGear);
                        numbs.add(comparingNumWithGearLocation.num * currentNumWithGearLocation.num);
                        break;
                }
            }
        }

        int sum = 0;
        for (int num : numbs) {
            sum += num;
        }
        System.out.println(sum);
    }

    private static boolean IsIgnored(List<int[]> inputList, int[] currentGear) {
        for (int[] num : inputList) {
            if (num[0] == currentGear[0] && num[1] == currentGear[1]) return false;
        }
        return true;
    }

    private static  List<NumWithGearLocation> getNumbersNextToGears(List<String> inputList) {
        List<NumWithGearLocation> NumWithGearLocationList = new ArrayList<>();

        for (int l = 0; l < inputList.size(); l++) {
            String currentLine = inputList.get(l);
            for (int c = 0; c < currentLine.length(); c++) {
                char currentChar = currentLine.charAt(c);

                if (Character.isDigit(currentChar)) {
                    int[] nextToSymbol = nextToGear(inputList, l, c, inputList.size() - 1, currentLine.length() - 1);
                    if (nextToSymbol[0] != 0) {
                        NumWithGearLocationList.add(new NumWithGearLocation(nextToSymbol[0], nextToSymbol[2], nextToSymbol[3]));
                    }

                    c = nextToSymbol[1];
                }
            }
        }

        return NumWithGearLocationList;
    }

    public static int[] numExtender(int[] gearLocation, List<String> inputList, int l, int c, int maxC) {
        StringBuilder stringOfNum = new StringBuilder(String.valueOf(inputList.get(l).charAt(c)));

        int charCounter = c - 1;
        while (Character.isDigit(inputList.get(l).charAt(charCounter)) && charCounter > 0) {
            stringOfNum.insert(0, inputList.get(l).charAt(charCounter));
            charCounter--;
        }
        if (charCounter == 0 && Character.isDigit(inputList.get(l).charAt(charCounter))) {
            stringOfNum.insert(0, inputList.get(l).charAt(charCounter));
        }

        charCounter = c + 1;
        while (Character.isDigit(inputList.get(l).charAt(charCounter)) && charCounter < maxC) {
            stringOfNum.append(inputList.get(l).charAt(charCounter));
            charCounter++;
        }
        if (charCounter == maxC && Character.isDigit(inputList.get(l).charAt(charCounter))) {
            stringOfNum.append(inputList.get(l).charAt(charCounter));
        }

        return new int[]{ Integer.parseInt(String.valueOf(stringOfNum)), charCounter, gearLocation[0], gearLocation[1] };
    }

    public static int[] nextToGear(List<String> inputList, int currentLine, int currentChar, int maxLine, int maxChar) {
        if (!(currentLine == 0 || currentChar == 0) &&
            inputList.get(currentLine - 1).charAt(currentChar - 1) == '*' ) {
            int[] gearLocation = new int[] {currentLine - 1, currentChar - 1};
            return numExtender(gearLocation, inputList, currentLine, currentChar, maxChar);
        }

        if (currentLine != 0 &&
            inputList.get(currentLine - 1).charAt(currentChar) == '*') {
            int[] gearLocation = new int[] {currentLine - 1, currentChar};
            return numExtender(gearLocation, inputList, currentLine, currentChar, maxChar);
        }

        if (!(currentLine == 0 || currentChar == maxChar) &&
            inputList.get(currentLine - 1).charAt(currentChar + 1) == '*') {
            int[] gearLocation = new int[] {currentLine - 1, currentChar + 1};
            return numExtender(gearLocation, inputList, currentLine, currentChar, maxChar);
        }

        if (currentChar != 0 &&
            inputList.get(currentLine).charAt(currentChar - 1) == '*') {
            int[] gearLocation = new int[] {currentLine, currentChar - 1};
            return numExtender(gearLocation, inputList, currentLine, currentChar, maxChar);
        }

        if (currentChar != maxChar &&
            inputList.get(currentLine).charAt(currentChar + 1) == '*') {
            int[] gearLocation = new int[] {currentLine, currentChar + 1};
            return numExtender(gearLocation, inputList, currentLine, currentChar, maxChar);
        }

        if (!(currentLine == maxLine || currentChar == 0) &&
            inputList.get(currentLine + 1).charAt(currentChar - 1) == '*') {
            int[] gearLocation = new int[] {currentLine + 1, currentChar - 1};
            return numExtender(gearLocation, inputList, currentLine, currentChar, maxChar);
        }

        if (currentLine != maxLine &&
            inputList.get(currentLine + 1).charAt(currentChar) == '*') {
            int[] gearLocation = new int[] {currentLine + 1, currentChar};
            return numExtender(gearLocation, inputList, currentLine, currentChar, maxChar);
        }

        if (!(currentLine == maxLine || currentChar == maxChar) &&
            inputList.get(currentLine + 1).charAt(currentChar + 1) == '*') {
            int[] gearLocation = new int[] {currentLine + 1, currentChar + 1};
            return numExtender(gearLocation, inputList, currentLine, currentChar, maxChar);
        }

        return new int[]{ 0, currentChar };
    }

    private static boolean sameRowLengthsChecker(List<String> inputList) {
        boolean roesAreSameLength = true;
        for (String row : inputList)
            if (inputList.getFirst().length() != row.length()) {
                roesAreSameLength = false;
                break;
            }
        return roesAreSameLength;
    }
}
