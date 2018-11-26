package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;

import java.util.Objects;

public abstract class Algorithm<T extends Algorithm.StepResult> {
    protected final Graph graph;
    final int source;
    final int sink;

    int maxFlow = 0;

    Algorithm(Graph graph, int source, int sink) {
        this.graph = Objects.requireNonNull(graph);
        this.source = graph.validateNode(source);
        this.sink = graph.validateNode(sink);
    }

    int maxFlow() {
        return maxFlow;
    }

    public abstract T update();

    interface StepResult {
        StepResult FINISHED = new StepResult() {};
    }
}
