package de.kibr.ega.generator.node;

import de.kibr.ega.core.graph.GraphNode;

public interface NodeGenerator {
    GraphNode[] generateNodes(int numNodes);
}
