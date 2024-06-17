package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BlackJackGameTest {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int STRING_LENGTH = 10; // 임의의 문자열 길이

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
        blackJackGame.serve((user) -> true);
        var users = blackJackGame.getParticipants().getUsers();
        assertAll(
                () -> assertThat(users.get(0).getCardDeck()).hasSize(1),
                () -> assertThat(users.get(1).getCardDeck()).hasSize(1),
                () -> assertThat(users.get(2).getCardDeck()).hasSize(1)
        );
    }

    @Test
    @DisplayName("Dealer는 현재 카드 점수가 16 이하면 카드를 무조건 받는다.")
    void test4() {
        BlackJackGame blackJackGame = new BlackJackGame("pobi", "jason");
        var dealer = blackJackGame.getParticipants().getUsers().get(0);
        dealer.receiveCard(Card.of(CardProperty.JACK, CardShape.CLOVER));
        dealer.receiveCard(Card.of(CardProperty.TWO, CardShape.CLOVER));
        blackJackGame.serve(User::judge);
        assertThat(dealer.getCardDeck()).hasSize(3);
    }

    @Test
    @DisplayName("Dealer는 현재 카드 점수가 17 이상이면 카드를 받지 않는다.")
    void test5() {
        BlackJackGame blackJackGame = new BlackJackGame("pobi", "jason");
        var dealer = blackJackGame.getParticipants().getUsers().get(0);
        dealer.receiveCard(Card.of(CardProperty.JACK, CardShape.CLOVER));
        dealer.receiveCard(Card.of(CardProperty.KING, CardShape.CLOVER));
        blackJackGame.serve(User::judge);
        assertThat(dealer.getCardDeck()).hasSize(2);
    }

    @Test
    @DisplayName("Player는 현재 카드 점수가 21이 넘으면 카드를 뽑을 수 없다.")
    void test6() {
        BlackJackGame blackJackGame = new BlackJackGame("pobi", "jason");
        var player = blackJackGame.getParticipants().getUsers().get(2);
        player.receiveCard(Card.of(CardProperty.JACK, CardShape.CLOVER));
        player.receiveCard(Card.of(CardProperty.KING, CardShape.CLOVER));
        player.receiveCard(Card.of(CardProperty.QUEEN, CardShape.CLOVER));
        assertThat(player.judge()).isFalse();
    }

    @Test
    @DisplayName("Player는 현재 카드 점수가 21이 넘지 않으면 카드를 받을 수 있다.")
    void test7() {
        BlackJackGame blackJackGame = new BlackJackGame("pobi", "jason");
        var player = blackJackGame.getParticipants().getUsers().get(2);
        player.receiveCard(Card.of(CardProperty.JACK, CardShape.CLOVER));
        player.receiveCard(Card.of(CardProperty.KING, CardShape.CLOVER));
        assertThat(player.judge()).isTrue();
    }

    @Test
    @DisplayName("Player는 현재 카드 점수가 21이 넘으면 탈락한다.")
    void test8() {
        BlackJackGame blackJackGame = new BlackJackGame("pobi", "jason");
        var player = (Player) blackJackGame.getParticipants().getUsers().get(2);
        player.receiveCard(Card.of(CardProperty.JACK, CardShape.CLOVER));
        player.receiveCard(Card.of(CardProperty.KING, CardShape.CLOVER));
        player.receiveCard(Card.of(CardProperty.QUEEN, CardShape.CLOVER));
        player.judge();
        assertThat(player.isAlive()).isFalse();
    }

    @Test
    @DisplayName("카드를 받는 경우이면 카드를 덱에서 한장 뽑는다.")
    void test9() {
        BlackJackGame blackJackGame = new BlackJackGame("pobi", "jason");
        var dealer = blackJackGame.getParticipants().getUsers().get(0);
        blackJackGame.serve(User::judge);
        assertAll(
                () -> assertThat(dealer.getCardDeck()).hasSize(1),
                () -> assertThat(blackJackGame.getCardDeck().getCards()).hasSize(CardDeck.INITIAL_CARDS_COUNT-3)
        );
    }

    @Test
    @DisplayName("카드 덱이 비어있을 때 서브하는 경우 예외를 발생시킨다.")
    void test10() {
        BlackJackGame blackJackGame = new BlackJackGame(generateRandomStrings(100).toArray(String[]::new));
        assertThatThrownBy(() -> {
            blackJackGame.serve(User::judge);
        })
                .isInstanceOf(NoSuchElementException.class);
    }

    private static Stream<String> generateRandomStrings(int count) {
        Random random = new Random();
        return Stream.generate(() -> random.ints(STRING_LENGTH, 0, CHARACTERS.length())
                        .mapToObj(CHARACTERS::charAt)
                        .map(Object::toString)
                        .collect(Collectors.joining()))
                .limit(count);
    }
}