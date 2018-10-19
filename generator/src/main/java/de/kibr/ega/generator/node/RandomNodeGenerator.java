package de.kibr.ega.generator.node;

import de.kibr.ega.graph.GraphNode;

import java.util.ArrayList;
import java.util.List;

public class RandomNodeGenerator implements NodeGenerator {
    private final double maxWidth;
    private final double maxHeight;

    public RandomNodeGenerator(double maxWidth, double maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    @Override
    public List<GraphNode> generateNodes(int size) {
        List<GraphNode> nodes = new ArrayList<>();
        for (int i = 0; i < size; i++) nodes.add(generateNode());
        return nodes;
    }

    private GraphNode generateNode() {
        return new GraphNode(Math.random() * maxWidth, Math.random() * maxHeight);
    }
}
