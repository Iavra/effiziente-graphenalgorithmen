package de.kibr.ega.graph;

import java.awt.geom.Point2D;
import java.util.Comparator;

public class GraphNode {
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

    @Override
    public String toString() {
        return "GraphNode{x=" + getX() + ", y=" + getY() + "}";
    }
}
