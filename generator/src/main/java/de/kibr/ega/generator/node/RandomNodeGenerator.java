package de.kibr.ega.generator.node;

import de.kibr.ega.core.graph.GraphNode;
import de.kibr.ega.generator.graph.SimpleGraphNode;

public class RandomNodeGenerator extends BaseNodeGenerator {
    public RandomNodeGenerator(double xMax, double yMax) {
        super(xMax, yMax);
    }

    @Override
    public GraphNode[] generateNodes(int numNodes) {
        GraphNode[] result = new GraphNode[numNodes];
        for (int i = 0; i < numNodes; i++)
            result[i] = new SimpleGraphNode(RANDOM.nextDouble() * xMax, RANDOM.nextDouble() * yMax);
        return result;
    }
}
