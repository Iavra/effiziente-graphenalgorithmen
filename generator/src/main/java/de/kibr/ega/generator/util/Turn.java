package de.kibr.ega.generator.util;

import de.kibr.ega.graph.GraphNode;

public enum Turn {
    CLOCKWISE,
    COUNTER_CLOCKWISE,
    COLLINEAR;

    public static Turn fromPoints(GraphNode a, GraphNode b, GraphNode c) {
        double cross = ((b.getX() - a.getX()) * (c.getY() - a.getY()))
                - ((b.getY() - a.getY()) * (c.getX() - a.getX()));
        if (cross > 0) return COUNTER_CLOCKWISE;
        if (cross < 0) return CLOCKWISE;
        return COLLINEAR;
    }
}
