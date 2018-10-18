package de.kibr.ega.generator;

import de.kibr.ega.core.graph.GraphEdge;
import de.kibr.ega.core.graph.GraphNode;
import de.kibr.ega.core.graph.GraphWrapper;
import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.HashMap;
import java.util.Map;

public class GraphBuilder {
    private GraphBuilder() {}

    public static GraphWrapper buildGraph(int size, int maxCapacity) {
        GraphNodeBuilder nodeBuilder = new GraphNodeBuilder(new IdBuilder());
        Graph<GraphNode, GraphEdge> graph = new DirectedWeightedMultigraph<>(nodeBuilder, GraphEdge::new);
        Map<String, GraphNode> resultMap = new HashMap<>();
        new PlanarGraphGenerator(size, maxCapacity).generateGraph(graph, resultMap);
        return new GraphWrapper(graph, resultMap.get(PlanarGraphGenerator.SOURCE), resultMap.get(PlanarGraphGenerator.SINK));
    }
}
