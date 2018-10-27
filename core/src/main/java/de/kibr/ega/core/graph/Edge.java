package de.kibr.ega.core.graph;

public interface Edge {
    Node tail();
    Node head();
    int capacity();
}
