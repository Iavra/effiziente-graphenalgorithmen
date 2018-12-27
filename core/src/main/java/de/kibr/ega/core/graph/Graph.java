package de.kibr.ega.core.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int size;
    private final List<List<Edge>> adj = new ArrayList<>();

    public Graph(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) adj.add(new ArrayList<>());
    }

    public int size() {
        return size;
    }

    public void addEdge(int from, int to, int capacity) {
        validateNode(from);
        validateNode(to);
        if (capacity < 0) throw new IllegalArgumentException("capacity must not be negative");
        Edge forward = new Edge(from, to, capacity);
        Edge backward = new Edge(to, from, 0);
        forward.reverse = backward;
        backward.reverse = forward;
        adj.get(from).add(forward);
        adj.get(to).add(backward);
    }

    public List<Edge> adjacent(int node) {
        return adj.get(validateNode(node));
    }

    public int validateNode(int node) {
        if (node < 0 || node >= size)
            throw new IllegalArgumentException("node not in graph: " + node);
        return node;
    }
}
