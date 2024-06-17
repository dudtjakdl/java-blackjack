package blackjack.domain;

public class Card {
    private final CardProperty cardProperty;
    private final CardShape cardShape;

    public CardProperty getCardProperty() {
        return cardProperty;
    }

    public CardShape getCardShape() {
        return cardShape;
    }

    protected Card(CardProperty cardProperty, CardShape cardShape) {
        this.cardProperty = cardProperty;
        this.cardShape = cardShape;
    }

    public static Card of(CardProperty cardProperty, CardShape cardShape) {
        return new Card(cardProperty, cardShape);
    }
}
