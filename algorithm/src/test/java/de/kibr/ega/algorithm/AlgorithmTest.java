package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("squid:S00100")
public abstract class AlgorithmTest {
    @Test
    public void returns_zero_if_source_and_sink_are_equal() {
        // given
        Graph graph = new Graph(1);

        // when
        int maxFlow = computeMaxFlow(graph);

        // then
        assertThat(maxFlow).isEqualTo(0);
    }

    @Test
    public void returns_zero_without_augmenting_path() {
        // given
        Graph graph = new Graph(2);

        // when
        int maxFlow = computeMaxFlow(graph);

        // then
        assertThat(maxFlow).isEqualTo(0);
    }

    @Test
    public void returns_single_edge_capacity() {
        // given
        Graph graph = new Graph(2);
        graph.addEdge(0, 1, 42);

        // when
        int maxFlow = computeMaxFlow(graph);

        // then
        assertThat(maxFlow).isEqualTo(42);
    }

    @Test
    public void returns_simple_path_bottleneck_capacity() {
        // given
        Graph graph = new Graph(3);
        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 20);

        // when
        int maxFlow = computeMaxFlow(graph);

        // then
        assertThat(maxFlow).isEqualTo(10);
    }

    @Test
    public void combines_multiple_path_capacities() {
        // given
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 8);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 10);

        // when
        int maxFlow = computeMaxFlow(graph);

        // then
        assertThat(maxFlow).isEqualTo(13);
    }

    @Test
    // Graph source: https://www.youtube.com/watch?v=M4fyCfFTYV8 (last example)
    public void test_example_graph_1() {
        // given
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 1000);
        graph.addEdge(0, 2, 1000);
        graph.addEdge(1, 3, 1);
        graph.addEdge(3, 2, 1);
        graph.addEdge(1, 4, 1000);
        graph.addEdge(2, 4, 1000);

        // when
        int maxFlow = computeMaxFlow(graph);

        // then
        assertThat(maxFlow).isEqualTo(2000);
    }

    @Test
    // Graph source: https://en.wikipedia.org/wiki/Dinic%27s_algorithm
    public void test_example_graph_2() {
        // given
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 10);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 4);
        graph.addEdge(1, 4, 8);
        graph.addEdge(2, 4, 9);
        graph.addEdge(4, 3, 6);
        graph.addEdge(3, 5, 10);
        graph.addEdge(4, 5, 10);

        // when
        int maxFlow = computeMaxFlow(graph);

        // then
        assertThat(maxFlow).isEqualTo(19);
    }

    @Test
    // Graph source: https://www.youtube.com/watch?v=M6cm8UeeziI
    public void test_example_graph_3() {
        // given
        Graph graph = new Graph(11);
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 10);
        graph.addEdge(0, 3, 15);
        graph.addEdge(2, 1, 15);
        graph.addEdge(1, 4, 10);
        graph.addEdge(2, 5, 20);
        graph.addEdge(3, 6, 25);
        graph.addEdge(4, 5, 25);
        graph.addEdge(5, 3, 5);
        graph.addEdge(4, 7, 10);
        graph.addEdge(5, 8, 30);
        graph.addEdge(6, 8, 20);
        graph.addEdge(6, 9, 10);
        graph.addEdge(8, 4, 15);
        graph.addEdge(7, 10, 5);
        graph.addEdge(8, 10, 15);
        graph.addEdge(9, 10, 10);

        // when
        int maxFlow = computeMaxFlow(graph);

        // then
        assertThat(maxFlow).isEqualTo(30);
    }

    abstract Algorithm algorithm(Graph graph, int source, int sink);

    private int computeMaxFlow(Graph graph) {
        Algorithm algorithm = algorithm(graph, 0, graph.size() - 1);
        while (!algorithm.finished()) algorithm.update();
        return algorithm.maxFlow();
    }
}
