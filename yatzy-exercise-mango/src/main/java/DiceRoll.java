public class DiceRoll {
    private final int[] dice;

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
