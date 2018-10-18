package de.kibr.ega.core.graph;

import org.jgrapht.Graph;

public class GraphWrapper {
    private final Graph<GraphNode, GraphEdge> graph;
    private final GraphNode source;
    private final GraphNode sink;

    public GraphWrapper(Graph<GraphNode, GraphEdge> graph, GraphNode source, GraphNode sink) {
        this.graph = graph;
        this.source = source;
        this.sink = sink;
    }

    public Graph<GraphNode, GraphEdge> getGraph() {
        return graph;
    }

    public GraphNode getSource() {
        return source;
    }

    public GraphNode getSink() {
        return sink;
    }
}
