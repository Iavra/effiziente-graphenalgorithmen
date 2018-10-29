package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AlgorithmTest {
    @Test
    public void returns_zero_flow_if_source_and_sink_are_equal() {
        // given
        Graph graph = new Graph(1, 0, 0);

        // when
        int maxFlow = runAlgorithm(algorithm(graph));

        // then
        assertThat(maxFlow).isZero();
    }

    @Test
    public void returns_capacity_of_only_edge_between_source_and_sink() {
        // given
        Graph graph = new Graph(2, 0, 1);
        graph.addEdge(0, 1, 42);

        // when
        int maxFlow = runAlgorithm(algorithm(graph));

        // then
        assertThat(maxFlow).isEqualTo(42);
    }

    @Test
    public void returns_capacity_of_max_flow_bottlenecked_by_one_edge() {
        // given
        Graph graph = new Graph(3, 0, 2);
        graph.addEdge(0, 1, 42);
        graph.addEdge(1, 2, 24);

        // when
        int maxFlow = runAlgorithm(algorithm(graph));

        // then
        assertThat(maxFlow).isEqualTo(24);
    }

    @Test
    public void returns_capacity_of_max_flow_across_multiple_edges() {
        // given
        Graph graph = new Graph(4, 0, 3);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 34);
        graph.addEdge(1, 3, 8);
        graph.addEdge(2, 3, 50);

        // when
        int maxFlow = runAlgorithm(algorithm(graph));

        // then
        assertThat(maxFlow).isEqualTo(42);
    }

    @Test
    public void returns_capacity_of_max_flow_with_loop() {
        // given
        Graph graph = new Graph(4, 0, 3);
        graph.addEdge(0, 1, 42);
        graph.addEdge(1, 2, 24);
        graph.addEdge(2, 0, 1);
        graph.addEdge(2, 3, 42);

        // when
        int maxFlow = runAlgorithm(algorithm(graph));

        // then
        assertThat(maxFlow).isEqualTo(24);
    }

    private int runAlgorithm(Algorithm algorithm) {
        boolean done = false;
        while (!done) done = algorithm.update();
        return algorithm.maxFlow();
    }

    abstract Algorithm algorithm(Graph graph);
}