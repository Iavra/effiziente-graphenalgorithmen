package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Graph;
import de.kibr.ega.core.graph.traversal.BreadthFirstSearch;

import java.util.Iterator;

public class Dinic extends Algorithm {
    private final int[] level;

    Dinic(Graph graph, int source, int sink) {
        super(graph, source, sink);
        level = new int[graph.size()];
    }

    @Override
    public StepResult update() {
        Edge[] path = findAugmentingPath();
        if (path[sink] != null) {
            int flow;
            do {
                flow = sendFlow(source, sink, Integer.MAX_VALUE);
                maxFlow += flow;
            } while (flow > 0);
            return null; // TODO return alg update
        }
        return StepResult.FINISHED;
    }

    private int sendFlow(int start, int end, int delta) {
        if (start == end) return delta;
        for(Edge edge : graph.adjacent(start)) {
            if (level[edge.to()] - level[edge.from()] == 1 && edge.residualCapacityTo(edge.to()) > 0) {
                delta = Math.min(delta, edge.residualCapacityTo(edge.to()));
                int tempFlow = sendFlow(edge.to(), end, delta);
                if (tempFlow > 0) {
                    edge.addResidualFlowTo(edge.to(), tempFlow);
                    return tempFlow;
                }
            }
        }
        return 0;
    }

    private Edge[] findAugmentingPath() {
        Edge[] path = new Edge[graph.size()];
        for (int i = 0; i < level.length; i++) level[i] = -1;
        level[source] = 0;
        Iterator<Edge> edgeIterator = new BreadthFirstSearch(graph, source);
        while(edgeIterator.hasNext()) {
            Edge edge = edgeIterator.next();
            level[edge.to()] = level[edge.from()] + 1;
            path[edge.to()] = edge;
        }
        return path;
    }
}
