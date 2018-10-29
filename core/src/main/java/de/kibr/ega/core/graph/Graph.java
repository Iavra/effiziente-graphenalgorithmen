package de.kibr.ega.core.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Graph {
    private final int size;
    private final int source;
    private final int sink;
    private final List<List<Edge>> adjacent;

    public Graph(int size, int source, int sink) {
        if (size < 0) throw new IllegalArgumentException("graph size must be non-negative");
        this.size = size;
        this.source = validate(source);
        this.sink = validate(sink);
        adjacent = IntStream.range(0, size)
                .mapToObj(i -> new LinkedList<Edge>())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public int size() {
        return size;
    }

    public int source() {
        return source;
    }

    public int sink() {
        return sink;
    }

    public List<Edge> adjacent(int node) {
        return adjacent.get(validate(node));
    }

    public void addEdge(int from, int to, int capacity) {
        addEdge(new Edge(from, to, capacity));
    }

    private void addEdge(Edge edge) {
        validate(edge.from());
        validate(edge.to());
        adjacent.get(edge.from()).add(edge);
        adjacent.get(edge.to()).add(edge);
    }

    public void resetResidualFlow() {
        adjacent.forEach(e -> e.forEach(Edge::resetResidualFlow));
    }

    private int validate(int node) {
        if (node < 0 || node >= size)
            throw new IllegalArgumentException("node outside of valid range [0, " + size + "[: " + node);
        return node;
    }
}
