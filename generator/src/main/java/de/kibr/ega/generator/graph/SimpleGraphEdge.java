package de.kibr.ega.generator.graph;

import de.kibr.ega.core.graph.GraphEdge;
import de.kibr.ega.core.graph.GraphNode;

public class SimpleGraphEdge implements GraphEdge {
    private final GraphNode tail;
    private final GraphNode head;
    private final int capacity;

    public SimpleGraphEdge(GraphNode tail, GraphNode head, int capacity) {
        this.tail = tail;
        this.head = head;
        this.capacity = capacity;
    }

    @Override
    public GraphNode tail() {
        return tail;
    }

    @Override
    public GraphNode head() {
        return head;
    }

    @Override
    public int capacity() {
        return capacity;
    }
}
