package ex4_java_client; /**
 * @author AchiyaZigi
 * A trivial example for starting the server and running all needed commands
 */

import datastructures.DWGraph;
import datastructures.DijkstreeData;
import datastructures.GraphAlgo;
import org.jgrapht.alg.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class StudentCode {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        try {
            client.startConnection("127.0.0.1", 6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String graphStr = client.getGraph();
        String pokemonsStr = client.getPokemons();
        String isRunningStr = client.isRunning();
        GameJson gm=new GameJson();
        GraphAlgo ga=new GraphAlgo();
        ga.loadGraph(graphStr);
        DWGraph g=ga.getGraph();
        String info=client.getInfo();
        double[] data=gm.JsonToInfo(info);
        int agentnumber=Integer.parseInt(String.valueOf(data[0]));
        Pair<Integer, HashMap<Integer, DijkstreeData>> centerout= ga.Center();
        client.addAgent("{\"id\":"+centerout.getFirst()+"}");
        String agentsStr = client.getAgents();
        HashMap<Integer, AgentV1> agents=gm.JsonToAgents(agentsStr);
        HashSet<Pokemon> pokemons=gm.JsonToPokemon(pokemonsStr);
        int time=Integer.parseInt(client.timeToEnd());
        GuiThread gui=new GuiThread(graphStr);
        gui.updateGui(time, agents, pokemons, data[1]);
        client.start();
        while (client.isRunning().equals("true")) {
            agentsStr = client.getAgents();
            pokemonsStr = client.getPokemons();
            agents=gm.JsonToAgents(agentsStr);
            pokemons=gm.JsonToPokemon(pokemonsStr);
            time=Integer.parseInt(client.timeToEnd());
            data=gm.JsonToInfo(client.getInfo());
            gui.updateGui(time, agents, pokemons, data[1]);
//            System.out.println(client.timeToEnd());
            for(AgentV1 a: agents.values()) {
                if(a.isAvailable()){continue;}
                int id=a.getId(), src=a.getSrc();
                int next = mod(src-1, g.graph.vertexSet().size());
                client.chooseNextEdge("{\"agent_id\":"+id+", \"next_node_id\": " + next + "}");
            }
            client.move();
        }
    }
    public static int mod ( int a, int n){
        return ((a % n) + n) % n;
    }

}