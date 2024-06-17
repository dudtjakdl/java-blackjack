package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    @DisplayName("사용자 이름을 받아서 플레이어를 생성한다.")
    void test1() {
        Player user = Player.from("pobi");
        assertThat(user.getName()).isEqualTo("pobi");
    }
}