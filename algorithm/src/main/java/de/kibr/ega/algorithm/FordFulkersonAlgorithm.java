package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Graph;
import de.kibr.ega.core.graph.traversal.DepthFirstSearchIterator;

import java.util.Iterator;

public class FordFulkersonAlgorithm extends Algorithm {
    FordFulkersonAlgorithm(Graph graph, int source, int sink) {
        super(graph, source, sink);
    }

    @Override
    protected boolean doUpdate() {
        Edge[] path = findAugmentingPath();
        if (path[sink] == null) return true;
        int delta = calculateBottleneck(path);
        augmentPath(path, delta);
        maxFlow += delta;
        return false;
    }

    private Edge[] findAugmentingPath() {
        Edge[] path = new Edge[graph.size()];
        for (Iterator<Edge> it = new DepthFirstSearchIterator(graph, source); it.hasNext(); ) {
            Edge edge = it.next();
            path[edge.to()] = edge;
        }
        return path;
    }

    private int calculateBottleneck(Edge[] path) {
        int flow = Integer.MAX_VALUE;
        for (Edge edge = path[sink]; edge != null; edge = path[edge.from()])
            flow = Math.min(flow, edge.residualCapacity());
        return flow;
    }

    private void augmentPath(Edge[] path, int flow) {
        for (Edge edge = path[sink]; edge != null; edge = path[edge.from()])
            edge.addResidualFlow(flow);
    }
}