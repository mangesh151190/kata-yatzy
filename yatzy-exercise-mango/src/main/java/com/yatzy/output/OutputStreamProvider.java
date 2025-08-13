package com.yatzy.output;

import java.io.PrintStream;

/**
 * Interface for providing an output stream.
 * This interface defines a method to get a PrintStream for output.
 */
public interface OutputStreamProvider {
    PrintStream getStream();
}