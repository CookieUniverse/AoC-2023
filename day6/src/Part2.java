import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part2 {

    public static void read(Scanner file) {
        long time = getTimes(file);
        long distance = getDistances(file);
        // distance = x(time - x) -> -(x^2) + x * time - distance = 0
        // subtracting the roots of this equation will give us the number of ways to beat the distance
        System.out.println(quadraticFormula(-1, time, Math.negateExact(distance)));
    }

    // Will always have 2 roots in this context
    private static long quadraticFormula(long a, long b, long c) {
        double[] roots = new double[2];
        double insideRoot = b * b - 4 * a * c;
        roots[0] = (-b + Math.sqrt(insideRoot)) / (2 * a);
        roots[1] = (-b - Math.sqrt(insideRoot)) / (2 * a);
        return (long) Math.abs(Math.abs(roots[1]) - Math.abs(roots[0]));
    }

    private static long getDistances(Scanner file) {
        String num = file.nextLine().split(": ")[1].trim().replaceAll("\\s+", "");
        return Long.parseLong(num);
    }

    private static long getTimes(Scanner file) {
        String num = file.nextLine().split(": ")[1].trim().replaceAll("\\s+", "");
        return Long.parseLong(num);
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
