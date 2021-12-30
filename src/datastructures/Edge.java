package datastructures;

import api.AbstractEdge;
import org.json.JSONObject;

public class Edge implements AbstractEdge {
    private int src, dest, type;
    private double weight;
    public Edge(int src, int dest, double w){
        this.src=src;
        this.dest=dest;
        this.weight=w;
        this.type= src>dest?-1:1;
    }
    @Override
    public int getSource() {
        return this.src;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public int getDestination() {
        return this.dest;
    }

    @Override
    public AbstractEdge setSource(int source) {
        this.src=source;
        return this;
    }

    @Override
    public AbstractEdge setWeight(double weight) {
        this.weight=weight;
        return this;
    }

    @Override
    public AbstractEdge setDestination(int destination) {
        this.dest=destination;
        return this;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json =new JSONObject();
        json.put("src", this.src);
        json.put("w", this.weight);
        json.put("dest", this.dest);
        return json;
    }

    @Override
    public int compareTo(AbstractEdge o) {
        return 0;
    }

    public boolean equals(Object e){
        if (!(e instanceof AbstractEdge)){
            return false;
        }
        boolean b1=((AbstractEdge) e).getSource()==this.src;
        boolean b2=(((AbstractEdge) e).getWeight())==this.weight;
        boolean b3=((AbstractEdge) e).getDestination()==this.dest;
        return b1&&b2&&b3;
    }

    public int getType(){
        return this.type;
    }

    public String toString(){
        return "("+src+", "+dest+"): "+weight;
    }
}
