package blackjack.domain;

import java.util.ArrayDeque;

public class Dealer extends User{

    public static final int MAX_DEALER_SCORE = 16;

    protected Dealer() {
        super.cardDeck = new ArrayDeque<>();
    }

    public static Dealer create() {
        return new Dealer();
    }

    @Override
    public boolean judge() {
        return getCardDeckScore() <= MAX_DEALER_SCORE;
    }
}
