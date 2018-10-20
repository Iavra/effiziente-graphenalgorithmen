package de.kibr.ega.generator.node;

import de.kibr.ega.graph.GraphNode;

import java.util.ArrayList;
import java.util.List;

public class GridNodeGenerator implements NodeGenerator {
    @Override
    public List<GraphNode> generateNodes(int size, double maxWidth, double maxHeight) {
        List<GraphNode> nodes = new ArrayList<>();
        int grid = (int) Math.ceil(Math.sqrt(size));
        for (int i = 0; i < size; i++)
            nodes.add(new GraphNode(
                    (maxWidth / grid) * (i % grid),
                    (maxHeight / grid) * (i / grid)));
        return nodes;
    }
}
