package datastructures;

import api.AbstractEdge;
import org.json.JSONObject;

/**
 * This class represents an edge in the graph. Each each can be represented as a pair (u,v) where u is the
 * id of the source node and v is the id of the destenation node, as well as the weight of every edge.
 */
public class Edge implements AbstractEdge {
    private int src, dest, type;
    private double weight;
    private double value=0;
    public Edge(int src, int dest, double w){
        this.src=src;
        this.dest=dest;
        this.weight=w;
        this.type= src>dest?-1:1;
    }

    /**
     * This function returns the id of the source node of the edge
     * @return src
     */
    @Override
    public int getSource() {
        return this.src;
    }

    /**
     * This function returns the weight of the edge
     * @return
     */
    @Override
    public double getWeight() {
        return this.weight;
    }

    /**
     * This function returns the id of the destination node of the edge
     * @return
     */
    @Override
    public int getDestination() {
        return this.dest;
    }

    /**
     * This function set a new source to the edge
     * @param source The id of the source node.
     * @return
     */
    @Override
    public AbstractEdge setSource(int source) {
        this.src=source;
        return this;
    }

    /**
     * This function set a new weight to the edge
     * @param weight The weight of the edge.
     * @return
     */
    @Override
    public AbstractEdge setWeight(double weight) {
        this.weight=weight;
        return this;
    }

    /**
     * This function set a new destination to the edge
     * @param destination The id of the destination node.
     * @return
     */
    @Override
    public AbstractEdge setDestination(int destination) {
        this.dest=destination;
        return this;
    }

    /**
     * Thsis function return a json object which contain the data of the edge
     * @return
     */
    @Override
    public JSONObject toJSON() {
        JSONObject json =new JSONObject();
        json.put("src", this.src);
        json.put("w", this.weight);
        json.put("dest", this.dest);
        return json;
    }

    /**
     * This function compare between two edges
     * @param o
     * @return
     */
    @Override
    public int compareTo(AbstractEdge o) {
        return 0;
    }

    /**
     * This function returns true if they are equal, otherwise false
     * @param e
     * @return
     */
    public boolean equals(Object e){
        if (!(e instanceof AbstractEdge)){
            return false;
        }
        boolean b1=((AbstractEdge) e).getSource()==this.src;
        boolean b2=(((AbstractEdge) e).getWeight())==this.weight;
        boolean b3=((AbstractEdge) e).getDestination()==this.dest;
        return b1&&b2&&b3;
    }

    /**
     * This function returns hte type of the edge
     * @return
     */
    public int getType(){
        return this.type;
    }

    /**
     * This function returns a string representation of the edge
     * @return
     */
    public String toString(){
        return "("+src+", "+dest+"): "+weight;
    }

    /**
     * This function return the value of the edge
     * @return
     */
    public double getValue(){
        return this.value;
    }

    /**
     * This function update the value of the edge
     * @param extra
     */
    public void updateValue(double extra){
        this.value+=extra;
    }

    /**
     * This function reset the value of the edge to 0
     */
    public void resetValue(){
        this.value=0;
    }
}
