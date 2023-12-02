import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Part2 {

    private static void read(Scanner file) {
        int sum = 0;
        while(file.hasNext()) {
            String line = file.nextLine();
            String[] sets = line.split(":")[1].split(";");

            int red, green, blue;
            red = green = blue = 0;

            for (String set : sets) {
                String[] cubes = set.split(",");
                for (String pair : cubes) {
                    String[] item = pair.trim().split(" ");
                    int num = Integer.parseInt(item[0]);
                    Part1.Color color = Part1.Color.valueOf(item[1].trim().toUpperCase());
                    switch (color) {
                        case RED -> {
                            if (num > red)
                                red = num;
                        }
                        case GREEN -> {
                            if (num > green)
                                green = num;
                        }
                        case BLUE -> {
                            if (num > blue)
                                blue = num;
                        }
                    }
                }
            }
            sum += red * green * blue;
        }
        System.out.println(sum);
    }
    public static void main(String[] args) {
        try {
            Scanner file1 = new Scanner(new FileReader("day2\\src\\input1.txt"));
            read(file1);
            file1.close();
            Scanner file2 = new Scanner(new FileReader("day2\\src\\input2.txt"));
            read(file2);
            file2.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}