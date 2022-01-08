package ex4_java_client;

import java.util.Comparator;
import java.util.HashSet;

/**
 * This function represent a pokemon. Each pokemon contain few fields of information:
 * 1. Value: How many points the pokemon worth
 * 2. X, Y: The position of the pokemon in 2D space.
 * 3, Type: The type of the pokemon, if Esrc<Edest, then the type is 1, otherwise the type will be -1
 * 4. Esrc, Edest: The ids of the source and destenation nodes of the edge the pokemon is standing on.
 * 5. pastallocation: The Ids of every agent whom we allocated to catch this pokemon, we will add the id
 * of an agent to this HashSet if and only if we switch the assigned agent to another agent, therefore,
 * we don't want to assign this pokemon to this agent again.
 */
public class Pokemon implements Comparable<Pokemon> {
    private double value, x, y;
    private int type;
    private int Esrc, Edest;
    private HashSet<Integer> pastallocation;

    public Pokemon(double x, double y, double value, int type) {
        pastallocation = new HashSet<>();
        this.x = x;
        this.y = y;
        this.value = value;
        this.type = type;
    }

    public Pokemon(Pokemon p) {
        this.x = p.x;
        this.y = p.y;
        this.value = p.value;
        this.type = p.type;
        this.pastallocation = p.pastallocation;
    }

    /**
     * This function returns the value of the pokemon
     *
     * @return value
     */
    public double getValue() {
        return this.value;
    }

    /**
     * This function returns the X coordinate of the pokemon
     *
     * @return X
     */
    public double getX() {
        return this.x;
    }

    /**
     * This function returns the Y coordinate of the pokemon
     *
     * @return Y
     */
    public double getY() {
        return this.y;
    }

    /**
     * This function returns the type of the pokemon
     *
     * @return type
     */
    public int type() {
        return this.type;
    }

    /**
     * This function get an object as input and check if it's equal to this Pokemon.
     * The function will return true iff the input is Pokemon which its fields are equal to this pokemon fields,
     * otherwise the function will return false.
     *
     * @param o
     * @return
     */
    public boolean equals(Object o) {
        if (!(o instanceof Pokemon)) {
            return false;
        }
        boolean b1 = this.type == ((Pokemon) o).type, b2 = this.value == ((Pokemon) o).value;
        boolean b3 = this.x == ((Pokemon) o).x, b4 = this.y == ((Pokemon) o).y;
        return (b1) && (b2) && (b3) && (b4);
    }

    /**
     * This function get pokemon p as input and compare it's value to this pokemon value and return a result:
     * -1: p's value is higher than this pokemon value
     * 0: Both values are equal
     * 1: This pokemon's value is higher than p's value
     *
     * @param p A Pokemon
     * @return result
     */
    @Override
    public int compareTo(Pokemon p) {
        double res = this.value - p.value;
        if (res == 0) {
            return 0;
        }
        if (res > 0) {
            return 1;
        }
        return -1;
    }

    /**
     * This function returns a string representation of this pokemon
     *
     * @return String representation of the pokemon
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ") value: " + this.value + " type: " + this.type;
    }

    /**
     * This function set the source node id of the pokemon.
     *
     * @param src The id of the source node
     */
    private void setEsrc(int src) {
        this.Esrc = src;
    }

    /**
     * This function set the destination node id of the pokemon
     *
     * @param dest The id of the destination node
     */
    private void setEdest(int dest) {
        this.Edest = dest;
    }

    /**
     * This function update the indexes of the source and destination node ids of the pokemon
     *
     * @param indexes The indexes of the source and destination node
     */
    public void updateIndexes(int[] indexes) {
        if (indexes.length == 1) {
            return;
        }
        this.setEsrc(indexes[0]);
        this.setEdest(indexes[1]);
    }

    /**
     * This function returns the id of the source node of this pokemon
     *
     * @return The id of the source node of this pokemon
     */
    public int getEsrc() {
        return this.Esrc;
    }

    /**
     * This function returns the id of the destination node of this pokemon
     * @return The id of the destination node of this pokemon
     */
    public int getEdest() {
        return this.Edest;
    }

    /**
     * This function check if the agent which his id is the input id already assigned to catch this pokemon
     * and return true if it does, otherwise the function will return false
     * @param id The id of the agent
     * @return True if the agent already assigned to catch this pokemon, otherwise false.
     */
    public boolean containsPastAllocation(int id) {
        return pastallocation.contains(id);
    }

    /**
     * This function get the id of an agent and add it to the past allocation of this pokemon
     *
     * @param id The id of the agent
     */
    public void addPastAllocation(int id) {
        pastallocation.add(id);
    }

    /**
     * This function returns how many agents were assigned to catch this pokemon
     * @return The number of agents that assigned to catch this pokemon in the past, not included the current
     * agent.
     */
    public int getPastSize() {
        return this.pastallocation.size();
    }
}