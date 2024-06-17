package blackjack.domain;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {
    private final Deque<Card> cards;

    public CardDeck() {
        cards = Stream.of(CardProperty.values())
                .flatMap(value -> Stream.of(CardShape.values())
                        .map(shape -> Card.of(value, shape)))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        collected -> {
                            Collections.shuffle(collected);
                            return new ArrayDeque<>(collected);
                        }
                ));
    }

    public Deque<Card> getCards() {
        return cards;
    }
}
