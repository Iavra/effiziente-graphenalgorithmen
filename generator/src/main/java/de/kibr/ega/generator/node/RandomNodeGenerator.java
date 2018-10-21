package de.kibr.ega.generator.node;

import de.kibr.ega.graph.GraphNode;

import java.util.ArrayList;
import java.util.List;

public class RandomNodeGenerator extends BaseNodeGenerator {
    public RandomNodeGenerator(double xMax, double yMax) {
        super(xMax, yMax);
    }

    @Override
    public List<GraphNode> generateNodes(int size) {
        List<GraphNode> nodes = new ArrayList<>();
        for (int i = 0; i < size; i++) nodes.add(generateNode());
        return nodes;
    }

    private GraphNode generateNode() {
        return new GraphNode(Math.random() * xMax, Math.random() * yMax);
    }
}
