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
        DirectedEdge edge = new DirectedEdge(validateNode(from), validateNode(to), capacity);
        adj.get(from).add(edge);
        adj.get(to).add(edge.reverse());
    }

    public List<Edge> adjacent(int node) {
        return adj.get(validateNode(node));
    }

    public int validateNode(int node) {
        if (node < 0 || node >= size)
            throw new IllegalArgumentException("node not in graph: " + node);
        return node;
    }

    private class DirectedEdge implements Edge {
        private final int from;
        private final int to;
        private final int capacity;
        private int flow = 0;

        DirectedEdge(int from, int to, int capacity) {
            if (from == to) throw new IllegalArgumentException("nodes must not be identical");
            if (capacity < 0) throw new IllegalArgumentException("capacity must not be negative");
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
        public int residualCapacityTo(int node) {
            if (from == node) return flow;
            if (to == node) return capacity - flow;
            throw new IllegalArgumentException("node not in edge: " + node);
        }

        @Override
        public void addResidualFlowTo(int node, int delta) {
            if (delta < 0) throw new IllegalArgumentException("delta must be non-negative");
            if (from == node) flow -= delta;
            else if (to == node) flow += delta;
            else throw new IllegalArgumentException("node not in edge: " + node);
            if (flow < 0) throw new IllegalArgumentException("resulting flow is negative");
            if (flow > capacity) throw new IllegalArgumentException("resulting flow exceeds capacity");
        }

        @Override
        public Edge reverse() {
            return new ReversedEdge(this);
        }
    }

    private class ReversedEdge implements Edge {
        private final Edge parent;

        private ReversedEdge(Edge parent) {
            this.parent = parent;
        }

        @Override
        public int from() {
            return parent.to();
        }

        @Override
        public int to() {
            return parent.from();
        }

        @Override
        public int residualCapacityTo(int node) {
            return parent.residualCapacityTo(node);
        }

        @Override
        public void addResidualFlowTo(int node, int delta) {
            parent.addResidualFlowTo(node, delta);
        }

        @Override
        public Edge reverse() {
            return parent;
        }
    }
}
