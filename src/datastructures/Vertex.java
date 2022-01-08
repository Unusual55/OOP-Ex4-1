package datastructures;

import api.AbstractNode;
import org.json.JSONObject;

import java.util.Objects;

public class Vertex extends Node implements AbstractNode {
    private double x;
    private double y;
    private double z;
    
    /**
     * @param id    The id of the vertex
     * @param value The value of the vertex
     * @param x     The x coordinate of the vertex
     * @param y     The y coordinate of the vertex
     * @param z     The z coordinate of the vertex
     */
    public Vertex(int id, double value, double x, double y, double z) {
        super(id, value);
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * @param id The id of the vertex
     * @param x  The x coordinate of the vertex
     * @param y  The y coordinate of the vertex
     * @param z  The z coordinate of the vertex
     */
    public Vertex(int id, double x, double y, double z) {
        this(id, 0.0, x, y, z);
    }
    
    /**
     * @param id The id of the vertex
     * @param x  The x coordinate of the vertex
     * @param y  The y coordinate of the vertex
     */
    public Vertex(int id, double x, double y) {
        this(id, 0.0, x, y, 0.0);
    }
    
    /**
     * @param id        The id of the vertex
     * @param value     The value of the vertex
     * @param xyz       The xyz coordinates of the vertex
     * @param delimiter The delimiter of the xyz coordinates
     */
    private Vertex(int id, double value, String xyz, String delimiter) {
        this(id, value);
        this.setXYZ(xyz, delimiter);
    }
    
    /**
     * @param id        The id of the vertex
     * @param xyz       The xyz coordinates of the vertex
     * @param delimiter The delimiter of the xyz coordinates
     */
    private Vertex(int id, String xyz, String delimiter) {
        this(id, 0.0, xyz, delimiter);
    }
    
    /**
     * @param id    The id of the vertex
     * @param value The value of the vertex
     * @param xyz   The xyz coordinates of the vertex
     */
    public Vertex(int id, double value, String xyz) {
        this(id, value, xyz, ",");
    }
    
    /**
     * @param id  The id of the vertex
     * @param xyz The xyz coordinates of the vertex
     */
    public Vertex(int id, String xyz) {
        this(id, 0.0, xyz, ",");
    }
    
    /**
     * @param id    The id of the vertex
     * @param value The value of the vertex
     */
    public Vertex(int id, double value) {
        this(id, value, 0.0, 0.0, 0.0);
    }
    
    /**
     * @param id The id of the vertex
     */
    public Vertex(int id) {
        this(id, 0.0);
    }
    
    /**
     * @param v The vertex to copy
     */
    public Vertex(Vertex v) {
        this(v.getID(), v.getValue(), v.getX(), v.getY(), v.getZ());
    }
    
    /**
     * @return The x coordinate of the vertex
     */
    public double getX() {
        return this.x;
    }
    
    /**
     * @return The y coordinate of the vertex
     */
    public double getY() {
        return this.y;
    }
    
    /**
     * @return The z coordinate of the vertex
     */
    public double getZ() {
        return this.z;
    }
    
    /**
     * @return The xyz coordinates of the vertex
     */
    public double[] getXYZ() {
        return new double[]{this.x, this.y, this.z};
    }
    
    /**
     * @param delimiter The delimiter of the xyz coordinates
     * @return The xyz coordinates of the vertex separated by the delimiter
     */
    private String getXYZString(String delimiter) {
        return this.x + delimiter + this.y + delimiter + this.z;
    }
    
    /**
     * @return The xyz coordinates of the vertex separated by a comma, e.g. "x,y,z"
     */
    public String getXYZString() {
        return this.getXYZString(",");
    }
    
    /**
     * @param x The x coordinate of the vertex
     * @return The vertex with the x coordinate set to the given value
     */
    public Vertex setX(double x) {
        this.x = x;
        return this;
    }
    
    /**
     * @param y The y coordinate of the vertex
     * @return The vertex with the y coordinate set to the given value
     */
    public Vertex setY(double y) {
        this.y = y;
        return this;
    }
    
    /**
     * @param z The z coordinate of the vertex
     * @return The vertex with the z coordinate set to the given value
     */
    public Vertex setZ(double z) {
        this.z = z;
        return this;
    }
    
    /**
     * @param x The x coordinate of the vertex
     * @param y The y coordinate of the vertex
     * @param z The z coordinate of the vertex
     * @return The vertex with the xyz coordinates set to the given values
     */
    public Vertex setXYZ(double x, double y, double z) {
        return this.setX(x).setY(y).setZ(z);
    }
    
    /**
     * @param v The vertex to copy the xyz coordinates from
     * @return The vertex with the xyz coordinates set to the given values
     */
    public Vertex setXYZ(Vertex v) {
        return this.setXYZ(v.getX(), v.getY(), v.getZ());
    }
    
    /**
     * @param x The x coordinate of the vertex
     * @param y The y coordinate of the vertex
     * @param z The z coordinate of the vertex
     * @return The vertex with the xyz coordinates set to the given values
     */
    public Vertex setXYZ(String x, String y, String z) {
        try {
            return this.setXYZ(Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return this;
        }
    }
    
    /**
     * @param xyz       The xyz coordinates of the vertex
     * @param delimiter The delimiter of the xyz coordinates
     * @return The vertex with the xyz coordinates set to the given values
     */
    private Vertex setXYZ(String xyz, String delimiter) {
        String[] xyzArray = xyz.trim().split(delimiter);
        return this.setXYZ(xyzArray[0], xyzArray[1], xyzArray[2]);
    }
    
    /**
     * @param xyz The xyz coordinates of the vertex
     * @return The vertex with the xyz coordinates set to the given values
     */
    public Vertex setXYZ(String xyz) {
        return this.setXYZ(xyz, ",");
    }
    
    
    /**
     * @param x The x coordinate of the vertex
     * @param y The y coordinate of the vertex
     * @param z The z coordinate of the vertex
     * @return The squared distance between the vertex and the given coordinates
     */
    public double squaredDistance(double x, double y, double z) {
        return Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2);
    }
    
    /**
     * @param v The vertex to calculate the squared distance to
     * @return The squared distance between the vertex and the given vertex
     */
    public double squaredDistance(Vertex v) {
        return this.squaredDistance(v.getX(), v.getY(), v.getZ());
    }
    
    /**
     * @return The squared distance between the vertex and the origin
     */
    public double squaredDistance() {
        return this.squaredDistance(0, 0, 0);
    }
    
    /**
     * @param x The x coordinate of the vertex
     * @param y The y coordinate of the vertex
     * @param z The z coordinate of the vertex
     * @return The distance between the vertex and the given coordinates
     */
    public double distance(double x, double y, double z) {
        return Math.sqrt(this.squaredDistance(x, y, z));
    }
    
    /**
     * @param v The vertex to calculate the distance to
     * @return The distance between the vertex and the given vertex
     */
    public double distance(Vertex v) {
        return this.distance(v.getX(), v.getY(), v.getZ());
    }
    
    /**
     * @return The distance between the vertex and the origin
     */
    public double distance() {
        return this.distance(0, 0, 0);
    }

    /**
     * This function return a json object which contains this vertex data
     * @return
     */
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("pos", this.getXYZString());
        return json;
    }

    /**
     * This fucntion returns a string representation of this vertex
     * @return
     */
    @Override
    public String toString() {
        return '{' + "id=" + this.getID() + ", value=" + this.getValue() + ", pos=" + this.getXYZString() + '}';
    }

    /**
     * This function get an object as input and check if its properties are equal to this vertex properties
     * @param o The input object
     * @return True if they are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex v)) return false;
        return this.getID() == v.getID() &&
                Double.compare(this.getValue(), v.getValue()) == 0 &&
                Double.compare(this.getX(), v.getX()) == 0 &&
                Double.compare(this.getY(), v.getY()) == 0 &&
                Double.compare(this.getZ(), v.getZ()) == 0;
    }

    /**
     * This fucntion returns the hashed int of this vertex
     * @return The hashed int of this vertex
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getID(), this.getValue(), this.getX(), this.getY(), this.getZ());
    }
}
