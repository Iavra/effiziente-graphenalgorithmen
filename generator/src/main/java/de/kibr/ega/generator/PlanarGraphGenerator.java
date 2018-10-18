package de.kibr.ega.generator;

import de.kibr.ega.core.graph.GraphEdge;
import de.kibr.ega.core.graph.GraphNode;
import org.jgrapht.Graph;
import org.jgrapht.generate.GraphGenerator;

import java.util.Comparator;
import java.util.Map;

public class PlanarGraphGenerator implements GraphGenerator<GraphNode, GraphEdge, GraphNode> {
    public static final String SOURCE = "s";
    public static final String SINK = "t";

    private final int size;
    private final int maxCapacity;

    public PlanarGraphGenerator(int size, int maxCapacity) {
        if (size < 0) throw new IllegalArgumentException("size must be non-negative");
        if (maxCapacity < 0) throw new IllegalArgumentException("max capacity must be non-negative");
        this.size = size;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void generateGraph(Graph<GraphNode, GraphEdge> target, Map<String, GraphNode> resultMap) {
        populateGraphWithNodesAndAllPossibleEdges(target);
        removeNonPlanarEdges(target);
        // TODO determine start/sink and add capacities (weights)
    }

    private void populateGraphWithNodesAndAllPossibleEdges(Graph<GraphNode, GraphEdge> target) {
        GraphNode previousNode = null;
        for (int i = 0; i < size; i++) {
            GraphNode currentNode = target.addVertex();
            if (previousNode != null) {
                target.addEdge(previousNode, currentNode);
                target.addEdge(currentNode, previousNode);
            }
            previousNode = currentNode;
        }
    }

    private void removeNonPlanarEdges(Graph<GraphNode, GraphEdge> target) {
        target.edgeSet().stream()
                .sorted(Comparator.comparing(GraphEdge::length).reversed())
                .filter(e -> target.edgeSet().stream().anyMatch(e::intersects))
                .forEach(target::removeEdge);
    }
}
