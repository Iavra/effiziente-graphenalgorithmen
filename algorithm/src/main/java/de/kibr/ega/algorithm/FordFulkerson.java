package de.kibr.ega.algorithm;

import de.kibr.ega.algorithm.util.GraphTraversal;
import de.kibr.ega.algorithm.util.ResidualGraph;
import de.kibr.ega.core.graph.Graph;

/**
 * Max Flow algorithm using Depth First Search
 */
public class FordFulkerson {
    public int maxFlow(Graph graph) {
        ResidualGraph residualGraph = new ResidualGraph(graph);
        int maxFlow = 0;
        int[] path = new int[graph.size()];
        while (traverseGraph(residualGraph, path)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int w = graph.sink(); w != graph.source(); w = path[w]) {
                int v = path[w];
                pathFlow = Math.min(pathFlow, residualGraph.capacity(v, w));
            }
            for (int w = graph.sink(); w != graph.source(); w = path[w]) {
                int v = path[w];
                residualGraph.updateCapacity(v, w, -pathFlow);
                residualGraph.updateCapacity(w, v, pathFlow);
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    protected boolean traverseGraph(Graph graph, int[] path) {
        return GraphTraversal.depthFirst(graph, path);
    }
}
