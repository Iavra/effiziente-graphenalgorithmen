package de.kibr.ega.core.graph;

import java.util.List;

/**
 * Represents a simple, directed graph. Implementation details are left for the generator.
 */
public interface Graph {
    int size();
    int source();
    int sink();

    List<Integer> adjacent(int node);

    Node node(int node);
    Edge edge(int from, int to);
}
