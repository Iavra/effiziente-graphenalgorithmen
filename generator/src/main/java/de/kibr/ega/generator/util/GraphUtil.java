package de.kibr.ega.generator.util;

import de.kibr.ega.graph.GraphNode;

import java.util.*;

public class GraphUtil {
    private GraphUtil() {}

    /**
     * Uses the Graham Scan algorithm, source: https://github.com/bkiers/GrahamScan
     */
    public static List<GraphNode> findConvexHull(List<GraphNode> nodes) {
        if (nodes.size() < 3) throw new IllegalArgumentException("need 3 or more points");
        List<GraphNode> sorted = new ArrayList<>(sortByAngle(nodes));
        if (areAllPointsCollinear(sorted)) throw new IllegalArgumentException("all points are collinear");

        Deque<GraphNode> stack = new LinkedList<>();
        stack.push(sorted.get(0));
        stack.push(sorted.get(1));

        for (int i = 2; i < sorted.size(); i++) {
            GraphNode head = sorted.get(i);
            GraphNode middle = stack.pop();
            GraphNode tail = stack.peek();

            Turn turn = Turn.fromPoints(tail, middle, head);
            switch(turn) {
                case COUNTER_CLOCKWISE:
                    stack.push(middle);
                    stack.push(head);
                    break;
                case CLOCKWISE:
                    i--;
                    break;
                case COLLINEAR:
                    stack.push(head);
                    break;
                default:
                    throw new IllegalArgumentException("unknown turn: " + turn);
            }
        }

        stack.push(sorted.get(0));
        return new ArrayList<>(stack);
    }

    private static Set<GraphNode> sortByAngle(List<GraphNode> nodes) {
        GraphNode lowest = getLowestPoint(nodes);
        Set<GraphNode> sortedSet = new TreeSet<>(new GraphNodeByAngleComparator(lowest));
        sortedSet.addAll(nodes);
        return sortedSet;
    }

    private static GraphNode getLowestPoint(List<GraphNode> nodes) {
        GraphNode lowest = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            GraphNode temp = nodes.get(i);
            if (temp.getY() < lowest.getY() || (temp.getY() == lowest.getY() && temp.getX() < lowest.getX()))
                lowest = temp;
        }
        return lowest;
    }

    private static boolean areAllPointsCollinear(List<GraphNode> nodes) {
        if (nodes.size() < 3) return true;
        GraphNode a = nodes.get(0);
        GraphNode b = nodes.get(1);
        for (int i = 2; i < nodes.size(); i++) {
            GraphNode c = nodes.get(i);
            if (Turn.fromPoints(a, b, c) != Turn.COLLINEAR) return false;
        }
        return true;
    }

    private static class GraphNodeByAngleComparator implements Comparator<GraphNode> {
        private final GraphNode lowest;

        private GraphNodeByAngleComparator(GraphNode lowest) {
            this.lowest = lowest;
        }

        @Override
        public int compare(GraphNode a, GraphNode b) {
            if (a == b || a.equals(b)) return 0;

            double thetaA = Math.atan2(a.getY() - lowest.getY(), a.getX() - lowest.getX());
            double thetaB = Math.atan2(b.getY() - lowest.getY(), b.getX() - lowest.getX());

            if (thetaA < thetaB) return -1;
            if (thetaA > thetaB) return 1;

            double distanceA = a.distanceTo(lowest);
            double distanceB = b.distanceTo(lowest);

            return distanceA < distanceB ? -1 : 1;
        }
    }
}
