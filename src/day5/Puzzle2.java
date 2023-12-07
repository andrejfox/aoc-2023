package day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.Utils.readInputFile;

public class Puzzle2 {
    private record Mapping(long destinationRangeStart, long sourceRangeStart, long rangeLength) {}

    public static void main(String[] args) {
        List<String> list = readInputFile();
        List<Long[]> seedFields = seedsParser(list);
        List<List<Mapping>> mappings = mappingParser(list);
        long lowestLocationNumber = bigBadAlgorithm(seedFields, mappings);

        System.out.println(lowestLocationNumber);
    }

    private static long bigBadAlgorithm(List<Long[]> seedFields, List<List<Mapping>> mappings) {
        List<Long[]> land = new ArrayList<>();
        for (Long[] currSeedField : seedFields) {
            System.out.println("soil");
            List<Long[]> soil = applyMappingToSeedFields(currSeedField, mappings.get(MappingName.SEED_TO_SOIL_MAP.index));
            for (Long[] cSoil : soil) {
                System.out.println("fertilizer");
                List<Long[]> fertilizer = applyMappingToSeedFields(cSoil, mappings.get(MappingName.SOIL_TO_FERTILIZER_MAP.index));
                for (Long[] cFertilizer : fertilizer) {
                    System.out.println("watter");
                    List<Long[]> watter = applyMappingToSeedFields(cFertilizer, mappings.get(MappingName.FERTILIZER_TO_WATER_MAP.index));
                    for (Long[] cWatter : watter) {
                        System.out.println("light");
                        List<Long[]> light = applyMappingToSeedFields(cWatter, mappings.get(MappingName.WATER_TO_LIGHT_MAP.index));
                        for (Long[] cLight : light) {
                            System.out.println("temperature-----------------");
                            List<Long[]> temperature = applyMappingToSeedFields(cLight, mappings.get(MappingName.LIGHT_TO_TEMPERATURE_MAP.index));
                            for (Long[] cTemperature : temperature) {
                                System.out.println("humidity");
                                List<Long[]> humidity = applyMappingToSeedFields(cTemperature, mappings.get(MappingName.TEMPERATURE_TO_HUMIDITY_MAP.index));
                                for (Long[] cHumidity : humidity) {
                                    System.out.println("land");
                                    land.addAll(applyMappingToSeedFields(cHumidity, mappings.get(MappingName.HUMIDITY_TO_LOCATION_MAP.index)));
                                }
                            }
                        }
                    }
                }
            }
        }

        long smallestLand = 1000000000;
        for (Long[] cLand : land) {
            if (cLand[0] < smallestLand && cLand[0] > 0) smallestLand = cLand[0];
        }

        return smallestLand;
    }

    private static List<Long[]> applyMappingToSeedFields(Long[] inputSeedField, List<Mapping> mappingList) {
        List<Long[]> out = new ArrayList<>();
        out.add(inputSeedField);
        for (Mapping currentMapping : mappingList) {
            long currInStart = currentMapping.sourceRangeStart;
            long currInEnd = currInStart + currentMapping.rangeLength;

            long modifier = currentMapping.destinationRangeStart - currInStart;

            List<Long[]> toRemove = new ArrayList<>();
            List<Long[]> toAdd = new ArrayList<>();
            for (Long[] seedRange : out) {
                out.forEach(x -> System.out.print(Arrays.toString(x)));
                System.out.println();
                long seedStart = seedRange[0];
                long seedEnd = seedStart + seedRange[1];

                //[......]
                if (seedStart > currInEnd || seedEnd < currInStart) {
                    System.out.println("[......]");
                    continue;
                }

                //[###...]
                if (seedStart > currInStart && seedStart < currInEnd && seedEnd > currInEnd) {
                    System.out.println("[###...]");
                    toRemove.add(seedRange);
                    toAdd.add(new Long[] {seedStart + modifier, currInEnd - seedStart});
                    toAdd.add(new Long[] {currInEnd, seedEnd - currInEnd});
                    continue;
                }

                //[...###]
                if (currInStart > seedStart && currInStart < seedEnd && currInEnd > seedEnd) {
                    System.out.println("[...###]");
                    toRemove.add(seedRange);
                    toAdd.add(new Long[] {seedStart, currInStart - seedStart});
                    toAdd.add(new Long[] {currInStart + modifier, seedEnd - currInStart});
                    continue;
                }

                //[..##..]
                if (currInStart < seedStart && currInEnd < seedEnd) {
                    System.out.println("[..##..]");
                    toRemove.add(seedRange);
                    toAdd.add(new Long[]{seedStart, currInStart - seedStart});
                    toAdd.add(new Long[]{currInStart + modifier, currInEnd - currInStart});
                    toAdd.add(new Long[]{currInEnd, seedEnd - currInEnd});
                }

                //[######]
                System.out.println("[######]");
                System.out.println(modifier);
                toRemove.add(seedRange);
                toAdd.add(new Long[]{seedStart + modifier, seedRange[1]});
            }
            out.removeAll(toRemove);
            out.addAll(toAdd);
        }
        return out;
    }

    private static List<Long[]> seedsParser(List<String> inputList) {
        String inputLine = inputList.getFirst();
        String[] seeds = inputLine.split(": ")[1].split(" ");
        List<Long[]> seedFields = new ArrayList<>();
        for (int i = 0; i < seeds.length; i += 2) {
            seedFields.add(new Long[]{Long.valueOf((seeds[i])), Long.valueOf(seeds[i + 1])});
        }
        return seedFields;
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