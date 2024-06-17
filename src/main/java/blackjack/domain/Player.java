package blackjack.domain;

import java.util.ArrayDeque;

public class Player extends User{
    private final String name;

    protected Player(String name) {
        this.name = name;
        super.cardDeck = new ArrayDeque<>();
        super.isAlive = true;
    }

    public String getName() {
        return name;
    }

    public static Player from(String name) {
        return new Player(name);
    }
}
