package de.kibr.ega.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<GraphNode> nodes;
    private Set<GraphEdge> edges;

    public Graph(Collection<GraphNode> nodes, Collection<GraphEdge> edges) {
        this.nodes = new HashSet<>(nodes);
        this.edges = new HashSet<>(edges);
    }

    public Graph() {
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
    }

    public void addNode(GraphNode node) {
        nodes.add(node);
    }

    public void addEdge(GraphEdge edge) {
        edges.add(edge);
    }

    public Set<GraphNode> getNodes() {
        return Collections.unmodifiableSet(nodes);
    }

    public Set<GraphEdge> getEdges() {
        return Collections.unmodifiableSet(edges);
    }
}
