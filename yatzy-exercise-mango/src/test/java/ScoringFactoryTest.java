import com.yatzy.model.Category;
import com.yatzy.scoring.ScoreStrategy;
import com.yatzy.scoring.ScoringFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoringFactoryTest {

    @Test
    void testChanceStrategy() {
        int[] dice = {1, 2, 3, 4, 5};
        ScoreStrategy strategy = ScoringFactory.getStrategy(Category.CHANCE);
        assertThat(strategy.score(dice)).isEqualTo(15);
    }

    @Test
    void testYatzyStrategy() {
        int[] dice = {6, 6, 6, 6, 6};
        ScoreStrategy strategy = ScoringFactory.getStrategy(Category.YATZY);
        assertThat(strategy.score(dice)).isEqualTo(50);
    }

    @Test
    void testOnesStrategy() {
        int[] dice = {1, 1, 2, 4, 4};
        ScoreStrategy strategy = ScoringFactory.getStrategy(Category.ONES);
        assertThat(strategy.score(dice)).isEqualTo(2);
    }

    @Test
    void testPairStrategy() {
        int[] dice = {3, 3, 2, 4, 5};
        ScoreStrategy strategy = ScoringFactory.getStrategy(Category.PAIR);
        assertThat(strategy.score(dice)).isEqualTo(6);
    }

    @Test
    void testFullHouseStrategy() {
        int[] dice = {2, 2, 3, 3, 3};
        ScoreStrategy strategy = ScoringFactory.getStrategy(Category.FULL_HOUSE);
        assertThat(strategy.score(dice)).isEqualTo(13);
    }

    @Test
    void testUnknownCategoryThrows() {
        assertThatThrownBy(() -> ScoringFactory.getStrategy(Category.valueOf("UNKNOWN_CATEGORY")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}