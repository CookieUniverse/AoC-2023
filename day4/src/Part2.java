import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Part2 {

    private static void read(String path) {
        int lines;
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(path));
            lineNumberReader.skip(Long.MAX_VALUE);
            lines = lineNumberReader.getLineNumber();
            lineNumberReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner file;
        try {
            file = new Scanner(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int[] nCopies = new int[lines];
        int[] matches = new int[lines];
        int sum = lines;
        while (file.hasNext()) {
            String line = file.nextLine();
            int id = Integer.parseInt(line.split(":")[0].replaceAll("\\D", ""));
            String[] sets = line.split(":")[1].split(" \\| ");
            String[] winningNumbers = sets[0].trim().replaceAll("\\s+", " ").split(" ");
            String[] myNumbers = sets[1].trim().replaceAll("\\s+", " ").split(" ");
            Set<Integer> winningNumbersSet = Arrays.stream(winningNumbers)
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
            int matching = (int) Arrays.stream(myNumbers)
                    .map(Integer::parseInt)
                    .filter(winningNumbersSet::contains)
                    .count();
            for (int i = id; i < id + matching; i++) {
                nCopies[i]++;
                matches[id - 1]++;
            }
        }

        for (int i = 1; i < lines; i++) {
            if (nCopies[i] != 0) {
                for (int j = 1; j <= matches[i]; j++) {
                    if (i + j < lines)
                        nCopies[i+j] += nCopies[i];
                }
            }
        }
        sum += Arrays.stream(nCopies).sum();
        System.out.println(sum);
    }

    public static void main(String[] args) {
        String testPath = "day4" + File.separator + "tests" + File.separator + "test.txt";
        System.out.println("Test answer:");
        read(testPath);
        String inputPath = "day4" + File.separator + "src" + File.separator + "input.txt";
        System.out.println("Puzzle answer:");
        read(inputPath);
    }
}
