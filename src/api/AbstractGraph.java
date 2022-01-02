package api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public interface AbstractGraph {
    /**
     * @return The number of modification operations performed on the graph.
     */
    public int getModCounter();
    
    /**
     * @param node The node to check.
     * @return If the node is in the graph.
     */
    public boolean hasNode(AbstractNode node);
    
    /**
     * @param edge The edge to check.
     * @return If the edge is in the graph.
     */
    public boolean hasEdge(AbstractEdge edge);
    
    /**
     * @param node The node to get.
     * @return The node instance. Null if the node does not exist in the graph.
     */
    public AbstractNode getNode(AbstractNode node);
    
    /**
     * @param edge The edge to get.
     * @return The edge instance. Null if the edge does not exist in the graph.
     */
    public AbstractEdge getEdge(AbstractEdge edge);
    
    /**
     * @param node The node to add.
     * @return True if the node was added, false otherwise (If the node already exists).
     */
    public boolean addNode(AbstractNode node);
    
    /**
     * @param edge The edge to add.
     * @return True if the edge was added, false otherwise (If the edge already exists).
     */
    public boolean addEdge(AbstractEdge edge);
    
    /**
     * @param node The node to remove.
     * @return True if the node was removed, false otherwise (If the node does not exist).
     */
    public boolean removeNode(AbstractNode node);
    
    /**
     * @param edge The edge to remove.
     * @return True if the edge was removed, false otherwise (If the edge does not exist).
     */
    public boolean removeEdge(AbstractEdge edge);
    
    /**
     * @param node The node.
     * @return An iterator over the neighbors of the node, or null if the node does not exist.
     */
    public Iterator<AbstractNode> getAdjacentNodes(AbstractNode node);
    
    /**
     * @return An iterator over the nodes in the graph.
     */
    public Iterator<AbstractNode> getNodes();
    
    /**
     * @return An iterator over the edges in the graph.
     */
    public Iterator<AbstractEdge> getEdges();
    
    /**
     * @param node The node.
     * @return An iterator over the in-edges of the node, or null if the node does not exist.
     */
    public Iterator<AbstractEdge> getInEdges(AbstractNode node);
    
    /**
     * @param node The node.
     * @return An iterator over the out-edges of the node, or null if the node does not exist.
     */
    public Iterator<AbstractEdge> getOutEdges(AbstractNode node);
    
    /**
     * @param node The node.
     * @return The in-degree of the node, or -1 if the node does not exist. (The in-degree of a node is the number of edges that have the node as their destination.)
     */
    public int inDegree(AbstractNode node);
    
    /**
     * @param node The node.
     * @return The out-degree of the node, or -1 if the node does not exist. (The out-degree of a node is the number of edges that have the node as their source.)
     */
    public int outDegree(AbstractNode node);
    
    /**
     * @return The number of nodes in the graph.
     */
    public int getNodeCount();
    
    /**
     * @return The number of edges in the graph.
     */
    public int getEdgeCount();
    
    /**
     * @return A JSON representation of the graph.
     */
    default JSONObject toJSON() {
        JSONObject json = new JSONObject();
        JSONArray nodes = new JSONArray();
        JSONArray edges = new JSONArray();
        Iterator<AbstractNode> nodeIterator = this.getNodes();
        while (nodeIterator.hasNext()) {
            AbstractNode node = nodeIterator.next();
            nodes.put(node.toJSON());
        }
        Iterator<AbstractEdge> edgeIterator = this.getEdges();
        while (edgeIterator.hasNext()) {
            AbstractEdge edge = edgeIterator.next();
            edges.put(edge.toJSON());
        }
        return json.put("Edges", edges).put("Nodes", nodes);
    }
    
}
