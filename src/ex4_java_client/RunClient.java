package ex4_java_client;

import datastructures.DWGraph;
import datastructures.DijkstreeData;
import datastructures.GraphAlgo;
import org.jgrapht.alg.util.Pair;

import java.io.IOException;
import java.util.*;

public class RunClient {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        try {
            client.startConnection("127.0.0.1", 6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String graphStr = client.getGraph();
        GraphAlgo ga = new GraphAlgo();
        ga.loadGraph(graphStr);
        DWGraph g = ga.getGraph();
        String infoStr = client.getInfo();
        GameJson gm = new GameJson(g);
        double[] infoArray = gm.JsonToInfo(infoStr);
        double score = infoArray[1];
        int AgentsNumber = (int) infoArray[0];
        String pokemonStr = client.getPokemons();
        HashSet<Pokemon> pokemons = gm.JsonToPokemon(pokemonStr);
        Pair<Integer, HashMap<Integer, DijkstreeData>> centerout = ga.Center();
        int center = centerout.getFirst();
        HashMap<Integer, DijkstreeData> data = centerout.getSecond();
        PositioningAlgorithms posAlgo = new PositioningAlgorithms();
        HashMap<Integer, Integer> AgentsPositions = posAlgo.getPositions(center, AgentsNumber, pokemons, g.graph.edgeSet());
        for (int pos : AgentsPositions.values()) {
            client.addAgent("{\"id\":" + pos + "}");
        }
        String agentsStr = client.getAgents();
        HashMap<Integer, AgentV1> agents = gm.JsonToAgents(agentsStr);
        int time = Integer.parseInt(client.timeToEnd());
        AllocationAlgorithm allocAlgo = new AllocationAlgorithm(g, agents, pokemons, data);
        GuiThread gui = new GuiThread(graphStr, client);
        gui.updateGui(time, agents, pokemons, score, 0);
        LinkedList<Long> log = new LinkedList<>();
        client.start();
        String pokemonStr2 = "";
        Timer timer = new Timer();
        MoveTask mt=new MoveTask(client);
        while (client.isRunning().equals("true")) {
            agentsStr = client.getAgents();
            pokemonStr = client.getPokemons();
            agents = gm.JsonToAgents(agentsStr);
            time = Integer.parseInt(client.timeToEnd());
            infoArray = gm.JsonToInfo(client.getInfo());
            pokemons = gm.JsonToPokemon(pokemonStr);
            allocAlgo.update(agents, pokemons);
            agents = allocAlgo.AllocatePokemons();
            int MoveCounter = 0;
            gui.updateGui(time, agents, pokemons, infoArray[1], infoArray[2]);
            for (AgentV1 a : agents.values()) {
                if (!a.isAvailable()) {
                    continue;
                }
//                if (a.getSpeed() > 1.0) {
//                    System.out.println(a.getSpeed());
//                }
                String Move = allocAlgo.AgentNextMove(a.getId());
                client.chooseNextEdge(Move);
                System.out.println(Move);
                MoveCounter++;
            }
            mt.run();
            mt.join();
            client.move();
//            System.out.println(client.getInfo());
//            if(agents.get(0).getTarget()==agents.get(1).getTarget()){
//                System.out.println("1");
//            }
        }
        client.stop();
        try {
            client.stopConnection();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MoveTask extends Thread {
    private Client c;
    public MoveTask(Client c) {
        this.c=c;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(98);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
