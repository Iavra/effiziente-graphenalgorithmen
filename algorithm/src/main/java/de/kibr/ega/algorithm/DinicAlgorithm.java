package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Graph;
import de.kibr.ega.core.graph.traversal.BreadthFirstSearchIterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DinicAlgorithm extends Algorithm {
    private final int[] level;

    DinicAlgorithm(Graph graph, int source, int sink) {
        super(graph, source, sink);
        level = new int[graph.size()];
    }

    @Override
    protected boolean doUpdate() {
        if (!buildLevelGraph()) return true;
        // used to skip dead ends after backtracking
        int[] index = new int[graph.size()];
        int flow;
        do {
            flow = augmentBlockingFlow(source, Integer.MAX_VALUE, index);
            maxFlow += flow;
        } while (flow > 0);
        return false;
    }

    private boolean buildLevelGraph() {
        Arrays.fill(level, -1);
        level[source] = 0;
        for (Iterator<Edge> it = new BreadthFirstSearchIterator(graph, source); it.hasNext(); ) {
            Edge edge = it.next();
            level[edge.to()] = level[edge.from()] + 1;
        }
        // does specifically not allow that source == sink and keeps maxFlow = 0 in that case
        return level[sink] > 0;
    }

    // TODO: this might be doable with the iterator, but i will need to add a Predicate for the level
    private int augmentBlockingFlow(int node, int flow, int[] index) {
        if (sink == node) return flow;
        for (List<Edge> edges = graph.adjacent(node); index[node] < edges.size(); index[node]++) {
            Edge edge = edges.get(index[node]);
            int capacity = edge.residualCapacity();
            if (capacity > 0 && level[edge.to()] - level[node] == 1) {
                int delta = augmentBlockingFlow(edge.to(), Math.min(flow, capacity), index);
                if (delta > 0) {
                    edge.addResidualFlow(delta);
                    return delta;
                }
            }
        }
        return 0;
    }
}
