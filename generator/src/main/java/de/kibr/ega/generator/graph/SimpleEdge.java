package de.kibr.ega.generator.graph;

import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Node;

public class SimpleEdge implements Edge {
    private final Node tail;
    private final Node head;
    private final int capacity;

    public SimpleEdge(Node tail, Node head, int capacity) {
        this.tail = tail;
        this.head = head;
        this.capacity = capacity;
    }

    @Override
    public Node tail() {
        return tail;
    }

    @Override
    public Node head() {
        return head;
    }

    @Override
    public int capacity() {
        return capacity;
    }
}
