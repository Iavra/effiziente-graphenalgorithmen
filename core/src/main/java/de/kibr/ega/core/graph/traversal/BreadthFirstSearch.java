package de.kibr.ega.core.graph.traversal;

import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Graph;

import java.util.*;

public class BreadthFirstSearch implements Iterator<Edge> {
    private final Graph graph;
    private final boolean[] visited;
    private final Queue<Edge> queue = new LinkedList<>();

    public BreadthFirstSearch(Graph graph, int start) {
        this.graph = Objects.requireNonNull(graph);
        visited = new boolean[graph.size()];
        visited[start] = true;
        addEdgesFrom(start);
    }

    @Override
    public Edge next() {
        if (!hasNext()) throw new NoSuchElementException();
        Edge edge = queue.remove();
        addEdgesFrom(edge.to());
        return edge;
    }

    private void addEdgesFrom(int node) {
        graph.adjacent(node).forEach(edge -> {
            int nextNode = edge.to();
            if (!visited[nextNode] && edge.residualCapacityTo(nextNode) > 0) {
                visited[nextNode] = true;
                queue.add(edge);
            }
        });
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
