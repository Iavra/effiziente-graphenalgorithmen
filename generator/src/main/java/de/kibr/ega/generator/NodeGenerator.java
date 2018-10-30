package de.kibr.ega.generator;

import org.apache.commons.math3.random.HaltonSequenceGenerator;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates pseudo random positions using the Halton sequence to ensure a better spread
 * and less issues due to points being near to each other
 */
public class NodeGenerator {
    private final HaltonSequenceGenerator generator = new HaltonSequenceGenerator(2);

    private final double xMax;
    private final double yMax;

    public NodeGenerator(double xMax, double yMax) {
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public List<Point2D> generate(int numNodes) {
        List<Point2D> result = new ArrayList<>();
        for (int i = 0; i < numNodes; i++)
            result.add(generateNode());
        return result;
    }

    private Point2D generateNode() {
        double[] vector = generator.nextVector();
        return new Point2D.Double(vector[0] * xMax, vector[1] * yMax);
    }
}
