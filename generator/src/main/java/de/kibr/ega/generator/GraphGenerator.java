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

    public GraphGenerator(NodeGenerator nodeGenerator, EdgeGenerator edgeGenerator, CapacityGenerator capacityGenerator) {
        this.nodeGenerator = nodeGenerator;
        this.edgeGenerator = edgeGenerator;
        this.capacityGenerator = capacityGenerator;
    }

    public Graph generateGraph(int size) {
        if (size < 0) throw new IllegalArgumentException("size must be non-negative");
        List<GraphNode> nodes = nodeGenerator.generateNodes(size);
        List<GraphEdge> edges = edgeGenerator.generateEdges(nodes);
        edges.addAll(edges.stream().map(GraphEdge::flip).collect(Collectors.toList()));
        // TODO determine start/sink
        Graph graph = new Graph(nodes, edges);
        capacityGenerator.setCapacities(graph);
        return graph;
    }
}
