package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Flooding available paths using DFS
 */
public class FordFulkersonAlgorithm extends BaseAlgorithm {
    final int[][] rGraph;

    public FordFulkersonAlgorithm(Graph graph) {
        super(graph);
        this.rGraph = toResidualGraph(graph);
    }

    @Override
    public boolean doUpdate() {
        int[] path = new int[rGraph.length];
        if (findPath(path)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int w = sink; w != source; w = path[w]) {
                int v = path[w];
                pathFlow = Math.min(pathFlow, rGraph[v][w]);
            }
            for (int w = sink; w != source; w = path[w]) {
                int v = path[w];
                rGraph[v][w] -= pathFlow;
                rGraph[w][v] += pathFlow;
            }
            maxFlow += pathFlow;
            return false;
        }
        return true;
    }

    /**
     * DFS: Outgoing from s, follows each path to the end, backtracking afterwards,
     * either until t has been found or all available paths have been explored.
     */
    protected boolean findPath(int[] path) {
        int size = rGraph.length;
        boolean[] visited = new boolean[size];
        Deque<Integer> stack = new LinkedList<>();

        visited[source] = true;
        stack.push(source);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int neighbor = 0; neighbor < size; neighbor++) {
                if (!visited[neighbor] && rGraph[current][neighbor] > 0) {
                    path[neighbor] = current;
                    if (neighbor == sink) return true;
                    stack.push(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return false;
    }
}
