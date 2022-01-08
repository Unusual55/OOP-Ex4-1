package ex4_java_client;

import java.util.LinkedList;

/**
 * This class represents the agent. Every agent has few unique properties which he gets from the json:
 * 1) Id: His unique number which we can use to identify the agent.
 * 2) Coordinations: His location.
 * 3) Score: The score the agent made so far.
 * 4) Speed: The speed of the agents.
 * 5) Source: The agent last position.
 * 6) Destination: The agent next destination
 * Every agent have fields which can support the allocation algorithms, as well as his cooperation with
 * other agents in any case where there are more than one agent:
 * 1) Target: The pokemon the agent is trying to catch.
 * 2) Path: The nodes in the way to the pokemon.
 * 3) Catch Time: The estimated time to catch the pokemon.
 */
public class AgentV1 {
    private int id;
    private double score, currentscore = 0;
    private int src, dest;
    private double speed, x, y;
    private Pokemon target;
    private LinkedList<Integer> VictoryRoad;
    private long catchtime;

    /**
     * This function returns the id of the agent
     *
     * @return The id of the agent
     */
    public int getId() {
        return id;
    }

    /**
     * This function set the id of the agent
     *
     * @param id The new id of the agent
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This function return the current score of the agent
     *
     * @return The current score
     */
    public double getScore() {
        return score;
    }

    /**
     * This function set a new score for this agent
     *
     * @param score The new score of the agent
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * This function returns the source node id of the agent
     *
     * @return The agent source node id
     */
    public int getSrc() {
        return src;
    }

    /**
     * This function set the source node id of this agent
     *
     * @param src The new source node id
     */
    public void setSrc(int src) {
        this.src = src;
    }

    /**
     * This function returns the destination node id of the agent
     *
     * @return The agent destination node id
     */
    public int getDest() {
        return dest;
    }

    /**
     * This function set the destination node id of this agent
     *
     * @param dest The new destination node id
     */
    public void setDest(int dest) {
        this.dest = dest;
    }

    /**
     * This function returns the speed of the agent
     *
     * @return The speed of the agent
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * This function set the speed of the agent
     *
     * @param speed The new speed of the agent
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * This function returns the X coordinate of this agent
     *
     * @return The X coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * This function set the X coordinate of this agent
     *
     * @param x The new X coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * This function returns the Y coordinate of this agent
     *
     * @return The Y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * This function set the Y coordinate of this agent
     *
     * @param y The new Y coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    public AgentV1(int id, int src, double speed, double x, double y) {
        this.id = id;
        this.score = 0;
        this.src = src;
        this.dest = -1;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.VictoryRoad = new LinkedList<>();
        this.target = null;
    }

    public AgentV1(int id, int src, int dest, double x, double y, double value, double speed, long catchtime) {
        this.id = id;
        this.src = src;
        this.dest = dest;
        this.score = value;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.target = null;
        this.VictoryRoad = new LinkedList<>();
        this.catchtime = catchtime;
    }

    /**
     * This function updates the agent so it will much the current agent
     *
     * @param a The current agent
     */
    public void update(AgentV1 a) {
        this.x = a.getX();
        this.y = a.getY();
        this.speed = a.getSpeed();
        this.score = a.score;
        this.src = a.getSrc();
        this.dest = a.dest;
        if (this.score > this.currentscore) {
            this.target = null;
            this.currentscore = score;
        }
        this.advanceNextMove();
        if (a.getCatchTime() != -1) {
            this.catchtime = a.catchtime;
        }
    }

    /**
     * This function return true if the agent is available, otherwise false
     *
     * @return
     */
    public boolean isAvailable() {
        return this.dest == -1;
    }

    /**
     * This function set the agent's target
     *
     * @param pokemon
     */
    public void setTarget(Pokemon pokemon) {
        this.target = pokemon;
    }

    /**
     * This function set the agent's path
     *
     * @param list The path
     */
    public void setPath(LinkedList<Integer> list) {
        this.VictoryRoad = list;
    }

    /**
     * This function remove the agent target and reset his path and catch time
     */
    public void removeTarget() {
        this.target = null;
        this.catchtime = -1;
        this.VictoryRoad = new LinkedList<>();
    }

    /**
     * This function returns the next destination of the agent
     *
     * @return The agent next destination
     */
    public int getNextMove() {
        if (this.VictoryRoad.size() == 0) {
            return -1;
        }
        return this.VictoryRoad.getFirst();
    }

    /**
     * This function prepare the next move for the agent
     */
    public void advanceNextMove() {
        if (this.VictoryRoad.size() == 0) {
            return;
        }
        if (this.src == this.VictoryRoad.getFirst()) {
            this.VictoryRoad.removeFirst();
        }
    }

    /**
     * This function returns the target of the agent
     *
     * @return The target of the agent
     */
    public Pokemon getTarget() {
        return this.target;
    }

    /**
     * This function returns the catch time of the target
     *
     * @return
     */
    public long getCatchTime() {
        return this.catchtime;
    }

    /**
     * This function set the catch time of the target
     *
     * @param time The catch time
     */
    public void setCatchTime(long time) {
        this.catchtime = time;
    }
}
