package datastructures;

import api.AbstractNode;
import org.json.JSONObject;

import java.util.Objects;

public class Node implements AbstractNode {
    private int id = 0;
    private double value = 0.0;
    
    /**
     * @param id The ID of the node.
     * @param value The value of the node.
     */
    public Node(int id, double value) {
        this.id = id;
        this.value = value;
    }
    
    /**
     * @param id The ID of the node.
     */
    public Node(int id) {
        this(id, 0.0);
    }
    
    /**
     * @param node The node to copy.
     */
    public Node(AbstractNode node) {
        this(node.getID(), node.getValue());
    }
    
    
    /**
     * @return The ID of the node.
     */
    @Override
    public int getID() {
        return this.id;
    }
    
    /**
     * @return The value of the node.
     */
    @Override
    public double getValue() {
        return this.value;
    }
    
    /**
     * @param id The ID of the node.
     * @return The node instance
     */
    @Override
    public AbstractNode setID(int id) {
        this.id = id;
        return this;
    }
    
    /**
     * @param value The value of the node.
     * @return The node instance
     */
    @Override
    public AbstractNode setValue(double value) {
        this.value = value;
        return this;
    }
    
    
    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(AbstractNode o) {
        if (this.getValue() < o.getValue()) {
            return -1;
        } else if (this.getValue() > o.getValue()) {
            return 1;
        }
        return 0;
    }
    
    
    
    @Override
    public String toString() {
        return '{' + "id=" + this.id + ", value=" + this.value + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractNode)) return false;
        Node O = (Node)o;
        return this.id == O.getID() && Double.compare(this.value, O.getValue()) == 0;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.value);
    }
}
