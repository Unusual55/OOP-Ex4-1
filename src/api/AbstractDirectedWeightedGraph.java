package api;

import datastructures.Edge;
import datastructures.Node;

import java.util.Iterator;

public interface AbstractDirectedWeightedGraph extends AbstractGraph {
    
    /**
     * @param node The id of the node.
     * @return If the node exists in the graph.
     */
    default boolean hasNode(int node) {
        return this.hasNode(new Node(node));
    }
    
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @return If the edge exists in the graph.
     */
    default boolean hasEdge(int source, int destination) {
        return this.hasEdge(new Edge(source, destination));
    }
    
    
    /**
     * @param node The id of the node.
     * @return The node with the given id.
     */
    default AbstractNode getNode(int node) {
        return this.getNode(new Node(node));
    }
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @return The edge with the given source and destination.
     */
    default AbstractEdge getEdge(int source, int destination) {
        return this.getEdge(new Edge(source, destination));
    }
    
    
    /**
     * @param id    The id of the node to add.
     * @param value The value of the node to add.
     * @return True if the node was added, false otherwise (If the node already exists).
     */
    default boolean addNode(int id, double value) {
        return this.addNode(new Node(id, value));
    }
    
    /**
     * @param id The id of the node to add.
     * @return True if the node was added, false otherwise (If the node already exists).
     */
    default boolean addNode(int id) {
        return this.addNode(id, 0.0);
    }
    
    /**
     * @param node The node to add.
     * @return The graph instance.
     */
    default AbstractDirectedWeightedGraph newNode(AbstractNode node) {
        this.addNode(node);
        return this;
    }
    
    /**
     * @param id    The id of the new node
     * @param value The value of the new node
     * @return The graph instance
     */
    default AbstractDirectedWeightedGraph newNode(int id, double value) {
        return this.newNode(new Node(id, value));
    }
    
    /**
     * @param id The id of the new node
     * @return The graph instance
     */
    default AbstractDirectedWeightedGraph newNode(int id) {
        return this.newNode(id, 0.0);
    }
    
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @param weight      The weight of the edge to add.
     * @return True if the edge was added, false otherwise
     */
    default boolean addEdge(int source, int destination, double weight) {
        return this.addEdge(new Edge(source, destination, weight));
    }
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @return True if the edge was added, false otherwise
     */
    default boolean addEdge(int source, int destination) {
        return this.addEdge(source, destination, 1.0);
    }
    
    /**
     * @param edge The edge to add.
     * @return The graph instance.
     */
    default AbstractDirectedWeightedGraph connect(AbstractEdge edge) {
        this.addEdge(edge);
        return this;
    }
    
    /**
     * @param source      The id of the source node
     * @param destination The id of the destination node
     * @param weight      The weight of the edge
     * @return The graph instance
     */
    default AbstractDirectedWeightedGraph connect(int source, int destination, double weight) {
        return this.connect(new Edge(source, destination, weight));
    }
    
    /**
     * @param source      The id of the source node
     * @param destination The id of the destination node
     * @return The graph instance
     */
    default AbstractDirectedWeightedGraph connect(int source, int destination) {
        return this.connect(source, destination, 1.0);
    }
    
    /**
     * @param node The id of the node to remove.
     * @return True if the node was removed, false otherwise (If the node does not exist).
     */
    default boolean removeNode(int node) {
        return this.removeNode(new Node(node));
    }
    
    /**
     * @param node The node to remove
     * @return The graph instance
     */
    default AbstractDirectedWeightedGraph deleteNode(AbstractNode node) {
        this.removeNode(node);
        return this;
    }
    
    /**
     * @param id The id of the node to be removed
     * @return The graph instance
     */
    default AbstractDirectedWeightedGraph deleteNode(int id) {
        return this.deleteNode(new Node(id));
    }
    
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @return True if the edge was removed, false otherwise (If the edge does not exist).
     */
    default boolean removeEdge(int source, int destination) {
        return this.removeEdge(new Edge(source, destination));
    }
    
    /**
     * @param edge The edge to remove
     * @return The graph instance
     */
    default AbstractDirectedWeightedGraph disconnect(AbstractEdge edge) {
        this.removeEdge(edge);
        return this;
    }
    
    /**
     * @param source      The id of the source node of the edge
     * @param destination The id of the destination node of the edge
     * @return The graph instance
     */
    default AbstractDirectedWeightedGraph disconnect(int source, int destination) {
        return this.disconnect(new Edge(source, destination));
    }
    
    
    /**
     * @param node The id of the node
     * @return An iterator over the adjacent nodes of the given node.
     */
    default Iterator<AbstractNode> getAdjacentNodes(int node) {
        return this.getAdjacentNodes(new Node(node));
    }
    
    /**
     * @param node The id of the node
     * @return An iterator over the in-edges of the node, or null if the node does not exist.
     */
    default Iterator<AbstractEdge> getInEdges(int node) {
        return this.getInEdges(new Node(node));
    }
    
    
    /**
     * @param node The id of the node
     * @return An iterator over the out-edges of the node, or null if the node does not exist.
     */
    default Iterator<AbstractEdge> getOutEdges(int node) {
        return this.getOutEdges(new Node(node));
    }
    
    
    /**
     * @param node The id of the node
     * @return The in-degree of the node, or 0 if the node does not exist. (The in-degree of a node is the number of edges that have the node as their destination.)
     */
    default int inDegree(int node) {
        return this.inDegree(new Node(node));
    }
    
    
    /**
     * @param node The id of the node
     * @return The out-degree of the node, or 0 if the node does not exist. (The out-degree of a node is the number of edges that have the node as their source.)
     */
    default int outDegree(int node) {
        return this.outDegree(new Node(node));
    }
    
}
