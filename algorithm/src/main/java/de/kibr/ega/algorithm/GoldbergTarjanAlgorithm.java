package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;

public class GoldbergTarjanAlgorithm extends Algorithm {
    GoldbergTarjanAlgorithm(Graph graph, int source, int sink) {
        super(graph, source, sink);
    }

    @Override
    protected boolean doUpdate() {
        return false;
    }
}
