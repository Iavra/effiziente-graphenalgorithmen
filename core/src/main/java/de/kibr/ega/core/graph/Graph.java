package de.kibr.ega.core.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Graph {
    public static final Graph EMPTY_GRAPH
            = new Graph(Collections.emptyList(), Collections.emptyList());

    private final List<GraphNode> nodes;
    private final List<GraphEdge> edges;

    public Graph(Collection<GraphNode> nodes, Collection<GraphEdge> edges) {
        this.nodes = new ArrayList<>(nodes);
        this.edges = new ArrayList<>(edges);
    }

    public List<GraphNode> getNodes() {
        return nodes;
    }

    public List<GraphEdge> getEdges() {
        return edges;
    }
}
