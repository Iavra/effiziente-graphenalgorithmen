package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;

public abstract class BaseAlgorithm implements Algorithm {
    int maxFlow = 0;
    final int source;
    final int sink;

    BaseAlgorithm(Graph graph) {
        this.source = graph.source();
        this.sink = graph.sink();
    }

    @Override
    public boolean update() {
        if (source == sink) return true;
        return doUpdate();
    }

    abstract boolean doUpdate();

    @Override
    public int maxFlow() {
        return maxFlow;
    }

    int[][] toResidualGraph(Graph graph) {
        int size = graph.size();
        int[][] matrix = new int[size][size];
        for (int v = 0; v < size; v++)
            for (int w = 0; w < size; w++)
                matrix[v][w] = graph.capacity(v, w);
        return matrix;
    }
}
