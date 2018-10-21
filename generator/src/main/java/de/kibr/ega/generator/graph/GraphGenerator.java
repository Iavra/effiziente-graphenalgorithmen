package de.kibr.ega.generator.graph;

import de.kibr.ega.core.graph.Graph;
import de.kibr.ega.core.graph.Position;
import de.kibr.ega.generator.node.NodeGenerator;

public class GraphGenerator {
    private final NodeGenerator nodeGenerator;

    public GraphGenerator(NodeGenerator nodeGenerator) {
        this.nodeGenerator = nodeGenerator;
    }

    public Graph generateGraph(int numNodes, int maxCapacity) {
        Position[] nodes = nodeGenerator.generateNodes(numNodes);
        // TODO: It might be a lot easier to just use actual classes for nodes/edges, at least during creation
        // TODO: delegate to sub generators. Also, it's probably really better to initialize max x/y in nodeGen constructor
        return null;
    }
}
