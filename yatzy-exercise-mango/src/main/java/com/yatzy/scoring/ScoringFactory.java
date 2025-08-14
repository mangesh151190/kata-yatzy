package com.yatzy.scoring;

import com.yatzy.model.Category;
import com.yatzy.model.DiceRoll;

/**
 * Factory class to create scoring strategies based on the Yatzy game categories.
 * This class provides a method to get the appropriate scoring strategy for a given category.
 */
public class ScoringFactory {
    public static ScoreStrategy getStrategy(Category category) {
        switch (category) {
            case CHANCE:
                return dice -> YatzyJava.chance(new DiceRoll(dice));
            case YATZY:
                return dice -> YatzyJava.yatzy(new DiceRoll(dice));
            case ONES:
                return dice -> YatzyJava.ones(new DiceRoll(dice));
            case TWOS:
                return dice -> YatzyJava.twos(new DiceRoll(dice));
            case THREES:
                return dice -> YatzyJava.threes(new DiceRoll(dice));
            case FOURS:
                return dice -> new YatzyJava(new DiceRoll(dice)).fours();
            case FIVES:
                return dice -> new YatzyJava(new DiceRoll(dice)).fives();
            case SIXES:
                return dice -> new YatzyJava(new DiceRoll(dice)).sixes();
            case PAIR:
                return dice -> YatzyJava.score_pair(new DiceRoll(dice));
            case TWO_PAIR:
                return dice -> YatzyJava.two_pair(new DiceRoll(dice));
            case THREE_OF_A_KIND:
                return dice -> YatzyJava.three_of_a_kind(new DiceRoll(dice));
            case FOUR_OF_A_KIND:
                return dice -> YatzyJava.four_of_a_kind(new DiceRoll(dice));
            case SMALL_STRAIGHT:
                return dice -> YatzyJava.smallStraight(new DiceRoll(dice));
            case LARGE_STRAIGHT:
                return dice -> YatzyJava.largeStraight(new DiceRoll(dice));
            case FULL_HOUSE:
                return dice -> YatzyJava.fullHouse(new DiceRoll(dice));
            default:
                throw new IllegalArgumentException("Unknown category");
        }
    }
}