/**
 * DiceRoll class represents a roll of 5 dice.
 * It ensures that exactly 5 dice values are provided upon instantiation.
 */
public class DiceRoll {
    private final int[] dice;

    /**
     * Constructs a DiceRoll with exactly 5 dice values.
     * @param dice the values of the rolled dice
     * @throws IllegalArgumentException if the number of dice is not exactly 5
     */
    public DiceRoll(int... dice) {
        if (dice.length != 5) {
            throw new IllegalArgumentException("Exactly 5 dice must be provided.");
        }
        this.dice = dice;
    }

    public int[] getDice() {
        return dice;
    }
}
