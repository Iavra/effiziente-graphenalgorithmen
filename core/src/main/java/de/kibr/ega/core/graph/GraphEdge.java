package de.kibr.ega.core.graph;

public interface GraphEdge {
    GraphNode tail();
    GraphNode head();
    int weight();
}
