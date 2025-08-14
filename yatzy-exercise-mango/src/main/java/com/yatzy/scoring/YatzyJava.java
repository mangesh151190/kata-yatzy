package com.yatzy.scoring;

import com.yatzy.model.DiceRoll;
import java.util.Arrays;

/**
 * Yatzy game implementation in Java.
 * This class provides methods to calculate scores based on the rules of Yatzy.
 */
public class YatzyJava {

    protected final DiceRoll roll;

    /**
     * Constructor for YatzyJava class.
     * @param roll the DiceRoll object containing the rolled dice
     */
    public YatzyJava(DiceRoll roll) {
        this.roll = roll;
    }

    /**
     * Calculates the score for the "Chance" category.
     */
    public static int chance(DiceRoll roll) {
        return Arrays.stream(roll.getDice()).sum();
    }

    /**
     * Calculates the score for the "Yatzy" category.
     */
    public static int yatzy(DiceRoll roll) {
        int[] dice = roll.getDice();
        int first = dice[0];
        return Arrays.stream(dice).allMatch(die -> die == first) ? 50 : 0;
    }

    /**
     * Calculates the score for the "Ones" category.
     */
    public static int ones(DiceRoll roll) {
        return (int) Arrays.stream(roll.getDice()).filter(die -> die == 1).count();
    }

    /**
     * Calculates the score for the "Twos" category.
     */
    public static int twos(DiceRoll roll) {
        return Arrays.stream(roll.getDice()).filter(die -> die == 2).map(die -> 2).sum();
    }

    /**
     * Calculates the score for the "Threes" category.
     */
    public static int threes(DiceRoll roll) {
        return Arrays.stream(roll.getDice()).filter(die -> die == 3).map(die -> 3).sum();
    }

    /**
     * Calculates the score for the "Fours" category.
     */
    public int fours() {
        return sumMatching(4, roll.getDice());
    }

    /**
     * Calculates the score for the "Fives" category.
     */
    public int fives() {
        return sumMatching(5, roll.getDice());
    }

    /**
     * Calculates the score for the "Sixes" category.
     */
    public int sixes() {
        return sumMatching(6, roll.getDice());
    }

    /**
     * Calculates the score for a pair of dice.
     */
    public static int score_pair(DiceRoll roll) {
        int[] counts = getCounts(roll.getDice());
        for (int i = 5; i >= 0; i--) {
            if (counts[i] >= 2) {
                return (i + 1) * 2;
            }
        }
        return 0;
    }

    /**
     * Calculates the score for two pairs of dice.
     */
    public static int two_pair(DiceRoll roll) {
        int[] counts = getCounts(roll.getDice());
        int pairs = 0, score = 0;
        for (int i = 5; i >= 0; i--) {
            if (counts[i] >= 2) {
                pairs++;
                score += (i + 1) * 2;
                counts[i] -= 2;
            }
        }
        return pairs == 2 ? score : 0;
    }

    /**
     * Helper method to count occurrences of each die value.
     */
    private static int[] getCounts(int... dice) {
        int[] counts = new int[6];
        for (int die : dice) {
            counts[die - 1]++;
        }
        return counts;
    }

    /**
     * Calculates the score for four of a kind.
     */
    public static int four_of_a_kind(DiceRoll roll) {
        int[] tallies = getCounts(roll.getDice());
        for (int i = 0; i < 6; i++)
            if (tallies[i] >= 4)
                return (i + 1) * 4;
        return 0;
    }

    /**
     * Calculates the score for three of a kind.
     */
    public static int three_of_a_kind(DiceRoll roll) {
        int[] t = getCounts(roll.getDice());
        for (int i = 0; i < 6; i++)
            if (t[i] >= 3)
                return (i + 1) * 3;
        return 0;
    }

    /**
     * Calculates the score for a small straight.
     */
    public static int smallStraight(DiceRoll roll) {
        int[] dice = roll.getDice();
        return Arrays.stream(new int[]{1, 2, 3, 4, 5})
                .allMatch(val -> Arrays.stream(dice).anyMatch(die -> die == val)) ? 15 : 0;
    }

    /**
     * Calculates the score for a large straight.
     */
    public static int largeStraight(DiceRoll roll) {
        int[] dice = roll.getDice();
        return Arrays.stream(new int[]{2, 3, 4, 5, 6})
                .allMatch(val -> Arrays.stream(dice).anyMatch(die -> die == val)) ? 20 : 0;
    }

    /**
     * Calculates the score for a full house.
     */
    public static int fullHouse(DiceRoll roll) {
        int[] dice = roll.getDice();
        int[] counts = getCounts(dice);
        boolean hasThree = false, hasPair = false;
        for (int count : counts) {
            if (count == 3) hasThree = true;
            if (count == 2) hasPair = true;
        }
        if (hasThree && hasPair) {
            return Arrays.stream(dice).sum();
        }
        return 0;
    }

    /**
     * Helper method to sum the values of dice that match a target value.
     */
    public static int sumMatching(int target, int[] dice) {
        int sum = 0;
        for (int die : dice) {
            if (die == target) {
                sum += target;
            }
        }
        return sum;
    }
}