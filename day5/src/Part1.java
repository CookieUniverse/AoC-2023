import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Part1 {

    private static void read(Scanner file) {
        int blankCounter = 0;
        List<Long> seeds = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<Range[]>[] lists = new List[7];

        while (file.hasNext()) {
            String line = file.nextLine();
            if (line.isEmpty()) {
                blankCounter++;
                file.nextLine();
                line = file.nextLine();
            }
            if (blankCounter == 0) {
                seeds = fillSeeds(line);
            } else {
                fillList(line, lists, blankCounter);
            }
        }

        for (List<Range[]> list : lists) {
            List<Long> progress = new ArrayList<>();
            for (Long seed : seeds) {
                boolean found = false;
                for (Range[] rArray : list) {
                    if (seed >= rArray[0].start && seed < rArray[0].end) {
                        progress.add(rArray[1].start + (seed - rArray[0].start));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    progress.add(seed);
                }
            }
            seeds = progress;
        }

        System.out.println(Collections.min(seeds));
    }

    private static void fillList(String line, List<Range[]>[] lists, int index) {
        String[] conversionArray = line.trim().split( " ");
        if (lists[index - 1] == null) {
            lists[index - 1] = new ArrayList<>();
        }
        Range[] ranges = new Range[2];
        long source = Long.parseLong(conversionArray[1]);
        long dest = Long.parseLong(conversionArray[0]);
        long range = Long.parseLong(conversionArray[2]);
        ranges[0] = new Range(source, source + range);
        ranges[1] = new Range(dest, dest + range);
        lists[index - 1].add(ranges);
    }

    private static List<Long> fillSeeds(String line) {
        String[] seedsArray = line.split(": ")[1].trim().split(" ");
        return Arrays.stream(seedsArray)
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        try {
            Scanner test = new Scanner(new FileReader("day5" + File.separator + "tests" + File.separator + "test.txt"));
            System.out.println("Test answer:");
            read(test);
            test.close();
            Scanner puzzle = new Scanner(new FileReader("day5" + File.separator + "src" + File.separator + "input.txt"));
            System.out.println("Puzzle answer:");
            read(puzzle);
            puzzle.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
