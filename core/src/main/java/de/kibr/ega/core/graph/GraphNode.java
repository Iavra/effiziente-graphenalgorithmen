package de.kibr.ega.core.graph;

import java.awt.geom.Point2D;

public class GraphNode {
    private final String id;
    private final Position position;

    final Point2D inner;

    public GraphNode(String id, Position position) {
        this.id = id;
        this.position = position;
        inner = new Point2D.Double(position.getX(), position.getY());
    }

    public String getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }
}
