package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Graph;
import de.kibr.ega.core.graph.traversal.BreadthFirstSearch;
import de.kibr.ega.core.graph.traversal.DepthFirstSearch;

import java.util.Iterator;

public class EdmondsKarp extends Algorithm {
    public EdmondsKarp(Graph graph, int source, int sink) {
        super(graph, source, sink);
    }

    @Override
    public StepResult update() {
        Edge[] path = findAugmentingPath();
        if (path[sink] != null) {
            int delta = Integer.MAX_VALUE;
            for (int i = sink; i != source; i = path[i].from()) {
                delta = Math.min(delta, path[i].residualCapacityTo(i));
            }
            for (int i = sink; i != source; i = path[i].from()) {
                path[i].addResidualFlowTo(i, delta);
            }
            maxFlow += delta;
            return null; // TODO: algo update
        }
        return StepResult.FINISHED;
    }

    private Edge[] findAugmentingPath() {
        Edge[] path = new Edge[graph.size()];
        Iterator<Edge> edgeIterator = new BreadthFirstSearch(graph, source);
        while(edgeIterator.hasNext()) {
            Edge edge = edgeIterator.next();
            path[edge.to()] = edge;
        }
        return path;
    }
}
