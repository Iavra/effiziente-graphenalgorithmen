package de.kibr.ega.generator.graph;

import de.kibr.ega.core.graph.Edge;

/**
 * Edge class inspired by: https://algs4.cs.princeton.edu/64maxflow/FlowEdge.java.html
 */
public class SimpleEdge implements Edge {
    private final int from;
    private final int to;
    private final int capacity;
    private int flow = 0;

    public SimpleEdge(int from, int to, int capacity) {
        if (from < 0) throw new IllegalArgumentException("node 'from' must be non-negative");
        if (to < 0) throw new IllegalArgumentException("node 'to' must be non-negative");
        this.from = from;
        this.to = to;
        this.capacity = capacity;
    }

    @Override
    public int from() {
        return from;
    }

    @Override
    public int to() {
        return to;
    }

    @Override
    public int other(int node) {
        if (node == from) return to;
        if (node == to) return from;

        throw new IllegalArgumentException("node not part of this edge: " + node);
    }

    @Override
    public int residualCapacityTo(int node) {
        if (node == from) return flow;
        if (node == to) return capacity - flow;

        throw new IllegalArgumentException("node not part of this edge: " + node);
    }

    @Override
    public void addResidualFlowTo(int node, int flow) {
        if      (node == from) this.flow -= flow;         // backward edge
        else if (node == to) this.flow += flow;           // forward edge

        else throw new IllegalArgumentException("node not part of this edge: " + node);

        if (this.flow < 0)        throw new IllegalArgumentException("flow is negative");
        if (this.flow > capacity) throw new IllegalArgumentException("flow exceeds capacity");
    }
}
