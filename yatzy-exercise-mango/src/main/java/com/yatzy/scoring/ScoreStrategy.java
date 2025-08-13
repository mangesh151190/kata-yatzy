package com.yatzy.scoring;

/**
 * Interface for scoring strategies in a dice game.
 */
public interface ScoreStrategy {
    int score(int[] dice);
}
