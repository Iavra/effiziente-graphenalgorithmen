package de.kibr.ega.generator;

import de.kibr.ega.generator.capacity.CapacityGenerator;
import de.kibr.ega.generator.edge.EdgeGenerator;
import de.kibr.ega.generator.node.NodeGenerator;
import de.kibr.ega.graph.Graph;
import de.kibr.ega.graph.GraphEdge;
import de.kibr.ega.graph.GraphNode;

import java.util.List;
import java.util.stream.Collectors;

public class GraphGenerator {
    private final NodeGenerator nodeGenerator;
    private final EdgeGenerator edgeGenerator;
    private final CapacityGenerator capacityGenerator;

    private final double maxWidth;
    private final double maxHeight;

    private final int size;
    private final int maxCapacity;

    public GraphGenerator(
            NodeGenerator nodeGenerator,
            EdgeGenerator edgeGenerator,
            CapacityGenerator capacityGenerator,
            double maxWidth, double maxHeight,
            int size, int maxCapacity) {
        this.nodeGenerator = nodeGenerator;
        this.edgeGenerator = edgeGenerator;
        this.capacityGenerator = capacityGenerator;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        if (size < 0) throw new IllegalArgumentException("size must be non-negative");
        if (maxCapacity < 0) throw new IllegalArgumentException("max capacity must be non-negative");
        this.size = size;
        this.maxCapacity = maxCapacity;
    }

    public Graph generateGraph() {
        List<GraphNode> nodes = nodeGenerator.generateNodes(size, maxWidth, maxHeight);
        List<GraphEdge> edges = edgeGenerator.generateEdges(nodes);
        edges.addAll(edges.stream().map(GraphEdge::flip).collect(Collectors.toList()));
        // TODO determine start/sink
        Graph graph = new Graph(nodes, edges);
        capacityGenerator.setCapacities(graph, maxCapacity);
        return graph;
    }
}
