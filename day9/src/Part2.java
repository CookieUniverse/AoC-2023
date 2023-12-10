import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part2 {
    private static void read(Scanner file) {
        int sum = 0;
        while (file.hasNext()) {
            String[] numbers = file.nextLine().split(" ");
            List<Integer> firstNumbers = new ArrayList<>();
            List<Integer> sequence = Arrays.stream(numbers).map(Integer::parseInt).toList();
            while (!sequence.isEmpty() && !sequence.stream().allMatch(i -> i == 0)) {
                List<Integer> newSeq = new ArrayList<>();
                for (int i = sequence.size() - 1; i > 0; i--) {
                    newSeq.add(0, sequence.get(i) - sequence.get(i - 1));
                }
                firstNumbers.add(0, newSeq.get(0));
                sequence = newSeq;
            }
            firstNumbers.add(Integer.parseInt(numbers[0]));
            int first = 0;
            for (Integer n : firstNumbers) {
                first = n - first;
            }
            sum += first;
        }
        System.out.println(sum);
    }
    public static void main(String[] args) {
        try {
            Scanner test = new Scanner(new FileReader("day9" + File.separator + "tests" + File.separator + "test.txt"));
            System.out.println("Test answer:");
            read(test);
            test.close();
            Scanner puzzle = new Scanner(new FileReader("day9" + File.separator + "src" + File.separator + "input.txt"));
            System.out.println("Puzzle answer:");
            read(puzzle);
            puzzle.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
