package de.kibr.ega.graph;

import java.awt.geom.Point2D;
import java.util.Comparator;

public class GraphNode {
    public static Comparator<GraphNode> BY_POSITION = Comparator.comparing(GraphNode::getX).thenComparing(GraphNode::getY);

    private final String id;
    final Point2D position;

    public GraphNode(String id, double posX, double posY) {
        this.id = id;
        position = new Point2D.Double(posX, posY);
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    @Override
    public String toString() {
        return "GraphNode{id='" + id + "'" + ", position=" + position + "}";
    }
}
