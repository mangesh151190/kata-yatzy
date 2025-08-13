import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class YatzyGameSimulatorTest {

    @Test
    public void testSimulatorOutputFormat() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        YatzyGameSimulator.main(new String[]{});

        String output = outContent.toString();
        System.setOut(originalOut);

        assertTrue(output.contains("ROLL"));
        assertTrue(output.contains("You've chosen"));
        assertTrue(output.contains("Score:"));
        assertTrue(output.contains("You've got"));
    }
}
