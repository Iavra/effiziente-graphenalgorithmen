package de.kibr.ega.algorithm.util;

import de.kibr.ega.core.graph.Graph;

import java.util.LinkedList;
import java.util.List;

public class ResidualGraph implements Graph {
    private final Graph original;
    private final int size;
    private final int[][] capacities;

    public ResidualGraph(Graph graph) {
        original = graph;
        size = graph.size();
        capacities = new int[size][size];
        for (int v = 0; v < size; v++)
            for (int w = 0; w < size; w++)
                capacities[v][w] = graph.capacity(v, w);
    }

    public void updateCapacity(int from, int to, int capacity) {
        capacities[from][to] += capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int source() {
        return original.source();
    }

    @Override
    public int sink() {
        return original.sink();
    }

    @Override
    public List<Integer> adjacent(int node) {
        return original.adjacent(node);
    }

    @Override
    public int capacity(int from, int to) {
        return capacities[from][to];
    }
}
