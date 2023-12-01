package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;

public class Puzzle1 {
    public static void main(String[] args) {
        //create an arr with our inputs
        List<String> inputList = readInputFile();
        List<Integer> list = new ArrayList<>();

        int indexCounter = 0;
        for (String value : inputList) {
            int maxCharIndex = value.length() - 1;

            //get the firstDigit from the string
            char firstDigitChar = '-';
            for (int i = 0; i <= maxCharIndex; i++) {
                if (isDigit(value.charAt(i))) {
                    firstDigitChar = value.charAt(i);
                    break;
                }
            }

            //get the lastDigit from the string
            char lastDigitChar = '-';
            for (int i = maxCharIndex; i >= 0; i--) {
                if (isDigit(value.charAt(i))) {
                    lastDigitChar = value.charAt(i);
                    break;
                }
            }

            //stitch the cars together
            String valueString = firstDigitChar + String.valueOf(lastDigitChar);

            //convert valueString into an int and add it to the list(ArrayList)
            list.add(indexCounter, Integer.parseInt(valueString));
            indexCounter++;
        }

        //add all values together
        int sum = 0;
        for (int value : list) {
            sum += value;
        }

        System.out.println(sum);
    }

    private static List<String> readInputFile() {
        try {
            return Files.readAllLines(Path.of("./src/Day1/input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
