package de.kibr.ega.core.graph;

import org.jgrapht.graph.DefaultWeightedEdge;

import java.awt.geom.Line2D;

import static java.lang.Math.*;

public class GraphEdge extends DefaultWeightedEdge {
    @Override
    protected GraphNode getSource() {
        return (GraphNode) super.getSource();
    }

    @Override
    protected GraphNode getTarget() {
        return (GraphNode) super.getTarget();
    }

    public boolean intersects(GraphEdge other) {
        if (getSource().equals(other.getSource()) ||
                getSource().equals(other.getTarget()) ||
                getTarget().equals(other.getSource()) ||
                getTarget().equals(other.getTarget()))
            return false;
        return asLine().intersectsLine(other.asLine());
    }

    public double length() {
        Line2D line = asLine();
        return abs(sqrt(pow(line.getX1() - line.getX2(), 2) + pow(line.getY1() - line.getY2(), 2)));
    }

    private Line2D asLine() {
        return new Line2D.Double(getSource().position, getTarget().position);
    }

    @Override
    public String toString() {
        return "GraphEdge{source=" + getSource() + ", target=" + getTarget() + ", weight=" + getWeight() + "}";
    }
}
