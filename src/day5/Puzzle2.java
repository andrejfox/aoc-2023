package day5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static utils.Utils.readInputFile;

public class Puzzle2 {
    private record Mapping(long destinationRangeStart, long sourceRangeStart, long rangeLength) {}
    public static void main(String[] args) {
        List<String> list = readInputFile();
        List<Long> seeds = seedsParser(list);
        List<List<Mapping>> mappings = mappingParser(list);

        System.out.println(seeds);

        List<Long> locations = new ArrayList<>();
        for (long seed : seeds) {
            locations.add(getLocation(seed, mappings));
        }

        Collections.sort(locations);
        System.out.println(locations.get(0));
    }

    private static long getLocation(long seed, List<List<Mapping>> mappings) {
        long soil = getNextMappingValue(seed, mappings.get(MappingName.SEED_TO_SOIL_MAP.index));
        long fertilizer = getNextMappingValue(soil, mappings.get(MappingName.SOIL_TO_FERTILIZER_MAP.index));
        long watter = getNextMappingValue(fertilizer, mappings.get(MappingName.FERTILIZER_TO_WATER_MAP.index));
        long light = getNextMappingValue(watter, mappings.get(MappingName.WATER_TO_LIGHT_MAP.index));
        long temperature = getNextMappingValue(light, mappings.get(MappingName.LIGHT_TO_TEMPERATURE_MAP.index));
        long humidity = getNextMappingValue(temperature, mappings.get(MappingName.TEMPERATURE_TO_HUMIDITY_MAP.index));
        return getNextMappingValue(humidity, mappings.get(MappingName.HUMIDITY_TO_LOCATION_MAP.index));
    }

    private static long getNextMappingValue(long seed, List<Mapping> mappings) {
        long out = seed;
        for (Mapping currentMapping : mappings) {
            if (seed >= currentMapping.sourceRangeStart && seed < currentMapping.sourceRangeStart + currentMapping.rangeLength) {
                out += currentMapping.destinationRangeStart - currentMapping.sourceRangeStart;
                break;
            }
        }
        return out;
    }

    private static List<Long> seedsParser(List<String> inputList) {
        String inputLine = inputList.get(0);
        String[] seeds = inputLine.split(": ")[1].split(" ");
        List<Long> out1 = new ArrayList<>();
        List<Long> out2 = new ArrayList<>();
        for (long i = 0; i < seeds.length; i++) {
            if (i % 2 == 0) {
                out1.add(Long.parseLong(seeds[(int) i]));
            } else {
                out2.add(Long.parseLong(seeds[(int) i]));
            }
        }

        List<Long> out = new ArrayList<>();
        for (long i = 0; i < out1.size(); i++) {
            for (long x = Math.min(out1.get((int) i), out2.get((int) i)); x < Math.max(out1.get((int) i), out2.get((int) i)); x++) {
                out.add(x);
            }
        }

        return out;
    }

    private static List<List<Mapping>> mappingParser(List<String> inputList) {
        List<List<Mapping>> out = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).isEmpty()) {
                List<Mapping> currentMappingList = new ArrayList<>();

                i ++;
                do {
                    i++;
                    String[] currentMappingStingArr = inputList.get(i).split(" ");
                    currentMappingList.add(new Mapping(Long.parseLong(currentMappingStingArr[0]), Long.parseLong(currentMappingStingArr[1]), Long.parseLong(currentMappingStingArr[2])));
                } while (!(i + 1 >= inputList.size() || inputList.get(i + 1).isEmpty()));

                out.add(currentMappingList);
            }
        }
        return out;
    }
}
