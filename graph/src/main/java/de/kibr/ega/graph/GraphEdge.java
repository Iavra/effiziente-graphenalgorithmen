package de.kibr.ega.graph;

import java.awt.geom.Line2D;

import static java.lang.Math.*;

public class GraphEdge implements GraphElement {
    private final GraphNode source;
    private final GraphNode target;
    private int capacity = 0;

    public GraphEdge(GraphNode source, GraphNode target) {
        this.source = source;
        this.target = target;
    }

    public GraphNode getSource() {
        return source;
    }

    public GraphNode getTarget() {
        return target;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean intersects(GraphEdge other) {
        // TODO This still has cases (especially in a grid) where it's not correct
        if (source.equals(other.source) ^ source.equals(other.target) ^ target.equals(other.source) ^ target.equals(other.target))
            return angle() == other.angle();
        return asLine().intersectsLine(other.asLine());
    }

    public double length() {
        Line2D line = asLine();
        return abs(sqrt(pow(line.getX1() - line.getX2(), 2) + pow(line.getY1() - line.getY2(), 2)));
    }

    public double angle() {
        Line2D line = asLine();
        return Math.atan2(line.getY1() - line.getY2(), line.getX1() - line.getX2());
    }

    public GraphEdge flip() {
        return new GraphEdge(target, source);
    }

    private Line2D asLine() {
        return new Line2D.Double(source.position, target.position);
    }

    @Override
    public String getLabel() {
        return null; // TODO
    }

    @Override
    public String toString() {
        return "GraphEdge{source=" + source + ", target=" + target + "}";
    }
}
