package de.kibr.ega.generator.edge;

import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Node;

import java.util.List;

public interface EdgeGenerator {
    List<Edge> generateEdges(List<Node> nodes);
}
