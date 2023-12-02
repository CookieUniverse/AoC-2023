import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Part1 {

    public static char getFirstDigit(String line) {
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (Character.isDigit(ch))
                return ch;
        }
        return '.';
    }

    public static char getLastDigit(String line) {
        for (int i = line.length() - 1; i >= 0; i--) {
            char ch = line.charAt(i);
            if (Character.isDigit(ch))
                return ch;
        }
        return '.';
    }

    public static void read(Scanner file) {
        int sum = 0;
        while (file.hasNext()) {
            String number = "";
            String line = file.nextLine();
            number += getFirstDigit(line);
            number += getLastDigit(line);
            sum += Integer.parseInt(number);
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        try {
            Scanner test = new Scanner(new FileReader("day1" + File.separator + "tests" + File.separator + "part1test.txt"));
            System.out.println("Test answer:");
            read(test);
            test.close();
            Scanner puzzle = new Scanner(new FileReader("day1" + File.separator + "src" + File.separator + "input.txt"));
            System.out.println("Puzzle answer:");
            read(puzzle);
            puzzle.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
    }

}
