import java.util.Arrays;

public class Deck1 implements Comparable<Deck1> {

    private final Card1[] cards;
    private final DeckType type;
    private final int bid;

    public Deck1(Card1[] cards, DeckType type, int bid) {
        this.cards = cards;
        this.type = type;
        this.bid = bid;
    }

    private DeckType getType() {
        return type;
    }

    private Card1[] getCards() {
        return cards;
    }

    public int getBid() {
        return bid;
    }

    @Override
    public int compareTo(Deck1 deck) {
        int res = deck.getType().ordinal() - type.ordinal();
        if (res != 0) {
            return res;
        }
        return compareElements(deck);
    }

    private int compareElements(Deck1 deck) {
        Card1[] otherCards = deck.getCards();
        int dif = 0;
        for (int i = 0; i < cards.length; i++) {
            int cmp = otherCards[i].compareTo(cards[i]);
            if (cmp != 0)
                return cmp;
        }
        return dif;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + Arrays.toString(cards) +
                ", type=" + type +
                ", bid=" + bid +
                '}';
    }
}
