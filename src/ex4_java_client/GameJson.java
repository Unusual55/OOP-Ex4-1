package ex4_java_client;

import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;

public class GameJson {
    public GameJson() {

    }

    /**
     * This function create map of agents from json file.
     * @param agents string which represent agents
     * @return map of agents
     */
    public HashMap<Integer, AgentV1> JsonToAgents(String agents) {
        HashMap<Integer, AgentV1> agentmap = new HashMap<>();
        final JSONObject obj2 = new JSONObject(agents);
        final JSONArray agentsArray = obj2.getJSONArray("Agents");
        for (int i = 0; i < agentsArray.length(); i++) {
            final JSONObject agent = agentsArray.getJSONObject(i).getJSONObject("Agent");
            final int id = agent.getInt("id");
            double value = agent.getDouble("value");
            String loc = agent.getString("pos");
            String[] coor = loc.split(",");
            double x = Double.parseDouble(coor[0]);
            double y = Double.parseDouble(coor[1]);
            double speed = agent.getDouble("speed");
            int src = agent.getInt("src");
            int dest = agent.getInt("dest");
            AgentV1 AskKetchumFromPalletTown = new AgentV1(id, src, dest, x, y, value, speed);
            agentmap.put(id, AskKetchumFromPalletTown);
        }
        return agentmap;
    }

    /**
     * This function get a string representation of pokemon's json and return a set of active pokemon
     * @param pokemons
     * @return set of pokemons
     */
    public LinkedList<Pokemon> JsonToPokemon(String pokemons) {
        LinkedList<Pokemon> pokelist = new LinkedList<>();
        final JSONObject obj2 = new JSONObject(pokemons);
        final JSONArray agentsArray = obj2.getJSONArray("Pokemons");
        for (int i = 0; i < agentsArray.length(); i++) {
            final JSONObject pokemon = agentsArray.getJSONObject(i).getJSONObject("Pokemon");
            double value = pokemon.getDouble("value");
            String loc = pokemon.getString("pos");
            String[] coor = loc.split(",");
            double x = Double.parseDouble(coor[0]);
            double y = Double.parseDouble(coor[1]);
            final int type = pokemon.getInt("type");
            Pokemon charmander = new Pokemon(x, y, value, type);
            pokelist.add(charmander);
        }
        return pokelist;
    }

}
