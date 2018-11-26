package de.kibr.ega.core.graph;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class GraphTest {
    @Test
    public void returns_correct_size() {
        // given
        Graph graph = new Graph(42);

        // when
        int size = graph.size();

        // then
        assertThat(size).isEqualTo(42);
    }

    @Test
    public void negative_nodes_are_invalid() {
        // given
        Graph graph = new Graph(42);

        // when
        // then
        assertThatThrownBy(() -> graph.validateNode(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void too_high_indices_are_invalid() {
        // given
        Graph graph = new Graph(42);

        // when
        // then
        assertThatThrownBy(() -> graph.validateNode(100)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void validate_indices_in_graph() {
        // given
        Graph graph = new Graph(42);

        // when
        // then
        assertThatCode(() -> graph.validateNode(30)).doesNotThrowAnyException();
    }

    @Test
    public void validate_from_node_in_edge() {
        // given
        Graph graph = new Graph(1);

        // when
        // then
        assertThatThrownBy(() -> graph.addEdge(-1, 0, 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void validate_to_node_in_edge() {
        // given
        Graph graph = new Graph(1);

        // when
        // then
        assertThatThrownBy(() -> graph.addEdge(0, -1, 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void does_not_allow_self_loops() {
        // given
        Graph graph = new Graph(2);

        // when
        // then
        assertThatThrownBy(() -> graph.addEdge(0, 0, 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void validate_node_on_reading_edges() {
        // given
        Graph graph = new Graph(1);

        // when
        // then
        assertThatThrownBy(() -> graph.adjacent(2)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void returns_empty_list_for_isolated_nodes() {
        // given
        Graph graph = new Graph(1);

        // when
        List<Edge> edges = graph.adjacent(0);

        // then
        assertThat(edges).isEmpty();
    }

    @Test
    public void also_maps_backward_edge() {
        // given
        Graph graph = new Graph(2);
        graph.addEdge(0, 1, 1);

        // when
        List<Edge> edges1 = graph.adjacent(0);
        List<Edge> edges2 = graph.adjacent(1);

        // then
        assertThat(edges1).extracting(Edge::to).containsExactly(1);
        assertThat(edges2).extracting(Edge::to).containsExactly(0);
    }

    @Test
    public void returns_outgoing_edges_in_order() {
        // given
        Graph graph = new Graph(3);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);

        // when
        List<Edge> edges = graph.adjacent(0);

        // then
        assertThat(edges).extracting(Edge::to).containsExactly(1, 2);
    }
}