package de.kibr.ega.algorithm.util;

import de.kibr.ega.core.graph.Graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// TODO: The methods in here can break, as soon as the sink is found
public class GraphTraversal {
    private GraphTraversal() {}

    public static boolean depthFirst(int[][] graph, int source, int sink, int[] result) {
        int size = graph.length;
        boolean[] visited = new boolean[size];
        Deque<Integer> stack = new LinkedList<>();

        result[source] = -1;
        visited[source] = true;
        stack.push(source);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            for (int neighbor = 0; neighbor < size; neighbor++) {
                if (!visited[neighbor] && graph[current][neighbor] > 0) {
                    result[neighbor] = current;
                    if (neighbor == sink) return true;
                    stack.push(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return false;
    }

    public static boolean breadthFirst(int[][] graph, int source, int sink, int[] result) {
        int size = graph.length;
        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new LinkedList<>();

        result[source] = -1;
        visited[source] = true;
        queue.add(source);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor = 0; neighbor < size; neighbor++) {
                if (!visited[neighbor] && graph[current][neighbor] > 0) {
                    result[neighbor] = current;
                    if (neighbor == sink) return true;
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return false;
    }
}
