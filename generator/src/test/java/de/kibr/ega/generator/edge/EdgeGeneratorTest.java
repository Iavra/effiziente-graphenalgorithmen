package de.kibr.ega.generator.edge;

import de.kibr.ega.graph.GraphEdge;
import de.kibr.ega.graph.GraphNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public abstract class EdgeGeneratorTest {
    protected abstract EdgeGenerator generator();

    @Test
    public void generated_edges_do_not_intersect_each_other() {
        // given
        EdgeGenerator generator = generator();
        List<GraphNode> nodes = createNodes();

        // when
        List<GraphEdge> edges = generator.generateEdges(nodes);

        // then
        assertThat(edges).allSatisfy(edge -> assertThat(edges.stream().noneMatch(edge::intersects)));
    }

    private List<GraphNode> createNodes() {
        return Arrays.asList(
                new GraphNode("A", 0, 0),
                new GraphNode("B", 1, 0),
                new GraphNode("C", 0, 1),
                new GraphNode("D", 1, 1)
        );
    }
}