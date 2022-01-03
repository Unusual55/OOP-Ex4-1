package api;

public interface AbstractAgent {
    
    /**
     * @return The id of the agent
     */
    public int getID();
    
    /**
     * @param id The id of the agent
     * @return The agent with the given id
     */
    public AbstractAgent setID(int id);
    
    /**
     * @return The score of the agent
     */
    public double getScore();
    
    /**
     * @param score The score of the agent
     * @return The agent with the given score
     */
    public AbstractAgent setScore(double score);
    
    /**
     * @return The id of the source
     */
    public int getSource();
    
    /**
     * @param source The id of the source
     * @return The agent with the given source
     */
    public AbstractAgent setSource(int source);
    
    /**
     * @return The id of the destination
     */
    public int getDestination();
    
    /**
     * @param destination The id of the destination
     * @return The agent with the given destination
     */
    public AbstractAgent setDestination(int destination);
    
    /**
     * @return The speed of the agent
     */
    public double getSpeed();
    
    /**
     * @param speed The speed of the agent
     * @return The agent with the given speed
     */
    public AbstractAgent setSpeed(double speed);
    
    /**
     * @return The x-coordinate of the agent
     */
    public double getX();
    
    /**
     * @param x The x-coordinate of the agent
     * @return The agent with the given x-coordinate
     */
    public AbstractAgent setX(double x);
    
    /**
     * @return The y-coordinate of the agent
     */
    public double getY();
    
    /**
     * @param y The y-coordinate of the agent
     * @return The agent with the given y-coordinate
     */
    public AbstractAgent setY(double y);
    
    /**
     * @return The z-coordinate of the agent
     */
    public double getZ();
    
    /**
     * @param z The z-coordinate of the agent
     * @return The agent with the given z-coordinate
     */
    public AbstractAgent setZ(double z);
    
    default AbstractAgent update(double score, double speed, double x, double y, int dest) {
        return this.setScore(score).setSpeed(speed).setX(x).setY(y).setDestination(dest);
    }
    
    default boolean isAvailable() {
        return this.getDestination() != -1;
    }
}
