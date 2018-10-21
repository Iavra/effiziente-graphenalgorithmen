package de.kibr.ega.generator.graph;

import de.kibr.ega.core.graph.GraphNode;

public class SimpleGraphNode implements GraphNode {
    private final double x;
    private final double y;

    public SimpleGraphNode(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }
}
