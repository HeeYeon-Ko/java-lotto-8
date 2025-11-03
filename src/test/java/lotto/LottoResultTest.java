package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoResultTest {

    @DisplayName("일치 개수 계산이 정확해야 한다")
    @Test
    void countMatch_검증() {
        Lotto ticket = new Lotto(List.of(1, 2, 3, 10, 20, 30));
        List<Integer> win = List.of(1, 2, 3, 4, 5, 6);
        int match = LottoResult.countMatch(ticket, win);
        assertThat(match).isEqualTo(3);
    }

    @DisplayName("등수 판정: 6개 일치 → 1등")
    @Test
    void rankOf_1등() {
        assertThat(LottoResult.rankOf(6, false)).isEqualTo(Rank.FIRST);
    }

    @DisplayName("등수 판정: 5개 + 보너스 → 2등")
    @Test
    void rankOf_2등() {
        assertThat(LottoResult.rankOf(5, true)).isEqualTo(Rank.SECOND);
    }

    @DisplayName("등수 판정: 5개만 → 3등")
    @Test
    void rankOf_3등() {
        assertThat(LottoResult.rankOf(5, false)).isEqualTo(Rank.THIRD);
    }

    @DisplayName("등수 판정: 4개 → 4등, 3개 → 5등, 그 외 낙첨(null)")
    @Test
    void rankOf_그외() {
        assertThat(LottoResult.rankOf(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(LottoResult.rankOf(3, false)).isEqualTo(Rank.FIFTH);
        assertThat(LottoResult.rankOf(2, false)).isNull();
        assertThat(LottoResult.rankOf(0, false)).isNull();
    }
}
