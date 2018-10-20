package de.kibr.ega.graph;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<GraphEdge> getEdgesFrom(GraphNode node) {
        return edges.stream()
                .filter(e -> e.getSource().equals(node)).collect(Collectors.toList());
    }

    public List<GraphEdge> getEdgesTo(GraphNode node) {
        return edges.stream()
                .filter(e -> e.getTarget().equals(node)).collect(Collectors.toList());
    }

    public Optional<GraphEdge> getEdge(GraphNode source, GraphNode target) {
        return edges.stream()
                .filter(e -> e.getSource().equals(source) && e.getTarget().equals(target))
                .findFirst();
    }

    public GraphNode getSource() {
        return null; // TODO
    }

    public GraphNode getSink() {
        return null; // TODO
    }
}
