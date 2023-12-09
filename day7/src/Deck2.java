import java.util.Arrays;

public class Deck2 implements Comparable<Deck2> {

    private final Card2[] cards;
    private final DeckType type;
    private final int bid;

    public Deck2(Card2[] cards, DeckType type, int bid) {
        this.cards = cards;
        this.type = type;
        this.bid = bid;
    }

    private DeckType getType() {
        return type;
    }

    private Card2[] getCards() {
        return cards;
    }

    public int getBid() {
        return bid;
    }

    @Override
    public int compareTo(Deck2 deck) {
        int res = deck.getType().ordinal() - type.ordinal();
        if (res != 0) {
            return res;
        }
        return compareElements(deck);
    }

    private int compareElements(Deck2 deck) {
        Card2[] otherCards = deck.getCards();
        for (int i = 0; i < cards.length; i++) {
            int cmp = otherCards[i].compareTo(cards[i]);
            if (cmp != 0)
                return cmp;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + Arrays.toString(cards) +
                ", type=" + type +
                ", bid=" + bid +
                '}' +
                "\n";
    }
}
