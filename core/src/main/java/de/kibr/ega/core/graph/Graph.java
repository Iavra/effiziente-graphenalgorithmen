package de.kibr.ega.core.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Graph {
    private final int v;
    private final int s;
    private final int t;
    private final List<List<Edge>> adj;

    public Graph(int v, int s, int t) {
        if (v < 0) throw new IllegalArgumentException("v (number of nodes) must not be negative");
        this.v = v;
        this.s = validate(s);
        this.t = validate(t);
        adj = IntStream.range(0, v)
                .mapToObj(i -> new LinkedList<Edge>())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public int v() {
        return v;
    }

    public int s() {
        return s;
    }

    public int t() {
        return t;
    }

    public List<Edge> adj(int node) {
        return adj.get(validate(node));
    }

    public void addEdge(int from, int to, int capacity) {
        addEdge(new Edge(from, to, capacity));
    }

    public void addEdge(Edge edge) {
        validate(edge.from());
        validate(edge.to());
        adj.get(edge.from()).add(edge);
        adj.get(edge.to()).add(edge);
    }

    public void resetResidualFlow() {
        adj.forEach(e -> e.forEach(Edge::resetResidualFlow));
    }

    private int validate(int node) {
        if (node < 0 || node >= v)
            throw new IllegalArgumentException("node outside of valid range [0, " + v + "[: " + node);
        return node;
    }
}
