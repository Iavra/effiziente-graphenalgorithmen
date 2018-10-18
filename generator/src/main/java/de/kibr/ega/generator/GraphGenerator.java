package de.kibr.ega.generator;

import de.kibr.ega.core.graph.Graph;
import de.kibr.ega.core.graph.GraphEdge;
import de.kibr.ega.core.graph.GraphNode;
import de.kibr.ega.core.graph.Position;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GraphGenerator {
    private final Supplier<String> idBuilder;

    public GraphGenerator(Supplier<String> idBuilder) {
        this.idBuilder = idBuilder;
    }

    public Graph buildGraph(int numNodes, int maxCapacity) {
        if (numNodes == 0) return Graph.EMPTY_GRAPH;

        List<GraphNode> nodes = buildRandomNodes(numNodes);
        List<GraphEdge> edges = buildPlanarEdges(nodes);
        Graph graph = new Graph(nodes, edges);

        List<GraphNode> sortedNodes = nodes.stream().sorted(Comparator.comparingDouble(e ->
                e.getPosition().getX() + e.getPosition().getY())).collect(Collectors.toList());
        assignCapacities(graph, sortedNodes.get(0), sortedNodes.get(sortedNodes.size() - 1));

        return graph;
    }

    private List<GraphNode> buildRandomNodes(int numNodes) {
        return IntStream.range(0, numNodes)
                .mapToObj(i -> buildRandomNode())
                .collect(Collectors.toList());
    }

    private GraphNode buildRandomNode() {
        return new GraphNode(idBuilder.get(), Position.randomPosition());
    }

    private ArrayList<GraphEdge> buildPlanarEdges(List<GraphNode> nodes) {
        return getAllPossibleEdges(nodes)
                .sorted(Comparator.comparing(GraphEdge::length))
                .collect(ArrayList::new, this::addEdgeToListIfItDoesntIntersect, ArrayList::addAll);
    }

    private Stream<GraphEdge> getAllPossibleEdges(List<GraphNode> nodes) {
        return nodes.stream()
                .flatMap(a -> nodes.stream()
                        .filter(b -> !a.equals(b))
                        .map(b -> new GraphEdge(a, b)));
    }

    private void addEdgeToListIfItDoesntIntersect(List<GraphEdge> list, GraphEdge edge) {
        if (list.stream().noneMatch(edge::intersects)) list.add(edge);
    }

    private void assignCapacities(Graph graph, GraphNode startNode, GraphNode endNode) {
        // TODO: do a random cut through the graph, start and end being in separate sets
        // TODO: get all edges belonging to the cut
        // TODO: randomly spread max capacity between them
        // TODO: assign each edge adjacent to those edges a capacity greater or equal
    }
}
