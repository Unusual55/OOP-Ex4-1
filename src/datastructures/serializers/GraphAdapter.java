package datastructures.serializers;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import datastructures.Edge;
import datastructures.Vertex;
import org.jgrapht.alg.util.Pair;

import java.util.*;

/**
 * This class use both EdgeAdapter and NodeAdapter in order to load and save the graph to .json file
 */
public class GraphAdapter {
    @SerializedName("Edges")
    @Expose
    public List<Edge> Edges;
    @SerializedName("Nodes")
    @Expose
    public List<Vertex> Nodes;

    public GraphAdapter() {
        this.Edges = new ArrayList<>();
        this.Nodes = new ArrayList<>();
    }

    public GraphAdapter(List<Edge> edges, List<Vertex> nodes) {
        this.Edges = edges;
        this.Nodes = nodes;
    }

    /**
     * This function return the edges of the graph
     *
     * @return
     */
    public List<Edge> getEdges() {
        return this.Edges;
    }

    /**
     * This function set the edges of the graph
     *
     * @param edges
     */
    public void setEdges(List<Edge> edges) {
        this.Edges = edges;
    }

    /**
     * This function return the nodes of the graph
     *
     * @return
     */
    public List<Vertex> getNodes() {
        return this.Nodes;
    }

    /**
     * This function add a new edge to the graph
     *
     * @param edge
     */
    public void addEdge(Edge edge) {
        this.Edges.add(edge);
    }

    /**
     * This function add a new node to the graph
     *
     * @param node
     */
    public void addNode(Vertex node) {
        this.Nodes.add(node);
    }

    /**
     * This function set the nodes of the graph
     *
     * @param nodes
     */
    public void setNodes(List<Vertex> nodes) {
        this.Nodes = nodes;
    }

//    /**
//     * This function turn the input graph into an GraphAdapter object in order to serialize it.
//     * @param graph
//     * @return
//     */
//    public static GraphAdapter fromGraph(DirectedWeightedGraph graph) {
//        GraphAdapter adapter = new GraphAdapter();
//        Iterator<EdgeData> edgeIterator = graph.edgeIter();
//        while (edgeIterator.hasNext()) {
//            EdgeData edge = edgeIterator.next();
//            adapter.addEdge(edge);
//        }
//        Iterator<NodeData> nodeIterator = graph.nodeIter();
//        while (nodeIterator.hasNext()) {
//            NodeData node = nodeIterator.next();
//            adapter.addNode(node);
//        }
//        return adapter;
//    }

    /**
     * This function get an GraphAdapter and turn it into an object that implement the
     * DirectedWeightedGraph interface
     *
     * @param adapter
     * @return
     */
    public static Pair<HashMap<Integer, Vertex>, HashMap<Integer, HashMap<Integer, Edge>>> toGraph(GraphAdapter adapter) {
        HashMap<Integer, Vertex> nodes = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Edge>> edges = new HashMap<>();
        for (Vertex node : adapter.getNodes()) {
            nodes.put(node.getID(), node);
            edges.put(node.getID(), new HashMap<>());
        }
        for (Edge edge : adapter.getEdges()) {
            edges.get(edge.getSource()).put(edge.getDestination(), edge);
        }
        return new Pair<>(nodes, edges);
    }
}
