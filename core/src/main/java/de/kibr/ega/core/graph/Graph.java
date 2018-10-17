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
        this.nodes = Collections.unmodifiableList(new ArrayList<>(nodes));
        this.edges = Collections.unmodifiableList(new ArrayList<>(edges));
    }

    public List<GraphNode> getNodes() {
        return nodes;
    }

    public List<GraphEdge> getEdges() {
        return edges;
    }

    public static class Builder {
        private List<GraphNode> nodes = new ArrayList<>();
        private List<GraphEdge> edges = new ArrayList<>();

        public Builder node(GraphNode node) {
            nodes.add(node);
            return this;
        }

        public Builder edge(GraphEdge edge) {
            edges.add(edge);
            return this;
        }

        public Graph build() {
            return new Graph(nodes, edges);
        }
    }
}
