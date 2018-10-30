package de.kibr.ega.generator.graph;

import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Graph;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdjacencyListGraph implements Graph {
    private final int size;
    private final int source;
    private final int sink;

    private final List<Point2D> positions;
    private final List<List<Edge>> adjacent;

    public AdjacencyListGraph(List<Point2D> nodes, int source, int sink) {
        this.size = nodes.size();
        this.source = validate(source);
        this.sink = validate(sink);
        positions = new ArrayList<>(nodes);
        adjacent = IntStream.range(0, size)
                .mapToObj(i -> new LinkedList<Edge>())
                .collect(Collectors.toCollection(ArrayList::new));
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
    public List<Edge> adjacent(int node) {
        return adjacent.get(validate(node));
    }

    @Override
    public Point2D position(int node) {
        return positions.get(validate(node));
    }

    private int validate(int node) {
        if (node < 0 || node >= size)
            throw new IllegalArgumentException("node outside of valid range [0, " + size + "[: " + node);
        return node;
    }
}
