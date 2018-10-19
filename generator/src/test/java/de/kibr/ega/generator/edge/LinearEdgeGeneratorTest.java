package de.kibr.ega.generator.edge;

public class LinearEdgeGeneratorTest extends EdgeGeneratorTest {

    @Override
    protected EdgeGenerator generator() {
        return new LinearEdgeGenerator();
    }
}