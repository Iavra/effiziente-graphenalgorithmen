package de.kibr.ega.core.graph;

public class Edge {
    final int from;
    final int to;
    final int capacity;
    int flow = 0;

    Edge reverse;

    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public int residualCapacity() {
        return capacity - flow;
    }

    public void addResidualFlow(int delta) {
        flow += delta;
        reverse.flow -= delta;
    }
}
