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
            Scanner file1 = new Scanner(new FileReader("day1\\tests\\part1test.txt"));
            read(file1);
            file1.close();
            Scanner file2 = new Scanner(new FileReader("day1\\src\\input.txt"));
            read(file2);
            file2.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
    }

}
