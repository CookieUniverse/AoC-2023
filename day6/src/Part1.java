import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part1 {

    public static void read(Scanner file) {
        Integer[] times = getTimes(file);
        Integer[] distances = getDistances(file);
        List<Integer> possibleWins = new ArrayList<>();

        for (int i = 0; i < times.length; i++) {
            int wins = 0;
            int time = times[i];
            int distanceToBeat = distances[i];
            int velocity = 0;
            for (int j = time; j >= 0; j--) {
                if (j * velocity > distanceToBeat) {
                    wins++;
                }
                velocity++;
            }
            if (wins != 0) {
                possibleWins.add(wins);
            }
        }
        System.out.println(possibleWins.stream().reduce(1, (a, b) -> a * b));
    }

    private static Integer[] getDistances(Scanner file) {
        String[] nums = file.nextLine().split(": ")[1].trim().replaceAll("\\s+", " ").split(" ");
        return Arrays.stream(nums).map(Integer::parseInt).toArray(Integer[]::new);
    }

    private static Integer[] getTimes(Scanner file) {
        String[] nums = file.nextLine().split(": ")[1].trim().replaceAll("\\s+", " ").split(" ");
        return Arrays.stream(nums).map(Integer::parseInt).toArray(Integer[]::new);
    }

    public static void main(String[] args) {
        try {
            Scanner test = new Scanner(new FileReader("day6" + File.separator + "tests" + File.separator + "test.txt"));
            System.out.println("Test answer:");
            read(test);
            test.close();
            Scanner puzzle = new Scanner(new FileReader("day6" + File.separator + "src" + File.separator + "input.txt"));
            System.out.println("Puzzle answer:");
            read(puzzle);
            puzzle.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
