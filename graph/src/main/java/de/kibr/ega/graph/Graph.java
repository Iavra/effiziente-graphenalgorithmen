package de.kibr.ega.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<GraphNode> nodes = new HashSet<>();
    private Set<GraphEdge> edges = new HashSet<>();

    public void addNode(GraphNode node) {
        nodes.add(node);
    }

    public void addEdge(GraphEdge edge) {
        edges.add(edge);
    }

    public Set<GraphNode> getNodes() {
        return Collections.unmodifiableSet(nodes);
    }
}
