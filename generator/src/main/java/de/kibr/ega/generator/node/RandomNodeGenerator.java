package de.kibr.ega.generator.node;

import de.kibr.ega.graph.GraphNode;

import java.util.ArrayList;
import java.util.List;

public class RandomNodeGenerator implements NodeGenerator {
    @Override
    public List<GraphNode> generateNodes(int size, double maxWidth, double maxHeight) {
        List<GraphNode> nodes = new ArrayList<>();
        for (int i = 0; i < size; i++) nodes.add(generateNode(maxWidth, maxHeight));
        return nodes;
    }

    private GraphNode generateNode(double maxWidth, double maxHeight) {
        return new GraphNode(Math.random() * maxWidth, Math.random() * maxHeight);
    }
}
