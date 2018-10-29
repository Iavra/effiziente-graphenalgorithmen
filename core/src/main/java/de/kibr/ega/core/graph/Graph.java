package de.kibr.ega.core.graph;

import java.util.List;

public interface Graph {
    int size();
    int source();
    int sink();
    List<Edge> adjacent(int node);
}
