package blackjack.domain;

import java.util.Deque;

public abstract class User {
    protected Deque<Card> cardDeck;

    public void receiveCard(Card card) {
        cardDeck.push(card);
    }

    public int getCardDeckScore() {
        return cardDeck.stream()
                .mapToInt(card -> card.getCardProperty().getScore())
                .sum();
    }

    public Deque<Card> getCardDeck() {
        return cardDeck;
    }

    public abstract boolean judge();
}
