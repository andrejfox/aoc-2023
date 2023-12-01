package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;

public class Puzzle2 {
    public static void main(String[] args) {
        //create an arr with our inputs
        List<String> inputList = readInputFile();
        List<Integer> list = new ArrayList<>();

        int indexCounter = 0;
        for (String value : inputList) {
            int maxCharIndex = value.length() - 1;

            //get the firstDigit from the string
            char firstDigitChar = '0';
            for (int i = 0; i <= maxCharIndex; i++) {
                if (isDigit(value.charAt(i))) {
                    firstDigitChar = value.charAt(i);
                    break;
                }

                switch (value.charAt(i)) {
                    case 'o':
                        if (value.length() - i < 3) break;
                        if (value.charAt(i + 1) == 'n' && value.charAt(i + 2) == 'e') {
                            firstDigitChar = '1';
                            break;
                        }
                        break;

                    case 't':
                        if (value.length() - i < 3) break;
                        if (value.charAt(i + 1) == 'w' && value.charAt(i + 2) == 'o') {
                            firstDigitChar = '2';
                            break;
                        }

                        if (value.length() - i < 5) break;
                        if (value.charAt(i + 1) == 'h' && value.charAt(i + 2) == 'r' && value.charAt(i + 3) == 'e' && value.charAt(i + 4) == 'e') {
                            firstDigitChar = '3';
                            break;
                        }
                        break;

                    case 'f':
                        if (value.length() - i < 4) break;
                        if (value.charAt(i + 1) == 'o' && value.charAt(i + 2) == 'u' && value.charAt(i + 3) == 'r') {
                            firstDigitChar = '4';
                            break;
                        }

                        if (value.charAt(i + 1) == 'i' && value.charAt(i + 2) == 'v' && value.charAt(i + 3) == 'e') {
                            firstDigitChar = '5';
                            break;
                        }
                        break;
                    case 's':
                        if (value.length() - i < 3) break;
                        if (value.charAt(i + 1) == 'i' && value.charAt(i + 2) == 'x') {
                            firstDigitChar = '6';
                            break;
                        }

                        if (value.length() - i < 5) break;
                        if (value.charAt(i + 1) == 'e' && value.charAt(i + 2) == 'v' && value.charAt(i + 3) == 'e' && value.charAt(i + 4) == 'n') {
                            firstDigitChar = '7';
                            break;
                        }
                        break;

                    case 'e':
                        if (value.length() - i < 5) break;
                        if (value.charAt(i + 1) == 'i' && value.charAt(i + 2) == 'g' && value.charAt(i + 3) == 'h' && value.charAt(i + 4) == 't') {
                            firstDigitChar = '8';
                            break;
                        }
                        break;

                    case 'n':
                        if (value.length() - i < 4) break;
                        if (value.charAt(i + 1) == 'i' && value.charAt(i + 2) == 'n' && value.charAt(i + 3) == 'e') {
                            firstDigitChar = '9';
                            break;
                        }
                        break;

                    default: break;
                }

                if (firstDigitChar != '0') {
                    break;
                }
            }

            //get the lastDigit from the string
            char lastDigitChar = '0';
            for (int i = maxCharIndex; i >= 0; i--) {
                if (isDigit(value.charAt(i))) {
                    lastDigitChar = value.charAt(i);
                    break;
                }

                int charsLeft = value.length() - (value.length() - i);
                switch (value.charAt(i)) {
                    case 'e':
                        if (charsLeft < 3) break;
                        if (value.charAt(i - 1) == 'n' && value.charAt(i - 2) == 'o') {
                            lastDigitChar = '1';
                            break;
                        }

                        if (charsLeft < 4) break;
                        if (value.charAt(i - 1) == 'v' && value.charAt(i - 2) == 'i' && value.charAt(i - 3) == 'f') {
                            lastDigitChar = '5';
                            break;
                        }

                        if (value.charAt(i - 1) == 'n' && value.charAt(i - 2) == 'i' && value.charAt(i - 3) == 'n') {
                            lastDigitChar = '9';
                            break;
                        }

                        if (charsLeft < 5) break;
                        if (value.charAt(i - 1) == 'e' && value.charAt(i - 2) == 'r' && value.charAt(i - 3) == 'h' && value.charAt(i - 4) == 't') {
                            lastDigitChar = '3';
                            break;
                        }
                        break;

                    case 'o':
                        if (charsLeft < 3) break;
                        if (value.charAt(i - 1) == 'w' && value.charAt(i - 2) == 't') {
                            lastDigitChar = '2';
                            break;
                        }
                        break;

                    case 'r':
                        if (charsLeft < 4) break;
                        if (value.charAt(i - 1) == 'u' && value.charAt(i - 2) == 'o' && value.charAt(i - 3) == 'f') {
                            lastDigitChar = '4';
                            break;
                        }
                        break;

                    case 'x':
                        if (charsLeft < 3) break;
                        if (value.charAt(i - 1) == 'i' && value.charAt(i - 2) == 's') {
                            lastDigitChar = '6';
                            break;
                        }
                        break;

                    case 'n':
                        if (charsLeft < 5) break;
                        if (value.charAt(i - 1) == 'e' && value.charAt(i - 2) == 'v' && value.charAt(i - 3) == 'e' && value.charAt(i - 4) == 's') {
                            lastDigitChar = '7';
                            break;
                        }
                        break;

                    case 't':
                        if (charsLeft < 5) break;
                        if (value.charAt(i - 1) == 'h' && value.charAt(i - 2) == 'g' && value.charAt(i - 3) == 'i' && value.charAt(i - 4) == 'e') {
                            lastDigitChar = '8';
                            break;
                        }
                        break;

                    default: break;
                }

                if (lastDigitChar != '0') {
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
