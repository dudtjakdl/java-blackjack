package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Test
    @DisplayName("카드를 한장 생성한다.")
    void test1() {
        Card card = Card.of(CardProperty.JACK, CardShape.CLOVER);
        assertAll(
                () -> assertThat(card.getCardProperty()).isEqualTo(CardProperty.JACK),
                () -> assertThat(card.getCardShape()).isEqualTo(CardShape.CLOVER)
        );
    }

    @Test
    @DisplayName("카드 묶음 덱을 생성한다.")
    void test2() {
        CardDeck cardDeck = CardDeck.create();
        assertThat(cardDeck.getCards().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("카드 묶음 덱을 셔플한다.")
    void test3() {
        CardDeck cardDeck = CardDeck.create();
        assertThat(cardDeck.getCards().size()).isEqualTo(52);
    }
}