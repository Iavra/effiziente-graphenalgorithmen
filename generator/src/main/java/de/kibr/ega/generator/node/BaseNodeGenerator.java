package de.kibr.ega.generator.node;

public abstract class BaseNodeGenerator implements NodeGenerator {
    protected final double xMax;
    protected final double yMax;

    protected BaseNodeGenerator(double xMax, double yMax) {
        this.xMax = xMax;
        this.yMax = yMax;
    }
}
