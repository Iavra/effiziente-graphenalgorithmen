package de.kibr.ega.generator.node;

import java.util.Random;

abstract class BaseNodeGenerator implements NodeGenerator {
    static final Random RANDOM = new Random();

    final double xMax;
    final double yMax;

    BaseNodeGenerator(double xMax, double yMax) {
        this.xMax = xMax;
        this.yMax = yMax;
    }
}
