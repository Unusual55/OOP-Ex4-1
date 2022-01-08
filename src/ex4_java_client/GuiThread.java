package ex4_java_client;

import ex4_java_client.Gui.MainPanel;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class is the thread which runs the GUI. We want to run the GUI on different thread so we can update the
 * current situation in the game faster, and seperate it from the main thread, by that we seperate the Model
 * and Control from the View according to MVC design pattern, we use the RunClient in order to send
 * updates for the gui, and we use the stop button in the gui in order to stop the client.
 */
public class GuiThread extends Thread{
    private Client client;
    private MainPanel mainPanel;
    private String graph;

    public GuiThread(String graph, Client client){
        this.client=client;
        this.graph=graph;
        run();
    }

    /**
     * This function run the main panel which start the GUI visualisation
     */
    @Override
    public void run() {
        try {
            this.mainPanel=new MainPanel(this.graph, this.client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function is the function which connect the RunClient to the GUI, this function gets the needed
     * updates and send it to the gui.
     * @param time The remaining time of the simulatino
     * @param agents The current positions of the agents
     * @param pokemons The current active pokemon
     * @param score The score of the player
     * @param moves The number of moves the player made so far
     */
    public void updateGui(int time, HashMap<Integer, AgentV1> agents, HashSet<Pokemon> pokemons, double score, double moves){
        this.mainPanel.UpdateGUI(time,score,agents,pokemons, (int)moves);
    }
}
