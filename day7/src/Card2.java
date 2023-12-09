public enum Card2 {
    A("A"),
    K("K"),
    Q("Q"),
    T("T"),
    NINE("9"),
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),

    J("J");

    private final String value;

    Card2(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
