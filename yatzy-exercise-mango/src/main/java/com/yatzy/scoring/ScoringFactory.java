package com.yatzy.scoring;

import com.yatzy.model.Category;

import java.util.Arrays;

/**
 * Factory class to create scoring strategies based on the Yatzy game categories.
 * This class provides a method to get the appropriate scoring strategy for a given category.
 */
public class ScoringFactory {
    public static ScoreStrategy getStrategy(Category category) {
        switch (category) {
            case CHANCE: return dice -> Arrays.stream(dice).sum();
            case YATZY: return YatzyJava::yatzy;
            case ONES: return dice -> YatzyJava.sumMatching(1, dice);
            case TWOS: return dice -> YatzyJava.sumMatching(2, dice);
            case THREES: return dice -> YatzyJava.sumMatching(3, dice);
            case FOURS: return dice -> YatzyJava.sumMatching(4, dice);
            case FIVES: return dice -> YatzyJava.sumMatching(5, dice);
            case SIXES: return dice -> YatzyJava.sumMatching(6, dice);
            case PAIR: return dice -> YatzyJava.score_pair(dice[0], dice[1], dice[2], dice[3], dice[4]);
            case TWO_PAIR: return dice -> YatzyJava.two_pair(dice[0], dice[1], dice[2], dice[3], dice[4]);
            case THREE_OF_A_KIND: return dice -> YatzyJava.three_of_a_kind(dice[0], dice[1], dice[2], dice[3], dice[4]);
            case FOUR_OF_A_KIND: return dice -> YatzyJava.four_of_a_kind(dice[0], dice[1], dice[2], dice[3], dice[4]);
            case SMALL_STRAIGHT: return dice -> YatzyJava.smallStraight(dice[0], dice[1], dice[2], dice[3], dice[4]);
            case LARGE_STRAIGHT: return dice -> YatzyJava.largeStraight(dice[0], dice[1], dice[2], dice[3], dice[4]);
            case FULL_HOUSE: return dice -> YatzyJava.fullHouse(dice[0], dice[1], dice[2], dice[3], dice[4]);
            default: throw new IllegalArgumentException("Unknown category");
        }
    }
}
