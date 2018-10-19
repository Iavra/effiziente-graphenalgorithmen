package de.kibr.ega.generator.edge;

import de.kibr.ega.graph.GraphEdge;
import de.kibr.ega.graph.GraphNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Tries to add all possible edges from shortest to longest distance, as long as there's no intersection.
 * This should be identical to the sample algorithm provided in the task description.
 *
 * Complexity: O(n^3), though the sort adds additional complexity, it's not strictly needed for the algorithm
 */
public class LinearEdgeGenerator implements EdgeGenerator {
    @Override
    public List<GraphEdge> generateEdges(List<GraphNode> nodes) {
        return generateAllPossibleEdges(nodes)
                .sorted(Comparator.comparing(GraphEdge::length))
                .collect(ArrayList::new, this::discardIntersectingEdges, ArrayList::addAll);
    }

    private Stream<GraphEdge> generateAllPossibleEdges(Collection<GraphNode> nodes) {
        return nodes.stream().flatMap(a -> nodes.stream()
                .filter(b -> !a.equals(b))
                .map(b -> new GraphEdge(a, b)));
    }

    private void discardIntersectingEdges(List<GraphEdge> edges, GraphEdge edge) {
        if (edges.stream().noneMatch(edge::intersects)) edges.add(edge);
    }
}
