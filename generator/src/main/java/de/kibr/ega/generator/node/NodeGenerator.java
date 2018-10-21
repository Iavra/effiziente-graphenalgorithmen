package de.kibr.ega.generator.node;

import de.kibr.ega.core.graph.Position;

public interface NodeGenerator {
    Position[] generateNodes(int numNodes);
}
