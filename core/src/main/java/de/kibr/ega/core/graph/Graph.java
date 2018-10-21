package de.kibr.ega.core.graph;

import java.util.List;
import java.util.Optional;

/**
 * Represents a simple, directed graph. Implementation details are left for the generator.
 */
public interface Graph {
    List<GraphNode> nodes();
    GraphNode source();
    GraphNode sink();

    List<GraphEdge> edgesFrom(GraphNode tail);
    List<GraphEdge> edgesTo(GraphNode head);
    Optional<GraphEdge> edge(GraphNode tail, GraphNode head);
}
