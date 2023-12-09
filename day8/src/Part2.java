import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Part2 {

    public static void read(Scanner file) {
        Map<String, Pair> map = new HashMap<>();
        char[] directions = file.nextLine().toCharArray();
        file.nextLine();
        while (file.hasNext()) {
            String line = file.nextLine();
            String s = line.split(" = ")[0].trim();
            String[] node = line.split(" = ")[1].split(", ");
            String left = node[0].replace("(", "").trim();
            String right = node[1].replace(")", "").trim();
            Pair pair = new Pair(left, right);
            map.put(s, pair);
        }
        List<String> instructions = new ArrayList<>(map.keySet().stream().filter(s -> s.endsWith("A")).toList());
        List<Integer> cycles = new ArrayList<>();
        for (String instruction : instructions) {
            int dirCount = 0;
            int steps = 0;
            boolean looped = false;
            String firstZ = "";
            while (!looped) {
                while (!instruction.endsWith("Z") || steps == 0) {
                    char direction = directions[dirCount];
                    steps++;
                    Pair node = map.get(instruction);
                    instruction = (direction == 'R') ? node.right() : node.left();
                    dirCount = (dirCount + 1) % directions.length;
                }
                if (firstZ.isEmpty()) {
                    firstZ = instruction;
                    steps = 0;
                }
                else if (firstZ.equals(instruction)) {
                    looped = true;
                    cycles.add(steps);
                }
            }
        }
        long lcm = lcm(cycles.stream().mapToLong(Long::valueOf).toArray(), 0);
        System.out.println(lcm);
    }

    public static void main(String[] args) {
        try {
            Scanner test = new Scanner(new FileReader("day8" + File.separator + "tests" + File.separator + "part2test.txt"));
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

    private static long gcd(long a, long b)
    {
        return b == 0? a:gcd(b, a % b);
    }

    // recursive implementation
    private static long lcm(long[] arr, int idx)
    {
        if (idx == arr.length - 1){
            return arr[idx];
        }
        long a = arr[idx];
        long b = lcm(arr, idx+1);
        return (a*b/gcd(a,b)); //
    }

    public record Pair(String left, String right){}
}
