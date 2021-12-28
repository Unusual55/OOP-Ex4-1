package api;

public interface AbstractEdge extends Comparable<AbstractEdge> {
    public int getSource();
    public double getWeight();
    public int getDestination();
    
    public AbstractEdge setSource(int source);
    public AbstractEdge setWeight(double weight);
    public AbstractEdge setDestination(int destination);
}
