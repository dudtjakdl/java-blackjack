package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Users {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public Users(String[] userNames) {
        users = new ArrayList<>();
        users.add(Dealer.create());
        Arrays.stream(userNames)
                .forEach(userName -> users.add(Player.from(userName)));
    }
}
