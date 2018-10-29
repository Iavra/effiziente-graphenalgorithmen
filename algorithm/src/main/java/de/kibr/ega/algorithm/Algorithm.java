package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;

public abstract class Algorithm {
    final Graph graph;
    final int v;
    final int s;
    final int t;

    private boolean done = false;
    int maxFlow = 0;

    Algorithm(Graph graph) {
        this.graph = graph;
        v = graph.v();
        s = graph.s();
        t = graph.t();
        if (s == t) done = true;
    }

    public int maxFlow() {
        return maxFlow;
    }

    boolean update() {
        if (done) return true;
        done = doUpdate();
        return done;
    }

    abstract boolean doUpdate();
}
