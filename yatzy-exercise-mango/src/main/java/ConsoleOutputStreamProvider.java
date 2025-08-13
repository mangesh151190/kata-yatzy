import java.io.PrintStream;

/**
 * Provides a PrintStream that outputs to the console.
 * This implementation of OutputStreamProvider returns System.out.
 */
public class ConsoleOutputStreamProvider implements OutputStreamProvider {

    /**
     * Returns a PrintStream that outputs to the console.
     */
    @Override
    public PrintStream getStream() {
        return System.out;
    }
}