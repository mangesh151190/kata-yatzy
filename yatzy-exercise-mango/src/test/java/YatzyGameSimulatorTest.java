import com.yatzy.game.YatzyGameSimulator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class YatzyGameSimulatorTest {

    @Test
    public void testSimulatorOutputFormat() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        YatzyGameSimulator.main(new String[]{});

        String output = outContent.toString();
        System.setOut(originalOut);

        assertThat(output).contains("ROLL");
        assertThat(output).contains("You've chosen");
        assertThat(output).contains("Score:");
        assertThat(output).contains("You've got");
    }
}
