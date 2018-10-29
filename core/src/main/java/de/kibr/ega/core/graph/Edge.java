package de.kibr.ega.core.graph;

/**
 * Edge class inspired by: https://algs4.cs.princeton.edu/64maxflow/FlowEdge.java.html
 */
public class Edge {
    private final int v;
    private final int w;
    private final int capacity;
    private int flow = 0;

    public Edge(int v, int w, int capacity) {
        if (v < 0) throw new IllegalArgumentException("node v must be non-negative");
        if (w < 0) throw new IllegalArgumentException("node w must be non-negative");
        this.v = v;
        this.w = w;
        this.capacity = capacity;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public int other(int node) {
        if (node == v) return w;
        if (node == w) return v;
        throw new IllegalArgumentException("node not part of this edge: " + node);
    }

    public int residualCapacityTo(int node) {
        if (node == v) return flow;
        if (node == w) return capacity - flow;
        throw new IllegalArgumentException("node not part of this edge: " + node);
    }

    public void addResidualFlowTo(int node, int flow) {
        if      (node == v) this.flow -= flow;           // backward edge
        else if (node == w) this.flow += flow;           // forward edge
        else throw new IllegalArgumentException("node not part of this edge: " + node);
        if (this.flow < 0)        throw new IllegalArgumentException("flow is negative");
        if (this.flow > capacity) throw new IllegalArgumentException("flow exceeds capacity");
    }

    void resetResidualFlow() {
        flow = 0;
    }
}
