package de.kibr.ega.algorithm;

import de.kibr.ega.core.graph.Graph;

// TODO: This is going to be used as the base class of all algorithms, which need to be able to proceed step-by-step
public interface Algorithm {
    boolean update();
    int maxFlow();
}
