package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BlackJackGameTest {
    @Test
    @DisplayName("게임을 시작할때 모든 참가자들을 생성한다.")
    void test1() {
        BlackJackGame blackJackGame = new BlackJackGame("pobi", "jason");
        assertThat(blackJackGame.getParticipants().getUsers()).hasSize(3);
    }

    @Test
    @DisplayName("유저에게 카드를 1장 나눠준다.")
    void test2() {
        User player = Player.from("pobi");
        player.receiveCard(Card.of(CardProperty.JACK, CardShape.CLOVER));
        assertThat(player.getCardDeck()).hasSize(1);
    }

    @Test
    @DisplayName("탈락하지 않은 모든 유저에게 카드를 1장 나눠준다.")
    void test3() {
        BlackJackGame blackJackGame = new BlackJackGame("pobi", "jason");
        blackJackGame.serve((user) -> user.isAlive);
        var users = blackJackGame.getParticipants().getUsers();
        assertAll(
                () -> assertThat(users.get(0).getCardDeck()).hasSize(1),
                () -> assertThat(users.get(1).getCardDeck()).hasSize(1),
                () -> assertThat(users.get(2).getCardDeck()).hasSize(1)
        );
    }
}