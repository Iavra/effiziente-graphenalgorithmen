package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;

import java.util.Objects;

public abstract class Algorithm {
    final Graph graph;
    final int source;
    final int sink;
    private boolean finished = false;

    int maxFlow = 0;

    Algorithm(Graph graph, int source, int sink) {
        this.graph = Objects.requireNonNull(graph);
        this.source = graph.validateNode(source);
        this.sink = graph.validateNode(sink);
    }

    public void update() {
        if (!finished) finished = doUpdate();
    }

    public int maxFlow() {
        return maxFlow;
    }

    public boolean finished() {
        return finished;
    }

    protected abstract boolean doUpdate();
}
