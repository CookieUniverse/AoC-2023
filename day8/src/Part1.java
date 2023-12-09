import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Part1 {

    public static void read(Scanner file) {
        Map<String, Pair> map = new HashMap<>();
        char[] directions = file.nextLine().toCharArray();
        file.nextLine();
        String instruction = "AAA";
        while (file.hasNext()) {
            String line = file.nextLine();
            String s = line.split(" = ")[0].trim();
            String[] node = line.split(" = ")[1].split(", ");
            String left = node[0].replace("(", "").trim();
            String right = node[1].replace(")", "").trim();
            Pair pair = new Pair(left, right);
            map.put(s, pair);
        }
        int steps = 0;
        int dirCount = 0;
        while (!instruction.equals("ZZZ")) {
            char direction = directions[dirCount];
            Pair node = map.get(instruction);
            instruction = (direction == 'R') ? node.right() : node.left();
            dirCount = (dirCount + 1) % directions.length;
            steps++;
        }
        System.out.println(steps);
    }

    public static void main(String[] args) {
        try {
            Scanner test = new Scanner(new FileReader("day8" + File.separator + "tests" + File.separator + "part1test.txt"));
            System.out.println("Test answer:");
            read(test);
            test.close();
            Scanner puzzle = new Scanner(new FileReader("day8" + File.separator + "src" + File.separator + "input.txt"));
            System.out.println("Puzzle answer:");
            read(puzzle);
            puzzle.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public record Pair(String left, String right){}
}
