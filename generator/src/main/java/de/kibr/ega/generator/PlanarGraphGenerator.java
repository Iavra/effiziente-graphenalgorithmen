package de.kibr.ega.generator;

import de.kibr.ega.generator.edge.EdgeGenerator;
import de.kibr.ega.generator.node.NodeGenerator;
import de.kibr.ega.graph.Graph;
import de.kibr.ega.graph.GraphEdge;
import de.kibr.ega.graph.GraphNode;

import java.util.List;

public class PlanarGraphGenerator {
    private NodeGenerator nodeGenerator;
    private EdgeGenerator edgeGenerator;

    private final int size;
    private final int maxCapacity;

    public PlanarGraphGenerator(int size, int maxCapacity) {
        if (size < 0) throw new IllegalArgumentException("size must be non-negative");
        if (maxCapacity < 0) throw new IllegalArgumentException("max capacity must be non-negative");
        this.size = size;
        this.maxCapacity = maxCapacity;
    }

    public Graph generateGraph() {
        List<GraphNode> nodes = nodeGenerator.generateNodes(size);
        List<GraphEdge> edges = edgeGenerator.generateEdges(nodes);
        // TODO determine start/sink and add capacities (weights)
        return new Graph(nodes, edges);
    }
}
