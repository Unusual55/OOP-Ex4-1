package ex4_java_client;

import ex4_java_client.Gui.MainPanel;

import java.util.HashMap;
import java.util.HashSet;

public class GuiThread implements Runnable{
    private MainPanel mainPanel;
    private String graph;
    public GuiThread(int time, HashMap<Integer, AgentV1> agents, HashSet<Pokemon> pokemons, String graphJson) throws Exception {
        this.graph=graphJson;
        run();
        this.updateGui(time, agents, pokemons, 0);
    }

    public GuiThread(String graph){
        this.graph=graph;
        run();
    }
    @Override
    public void run() {
        try {
            this.mainPanel=new MainPanel(this.graph);
            System.out.println("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateGui(int time, HashMap<Integer, AgentV1> agents, HashSet<Pokemon> pokemons, double score){
        this.mainPanel.UpdateGUI(time,score,agents,pokemons);
    }
}
