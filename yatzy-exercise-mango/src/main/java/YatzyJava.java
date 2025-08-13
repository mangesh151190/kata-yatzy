import java.util.Arrays;

/**
 * Yatzy game implementation in Java.
 * This class provides methods to calculate scores based on the rules of Yatzy.
 */
public class YatzyJava {

    /**
     * The values of the rolled dice.
     * Must contain exactly 5 values.
     */
    protected int[] dice;

    /**
     * Constructor for YatzyJava class.
     * Initializes the dice with exactly 5 values.
     * @param dice the values of the rolled dice
     * @throws IllegalArgumentException if the number of dice is not 5
     */
    public YatzyJava(int... dice) {
        if (dice.length != 5) {
            throw new IllegalArgumentException("Exactly 5 dice required");
        }
        this.dice = Arrays.copyOf(dice, 5);
    }

    /**
     * Calculates the score for the "Chance" category.
     * @param roll the DiceRoll object containing the rolled dice
     * @return the sum of all dice values
     */
    public static int chance(DiceRoll roll) {
        return Arrays.stream(roll.getDice()).sum();
    }

    /**
     * Calculates the score for the "Yatzy" category.
     * @param dice the values of the rolled dice
     * @return 50 if all dice are the same, otherwise 0
     */
    public static int yatzy(int... dice) {
        int[] counts = new int[6];
        for (int die : dice)
            counts[die - 1]++;
        for (int i = 0; i != 6; i++)
            if (counts[i] == 5)
                return 50;
        return 0;
    }

    /**
     * Calculates the score for the "Ones" category.
     * Sums all dice showing 1.
     * @return the total number of ones
     */
    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        int[] dice = {d1, d2, d3, d4, d5};
        return (int) Arrays.stream(dice).filter(die -> die == 1).count();
    }

    /**
     * Calculates the score for the "Twos" category.
     * Sums all dice showing 2.
     * @return the total score for twos
     */
    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        int[] dice = {d1, d2, d3, d4, d5};
        return Arrays.stream(dice).filter(die -> die == 2).map(die -> 2).sum();
    }

    /**
     * Calculates the score for the "Threes" category.
     * Sums all dice showing 3.
     * @return the total score for threes
     */
    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        int[] dice = {d1, d2, d3, d4, d5};
        return Arrays.stream(dice).filter(die -> die == 3).map(die -> 3).sum();
    }

    /**
     * Calculates the score for the "Fours" category.
     * Sums all dice showing 4.
     * @return the total score for fours
     */
    public int fours() {
        return sumMatching(4, dice);
    }

    /**
     * Calculates the score for the "Fives" category.
     * Sums all dice showing 5.
     * @return the total score for fives
     */
    public int fives() {
        return sumMatching(5, dice);
    }

    /**
     * Calculates the score for the "Sixes" category.
     * Sums all dice showing 6.
     * @return the total score for sixes
     */
    public int sixes() {
        return sumMatching(6, dice);
    }


    /**
     * Calculates the score for a pair of dice.
     * @param d1 the value of the first die
     * @param d2 the value of the second die
     * @param d3 the value of the third die
     * @param d4 the value of the fourth die
     * @param d5 the value of the fifth die
     * @return the score for a pair, or 0 if no pair exists
     */
    public static int score_pair(int d1, int d2, int d3, int d4, int d5) {
        int[] counts = getCounts(d1, d2, d3, d4, d5);
        for (int i = 5; i >= 0; i--) {
            if (counts[i] >= 2) {
                return (i + 1) * 2;
            }
        }
        return 0;
    }

    /**
     * Calculates the score for two pairs of dice.
     * @param d1 the value of the first die
     * @param d2 the value of the second die
     * @param d3 the value of the third die
     * @param d4 the value of the fourth die
     * @param d5 the value of the fifth die
     * @return the score for two pairs, or 0 if not exactly two pairs exist
     */
    public static int two_pair(int d1, int d2, int d3, int d4, int d5) {
        int[] counts = getCounts(d1, d2, d3, d4, d5);
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
     * @param dice the values of the rolled dice
     * @return an array where index 0 corresponds to die value 1, index 1 to die value 2, etc.
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
     * @param d1 the value of the first die
     * @param d2 the value of the second die
     * @param d3 the value of the third die
     * @param d4 the value of the fourth die
     * @param d5 the value of the fifth die
     * @return the score for four of a kind, or 0 if not found
     */
    public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies = getCounts(d1, d2, d3, d4, d5);
        for (int i = 0; i < 6; i++)
            if (tallies[i] >= 4)
                return (i + 1) * 4;
        return 0;
    }

    /**
     * Calculates the score for three of a kind.
     * @param d1 the value of the first die
     * @param d2 the value of the second die
     * @param d3 the value of the third die
     * @param d4 the value of the fourth die
     * @param d5 the value of the fifth die
     * @return the score for three of a kind, or 0 if not found
     */
    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        int[] t = getCounts(d1, d2, d3, d4, d5);
        for (int i = 0; i < 6; i++)
            if (t[i] >= 3)
                return (i + 1) * 3;
        return 0;
    }

    /**
     * Calculates the score for a small straight.
     * A small straight consists of the numbers 1, 2, 3, 4, and 5.
     * @param d1 the value of the first die
     * @param d2 the value of the second die
     * @param d3 the value of the third die
     * @param d4 the value of the fourth die
     * @param d5 the value of the fifth die
     * @return 15 if a small straight is found, otherwise 0
     */
    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] dice = {d1, d2, d3, d4, d5};
        return Arrays.stream(new int[]{1, 2, 3, 4, 5})
                .allMatch(val -> Arrays.stream(dice).anyMatch(die -> die == val)) ? 15 : 0;
    }

    /**
     * Calculates the score for a large straight.
     * A large straight consists of the numbers 2, 3, 4, 5, and 6.
     * @param d1 the value of the first die
     * @param d2 the value of the second die
     * @param d3 the value of the third die
     * @param d4 the value of the fourth die
     * @param d5 the value of the fifth die
     * @return 20 if a large straight is found, otherwise 0
     */
    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] dice = {d1, d2, d3, d4, d5};
        return Arrays.stream(new int[]{2, 3, 4, 5, 6})
                .allMatch(val -> Arrays.stream(dice).anyMatch(die -> die == val)) ? 20 : 0;
    }

    /**
     * Calculates the score for a full house.
     * A full house consists of three of one number and two of another.
     * @param d1 the value of the first die
     * @param d2 the value of the second die
     * @param d3 the value of the third die
     * @param d4 the value of the fourth die
     * @param d5 the value of the fifth die
     * @return the total score if a full house is found, otherwise 0
     */
    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        int[] counts = getCounts(d1, d2, d3, d4, d5);
        boolean hasThree = false, hasPair = false;
        for (int count : counts) {
            if (count == 3) hasThree = true;
            if (count == 2) hasPair = true;
        }

        if (hasThree && hasPair) {
            return d1 + d2 + d3 + d4 + d5;
        }
        return 0;
    }

    /**
     * Helper method to sum the values of dice that match a target value.
     * @param target the value to match
     * @param dice the array of dice values
     * @return the sum of all dice matching the target value
     */
    static int sumMatching(int target, int[] dice) {
        int sum = 0;
        for (int die : dice) {
            if (die == target) {
                sum += target;
            }
        }
        return sum;
    }
}



