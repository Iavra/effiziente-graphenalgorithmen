package de.kibr.ega.core.graph;

public interface Edge {
    int from();
    int to();

    int residualCapacityTo(int node);
    void addResidualFlowTo(int node, int delta);

    Edge reverse();
}
