package de.kibr.ega.gui;

import com.google.common.collect.HashBiMap;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxICell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxPerimeter;
import com.mxgraph.view.mxStylesheet;
import de.kibr.ega.graph.Graph;
import de.kibr.ega.graph.GraphEdge;
import de.kibr.ega.graph.GraphElement;
import de.kibr.ega.graph.GraphNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JGraphXAdapter extends mxGraph {
    private static final String STYLE_NODE = "custom_node_style";
    private static final String STYLE_EDGE = "custom_edge_style";

    private Graph graph;

    Map<GraphNode, mxICell> nodeMapping = HashBiMap.create();
    Map<GraphEdge, mxICell> edgeMapping = HashBiMap.create();

    public JGraphXAdapter() {
        setLayout();
        createStyles();
    }

    public void setGraph(Graph graph) {
        this.graph = Objects.requireNonNull(graph);
        graph.getNodes().forEach(this::addNode);
        graph.getEdges().forEach(this::addEdge);
    }

    private void addNode(GraphNode node) {
        updateModel(() -> {
            mxICell cell = (mxICell) insertVertex(defaultParent, null,
                    node, node.getX(), node.getY(), 0, 0, STYLE_NODE);
            updateCellSize(cell);
            nodeMapping.put(node, cell);
        });
    }

    private void addEdge(GraphEdge edge) {
        updateModel(() -> {
            mxICell source = nodeMapping.get(edge.getSource());
            mxICell target = nodeMapping.get(edge.getTarget());
            if (source == null || target == null) return;
            mxICell cell = (mxICell) insertEdge(defaultParent, null,
                    edge, source, target, STYLE_EDGE);
            updateCellSize(cell);
            edgeMapping.put(edge, cell);
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
    private void setLayout() {
        // new mxParallelEdgeLayout(this).execute(defaultParent);
    }

    private void createStyles() {
        mxStylesheet stylesheet = getStylesheet();
        stylesheet.putCellStyle(STYLE_NODE, createNodeStyle());
        stylesheet.putCellStyle(STYLE_EDGE, createEdgeStyle());
    }

    private Map<String, Object> createNodeStyle() {
        Map<String, Object> style = new HashMap<>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        style.put(mxConstants.STYLE_PERIMETER, mxPerimeter.EllipsePerimeter);
        style.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
        style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
        style.put(mxConstants.STYLE_FILLCOLOR, "#C3D9FF");
        style.put(mxConstants.STYLE_STROKECOLOR, "#6482B9");
        style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
        return style;
    }

    private Map<String, Object> createEdgeStyle() {
        Map<String, Object> style = new HashMap<>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        style.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
        style.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
        style.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
        style.put(mxConstants.STYLE_STROKECOLOR, "#6482B9");
        style.put(mxConstants.STYLE_FONTCOLOR, "#446299");
        return style;
    }

    @Override
    public String convertValueToString(Object cell) {
        Object value = model.getValue(cell);
        if (value instanceof GraphElement)
            return ((GraphElement) value).getLabel();
        return super.convertValueToString(cell);
    }
}
