package de.kibr.ega.generator.capacity;

import de.kibr.ega.graph.Graph;
import de.kibr.ega.graph.GraphEdge;

import java.util.List;
import java.util.Random;

public class SourceCutCapacityGenerator extends BaseCapacityGenerator {
    private static final Random RANDOM = new Random();

    public SourceCutCapacityGenerator(int maxCapacity) {
        super(maxCapacity);
    }

    @Override
    public void setCapacities(Graph graph) {
        List<GraphEdge> edges = graph.getEdgesFrom(graph.getSource());
        int[] capacities = splitRandomly(maxCapacity, edges.size());
        for (int i = 0; i < edges.size(); i++)
            edges.get(i).setCapacity(capacities[i]);
        // TODO: Fill the remaining edges while ensuring the first cut remains minimal
    }

    private void fillCapacities(Graph graph, GraphEdge edge) {

    }

    private int[] splitRandomly(int value, int parts) {
        if (value < 0) throw new IllegalArgumentException("value has to be positive");
        int[] result = new int[parts];
        for (int i = 0; i < parts; i++) {
            result[i] = RANDOM.nextInt(value);
            value -= result[i];
        }
        return result;
    }
}
