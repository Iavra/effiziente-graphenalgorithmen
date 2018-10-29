package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Graph;

import java.util.Deque;
import java.util.LinkedList;

public class FordFulkersonAlgorithm extends Algorithm {
    public FordFulkersonAlgorithm(Graph graph) {
        super(graph);
    }

    @Override
    boolean doUpdate() {
        Edge[] path = new Edge[v];
        if (traverseGraph(path)) {
            int capacity = getLowestCapacity(path);
            augmentFlow(path, capacity);
            maxFlow += capacity;
            return false;
        } else
            return true;
    }

    /**
     * DFS using a stack to traverse the graph
     */
    protected boolean traverseGraph(Edge[] path) {
        Deque<Integer> stack = new LinkedList<>();
        boolean[] visited = new boolean[v];

        stack.push(s);
        visited[s] = true;

        while (!stack.isEmpty() && !visited[t]) {
            int current = stack.pop();
            for (Edge edge : graph.adj(current)) {
                int node = edge.other(current);
                if (!visited[node] && edge.residualCapacityTo(node) > 0) {
                    path[node] = edge;
                    visited[node] = true;
                    stack.push(node);
                }
            }
        }

        return visited[t];
    }

    private int getLowestCapacity(Edge[] path) {
        int capacity = Integer.MAX_VALUE;
        for (int v = t; v != s; v = path[v].other(v))
            capacity = Math.min(capacity, path[v].residualCapacityTo(v));
        return capacity;
    }

    private void augmentFlow(Edge[] path, int flow) {
        for (int v = t; v != s; v = path[v].other(v))
            path[v].addResidualFlowTo(v, flow);
    }
}
