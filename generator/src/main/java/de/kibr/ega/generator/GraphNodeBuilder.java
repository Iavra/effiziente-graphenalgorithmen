package de.kibr.ega.generator;

import de.kibr.ega.graph.GraphNode;

import java.util.function.Supplier;

public class GraphNodeBuilder implements Supplier<GraphNode> {
    private final Supplier<String> idBuilder;

    public GraphNodeBuilder(Supplier<String> idBuilder) {
        this.idBuilder = idBuilder;
    }

    @Override
    public GraphNode get() {
        return new GraphNode(idBuilder.get(), Math.random() * 100, Math.random() * 100);
    }
}
