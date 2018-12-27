package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;

public class FordFulkersonAlgorithmTest extends AlgorithmTest {
    @Override
    Algorithm algorithm(Graph graph, int source, int sink) {
        return new FordFulkersonAlgorithm(graph, source, sink);
    }
}