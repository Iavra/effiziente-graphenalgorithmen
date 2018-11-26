package de.kibr.ega.core.graph.traversal;

import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Graph;

import java.util.*;

public class DepthFirstSearch implements Iterator<Edge> {
    private final Graph graph;
    private final boolean[] visited;
    private final Deque<Iterator<Edge>> stack = new LinkedList<>();
    private Edge next;

    public DepthFirstSearch(Graph graph, int start) {
        this.graph = Objects.requireNonNull(graph);
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
        } while (visited[nextNode] || next.residualCapacityTo(nextNode) <= 0);
        stack.push(graph.adjacent(nextNode).iterator());
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
}
