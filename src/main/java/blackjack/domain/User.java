package blackjack.domain;

import java.util.Deque;

public abstract class User {
    protected Deque<Card> cardDeck;
    protected boolean isAlive;

    void receiveCard(Card card) {
        cardDeck.push(card);
    }

    public Deque<Card> getCardDeck() {
        return cardDeck;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
