import com.yatzy.model.DiceRoll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import com.yatzy.scoring.YatzyJava;

import static org.assertj.core.api.Assertions.assertThat;

public class YatzyJavaTest {

    @Test
    public void chance_scores_sum_of_all_dice() {
        assertThat(YatzyJava.chance(new DiceRoll(2, 3, 4, 5, 1))).isEqualTo(15);
        assertThat(YatzyJava.chance(new DiceRoll(3, 3, 4, 5, 1))).isEqualTo(16);
    }

    @Test
    public void yatzy_scores_50() {
        assertThat(YatzyJava.yatzy(4, 4, 4, 4, 4)).isEqualTo(50);
        assertThat(YatzyJava.yatzy(6, 6, 6, 6, 6)).isEqualTo(50);
        assertThat(YatzyJava.yatzy(6, 6, 6, 6, 3)).isZero();
    }

    @Test
    public void test_1s() {
        assertThat(YatzyJava.ones(1, 2, 3, 4, 5)).isEqualTo(1);
        assertThat(YatzyJava.ones(1, 2, 1, 4, 5)).isEqualTo(2);
        assertThat(YatzyJava.ones(6, 2, 2, 4, 5)).isZero();
        assertThat(YatzyJava.ones(1, 2, 1, 1, 1)).isEqualTo(4);
    }

    @Test
    public void test_2s() {
        assertThat(YatzyJava.twos(1, 2, 3, 2, 6)).isEqualTo(4);
        assertThat(YatzyJava.twos(2, 2, 2, 2, 2)).isEqualTo(10);
    }

    @Test
    public void test_threes() {
        assertThat(YatzyJava.threes(1, 2, 3, 2, 3)).isEqualTo(6);
        assertThat(YatzyJava.threes(2, 3, 3, 3, 3)).isEqualTo(12);
    }

    @Test
    public void fours_test() {
        assertThat(new YatzyJava(4, 4, 4, 5, 5).fours()).isEqualTo(12);
        assertThat(new YatzyJava(4, 4, 5, 5, 5).fours()).isEqualTo(8);
        assertThat(new YatzyJava(4, 5, 5, 5, 5).fours()).isEqualTo(4);
    }

    @Test
    public void fives() {
        assertThat(new YatzyJava(4, 4, 4, 5, 5).fives()).isEqualTo(10);
        assertThat(new YatzyJava(4, 4, 5, 5, 5).fives()).isEqualTo(15);
        assertThat(new YatzyJava(4, 5, 5, 5, 5).fives()).isEqualTo(20);
    }

    @Test
    public void sixes_test() {
        assertThat(new YatzyJava(4, 4, 4, 5, 5).sixes()).isZero();
        assertThat(new YatzyJava(4, 4, 6, 5, 5).sixes()).isEqualTo(6);
        assertThat(new YatzyJava(6, 5, 6, 6, 5).sixes()).isEqualTo(18);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 1,2,3,3,3, 9",
            "3, 1,2,4,5,6, 0",
            "2, 2,2,2,2,2, 10",
            "4, 1,2,3,4,5, 4"
    })
    public void testSumMatchingParameterized(int target, int d1, int d2, int d3, int d4, int d5, int expected) {
        int[] dice = {d1, d2, d3, d4, d5};
        assertThat(YatzyJava.sumMatching(target, dice)).isEqualTo(expected);
    }

    @Test
    public void one_pair() {
        assertThat(YatzyJava.score_pair(3, 4, 3, 5, 6)).isEqualTo(6);
        assertThat(YatzyJava.score_pair(5, 3, 3, 3, 5)).isEqualTo(10);
        assertThat(YatzyJava.score_pair(5, 3, 6, 6, 5)).isEqualTo(12);
    }

    @Test
    public void two_Pair() {
        assertThat(YatzyJava.two_pair(3, 3, 5, 4, 5)).isEqualTo(16);
        assertThat(YatzyJava.two_pair(3, 3, 5, 5, 5)).isEqualTo(16);
    }

    @Test
    public void three_of_a_kind() {
        assertThat(YatzyJava.three_of_a_kind(3, 3, 3, 4, 5)).isEqualTo(9);
        assertThat(YatzyJava.three_of_a_kind(5, 3, 5, 4, 5)).isEqualTo(15);
        assertThat(YatzyJava.three_of_a_kind(3, 3, 3, 3, 5)).isEqualTo(9);
    }

    @Test
    public void four_of_a_knd() {
        assertThat(YatzyJava.four_of_a_kind(3, 3, 3, 3, 5)).isEqualTo(12);
        assertThat(YatzyJava.four_of_a_kind(5, 5, 5, 4, 5)).isEqualTo(20);
        assertThat(YatzyJava.four_of_a_kind(3, 3, 3, 3, 3)).isEqualTo(12);
    }

    @Test
    public void smallStraight() {
        assertThat(YatzyJava.smallStraight(1, 2, 3, 4, 5)).isEqualTo(15);
        assertThat(YatzyJava.smallStraight(2, 3, 4, 5, 1)).isEqualTo(15);
        assertThat(YatzyJava.smallStraight(1, 2, 2, 4, 5)).isZero();
    }

    @Test
    public void largeStraight() {
        assertThat(YatzyJava.largeStraight(6, 2, 3, 4, 5)).isEqualTo(20);
        assertThat(YatzyJava.largeStraight(2, 3, 4, 5, 6)).isEqualTo(20);
        assertThat(YatzyJava.largeStraight(1, 2, 2, 4, 5)).isZero();
    }

    @Test
    public void fullHouse() {
        assertThat(YatzyJava.fullHouse(6, 2, 2, 2, 6)).isEqualTo(18);
        assertThat(YatzyJava.fullHouse(2, 3, 4, 5, 6)).isZero();
    }

    @Test
    public void one_pair_no_pair_returns_zero() {
        assertThat(YatzyJava.score_pair(1, 2, 3, 4, 5)).isZero();
    }

    @Test
    public void two_pair_only_one_pair_returns_zero() {
        assertThat(YatzyJava.two_pair(1, 1, 2, 3, 4)).isZero();
    }

    @Test
    public void two_pair_three_of_a_kind_only_returns_two_pair_score() {
        assertThat(YatzyJava.two_pair(1, 1, 2, 2, 2)).isEqualTo(6);
    }

    @Test
    public void three_of_a_kind_no_triplet_returns_zero() {
        assertThat(YatzyJava.three_of_a_kind(1, 2, 3, 4, 5)).isZero();
    }

    @Test
    public void four_of_a_kind_no_quad_returns_zero() {
        assertThat(YatzyJava.four_of_a_kind(1, 1, 1, 2, 3)).isZero();
    }

    @Test
    public void smallStraight_wrong_combination_returns_zero() {
        assertThat(YatzyJava.smallStraight(1, 2, 2, 4, 5)).isZero();
    }

    @Test
    public void largeStraight_wrong_combination_returns_zero() {
        assertThat(YatzyJava.largeStraight(1, 2, 3, 4, 5)).isZero();
    }

    @Test
    public void fullHouse_all_same_returns_zero() {
        assertThat(YatzyJava.fullHouse(4, 4, 4, 4, 4)).isZero();
    }

    @Test
    public void fullHouse_no_pair_or_triplet_returns_zero() {
        assertThat(YatzyJava.fullHouse(1, 2, 3, 4, 5)).isZero();
    }
}