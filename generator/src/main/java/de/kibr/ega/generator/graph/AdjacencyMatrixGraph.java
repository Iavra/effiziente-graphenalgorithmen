package de.kibr.ega.generator.graph;

import de.kibr.ega.core.graph.Graph;
import de.kibr.ega.core.graph.GraphEdge;
import de.kibr.ega.core.graph.GraphNode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Uses an adjacency matrix to store edges and a separate array for positions.
 */
public class AdjacencyMatrixGraph implements Graph {
    private final GraphEdge[][] matrix;
    private final GraphNode[] nodes;
    private final int size;
    private final int source;
    private final int sink;

    public AdjacencyMatrixGraph(GraphNode[] nodes, int source, int sink) {
        this.size = nodes.length;
        this.nodes = nodes;
        this.matrix = new GraphEdge[size][size];
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
    public int capacity(int from, int to) {
        return edge(from, to).capacity();
    }

    @Override
    public GraphNode node(int node) {
        validateNode(node);
        return nodes[node];
    }

    @Override
    public GraphEdge edge(int from, int to) {
        validateNode(from);
        validateNode(to);
        return matrix[from][to];
    }

    private void validateNode(int node) {
        if (node < 0 || node >= size)
            throw new IllegalArgumentException("node " + node + " outside of valid range [0;" + (size - 1) + "]");
    }
}
