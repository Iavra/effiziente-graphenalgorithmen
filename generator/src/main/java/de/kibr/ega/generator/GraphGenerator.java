package de.kibr.ega.generator;

import de.kibr.ega.core.graph.Graph;
import de.kibr.ega.core.graph.GraphEdge;
import de.kibr.ega.core.graph.GraphNode;
import de.kibr.ega.core.graph.Position;
import de.kibr.ega.generator.util.CollectionUtil;
import de.kibr.ega.generator.util.IdBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraphGenerator {
    private final Supplier<String> idBuilder;

    public GraphGenerator(Supplier<String> idBuilder) {
        this.idBuilder = idBuilder;
    }

    public Graph buildGraph(int numNodes, int maxCapacity) {
        Graph.Builder graphBuilder = new Graph.Builder();
        List<GraphNode> nodes = buildRandomNodes(numNodes);
        nodes.forEach(graphBuilder::node);
        CollectionUtil.getAllPermutations(nodes)
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

    public static void main(String[] args) {
        Graph graph = new GraphGenerator(new IdBuilder()).buildGraph(3, 0);
    }
}
