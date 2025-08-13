import java.util.Arrays;
import java.util.Random;

public class YatzyGameSimulator {

    private static final Random random = new Random();

    public static void main(String[] args) {
        for (int rollNumber = 1; rollNumber <= 3; rollNumber++) {
            int[] dice = rollDice();
            System.out.println(Arrays.toString(dice));
            Category chosenCategory = chooseCategory(dice);
            ScoreStrategy strategy = ScoringFactory.getStrategy(chosenCategory);
            int score = strategy.score(dice);

            printRollResult(rollNumber, chosenCategory, score);
        }
    }

    private static int[] rollDice() {
        int[] dice = new int[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = random.nextInt(6) + 1;
        }
        return dice;
    }

    private static Category chooseCategory(int[] dice) {

        return switch (dice[0]) {
            case 1 -> Category.YATZY;
            case 2 -> Category.THREE_OF_A_KIND;
            case 3 -> Category.SMALL_STRAIGHT;
            default -> Category.CHANCE;
        };
    }

    private static void printRollResult(int rollNumber, Category category, int score) {
        System.out.println(rollNumber + ". ROLL");
        System.out.println("You've chosen " + category + " as score category");
        System.out.println("Score: " + score);
        System.out.println("You've got " + getOutcomeLabel(category, score));
        System.out.println();
    }

    private static String getOutcomeLabel(Category category, int score) {
        if (score == 0) return "NOTHING";
        return category.name().replace("_", " ");
    }
}
