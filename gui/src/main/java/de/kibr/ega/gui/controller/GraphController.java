package de.kibr.ega.gui.controller;

import com.mxgraph.swing.mxGraphComponent;
import de.kibr.ega.generator.PlanarGraphGenerator;
import de.kibr.ega.generator.edge.EdgeGenerator;
import de.kibr.ega.generator.edge.LinearEdgeGenerator;
import de.kibr.ega.generator.node.NodeGenerator;
import de.kibr.ega.generator.node.RandomNodeGenerator;
import de.kibr.ega.graph.Graph;
import de.kibr.ega.gui.JGraphXAdapter;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class GraphController implements Initializable {
    @FXML private SwingNode graphContainer;

    private JGraphXAdapter adapter = new JGraphXAdapter();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SwingUtilities.invokeLater(() -> {
            mxGraphComponent component = new mxGraphComponent(adapter);
            component.setBounds(0, 0, 500, 500);
            component.setEnabled(false);
            graphContainer.setContent(component);
            generateGraph();
        });
    }

    private void generateGraph() {
        NodeGenerator nodeGenerator = new RandomNodeGenerator(500, 500);
        EdgeGenerator edgeGenerator = new LinearEdgeGenerator();
        Graph graph = new PlanarGraphGenerator(nodeGenerator, edgeGenerator, 5, 0).generateGraph();
        adapter.setGraph(graph);
    }
}
