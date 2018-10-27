package de.kibr.ega.generator.graph;

import de.kibr.ega.core.graph.Graph;
import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Uses an adjacency matrix to store edges and a separate array for positions.
 */
public class AdjacencyMatrixGraph implements Graph {
    private final Edge[][] matrix;
    private final Node[] nodes;
    private final int size;
    private final int source;
    private final int sink;

    public AdjacencyMatrixGraph(Node[] nodes, int source, int sink) {
        this.size = nodes.length;
        this.nodes = nodes;
        this.matrix = new Edge[size][size];
        this.source = source;
        this.sink = sink;
        validateNode(source);
        validateNode(sink);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int source() {
        return source;
    }

    @Override
    public int sink() {
        return sink;
    }

    @Override
    public Node node(int node) {
        validateNode(node);
        return nodes[node];
    }

    @Override
    public Edge edge(int from, int to) {
        validateNode(from);
        validateNode(to);
        return matrix[from][to];
    }

    private void validateNode(int node) {
        if (node < 0 || node >= size)
            throw new IllegalArgumentException("node " + node + " outside of valid range [0;" + (size - 1) + "]");
    }
}
