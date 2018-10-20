package de.kibr.ega.generator.node;

import de.kibr.ega.graph.GraphNode;

import java.util.List;

public interface NodeGenerator {
    List<GraphNode> generateNodes(int size, double maxWidth, double maxHeight);
}
