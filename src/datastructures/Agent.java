package datastructures;

import api.AbstractAgent;

public class Agent implements AbstractAgent {
    private int id;
    private double x, y, z;
    private double speed, score;
    private int source, destination;
    
    public Agent(int id, int source, int destination, double x, double y, double value, double speed) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.score = value;
        this.x = x;
        this.y = y;
        this.z = 0.0;
        this.speed = speed;
    }
    
    public Agent(int id, int source, double speed, double x, double y) {
        this(id, source, -1, x, y, 0.0, speed);
    }
    
    
    /**
     * @return The id of the agent
     */
    @Override
    public int getID() {
        return this.id;
    }
    
    /**
     * @param id The id of the agent
     * @return The agent with the given id
     */
    @Override
    public AbstractAgent setID(int id) {
        this.id = id;
        return this;
    }
    
    /**
     * @return The score of the agent
     */
    @Override
    public double getScore() {
        return this.score;
    }
    
    /**
     * @param score The score of the agent
     * @return The agent with the given score
     */
    @Override
    public AbstractAgent setScore(double score) {
        this.score = score;
        return this;
    }
    
    /**
     * @return The id of the source
     */
    @Override
    public int getSource() {
        return this.source;
    }
    
    /**
     * @param source The id of the source
     * @return The agent with the given source
     */
    @Override
    public AbstractAgent setSource(int source) {
        this.source = source;
        return this;
    }
    
    /**
     * @return The id of the destination
     */
    @Override
    public int getDestination() {
        return this.destination;
    }
    
    /**
     * @param destination The id of the destination
     * @return The agent with the given destination
     */
    @Override
    public AbstractAgent setDestination(int destination) {
        this.destination = destination;
        return this;
    }
    
    /**
     * @return The speed of the agent
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }
    
    /**
     * @param speed The speed of the agent
     * @return The agent with the given speed
     */
    @Override
    public AbstractAgent setSpeed(double speed) {
        this.speed = speed;
        return this;
    }
    
    /**
     * @return The x-coordinate of the agent
     */
    @Override
    public double getX() {
        return this.x;
    }
    
    /**
     * @param x The x-coordinate of the agent
     * @return The agent with the given x-coordinate
     */
    @Override
    public AbstractAgent setX(double x) {
        this.x = x;
        return this;
    }
    
    /**
     * @return The y-coordinate of the agent
     */
    @Override
    public double getY() {
        return this.y;
    }
    
    /**
     * @param y The y-coordinate of the agent
     * @return The agent with the given y-coordinate
     */
    @Override
    public AbstractAgent setY(double y) {
        this.y = y;
        return this;
    }
    
    /**
     * @return The z-coordinate of the agent
     */
    @Override
    public double getZ() {
        return this.z;
    }
    
    /**
     * @param z The z-coordinate of the agent
     * @return The agent with the given z-coordinate
     */
    @Override
    public AbstractAgent setZ(double z) {
        this.z = z;
        return this;
    }
}
