import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Part2 {

    private static int rows;
    private static int columns;

    private static void read(String path) {
        int lines;
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(path));
            lineNumberReader.skip(Long.MAX_VALUE);
            lines = lineNumberReader.getLineNumber();
            lineNumberReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        rows = lines;
        columns = lines;

        Scanner file;
        try {
            file = new Scanner(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        char[][] matrix = new char[rows][columns];
        int i = 0;
        while (file.hasNext()) {
            String line = file.nextLine();
            for (int j = 0; j < line.length(); j++) {
                char ch = line.charAt(j);
                matrix[i][j] = ch;
            }
            i++;
        }

        int sum = 0;
        for (int k = 0; k < rows; k++) {
            for (int v = 0; v < columns; v++) {
                char ch = matrix[k][v];
                if (ch == '*') {
                    sum += checkSymbolNeighbors(matrix, k, v);
                }
            }
        }
        System.out.println(sum);
        file.close();
    }

    private static int checkSymbolNeighbors(char[][] matrix, int startingX, int startingY) {
        Set<Integer> set = new HashSet<>();
        for (int i = startingX - 1; i < startingX + 2; i++) {
            for (int j = startingY - 1; j < startingY + 2; j++) {
                if (Character.isDigit(matrix[i][j])) {
                    int num = createNumber(matrix, i, j);
                    set.add(num);
                }
            }
        }

        if (set.size() != 2) {
            return 0;
        }

        Integer[] array = set.toArray(Integer[]::new);
        return array[0] * array[1];
    }

    private static int createNumber(char[][] matrix, int i, int j) {
        if (isInBounds(i, j + 1)) {
            if (!Character.isDigit(matrix[i][j + 1])) {
                return Integer.parseInt(buildNumberFromEnd(matrix,i,j));
            }
        }
        if (isInBounds(i, j - 1)) {
            if (!Character.isDigit(matrix[i][j - 1])) {
                return Integer.parseInt(buildNumberFromStart(matrix,i,j));
            }
        }
        int currentY = j;
        while (isInBounds(i, currentY)) {
            if (!Character.isDigit(matrix[i][currentY])) {
                break;
            }
            currentY--;
        }
        return Integer.parseInt(buildNumberFromStart(matrix,i,currentY + 1));
    }

    private static String buildNumberFromStart(char[][] matrix, int i, int j) {
        StringBuilder num = new StringBuilder();
        int currentY = j;
        while (isInBounds(i, currentY)) {
            if (!Character.isDigit(matrix[i][currentY])) {
                break;
            }
            num.append(matrix[i][currentY++]);
        }
        return num.toString();
    }
    private static String buildNumberFromEnd(char[][] matrix, int i, int j) {
        StringBuilder num = new StringBuilder();
        int currentY = j;
        while (isInBounds(i, currentY)) {
            if (!Character.isDigit(matrix[i][currentY])) {
                break;
            }
            num.append(matrix[i][currentY--]);
        }
        return num.reverse().toString();
    }

    private static boolean isInBounds(int x, int y) {
        return x >= 0 && x <= rows - 1 && y >= 0 && y <= columns - 1;
    }

    public static void main(String[] args) {
        String testPath = "day3" + File.separator + "tests" + File.separator + "test.txt";
        System.out.println("Test answer:");
        read(testPath);
        String inputPath = "day3" + File.separator + "src" + File.separator + "input.txt";
        System.out.println("Puzzle answer:");
        read(inputPath);
    }

}
