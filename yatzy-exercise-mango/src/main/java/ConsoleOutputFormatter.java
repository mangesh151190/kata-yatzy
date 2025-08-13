/**
 * ConsoleOutputFormatter is a class that implements the OutputFormatter interface.
 * It formats the output of a roll result for display in the console.
 */
public class ConsoleOutputFormatter implements OutputFormatter {
    @Override
    public String formatRollResult(int rollNumber, Category category, int score, String outcomeLabel) {
        return String.format(
                "%d. ROLL\nYou've chosen %s as score category\nScore: %d\nYou've got %s\n",
                rollNumber, category, score, outcomeLabel
        );
    }
}