package ex4_java_client;

import datastructures.DWGraph;
import datastructures.DijkstreeData;
import datastructures.GraphAlgo;
import org.jgrapht.alg.util.Pair;

import java.io.IOException;
import java.util.*;

/**
 * This class represent our client connection class, as well as the Control part in the Model View Control
 * design pattern which we used in this assignment.
 * At the beginning of the class we will connect to the game server through a socket, get the basic information
 * we need before we start the simulation like the graph json, the number of agents and the current pokemons
 * for the simulation.
 * Before we will start the simulation we will run positioning algorithms, which gives us the optimal starting
 * positions for the agents.
 * The GUI runs of differentt thread, so we need to send it the needed information so we can update the it
 * to match the current situation.
 * After we started the simulation we will run the allocation algorithm, and set the next destination for every
 * agent and send the updates to the server and after we finished we will call move.
 * After we called the move, we will activate a MoveTask thread which sleeps for 99 milliseconds, and the
 * main thread has to wait for it to finish before it will continue to the next iteration, that way we limit
 * the moves number so it will never reach the limit.
 */
public class RunClient {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        /**
         * Connecting to the server
         */
        try {
            client.startConnection("127.0.0.1", 6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * Get the needed information before the simulation started
         */
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
        /**
         * Use Center algorithm on the graph in order to get the center node id as well as all of the
         * information about the distacnes and the paths from each vertex to any other vertex
         */
        Pair<Integer, HashMap<Integer, DijkstreeData>> centerout = ga.Center();
        int center = centerout.getFirst();
        HashMap<Integer, DijkstreeData> data = centerout.getSecond();
        /**
         * Create a PositioningAlgorithms singleton object, and choose the optimal starting positions for
         * the agents
         */
        PositioningAlgorithms posAlgo = new PositioningAlgorithms();
        HashMap<Integer, Integer> AgentsPositions = posAlgo.getPositions(center, AgentsNumber, pokemons, g.graph.edgeSet());
        for (int pos : AgentsPositions.values()) {
            client.addAgent("{\"id\":" + pos + "}");
        }
        String agentsStr = client.getAgents();
        HashMap<Integer, AgentV1> agents = gm.JsonToAgents(agentsStr);
        int time = Integer.parseInt(client.timeToEnd());
        /**
         * Creating the AllocationAlgorithm object and send it the basic information
         */
        AllocationAlgorithm allocAlgo = new AllocationAlgorithm(g, agents, pokemons, data);
        /**
         * Creating the Gui Thread and send it the graph json string and client, as well as the needed
         * information we need to visualise before the game started
         */
        GuiThread gui = new GuiThread(graphStr, client);
        gui.updateGui(time, agents, pokemons, score, 0);
        /**
         * Start the client and create the MoveTask thread
         */
        client.start();
        MoveTask mt = new MoveTask(client);
        while (client.isRunning().equals("true")) {
            /**
             * Get the current situation json string and parse it to update the GUI and the allocation
             * algorithms.
             */
            agentsStr = client.getAgents();
            pokemonStr = client.getPokemons();
            agents = gm.JsonToAgents(agentsStr);
            time = Integer.parseInt(client.timeToEnd());
            infoArray = gm.JsonToInfo(client.getInfo());
            pokemons = gm.JsonToPokemon(pokemonStr);
            allocAlgo.update(agents, pokemons);
            /**
             * Allocate the next moves of the agents
             */
            agents = allocAlgo.AllocatePokemons();
            gui.updateGui(time, agents, pokemons, infoArray[1], infoArray[2]);
            for (AgentV1 a : agents.values()) {
                if (!a.isAvailable()) {
                    continue;
                }
                /**
                 * Get the information about the next move of each available agent and send updates to the
                 * client about the next moves
                 */
                String Move = allocAlgo.AgentNextMove(a.getId());
                client.chooseNextEdge(Move);
            }
            /**
             * Put the main thread to sleep for 99 milliseconds and wait for it to pass
             */
            mt.run();
            mt.join();
            /**
             * Call for move, which move all of the valid choose_next_edge calls we made this iteration
             */
            client.move();

        }
        /**
         *
         * After the simulation ended, stop the connection to the server and exit.
         */
        client.stop();
        try {
            client.stopConnection();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * This class is the thread that helps us control the number of times we call for move.
 */
class MoveTask extends Thread {
    private Client c;

    public MoveTask(Client c) {
        this.c = c;
    }

    /**
     * This function put this thread to sleep for 99 milliseconds
     */
    @Override
    public void run() {
        try {
            Thread.sleep(99);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
