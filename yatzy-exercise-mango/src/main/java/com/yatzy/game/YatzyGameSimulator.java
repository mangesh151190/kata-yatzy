package com.yatzy.game;

import com.yatzy.model.Category;
import com.yatzy.output.OutputFormatter;
import com.yatzy.output.OutputStreamProvider;
import com.yatzy.output.impl.ConsoleOutputFormatter;
import com.yatzy.output.impl.ConsoleOutputStreamProvider;
import com.yatzy.scoring.ScoreStrategy;
import com.yatzy.scoring.ScoringFactory;

import java.util.Random;

/**
 * Simulates a Yatzy-like dice game.
 * Rolls dice, selects a scoring category, calculates the score, and outputs results.
 */
public class YatzyGameSimulator {

    private static final Random random = new Random();

    /**
     * Entry point for the simulator.
     * Rolls dice three times, scores each roll, and prints the result.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        OutputFormatter formatter = new ConsoleOutputFormatter();
        OutputStreamProvider streamProvider = new ConsoleOutputStreamProvider();

        for (int rollNumber = 1; rollNumber <= 3; rollNumber++) {
            int[] dice = rollDice();
            Category chosenCategory = chooseCategory(dice);
            ScoreStrategy strategy = ScoringFactory.getStrategy(chosenCategory);
            int score = strategy.score(dice);

            String outcomeLabel = getOutcomeLabel(chosenCategory, score);
            String output = formatter.formatRollResult(rollNumber, chosenCategory, score, outcomeLabel);
            streamProvider.getStream().print(output);
        }
    }

    /**
     * Rolls five dice and returns their values.
     * @return an array of integers representing the rolled dice
     */
    private static int[] rollDice() {
        int[] dice = new int[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = random.nextInt(6) + 1;
        }
        return dice;
    }

    /**
     * Chooses a scoring category based on the first die rolled.
     * @param dice an array of integers representing the rolled dice
     * @return a Category enum value representing the chosen category
     */
    private static Category chooseCategory(int[] dice) {
        return switch (dice[0]) {
            case 1 -> Category.YATZY;
            case 2 -> Category.THREE_OF_A_KIND;
            case 3 -> Category.SMALL_STRAIGHT;
            default -> Category.CHANCE;
        };
    }

    /**
     * Returns a label for the outcome based on the category and score.
     * @param category the scoring category
     * @param score the score achieved in that category
     * @return a string label representing the outcome
     */
    private static String getOutcomeLabel(Category category, int score) {
        if (score == 0) return "NOTHING";
        return category.name().replace("_", " ");
    }
}