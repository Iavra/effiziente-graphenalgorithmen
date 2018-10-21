package de.kibr.ega.algorithm.util;

import de.kibr.ega.core.graph.Graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// TODO: The methods in here can break, as soon as the sink is found
public class GraphTraversal {
    private GraphTraversal() {}

    public static boolean depthFirst(Graph graph, int[] result) {
        boolean[] visited = new boolean[graph.size()];
        Deque<Integer> stack = new LinkedList<>();

        int source = graph.source();
        result[source] = -1;
        visited[source] = true;
        stack.push(source);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int neighbor : graph.adjacent(current)) {
                if (!visited[neighbor] && graph.capacity(current, neighbor) > 0) {
                    result[neighbor] = current;
                    if (neighbor == graph.sink()) return true;
                    stack.push(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return false;
    }

    public static boolean breadthFirst(Graph graph, int[] result) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();

        int source = graph.source();
        result[source] = -1;
        visited[source] = true;
        queue.add(source);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.adjacent(current)) {
                if (!visited[neighbor] && graph.capacity(current, neighbor) > 0) {
                    result[neighbor] = current;
                    if (neighbor == graph.sink()) return true;
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return false;
    }
}
