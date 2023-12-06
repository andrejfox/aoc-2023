package day6;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.readInputFile;

public class Puzzle1 {
    public static void main(String[] args) {
        List<String> list = readInputFile();

        List<List<Integer>> parsedList = parser(list);
        List<Integer> timeList = parsedList.get(0);
        List<Integer> distanceList = parsedList.get(1);

        if (timeList.size() != distanceList.size()) {
            return;
        }

        int total = 1;
        for (int i = 0; i < timeList.size(); i++) {
            int counter = 0;
            for (int x = 1; x < timeList.get(i); x++) {
                int y = timeList.get(i) - x;
                if (y * x > distanceList.get(i)) {
                    System.out.println(y + " | " + x + " | " + y * x);
                    counter++;
                }
            }
            System.out.println("-------" + counter);
            total *= counter;
        }
        System.out.println(total);
    }

    private static List<List<Integer>> parser(List<String> inputList) {
        List<List<Integer>> out = new ArrayList<>();

        for (String line : inputList) {
            String[] numberStringsWithSpaces = line.split(":")[1].split(" ");
            List<Integer> numbers = new ArrayList<>();
            for (String numberString : numberStringsWithSpaces) {
                if (!numberString.isEmpty()) {
                    numbers.add(Integer.parseInt(numberString));
                }
            }
            out.add(numbers);
        }

        return out;
    }
}
