package de.kibr.ega.algorithm;

import de.kibr.ega.algorithm.util.GraphTraversal;
import de.kibr.ega.core.graph.Graph;

public class EdmondsKarp extends FordFulkerson {
    @Override
    protected boolean traverseGraph(int[][] graph, int source, int sink, int[] path) {
        return GraphTraversal.breadthFirst(graph, source, sink, path);
    }
}
