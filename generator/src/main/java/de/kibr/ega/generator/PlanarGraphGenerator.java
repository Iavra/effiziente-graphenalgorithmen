package de.kibr.ega.generator;

import de.kibr.ega.generator.triangulate.GraphTriangulator;
import de.kibr.ega.graph.Graph;
import de.kibr.ega.graph.GraphEdge;
import de.kibr.ega.graph.GraphNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PlanarGraphGenerator {
    private Supplier<String> idBuilder;
    private GraphTriangulator triangulator;

    private final int size;
    private final int maxCapacity;

    public PlanarGraphGenerator(int size, int maxCapacity) {
        if (size < 0) throw new IllegalArgumentException("size must be non-negative");
        if (maxCapacity < 0) throw new IllegalArgumentException("max capacity must be non-negative");
        this.size = size;
        this.maxCapacity = maxCapacity;
    }

    public Graph generateGraph() {
        List<GraphNode> nodes = generateGraphNodes();
        List<GraphEdge> edges = triangulator.triangulateEdges(nodes);
        // TODO determine start/sink and add capacities (weights)
        return new Graph(nodes, edges);
    }

    private List<GraphNode> generateGraphNodes() {
        List<GraphNode> nodes = new ArrayList<>();
        for (int i = 0; i < size; i++)
            nodes.add(new GraphNode(idBuilder.get(), Math.random() * 100, Math.random() * 100));
        return nodes;
    }
}
