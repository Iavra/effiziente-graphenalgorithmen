package de.kibr.ega.generator;

import de.kibr.ega.core.graph.GraphEdge;
import de.kibr.ega.core.graph.GraphNode;
import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedMultigraph;

public class GraphBuilder {
    public static Graph<GraphNode, GraphEdge> buildGraph(int size, int maxCapacity) {
        GraphNodeBuilder nodeBuilder = new GraphNodeBuilder(new IdBuilder());
        Graph<GraphNode, GraphEdge> graph = new DirectedWeightedMultigraph<>(nodeBuilder, GraphEdge::new);
        // TODO: needs to supply a map to capture source/sink and return it together with the graph in a wrapper
        new PlanarGraphGenerator(size, maxCapacity).generateGraph(graph);
        return graph;
    }
}
