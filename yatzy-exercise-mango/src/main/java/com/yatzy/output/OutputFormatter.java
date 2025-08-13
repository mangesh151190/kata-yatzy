package com.yatzy.output;

import com.yatzy.model.Category;

/**
 * OutputFormatter interface for formatting the results of a dice roll.
 * This interface defines a method to format the output of a roll result
 * including the roll number, category, score, and outcome label.
 */
public interface OutputFormatter {
    String formatRollResult(int rollNumber, Category category, int score, String outcomeLabel);
}