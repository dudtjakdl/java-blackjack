package blackjack.domain;

import java.util.ArrayDeque;

public class Dealer extends User{
    protected Dealer() {
        super.cardDeck = new ArrayDeque<>();
        super.isAlive = true;
    }

    public static Dealer create() {
        return new Dealer();
    }
}
