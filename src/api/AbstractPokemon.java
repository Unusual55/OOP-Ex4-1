package api;

import org.json.JSONObject;

public interface AbstractPokemon extends Comparable<AbstractPokemon> {
    
    /**
     * @return The value of the Pokémon
     */
    public double getValue();
    
    /**
     * @return The x-coordinate of the Pokémon
     */
    public double getX();
    
    /**
     * @return The y-coordinate of the Pokémon
     */
    public double getY();
    
    /**
     * @return The z-coordinate of the Pokémon
     */
    public double getZ();
    
    
    default double[] getXY() {
        return new double[] {this.getX(), this.getY()};
    }
    
    default double[] getXYZ() {
        return new double[] {this.getX(), this.getY(), this.getZ()};
    }
    
    /**
     * @return The type of the Pokémon (src < dest => type > 0,dest < src => type < 0)
     */
    public int getType();
    
    /**
     * @param value The value of the Pokémon
     * @return The Pokémon with the new value
     */
    public AbstractPokemon setValue(double value);
    
    /**
     * @param x The x-coordinate of the Pokémon
     * @return The Pokémon with the new x-coordinate
     */
    public AbstractPokemon setX(double x);
    
    /**
     * @param y The y-coordinate of the Pokémon
     * @return The Pokémon with the new y-coordinate
     */
    public AbstractPokemon setY(double y);
    
    /**
     * @param z The z-coordinate of the Pokémon
     * @return The Pokémon with the new z-coordinate
     */
    public AbstractPokemon setZ(double z);
    
    
//    /**
//     * @param graph The graph
//     * @return
//     */
//    default AbstractEdge getEdge(AbstractDirectedWeightedGraph graph) {
//        return new AbstractEdge(this, dest);
//    }
    
    default JSONObject toJSON() {
        JSONObject Pokemon = new JSONObject();
        JSONObject props = new JSONObject();
        props.put("value", this.getValue());
        props.put("type", this.getType());
        props.put("pos", this.getX() + "," + this.getY() + "," + this.getZ());
        Pokemon.put("Pokemon", props);
        return Pokemon;
    }
}
