import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    private static void read(Scanner file) {
        int sum = 0;
        while (file.hasNext()) {
            String[] numbers = file.nextLine().split(" ");
            List<Integer> lastNumbers = new ArrayList<>();
            List<Integer> sequence = Arrays.stream(numbers).map(Integer::parseInt).toList();
            while (!sequence.isEmpty() && !sequence.stream().allMatch(i -> i == 0)) {
                List<Integer> newSeq = new ArrayList<>();
                for (int i = 1; i < sequence.size(); i++) {
                    newSeq.add(sequence.get(i) - sequence.get(i - 1));
                }
                lastNumbers.add(0, newSeq.get(newSeq.size() - 1));
                sequence = newSeq;
            }
            lastNumbers.add(Integer.parseInt(numbers[numbers.length - 1]));
            int last = 0;
            for (Integer n : lastNumbers) {
                last = n + last;
            }
            sum += last;
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
