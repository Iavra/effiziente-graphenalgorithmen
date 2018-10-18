package de.kibr.ega.generator;

import de.kibr.ega.core.graph.Graph;
import de.kibr.ega.core.graph.GraphNode;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlanarGraphGenerator {

    private final int size;
    private final int maxCapacity;

    public PlanarGraphGenerator(int size, int maxCapacity) {
        if (size < 0) throw new IllegalArgumentException("size must be non-negative");
        if (maxCapacity < 0) throw new IllegalArgumentException("max capacity must be non-negative");
        this.size = size;
        this.maxCapacity = maxCapacity;
    }

    public Graph generateGraph() {
        Graph graph = new Graph();
        generateGraphNodes(graph);
        generateGraphEdges(graph);
        // TODO determine start/sink and add capacities (weights)
        return graph;
    }

    private void generateGraphNodes(Graph graph) {
        for (int i = 0; i < size; i++)
            graph.addNode(new GraphNode("A", Math.random() * 100, Math.random() * 100));
    }

    private void generateGraphEdges(Graph graph) {
        // TODO use Delaunay triangulation
        List<GraphNode> sortedNodes = graph.getNodes().stream().sorted(GraphNode.BY_POSITION).collect(Collectors.toList());
    }

    private List<GraphNode> sortByPosition(List<GraphNode> nodes) {
        return nodes.stream().sorted(GraphNode.BY_POSITION).collect(Collectors.toList());
    }

    private List<List<GraphNode>> divideIntoSimpleSets(List<GraphNode> nodes) {

    }
}
