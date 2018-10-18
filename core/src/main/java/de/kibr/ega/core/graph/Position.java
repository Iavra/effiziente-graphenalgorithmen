package de.kibr.ega.core.graph;

import static java.lang.Math.*;

public class Position {
    private final double x;
    private final double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(Position other) {
        return abs(sqrt(pow(x - other.x, 2) + pow(y - other.y, 2)));
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static Position randomPosition() {
        return new Position(Math.random() * 100, Math.random() * 100);
    }
}
