package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class EdmondsKarpAlgorithm extends FordFulkersonAlgorithm {
    public EdmondsKarpAlgorithm(Graph graph) {
        super(graph);
    }

    /**
     * BFS using a queue to traverse the graph
     */
    @Override
    protected boolean traverseGraph(Edge[] path) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[v];

        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty() && !visited[t]) {
            int current = queue.poll();
            for (Edge edge : graph.adjacent(current)) {
                int node = edge.other(current);
                if (!visited[node] && edge.residualCapacityTo(node) > 0) {
                    path[node] = edge;
                    visited[node] = true;
                    queue.add(node);
                }
            }
        }

        return visited[t];
    }
}
