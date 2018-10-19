package de.kibr.ega.generator.triangulate;

import de.kibr.ega.graph.GraphEdge;
import de.kibr.ega.graph.GraphNode;

import java.util.Collection;
import java.util.List;

public interface GraphTriangulator {
    List<GraphEdge> triangulateEdges(Collection<GraphNode> nodes);
}
