package de.kibr.ega.generator.util;

import java.util.function.Supplier;

public class IdBuilder implements Supplier<String> {
    private int sequence = 0;

    @Override
    public String get() {
        return intToAlphabeticalSequence(sequence++);
    }

    /**
     * Source: https://stackoverflow.com/a/32532049
     */
    private String intToAlphabeticalSequence(int index) {
        return index < 0 ? "" : intToAlphabeticalSequence((index / 26) - 1) + (char)('A' + index % 26);
    }
}
