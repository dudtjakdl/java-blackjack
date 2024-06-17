package blackjack.domain;

public enum CardShape {
    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String shape;

    CardShape(String shape) {
        this.shape = shape;
    }
}
