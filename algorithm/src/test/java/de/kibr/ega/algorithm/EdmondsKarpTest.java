package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;

public class EdmondsKarpTest extends AlgorithmTest {
    @Override
    Algorithm algorithm(Graph graph, int source, int sink) {
        return new EdmondsKarp(graph, source, sink);
    }
}