package de.kibr.ega.core.graph;

import java.awt.geom.Line2D;

public class GraphEdge {
    private final GraphNode start;
    private final GraphNode end;

    final Line2D inner;

    public GraphEdge(GraphNode start, GraphNode end) {
        this.start = start;
        this.end = end;
        inner = new Line2D.Double(start.inner, end.inner);
    }

    public GraphNode getStart() {
        return start;
    }

    public GraphNode getEnd() {
        return end;
    }

    public double length() {
        return start.getPosition().distanceTo(end.getPosition());
    }

    public boolean intersects(GraphEdge other) {
        if (start.equals(other.start) || start.equals(other.end) || end.equals(other.start) || end.equals(other.end))
            return false;
        return inner.intersectsLine(other.inner);
    }

    @Override
    public String toString() {
        return "GraphEdge{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
