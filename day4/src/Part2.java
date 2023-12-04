import java.io.*;
import java.util.*;

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
            String[] sets = line.split(":")[1].split("\\|");
            String[] winningNumbers = sets[0].trim().split(" ");
            String[] myNumbers = sets[1].trim().split(" ");
            Set<Integer> winningNumbersSet = new HashSet<>();
            for (String n : winningNumbers) {
                if (!n.isEmpty())
                    winningNumbersSet.add(Integer.parseInt(n));
            }
            int matching = 0;
            for (String n : myNumbers) {
                if (!n.isEmpty() && winningNumbersSet.contains(Integer.parseInt(n)))
                    matching++;
            }
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
