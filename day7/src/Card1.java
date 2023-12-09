public enum Card1 {
    A("A"),
    K("K"),
    Q("Q"),
    J("J"),
    T("T"),
    NINE("9"),
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2");

    private final String value;

    Card1(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
