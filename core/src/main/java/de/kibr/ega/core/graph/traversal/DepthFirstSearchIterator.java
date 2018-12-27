package de.kibr.ega.core.graph.traversal;

import de.kibr.ega.core.graph.Graph;
import de.kibr.ega.core.graph.Edge;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class DepthFirstSearchIterator implements Iterator<Edge> {
    private final Graph graph;
    private final boolean[] visited;
    private final Deque<Iterator<Edge>> stack = new LinkedList<>();
    private Edge next;

    public DepthFirstSearchIterator(Graph graph, int start) {
        this.graph = graph;
        visited = new boolean[graph.size()];
        visited[start] = true;
        stack.push(graph.adjacent(start).iterator());
        advance();
    }

    @Override
    public Edge next() {
        if (!hasNext()) throw new NoSuchElementException();
        try {
            visited[next.to()] = true;
            return next;
        } finally {
            advance();
        }
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    private void advance() {
        Iterator<Edge> edges = stack.getFirst();
        int nextNode;
        do {
            while (!edges.hasNext()) {
                stack.pop();
                if (stack.isEmpty()) {
                    next = null;
                    return;
                }
                edges = stack.getFirst();
            }
            next = edges.next();
            nextNode = next.to();
        } while (visited[nextNode] || next.residualCapacity() <= 0);
        stack.push(graph.adjacent(nextNode).iterator());
    }
}
