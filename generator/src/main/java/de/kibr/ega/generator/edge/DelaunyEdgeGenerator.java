package de.kibr.ega.generator.edge;

import de.kibr.ega.graph.GraphEdge;
import de.kibr.ega.graph.GraphNode;

import java.util.Collections;
import java.util.List;

public class DelaunyEdgeGenerator implements EdgeGenerator {
    @Override
    public List<GraphEdge> generateEdges(List<GraphNode> nodes) {
        if (nodes.size() < 2) return Collections.emptyList();
        if (nodes.size() < 3) return Collections.singletonList(new GraphEdge(nodes.get(0), nodes.get(1)));
        return null; // TODO
    }
}
