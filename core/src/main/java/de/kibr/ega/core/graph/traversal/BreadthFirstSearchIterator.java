package de.kibr.ega.core.graph.traversal;

import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Graph;

import java.util.*;

public class BreadthFirstSearchIterator implements Iterator<Edge> {
    private final Graph graph;
    private final boolean[] visited;
    private final Queue<Edge> queue = new LinkedList<>();

    public BreadthFirstSearchIterator(Graph graph, int start) {
        this.graph = Objects.requireNonNull(graph);
        visited = new boolean[graph.size()];
        addOutgoingEdges(start);
    }

    @Override
    public Edge next() {
        if (!hasNext()) throw new NoSuchElementException();
        Edge next = queue.remove();
        addOutgoingEdges(next.to());
        return next;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    private void addOutgoingEdges(int node) {
        visited[node] = true;
        for (Edge edge : graph.adjacent(node)) {
            int to = edge.to();
            if (!visited[to] && edge.residualCapacity() > 0) {
                visited[to] = true;
                queue.add(edge);
            }
        }
    }
}
