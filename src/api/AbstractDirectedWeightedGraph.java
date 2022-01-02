package api;

import java.util.Iterator;

public interface AbstractDirectedWeightedGraph extends AbstractGraph {
    
    /**
     * @param node The id of the node.
     * @return If the node exists in the graph.
     */
    public boolean hasNode(int node);
    
    /**
     * @param node The node to check.
     * @return If the node is in the graph.
     */
    default boolean hasNode(AbstractNode node) {
        return this.hasNode(node.getID());
    }
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @return If the edge exists in the graph.
     */
    public boolean hasEdge(int source, int destination);
    
    /**
     * @param edge The edge to check.
     * @return If the edge is in the graph.
     */
    default boolean hasEdge(AbstractEdge edge) {
        return this.hasEdge(edge.getSource(), edge.getDestination());
    }
    
    /**
     * @param node The id of the node.
     * @return The node with the given id.
     */
    public AbstractNode getNode(int node);
    
    /**
     * @param node The node to get.
     * @return The node instance. Null if the node does not exist in the graph.
     */
    default AbstractNode getNode(AbstractNode node) {
        return this.getNode(node.getID());
    }
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @return The edge with the given source and destination.
     */
    public AbstractEdge getEdge(int source, int destination);
    
    /**
     * @param edge The edge to get.
     * @return The edge instance. Null if the edge does not exist in the graph.
     */
    default AbstractEdge getEdge(AbstractEdge edge) {
        return this.getEdge(edge.getSource(), edge.getDestination());
    }
    
    /**
     * @param id    The id of the node to add.
     * @param value The value of the node to add.
     * @return True if the node was added, false otherwise (If the node already exists).
     */
    public boolean addNode(int id, double value);
    
    /**
     * @param id The id of the node to add.
     * @return True if the node was added, false otherwise (If the node already exists).
     */
    default boolean addNode(int id) {
        return this.addNode(id, 0.0);
    }
    
    /**
     * @param node The node to add.
     * @return True if the node was added, false otherwise (If the node already exists).
     */
    default boolean addNode(AbstractNode node) {
        return this.addNode(node.getID(), node.getValue());
    }
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @param weight      The weight of the edge to add.
     * @return True if the edge was added, false otherwise
     */
    public boolean addEdge(int source, int destination, double weight);
    
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
     * @return True if the edge was added, false otherwise
     */
    default boolean addEdge(AbstractEdge edge) {
        return this.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
    }
    
    /**
     * @param node The id of the node to remove.
     * @return True if the node was removed, false otherwise (If the node does not exist).
     */
    public boolean removeNode(int node);
    
    /**
     * @param node The node to remove.
     * @return True if the node was removed, false otherwise (If the node does not exist).
     */
    default boolean removeNode(AbstractNode node) {
        return this.removeNode(node.getID());
    }
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @return True if the edge was removed, false otherwise (If the edge does not exist).
     */
    public boolean removeEdge(int source, int destination);
    
    /**
     * @param edge The edge to remove.
     * @return True if the edge was removed, false otherwise (If the edge does not exist).
     */
    default boolean removeEdge(AbstractEdge edge) {
        return this.removeEdge(edge.getSource(), edge.getDestination());
    }
    
    /**
     * @param node The id of the node
     * @return An iterator over the adjacent nodes of the given node.
     */
    public Iterator<AbstractNode> getAdjacentNodes(int node);
    
    /**
     * @param node The node
     * @return An iterator over the adjacent nodes of the given node.
     */
    default Iterator<AbstractNode> getAdjacentNodes(AbstractNode node) {
        return this.getAdjacentNodes(node.getID());
    }
    
    /**
     * @param node The id of the node
     * @return An iterator over the in-edges of the node, or null if the node does not exist.
     */
    public Iterator<AbstractEdge> getInEdges(int node);
    
    /**
     * @param node The node
     * @return An iterator over the in-edges of the node, or null if the node does not exist.
     */
    default Iterator<AbstractEdge> getInEdges(AbstractNode node) {
        return this.getInEdges(node.getID());
    }
    
    /**
     * @param node The id of the node
     * @return An iterator over the out-edges of the node, or null if the node does not exist.
     */
    public Iterator<AbstractEdge> getOutEdges(int node);
    
    /**
     * @param node The node
     * @return An iterator over the out-edges of the node, or null if the node does not exist.
     */
    default Iterator<AbstractEdge> getOutEdges(AbstractNode node) {
        return this.getOutEdges(node.getID());
    }
    
    /**
     * @param node The id of the node
     * @return The in-degree of the node, or 0 if the node does not exist. (The in-degree of a node is the number of edges that have the node as their destination.)
     */
    public int inDegree(int node);
    
    /**
     * @param node The node.
     * @return The in-degree of the node, or 0 if the node does not exist. (The in-degree of a node is the number of edges that have the node as their destination.)
     */
    default int inDegree(AbstractNode node) {
        return this.inDegree(node.getID());
    }
    
    /**
     * @param node The id of the node
     * @return The out-degree of the node, or 0 if the node does not exist. (The out-degree of a node is the number of edges that have the node as their source.)
     */
    public int outDegree(int node);
    
    /**
     * @param node The node.
     * @return The out-degree of the node, or 0 if the node does not exist. (The out-degree of a node is the number of edges that have the node as their source.)
     */
    default int outDegree(AbstractNode node) {
        return this.outDegree(node.getID());
    }
}
