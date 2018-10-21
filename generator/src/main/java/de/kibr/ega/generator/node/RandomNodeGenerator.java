package de.kibr.ega.generator.node;

import de.kibr.ega.core.graph.Position;

public class RandomNodeGenerator extends BaseNodeGenerator {
    public RandomNodeGenerator(double xMax, double yMax) {
        super(xMax, yMax);
    }

    @Override
    public Position[] generateNodes(int numNodes) {
        Position[] result = new Position[numNodes];
        for (int i = 0; i < numNodes; i++)
            result[i] = new Position(RANDOM.nextDouble() * xMax, RANDOM.nextDouble() * yMax);
        return result;
    }
}
