package de.kibr.ega.algorithm;

import de.kibr.ega.algorithm.util.GraphTraversal;
import de.kibr.ega.algorithm.util.GraphUtil;
import de.kibr.ega.core.graph.Graph;

/**
 * Max Flow algorithm using Depth First Search
 */
public class FordFulkerson {
    public int maxFlow(Graph graph) {
        int[][] residualGraph = GraphUtil.toResidualGraph(graph);
        int maxFlow = 0;
        int source = graph.source();
        int sink = graph.sink();
        int[] path = new int[graph.size()];
        while (traverseGraph(residualGraph, source, sink, path)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int w = sink; w != source; w = path[w]) {
                int v = path[w];
                pathFlow = Math.min(pathFlow, residualGraph[v][w]);
            }
            for (int w = sink; w != source; w = path[w]) {
                int v = path[w];
                residualGraph[v][w] -= pathFlow;
                residualGraph[w][v] += pathFlow;
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    protected boolean traverseGraph(int[][] graph, int source, int sink, int[] path) {
        return GraphTraversal.depthFirst(graph, source, sink, path);
    }
}
