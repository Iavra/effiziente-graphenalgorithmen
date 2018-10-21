package de.kibr.ega.generator.node;

import de.kibr.ega.graph.GraphNode;

import java.util.ArrayList;
import java.util.List;

public class GridNodeGenerator extends BaseNodeGenerator {
    public GridNodeGenerator(double xMax, double yMax) {
        super(xMax, yMax);
    }

    @Override
    public List<GraphNode> generateNodes(int size) {
        List<GraphNode> nodes = new ArrayList<>();
        int grid = (int) Math.ceil(Math.sqrt(size));
        for (int i = 0; i < size; i++)
            nodes.add(new GraphNode(
                    (xMax / grid) * (i % grid),
                    (yMax / grid) * (i / grid)));
        return nodes;
    }
}
