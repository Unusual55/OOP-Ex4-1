package datastructures;

import api.AbstractEdge;
import api.AbstractNode;
import org.json.JSONObject;

import java.util.Objects;

public class Edge implements AbstractEdge {
    private int source, destination;
    private double weight;
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @param weight      The weight of the edge.
     */
    public Edge(int source, int destination, double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive.");
        }
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    
    /**
     * Equivalent to Edge(source, destination, 1).
     *
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     */
    public Edge(int source, int destination) {
        this(source, destination, 1.0);
    }
    
    /**
     * @param edge The edge to copy.
     */
    public Edge(AbstractEdge edge) {
        this(edge.getSource(), edge.getDestination(), edge.getWeight());
    }
    
    /**
     * @return The id of the source node.
     */
    @Override
    public int getSource() {
        return this.source;
    }
    
    /**
     * @return The weight of the edge.
     */
    @Override
    public double getWeight() {
        return this.weight;
    }
    
    /**
     * @return The id of the destination node.
     */
    @Override
    public int getDestination() {
        return this.destination;
    }
    
    /**
     * @return The type of the edge. (if source > destination then type = -1 else type = 1)
     */
    @Override
    public int getType() {
        return (this.source > this.destination) ? -1 : 1;
    }
    
    /**
     * @param source The id of the source node.
     * @return The edge with the given source.
     */
    @Override
    public AbstractEdge setSource(int source) {
        this.source = source;
        return this;
    }
    
    /**
     * @param weight The weight of the edge.
     * @return The edge with the given weight.
     */
    @Override
    public AbstractEdge setWeight(double weight) {
        this.weight = weight;
        return this;
    }
    
    /**
     * @param destination The id of the destination node.
     * @return The edge with the given destination.
     */
    @Override
    public AbstractEdge setDestination(int destination) {
        this.destination = destination;
        return this;
    }
    
    /**
     * @return JSONObject representation of the node.
     */
    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("src", this.source);
        json.put("w", this.weight);
        json.put("dest", this.destination);
        return null;
    }
    
    @Override
    public String toString() {
        return '{' + "src=" + this.source + ", w=" + this.weight + ", dest=" + this.destination + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEdge)) return false;
        Edge O = (Edge)o;
        return this.source == O.getSource() && Double.compare(this.weight, O.getWeight()) == 0 && this.destination == O.getDestination();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.source, this.weight, this.destination);
    }
}
