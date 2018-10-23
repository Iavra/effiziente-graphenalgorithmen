package de.kibr.ega.generator.edge;

import de.kibr.ega.core.graph.GraphEdge;
import de.kibr.ega.core.graph.GraphNode;

import java.util.List;

public interface EdgeGenerator {
    List<GraphEdge> generateEdges(List<GraphNode> nodes);
}
