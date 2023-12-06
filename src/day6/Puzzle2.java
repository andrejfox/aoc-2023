package day6;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.readInputFile;

public class Puzzle2 {
    public static void main(String[] args) {
        List<String> list = readInputFile();
        List<Long> parsedList = parser(list);

        long counter = 0;
        for (long x = 1; x < parsedList.get(0); x++) {
            long y = parsedList.get(0) - x;
            if (y * x > parsedList.get(1)) {
                counter++;
            }
        }

        System.out.println(counter);
    }

    private static List<Long> parser(List<String> inputList) {
        List<Long> out = new ArrayList<>();

        for (String line : inputList) {
            String[] numberStringsWithSpaces = line.split(":")[1].split(" ");
            StringBuilder numberLongString = new StringBuilder();
            for (String numberString : numberStringsWithSpaces) {
                if (!numberString.isEmpty()) {
                    numberLongString.append(numberString);
                }
            }
            out.add(Long.parseLong(numberLongString.toString()));
        }
        return out;
    }
}
