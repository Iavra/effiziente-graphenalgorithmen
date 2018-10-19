package de.kibr.ega.graph;

import java.awt.geom.Line2D;

import static java.lang.Math.*;

public class GraphEdge {
    private final GraphNode source;
    private final GraphNode target;

    public GraphEdge(GraphNode source, GraphNode target) {
        this.source = source;
        this.target = target;
    }

    public boolean intersects(GraphEdge other) {
        if (source.equals(other.source) || source.equals(other.target) || target.equals(other.source) || target.equals(other.target))
            return false;
        return asLine().intersectsLine(other.asLine());
    }

    public double length() {
        Line2D line = asLine();
        return abs(sqrt(pow(line.getX1() - line.getX2(), 2) + pow(line.getY1() - line.getY2(), 2)));
    }

    private Line2D asLine() {
        return new Line2D.Double(source.position, target.position);
    }

    @Override
    public String toString() {
        return "GraphEdge{source=" + source + ", target=" + target + "}";
    }
}
