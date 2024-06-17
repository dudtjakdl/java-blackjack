package blackjack.domain;

import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class BlackJackGame {
    private Users participants;
    private CardDeck cardDeck;

    public Users getParticipants() {
        return participants;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public BlackJackGame(String... userNames) {
        this.participants = new Users(userNames);
        this.cardDeck = CardDeck.create();
    }

    public void serve(Predicate<User> predicate) {
        try {
            for (User user : participants.getUsers()) {
                if (predicate.test(user)) {
                    user.receiveCard(cardDeck.getCards().removeLast());
                }
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("deck is empty");
        }

    }
}
