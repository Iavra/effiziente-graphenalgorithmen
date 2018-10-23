package de.kibr.ega.generator.graph;

import de.kibr.ega.core.graph.Graph;
import de.kibr.ega.core.graph.GraphEdge;
import de.kibr.ega.core.graph.GraphNode;
import de.kibr.ega.generator.edge.EdgeGenerator;
import de.kibr.ega.generator.node.NodeGenerator;

import java.util.List;

public class GraphGenerator {
    private final NodeGenerator nodeGenerator;
    private final EdgeGenerator edgeGenerator;

    public GraphGenerator(NodeGenerator nodeGenerator, EdgeGenerator edgeGenerator) {
        this.nodeGenerator = nodeGenerator;
        this.edgeGenerator = edgeGenerator;
    }

    public Graph generateGraph(int numNodes, int maxCapacity) {
        List<GraphNode> nodes = nodeGenerator.generateNodes(numNodes);
        List<GraphEdge> edges = edgeGenerator.generateEdges(nodes);
        // TODO: It might be a lot easier to just use actual classes for nodes/edges, at least during creation
        // TODO: delegate to sub generators. Also, it's probably really better to initialize max x/y in nodeGen constructor
        return null;
    }
}
