import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Part1 {

    private static final int MAX_RED = 12;
    private static final int MAX_GREEN = 13;
    private static final int MAX_BLUE = 14;

    public enum Color {
        RED, GREEN, BLUE
    }

    public static boolean checkIfValid(int amount, Color color) {
        boolean valid = true;
        switch (color) {
            case RED -> {
                if (amount > MAX_RED)
                    valid = false;
            }
            case GREEN -> {
                if (amount > MAX_GREEN)
                    valid = false;
            }
            case BLUE -> {
                if (amount > MAX_BLUE)
                    valid = false;
            }
        }
        return valid;
    }

    private static void read(Scanner file) {
        int sum = 0;
        while(file.hasNext()) {
            String line = file.nextLine();
            String[] slices = line.split(":");

            int id = Integer.parseInt(slices[0].split(" ")[1]);
            String[] sets = slices[1].split(";");

            int i = 0;
            boolean valid = true;
            while (i < sets.length && valid) {
                String[] cubes = sets[i].split(",");
                int j = 0;
                while(j < cubes.length && valid) {
                    String[] item = cubes[j].trim().split(" ");
                    int num = Integer.parseInt(item[0]);
                    Color color = Color.valueOf(item[1].trim().toUpperCase());
                    valid = checkIfValid(num, color);
                    j++;
                }
                i++;
            }
            if (valid) {
                sum += id;
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        try {
            Scanner file1 = new Scanner(new FileReader("day2" + File.separator + "tests" + File.separator + "test.txt"));
            read(file1);
            file1.close();
            Scanner file2 = new Scanner(new FileReader("day2" + File.separator + "src" + File.separator + "input.txt"));
            read(file2);
            file2.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
