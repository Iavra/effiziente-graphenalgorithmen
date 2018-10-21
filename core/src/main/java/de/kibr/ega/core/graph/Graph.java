package de.kibr.ega.core.graph;

/**
 * Represents a simple, directed graph. Implementation details are left for the generator.
 */
public interface Graph {
    int size();
    int source();
    int sink();

    int capacity(int from, int to);
    GraphNode node(int node);
}
