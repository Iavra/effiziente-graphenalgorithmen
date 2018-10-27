package de.kibr.ega.generator.node;

import de.kibr.ega.core.graph.Node;
import de.kibr.ega.generator.graph.SimpleNode;

import java.util.ArrayList;
import java.util.List;

public class RandomNodeGenerator extends BaseNodeGenerator {
    public RandomNodeGenerator(double xMax, double yMax) {
        super(xMax, yMax);
    }

    @Override
    public List<Node> generateNodes(int numNodes) {
        List<Node> result = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++)
            result.add(new SimpleNode(RANDOM.nextDouble() * xMax, RANDOM.nextDouble() * yMax));
        return result;
    }
}
