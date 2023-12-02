import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Part2 {

    public enum Number {
        ONE('1'), TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'), EIGHT('8'), NINE('9'),
        UNKNOWN('!');

        private final char value;
        Number(char value) {
            this.value = value;
        }

        public char getChar() {
            return value;
        }
    }

    public static String buildStringFromLine(String line, int startingInt, int endInt) {
        StringBuilder word = new StringBuilder();
        for (int i = startingInt; i <= endInt; i++) {
            word.append(line.charAt(i));
        }
        return word.toString();
    }

    public static char getNumberAsChar(String word) {
        try {
            Number num = Number.valueOf(word.toUpperCase());
            return num.getChar();
        } catch (IllegalArgumentException ignored) {
            return Number.UNKNOWN.getChar();
        }
    }

    public static char getFirstDigit(String line) {
        int length = line.length();
        for (int i = 0; i < length; i++) {
            char ch = line.charAt(i);
            if (Character.isDigit(ch))
                return ch;
            for (int j = i + 4; j >= i + 2; j--) {
                if (j < length) {
                    ch = getCharFromLine(line, i, j);
                    if (ch != '!')
                        return ch;
                }
            }
        }
        return '.';
    }

    public static char getLastDigit(String line) {
        int length = line.length();
        for (int i = length - 1; i >= 0; i--) {
            char ch = line.charAt(i);
            if (Character.isDigit(ch))
                return ch;
            for (int j = i - 4; j <= i - 2; j++) {
                if (j > 0) {
                    ch = getCharFromLine(line, j, i);
                    if (ch != '!')
                        return ch;
                }
            }
        }
        return '.';
    }

    public static char getCharFromLine(String line, int starting, int ending) {
        return getNumberAsChar(buildStringFromLine(line, starting, ending));
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
            Scanner file1 = new Scanner(new FileReader("day1\\tests\\part2test.txt"));
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
