package api;

import org.json.JSONObject;

public interface AbstractNode extends Comparable<AbstractNode> {
    
    /**
     * @return The ID of the node.
     */
    public int getID();
    
    /**
     * @return The value of the node.
     */
    public double getValue();
    
    /**
     * @param id The ID of the node.
     * @return The node instance
     */
    public AbstractNode setID(int id);
    
    /**
     * @param value The value of the node.
     * @return The node instance
     */
    public AbstractNode setValue(double value);
    
    /**
     * @return JSONObject representation of the node.
     */
    default JSONObject toJSON() {
        return new JSONObject().put("id", this.getID());
    }
}
