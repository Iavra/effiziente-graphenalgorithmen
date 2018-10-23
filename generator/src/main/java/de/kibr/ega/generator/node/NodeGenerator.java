package de.kibr.ega.generator.node;

import de.kibr.ega.core.graph.GraphNode;

import java.util.List;

public interface NodeGenerator {
    List<GraphNode> generateNodes(int numNodes);
}
