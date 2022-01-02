package api;

import org.json.JSONObject;

public interface AbstractEdge {
    /**
     * @return The id of the source node.
     */
    public int getSource();
    
    /**
     * @return The weight of the edge.
     */
    public double getWeight();
    
    /**
     * @return The id of the destination node.
     */
    public int getDestination();
    
    /**
     * @return The type of the edge. (src < dest => type > 0, src > dest => type < 0)
     */
    default int getType() {
        return this.getSource() > this.getDestination() ? -1 : 1;
    }
    
    
    /**
     * @param source The id of the source node.
     * @return The edge with the given source.
     */
    public AbstractEdge setSource(int source);
    
    /**
     * @param weight The weight of the edge.
     * @return The edge with the given weight.
     */
    public AbstractEdge setWeight(double weight);
    
    /**
     * @param destination The id of the destination node.
     * @return The edge with the given destination.
     */
    public AbstractEdge setDestination(int destination);
    
    //    /**
//     * @return JSONObject representation of the node.
//     */
    default JSONObject toJSON() {
        return new JSONObject()
                .put("src", this.getSource())
                .put("w", this.getWeight())
                .put("dest", this.getDestination());
    }
}
