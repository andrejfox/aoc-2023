package day3;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.readInputFile;

public class Puzzle1 {
    public static void main(String[] args) {
        //create an arr with our inputs
        List<String> list = readInputFile();

        //check if all rows are the same length
        if (!sameRowLengthsChecker(list)) {
            System.out.println("Invalid input file.");
            return;
        }

        List<Integer> listOfNumbersNextToSymbols = getNumbersNextToSymbols(list);

        int sum = 0;
        for (int currentNum : listOfNumbersNextToSymbols) {
            sum += currentNum;
        }

        System.out.println(sum);
    }

    private static  List<Integer> getNumbersNextToSymbols(List<String> inputList) {
        List<Integer> NumbersNextToSymbolsList = new ArrayList<>();

        for (int l = 0; l < inputList.size(); l++) {
            String currentLine = inputList.get(l);
            for (int c = 0; c < currentLine.length(); c++) {
                char currentChar = currentLine.charAt(c);

                if (Character.isDigit(currentChar)) {
                    int[] nextToSymbol = nextToSymbol(inputList, l, c, inputList.size() - 1, currentLine.length() - 1);

                    if (nextToSymbol[0] != 0) NumbersNextToSymbolsList.add(nextToSymbol[0]);

                    c = nextToSymbol[1];
                }
            }
        }

        return NumbersNextToSymbolsList;
    }

    public static int[] numExtender(List<String> inputList, int l, int c, int maxC) {
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

        return new int[]{ Integer.parseInt(String.valueOf(stringOfNum)), charCounter };
    }

    public static int[] nextToSymbol(List<String> inputList, int currentLine, int currentChar, int maxLine, int maxChar) {
        if (!(currentLine == 0 || currentChar == 0) &&
            inputList.get(currentLine - 1).charAt(currentChar - 1) != '.' &&
            !Character.isDigit(inputList.get(currentLine - 1).charAt(currentChar - 1))) {
            return numExtender(inputList, currentLine, currentChar, maxChar);
        }

        if (currentLine != 0 &&
            inputList.get(currentLine - 1).charAt(currentChar) != '.' &&
            !Character.isDigit(inputList.get(currentLine - 1).charAt(currentChar))) {
            return numExtender(inputList, currentLine, currentChar, maxChar);
        }

        if (!(currentLine == 0 || currentChar == maxChar) &&
            inputList.get(currentLine - 1).charAt(currentChar + 1) != '.' &&
            !Character.isDigit(inputList.get(currentLine - 1).charAt(currentChar + 1))) {
            return numExtender(inputList, currentLine, currentChar, maxChar);
        }

        if (currentChar != 0 &&
            inputList.get(currentLine).charAt(currentChar - 1) != '.' &&
            !Character.isDigit(inputList.get(currentLine).charAt(currentChar - 1))) {
            return numExtender(inputList, currentLine, currentChar, maxChar);
        }

        if (currentChar != maxChar &&
            inputList.get(currentLine).charAt(currentChar + 1) != '.' &&
            !Character.isDigit(inputList.get(currentLine).charAt(currentChar + 1))) {
            return numExtender(inputList, currentLine, currentChar, maxChar);
        }

        if (!(currentLine == maxLine || currentChar == 0) &&
            inputList.get(currentLine + 1).charAt(currentChar - 1) != '.' &&
            !Character.isDigit(inputList.get(currentLine + 1).charAt(currentChar - 1))) {
            return numExtender(inputList, currentLine, currentChar, maxChar);
        }

        if (currentLine != maxLine &&
            inputList.get(currentLine + 1).charAt(currentChar) != '.' &&
            !Character.isDigit(inputList.get(currentLine + 1).charAt(currentChar))) {
            return numExtender(inputList, currentLine, currentChar, maxChar);
        }

        if (!(currentLine == maxLine || currentChar == maxChar) &&
            inputList.get(currentLine + 1).charAt(currentChar + 1) != '.' &&
            !Character.isDigit(inputList.get(currentLine + 1).charAt(currentChar + 1))) {
            return numExtender(inputList, currentLine, currentChar, maxChar);
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
