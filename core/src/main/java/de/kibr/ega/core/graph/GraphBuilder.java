package de.kibr.ega.core.graph;

import de.kibr.ega.core.util.CollectionUtil;
import de.kibr.ega.core.util.IdBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// note: number of edges in maximal planar graph = 3 * number of vertices - 6
// given there are at least 3 vertices
public class GraphBuilder {
    private IdBuilder idBuilder = new IdBuilder();

    public Graph buildGraph(int numNodes, int maxCapacity) {
        Graph.Builder graphBuilder = new Graph.Builder();
        List<GraphNode> nodes = buildRandomNodes(numNodes);
        nodes.forEach(graphBuilder::node);
        CollectionUtil.getAllPermutationsWithoutDuplicates(nodes)
                .map(pair -> new GraphEdge(pair.getValue0(), pair.getValue1()))
                .sorted(Comparator.comparing(GraphEdge::length))
                .collect(ArrayList::new, this::addEdgeToListIfItDoesntIntersect, ArrayList::addAll)
                .forEach(graphBuilder::edge);
        return graphBuilder.build();
    }

    private List<GraphNode> buildRandomNodes(int numNodes) {
        return IntStream.range(0, numNodes)
                .mapToObj(i -> buildRandomNode())
                .collect(Collectors.toList());
    }

    private GraphNode buildRandomNode() {
        return new GraphNode(idBuilder.get(), Position.randomPosition());
    }

    private void addEdgeToListIfItDoesntIntersect(List<GraphEdge> list, GraphEdge edge) {
        if (list.stream().noneMatch(edge::intersects)) list.add(edge);
    }
}
