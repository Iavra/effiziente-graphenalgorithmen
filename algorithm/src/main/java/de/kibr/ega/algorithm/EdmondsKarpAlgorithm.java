package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Flooding available paths using BFS
 */
public class EdmondsKarpAlgorithm extends FordFulkersonAlgorithm {
    public EdmondsKarpAlgorithm(Graph graph) {
        super(graph);
    }

    /**
     * BFS: Explores all direct neighbors of a node, starting from s, and follows all
     * available paths in parallel, until they all end or t has been found.
     */
    @Override
    protected boolean findPath(int[] path) {
        int size = rGraph.length;
        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new LinkedList<>();

        visited[source] = true;
        queue.add(source);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor = 0; neighbor < size; neighbor++) {
                if (!visited[neighbor] && rGraph[current][neighbor] > 0) {
                    path[neighbor] = current;
                    if (neighbor == sink) return true;
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return false;
    }
}
