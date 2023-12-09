import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {
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

        Set<Deck2> decks = new TreeSet<>();
        int winnings = 0;
        while (file.hasNext()) {
            String[] deckAndBid = file.nextLine().split(" ");
            int bid = Integer.parseInt(deckAndBid[1]);
            Deck2 deck = createDeck(deckAndBid[0], bid);
            decks.add(deck);
        }
        int count = 1;
        for (Deck2 deck : decks) {
            winnings += deck.getBid() * count++;
        }
        System.out.println(winnings);
    }

    private static Deck2 createDeck(String s, int bid) {
        int[] num = new int[13];
        Card2[] cards = new Card2[5];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!Character.isDigit(ch)) {
                cards[i] = Card2.valueOf(Character.toString(ch).toUpperCase());
            }
            else {
                cards[i] = Card2.valueOf(intMap.get(Integer.parseInt(Character.toString(ch))));
            }
            num[cards[i].ordinal()]++;
        }
        DeckType type = getType(num);
        return new Deck2(cards, type, bid);
    }

    private static DeckType getType(int[] num) {
        // counting[0] -> number of solo cards
        // counting[1] -> number of pairs
        // counting[2] -> number of triples
        // counting[3] -> number of fours
        // counting[4] -> number of jokers
        int[] counting = new int[5];
        for (int i = 0; i < num.length - 1; i++) {
            switch (num[i]) {
                case 5 -> {
                    return DeckType.FIVE_OF_A_KIND;
                }
                case 4, 3, 2, 1 -> counting[num[i] - 1]++;
            }
        }
        switch (num[num.length - 1]) {
            case 5, 4 -> {
                return DeckType.FIVE_OF_A_KIND;
            }
            default -> counting[4] = num[num.length - 1];
        }
        if (counting[4] != 0) {
            switch (counting[4]) {
                case 3 -> {
                    if (counting[1] == 1)
                        return DeckType.FIVE_OF_A_KIND;
                    return DeckType.FOUR_OF_A_KIND;
                }
                case 2 -> {
                    if (counting[2] == 1)
                        return DeckType.FIVE_OF_A_KIND;
                    if (counting[1] == 1)
                        return DeckType.FOUR_OF_A_KIND;
                    return DeckType.THREE_OF_A_KIND;
                }
                case 1 -> {
                    if (counting[3] == 1)
                        return DeckType.FIVE_OF_A_KIND;
                    if (counting[2] == 1)
                        return DeckType.FOUR_OF_A_KIND;
                    if (counting[1] == 2)
                        return DeckType.FULL_HOUSE;
                    if (counting[1] == 1)
                        return DeckType.THREE_OF_A_KIND;
                    return DeckType.ONE_PAIR;
                }
            }
        }
        if (counting[3] == 1)
            return DeckType.FOUR_OF_A_KIND;
        if (counting[2] == 1) {
            if (counting[1] == 1) {
                return DeckType.FULL_HOUSE;
            }
            return DeckType.THREE_OF_A_KIND;
        }
        if (counting[1] == 2) {
            return DeckType.TWO_PAIR;
        }
        if (counting[1] == 1 && counting[0] == 3) {
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
