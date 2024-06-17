package blackjack.domain;

import java.util.ArrayDeque;

public class Player extends User{
    public static final int USER_MAX_SCORE = 21;
    private final String name;
    protected boolean isAlive;

    protected Player(String name) {
        this.name = name;
        super.cardDeck = new ArrayDeque<>();
        this.isAlive = true;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public static Player from(String name) {
        return new Player(name);
    }

    @Override
    public boolean judge() {
        return isAlive = getCardDeckScore() < USER_MAX_SCORE;
    }
}
