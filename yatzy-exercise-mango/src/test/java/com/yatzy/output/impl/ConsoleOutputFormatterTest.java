package com.yatzy.output.impl;

// File: src/test/java/com/yatzy/output/impl/ConsoleOutputFormatterTest.java
import com.yatzy.model.Category;
import com.yatzy.output.impl.ConsoleOutputFormatter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleOutputFormatterTest {

    @Test
    void formatRollResult_returnsExpectedString() {
        ConsoleOutputFormatter formatter = new ConsoleOutputFormatter();
        String result = formatter.formatRollResult(1, Category.YATZY, 50, "YATZY");

        assertThat(result).isEqualTo(
                "1. ROLL\nYou've chosen YATZY as score category\nScore: 50\nYou've got YATZY\n"
        );
    }
}