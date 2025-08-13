import com.yatzy.model.Category;
import com.yatzy.scoring.ScoreStrategy;
import com.yatzy.scoring.ScoringFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScoringFactoryTest {

    @Test
    void testChanceStrategy() {
        int[] dice = {1, 2, 3, 4, 5};
        ScoreStrategy strategy = ScoringFactory.getStrategy(Category.CHANCE);
        assertEquals(15, strategy.score(dice));
    }

    @Test
    void testYatzyStrategy() {
        int[] dice = {6, 6, 6, 6, 6};
        ScoreStrategy strategy = ScoringFactory.getStrategy(Category.YATZY);
        assertEquals(50, strategy.score(dice));
    }

    @Test
    void testOnesStrategy() {
        int[] dice = {1, 1, 2, 4, 4};
        ScoreStrategy strategy = ScoringFactory.getStrategy(Category.ONES);
        assertEquals(2, strategy.score(dice));
    }

    @Test
    void testPairStrategy() {
        int[] dice = {3, 3, 2, 4, 5};
        ScoreStrategy strategy = ScoringFactory.getStrategy(Category.PAIR);
        assertEquals(6, strategy.score(dice));
    }

    @Test
    void testFullHouseStrategy() {
        int[] dice = {2, 2, 3, 3, 3};
        ScoreStrategy strategy = ScoringFactory.getStrategy(Category.FULL_HOUSE);
        assertEquals(13, strategy.score(dice));
    }

    @Test
    void testUnknownCategoryThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            ScoringFactory.getStrategy(Category.valueOf("UNKNOWN_CATEGORY"));
        });
    }
}