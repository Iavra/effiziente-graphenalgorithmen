package de.kibr.ega.graph;

import java.awt.geom.Point2D;

public class GraphNode implements GraphElement {
    final Point2D position;

    public GraphNode(double posX, double posY) {
        position = new Point2D.Double(posX, posY);
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public double distanceTo(GraphNode other) {
        return position.distance(other.position);
    }

    @Override
    public String getLabel() {
        return null; // TODO
    }

    @Override
    public String toString() {
        return "GraphNode{x=" + getX() + ", y=" + getY() + "}";
    }
}
