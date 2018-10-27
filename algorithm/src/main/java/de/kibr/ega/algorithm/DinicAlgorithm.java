package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DinicAlgorithm extends BaseAlgorithm {
    private final int[][] rGraph;;

    DinicAlgorithm(Graph graph) {
        super(graph);
        this.rGraph = toResidualGraph(graph);
    }

    @Override
    public boolean doUpdate() {
        int[] levels = new int[rGraph.length];
        if (traverseGraph(levels)) {
            // TODO: send flow
            return false;
        }
        return true;
    }

    private boolean traverseGraph(int[] levels) {
        int size = rGraph.length;
        Arrays.fill(levels, -1);
        Queue<Integer> queue = new LinkedList<>();

        levels[source] = 0;
        queue.add(source);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor = 0; neighbor < size; neighbor++) {
                if (levels[neighbor] < 0 && rGraph[current][neighbor] > 0) {
                    levels[neighbor] = levels[current] + 1;
                    if (neighbor == sink) return true;
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }
}
