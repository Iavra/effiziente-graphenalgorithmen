package de.kibr.ega.generator.edge;

import de.kibr.ega.graph.GraphEdge;
import de.kibr.ega.graph.GraphNode;

import java.util.List;

public interface EdgeGenerator {
    List<GraphEdge> generateEdges(List<GraphNode> nodes);
}
