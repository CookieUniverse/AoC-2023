import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Part1 {

    private static void read(Scanner file) {
        int sum = 0;
        while (file.hasNext()) {
            int value = 0;
            String line = file.nextLine();
            String[] sets = line.split(":")[1].split("\\|");
            String[] winningNumbers = sets[0].trim().split(" ");
            String[] myNumbers = sets[1].trim().split(" ");
            Set<Integer> winningNumbersSet = new HashSet<>();
            for (String n : winningNumbers) {
                if (!n.isEmpty())
                    winningNumbersSet.add(Integer.parseInt(n));
            }
            for (String n : myNumbers) {
                if (!n.isEmpty() && winningNumbersSet.contains(Integer.parseInt(n)))
                    value = (value == 0) ? 1 : value * 2;
            }
            sum += value;
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        try {
            Scanner test = new Scanner(new FileReader("day4" + File.separator + "tests" + File.separator + "test.txt"));
            System.out.println("Test answer:");
            read(test);
            test.close();
            Scanner puzzle = new Scanner(new FileReader("day4" + File.separator + "src" + File.separator + "input.txt"));
            System.out.println("Puzzle answer:");
            read(puzzle);
            puzzle.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
