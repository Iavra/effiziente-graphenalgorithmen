package de.kibr.ega.core.graph;

public interface Edge {
    int from();
    int to();
    int other(int node);

    int residualCapacityTo(int node);
    void addResidualFlowTo(int node, int flow);
}
