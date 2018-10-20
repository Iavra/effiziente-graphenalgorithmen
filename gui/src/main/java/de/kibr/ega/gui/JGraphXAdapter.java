package de.kibr.ega.gui;

import com.mxgraph.model.mxICell;
import com.mxgraph.view.mxGraph;
import de.kibr.ega.graph.Graph;
import de.kibr.ega.graph.GraphEdge;
import de.kibr.ega.graph.GraphNode;

import java.util.Objects;

public class JGraphXAdapter extends mxGraph {
    private Graph graph;

    public void setGraph(Graph graph) {
        this.graph = Objects.requireNonNull(graph);
    }

    private void addNode(GraphNode node) {
        updateModel(() -> {
            mxICell cell = (mxICell) insertVertex(defaultParent, null, node, node.getX(), node.getY(), 0, 0);
            updateCellSize(cell);
        });
    }
    private void addEdge(GraphEdge edge) {
        updateModel(() -> {
            mxICell cell = (mxICell) insertEdge(defaultParent, null, edge, edge.getSource(), edge.getTarget());
            updateCellSize(cell);
        });
    }

    private void updateModel(Runnable command) {
        getModel().beginUpdate();
        try {
            command.run();
        } finally {
            getModel().endUpdate();
        }
    }
}
