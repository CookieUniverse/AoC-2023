import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Part2 {

    private static void read(Scanner file) {
        int blankCounter = 0;
        List<Range> seedsRange = new ArrayList<>();
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
                fillSeeds(line, seedsRange);
            } else {
                fillList(line, lists, blankCounter);
            }
        }

        for (List<Range[]> l : lists) {
            List<Range> newList = new ArrayList<>();
            while (!seedsRange.isEmpty()) {
                Range r = seedsRange.remove(0);
                int overlaps = 0;
                for (Range[] rArray : l) {
                    Range overlap = r.overlap(rArray[0]);
                    if (overlap != null) {
                        overlaps++;
                        newList.add(new Range(rArray[1].start + (overlap.start - rArray[0].start), rArray[1].start + (overlap.end - rArray[0].start)));
                        if (overlap.start > r.start) {
                            seedsRange.add(new Range(r.start, overlap.start));
                        }
                        if (r.end > overlap.end) {
                            seedsRange.add(new Range(overlap.end, r.end));
                        }
                        break;
                    }
                }
                if (overlaps == 0) {
                    newList.add(r);
                }
            }
            seedsRange = newList;
        }
        long min = Long.MAX_VALUE;
        for (Range r : seedsRange) {
            if (r.start < min) {
                min = r.start;
            }
        }
        System.out.println(min);
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

    private static void fillSeeds(String line, List<Range> ranges) {
        String[] seedsAndRange = line.split(": ")[1].trim().split(" ");
        for (int i = 1; i < seedsAndRange.length; i+=2) {
            long starting = Long.parseLong(seedsAndRange[i - 1]);
            long range = Long.parseLong(seedsAndRange[i]);
            ranges.add(new Range(starting, starting + range - 1));
        }
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
