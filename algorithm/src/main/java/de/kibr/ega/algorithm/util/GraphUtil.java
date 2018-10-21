package de.kibr.ega.algorithm.util;

import de.kibr.ega.core.graph.Graph;

public class GraphUtil {
    private GraphUtil() {}

    public static int[][] toResidualGraph(Graph graph) {
        int size = graph.size();
        int[][] matrix = new int[size][size];
        for (int v = 0; v < size; v++)
            for (int w = 0; w < size; w++)
                matrix[v][w] = graph.capacity(v, w);
        return matrix;
    }
}
