package de.kibr.ega.core.graph;

import java.awt.geom.Point2D;

public class GraphNode {
    private final String id;
    final Point2D position;

    public GraphNode(String id, double posX, double posY) {
        this.id = id;
        position = new Point2D.Double(posX, posY);
    }

    @Override
    public String toString() {
        return "GraphNode{id='" + id + "'" + ", position=" + position + "}";
    }
}
