package de.kibr.ega.generator.graph;

import de.kibr.ega.core.graph.Graph;
import de.kibr.ega.core.graph.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Uses an adjacency matrix to store edges and a separate array for positions.
 */
public class AdjacencyMatrixGraph implements Graph {
    private final int[][] matrix;
    private final Position[] positions;
    private final int size;
    private final int source;
    private final int sink;

    public AdjacencyMatrixGraph(Position[] nodes, int source, int sink) {
        size = nodes.length;
        positions = nodes;
        matrix = new int[size][size];
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
    public List<Integer> adjacent(int node) {
        validateNode(node);
        return Arrays.stream(matrix[node]).filter(i -> i > 0).boxed().collect(Collectors.toList());
    }

    @Override
    public int capacity(int from, int to) {
        validateNode(from);
        validateNode(to);
        return matrix[from][to];
    }

    @Override
    public Position position(int node) {
        validateNode(node);
        return positions[node];
    }

    private void validateNode(int node) {
        if (node < 0 || node >= size)
            throw new IllegalArgumentException("node " + node + " outside of valid range [0;" + (size - 1) + "]");
    }
}
