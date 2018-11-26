package de.kibr.ega.core.graph.traversal;

import de.kibr.ega.core.graph.Edge;
import de.kibr.ega.core.graph.Graph;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class BreadthFirstSearchTest {
    @Test
    public void terminates_in_graph_without_edges() {
        // given
        Graph graph = new Graph(1);

        // when
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);

        // then
        assertThat(bfs.hasNext()).isFalse();
    }

    @Test
    public void skips_edges_without_capacity() {
        // given
        Graph graph = new Graph(2);
        graph.addEdge(0, 1, 0);

        // when
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);

        // then
        assertThat(bfs.hasNext()).isFalse();
    }

    @Test
    public void does_only_traverse_nodes_once() {
        // given
        Graph graph = new Graph(2);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 0, 1);

        // when
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);

        // then
        assertThat(bfs.next()).extracting(Edge::from, Edge::to).containsExactly(0, 1);
        assertThat(bfs.hasNext()).isFalse();
    }

    @Test
    public void returns_single_edge_in_graph() {
        // given
        Graph graph = new Graph(2);
        graph.addEdge(0, 1, 1);

        // when
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);

        // then
        assertThat(bfs.next()).extracting(Edge::from, Edge::to).containsExactly(0, 1);
        assertThat(bfs.hasNext()).isFalse();
    }

    @Test
    public void returns_outgoing_edges_in_order() {
        // given
        Graph graph = new Graph(3);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);

        // when
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);

        // then
        assertThat(bfs.next()).extracting(Edge::from, Edge::to).containsExactly(0, 1);
        assertThat(bfs.next()).extracting(Edge::from, Edge::to).containsExactly(0, 2);
        assertThat(bfs.hasNext()).isFalse();
    }

    @Test
    public void correctly_traverses_graph() {
        // given
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(3, 4, 1);

        // when
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);

        // then
        assertThat(bfs.next()).extracting(Edge::from, Edge::to).containsExactly(0, 1);
        assertThat(bfs.next()).extracting(Edge::from, Edge::to).containsExactly(0, 2);
        assertThat(bfs.next()).extracting(Edge::from, Edge::to).containsExactly(1, 3);
        assertThat(bfs.next()).extracting(Edge::from, Edge::to).containsExactly(3, 4);
        assertThat(bfs.hasNext()).isFalse();
    }
}