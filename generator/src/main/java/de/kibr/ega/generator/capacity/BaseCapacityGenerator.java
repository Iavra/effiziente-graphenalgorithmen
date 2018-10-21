package de.kibr.ega.generator.capacity;

public abstract class BaseCapacityGenerator implements CapacityGenerator {
    protected final int maxCapacity;

    protected BaseCapacityGenerator(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
