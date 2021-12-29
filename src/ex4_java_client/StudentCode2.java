package ex4_java_client;
/**
 * @author AchiyaZigi
 * A trivial example for starting the server and running all needed commands
 */

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class StudentCode2 {
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.startConnection("127.0.0.1", 6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String graphStr = client.getGraph();
        System.out.println(graphStr);
        final JSONObject graphJOBJ = new JSONObject(graphStr);
        final JSONArray edgesArray = graphJOBJ.getJSONArray("Edges");
        final JSONArray nodesArray = graphJOBJ.getJSONArray("Nodes");
//        System.out.println(nodesArray.length());
        
        client.addAgent("{\"id\":0}");
        String agentsStr = client.getAgents();
//        System.out.println(agentsStr);
        String pokemonsStr = client.getPokemons();
//        System.out.println(pokemonsStr);
        String isRunningStr = client.isRunning();
//        System.out.println(isRunningStr);
        
        client.start();
        
        while (client.isRunning().equals("true")) {
//            String pokemons = client.getPokemons();
//            final JSONObject obj = new JSONObject(pokemons);
//            final JSONArray pokemonsArray = obj.getJSONArray("Pokemons");
//            for (int i = 0; i < pokemonsArray.length(); i++) {
//                final JSONObject pokemon = pokemonsArray.getJSONObject(i).getJSONObject("Pokemon");
//                final double value = pokemon.getDouble("value");
//                final int type = pokemon.getInt("type");
//                final String pos = pokemon.getString("pos");
//                final String[] posArr = pos.split(",");
//                final double x = Double.parseDouble(posArr[0]);
//                final double y = Double.parseDouble(posArr[1]);
//            }
            String agents = client.getAgents();
            final JSONObject obj2 = new JSONObject(agents);
            final JSONArray agentsArray = obj2.getJSONArray("Agents");
            for (int i = 0; i < agentsArray.length(); i++) {
                final JSONObject agent = agentsArray.getJSONObject(i).getJSONObject("Agent");
//                JSONObject agent = new JSONObject(agentsArray.get(i).toString());
//                System.out.println(agent);
                final int id = agent.getInt("id");
                final double value = agent.getDouble("value");
                final int src = agent.getInt("src");
                final int dest = agent.getInt("dest");
                final double speed = agent.getDouble("speed");
                final String pos = agent.getString("pos");
                final String[] posArr = pos.split(",");
                final double x = Double.parseDouble(posArr[0]);
                final double y = Double.parseDouble(posArr[1]);
                if (dest == -1) {
                    int nextNode = mod((src - 1), nodesArray.length());
                    client.chooseNextEdge("{\"agent_id\":" + id + ", \"next_node_id\": " + nextNode + "}");
                    System.out.println(client.getAgents());
                    System.out.println(client.getPokemons());
                    System.out.println("Agent " + id + " is moving to node " + nextNode);
                    System.out.println();
//                    String ttl = client.timeToEnd();
//                    System.out.println(ttl + ", " + client.getInfo());
                }
            }
//            if (client.isRunning().equals("true")) {
//                client.move();
//            }
            client.move();
            
        }
//        client.stop();
        try {
            client.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean collinear(double x1, double y1, double x2, double y2, double x3, double y3) {
//        double a = x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2); // area of triangle
//        return a == 0;
        return (y2 - y1) * (x3 - x2) == (y3 - y2) * (x2 - x1);
    }
    
    public static int mod(int a, int n) {
        return ((a % n) + n) % n;
    }
}