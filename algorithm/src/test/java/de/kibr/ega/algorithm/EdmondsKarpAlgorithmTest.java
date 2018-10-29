package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;

public class EdmondsKarpAlgorithmTest extends AlgorithmTest {

    @Override
    Algorithm algorithm(Graph graph) {
        return new EdmondsKarpAlgorithm(graph);
    }
}