import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    private static final Map<Integer, String> intMap = Stream.of(new Object[][] {
            { 1, "ONE" },
            { 2, "TWO" },
            { 3, "THREE" },
            { 4, "FOUR" },
            { 5, "FIVE" },
            { 6, "SIX" },
            { 7, "SEVEN" },
            { 8, "EIGHT" },
            { 9, "NINE" },
    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));

    public static void read(Scanner file) {

        Set<Deck1> decks = new TreeSet<>();
        int winnings = 0;
        while (file.hasNext()) {
            String[] deckAndBid = file.nextLine().split(" ");
            int bid = Integer.parseInt(deckAndBid[1]);
            Deck1 deck = createDeck(deckAndBid[0], bid);
            decks.add(deck);
        }
        int count = 1;
        for (Deck1 deck : decks) {
            winnings += deck.getBid() * count++;
        }
        System.out.println(winnings);
    }

    private static Deck1 createDeck(String s, int bid) {
        int[] num = new int[13];
        Card1[] cards = new Card1[5];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!Character.isDigit(ch)) {
                cards[i] = Card1.valueOf(Character.toString(ch).toUpperCase());
            }
            else {
                cards[i] = Card1.valueOf(intMap.get(Integer.parseInt(Character.toString(ch))));
            }
            num[cards[i].ordinal()]++;
        }
        DeckType type = getType(num);
        return new Deck1(cards, type, bid);
    }

    private static DeckType getType(int[] num) {
        int nOnes = 0;
        int nTwos = 0;
        int nThree = 0;
        for (int i : num) {
            switch (i) {
                case 5 -> {
                    return DeckType.FIVE_OF_A_KIND;
                }
                case 4 -> {
                    return DeckType.FOUR_OF_A_KIND;
                }
                case 3 -> nThree++;
                case 2 -> nTwos++;
                case 1 -> nOnes++;
            }
        }
        if (nThree == 1) {
            if (nTwos == 1) {
                return DeckType.FULL_HOUSE;
            }
            return DeckType.THREE_OF_A_KIND;
        }
        if (nTwos == 2) {
            return DeckType.TWO_PAIR;
        }
        if (nTwos == 1 && nOnes == 3) {
            return DeckType.ONE_PAIR;
        }
        return DeckType.HIGH_CARD;
    }

    public static void main(String[] args) {
        try {
            Scanner test = new Scanner(new FileReader("day7" + File.separator + "tests" + File.separator + "test.txt"));
            System.out.println("Test answer:");
            read(test);
            test.close();
            Scanner puzzle = new Scanner(new FileReader("day7" + File.separator + "src" + File.separator + "input.txt"));
            System.out.println("Puzzle answer:");
            read(puzzle);
            puzzle.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
