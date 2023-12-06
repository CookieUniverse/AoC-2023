import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Part1 {

    public static void read(Scanner file) {
        Iterator<Integer> times = getTimes(file).iterator();
        Iterator<Integer> distances = getDistances(file).iterator();
        List<Integer> possibleWins = new ArrayList<>();

        while (times.hasNext() && distances.hasNext()) {
            int time = times.next();
            int distance = distances.next();
            int beaten = 0;
            for (int i = time; i >= 0; i--) {
                if (i * (time - i) > distance) {
                    beaten++;
                }
            }
            if (beaten != 0)
                possibleWins.add(beaten);
        }
        System.out.println(possibleWins.stream().reduce(1, (a, b) -> a * b));
    }

    private static List<Integer> getDistances(Scanner file) {
        String[] nums = file.nextLine().split(": ")[1].trim().replaceAll("\\s+", " ").split(" ");
        return Arrays.stream(nums).map(Integer::parseInt).toList();
    }

    private static List<Integer> getTimes(Scanner file) {
        String[] nums = file.nextLine().split(": ")[1].trim().replaceAll("\\s+", " ").split(" ");
        return Arrays.stream(nums).map(Integer::parseInt).toList();
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
