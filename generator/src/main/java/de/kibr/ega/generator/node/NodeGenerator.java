package de.kibr.ega.generator.node;

import de.kibr.ega.core.graph.Node;

import java.util.List;

public interface NodeGenerator {
    List<Node> generateNodes(int numNodes);
}
