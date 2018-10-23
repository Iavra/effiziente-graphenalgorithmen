package de.kibr.ega.generator.node;

import de.kibr.ega.core.graph.GraphNode;
import de.kibr.ega.generator.graph.SimpleGraphNode;

import java.util.ArrayList;
import java.util.List;

public class RandomNodeGenerator extends BaseNodeGenerator {
    public RandomNodeGenerator(double xMax, double yMax) {
        super(xMax, yMax);
    }

    @Override
    public List<GraphNode> generateNodes(int numNodes) {
        List<GraphNode> result = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++)
            result.add(new SimpleGraphNode(RANDOM.nextDouble() * xMax, RANDOM.nextDouble() * yMax));
        return result;
    }
}
