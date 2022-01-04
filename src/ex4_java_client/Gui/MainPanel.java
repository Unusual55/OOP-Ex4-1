package ex4_java_client.Gui;

import datastructures.DWGraph;
import datastructures.GraphAlgo;
import ex4_java_client.AgentV1;
import ex4_java_client.Pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.HashSet;

public class MainPanel extends JFrame {
    private GraphAlgo algo;
    private DWGraph graph;
    private GraphDisplay painter;
    public MainPanel(String json) throws Exception {
        this.setTitle("Ofri and Nir PokeGUI");
        algo=new GraphAlgo();
        algo.loadGraph(json);
        this.graph=algo.getGraph();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setBounds(0,0,800,600);
        this.painter=new GraphDisplay(this.graph, this.getHeight(), this.getWidth(), this);
        this.setLayout(new BorderLayout());
//        painter.addAgent(new AgentV1(0, 24, 1.0, 35.19797411945117,32.102764564705886));
//        painter.addPokemon(new Pokemon(35.201752489104116,32.104636394957986, 5.0, 1));
//        painter.addPokemon(new Pokemon(35.193304224374494,32.10634466722689, 5.0, 1));
//        painter.addPokemon(new Pokemon(35.211283320419696,32.10476360672269, 5.0, 1));
//        painter.addPokemon(new Pokemon(35.207993167070214,32.10467274117647, 5.0, 1));
//        painter.addPokemon(new Pokemon(35.212217299435025,32.106235628571426, 5.0, 1));
//        painter.addPokemon(new Pokemon(35.203259591606134,32.1031462, 5.0, 1));
//        painter.addPokemon(new Pokemon(35.19943876836158,32.10038388739496, 5.0, 1));
//        painter.addPokemon(new Pokemon(35.20364167393059,32.109325057142854, 5.0, 1));
        GraphDisplay.music();
        this.add(painter,BorderLayout.CENTER);
        this.painter.setLayout(new BorderLayout());
//        bg.setLayout(new FlowLayout());
//        this.setLayout(new FlowLayout());

        this.setResizable(true);
//        this.pack();
        repaint();
        this.setVisible(true);


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                painter.Resize();
                painter.repaint();
            }
        });
    }

    public static void main(String[] args) throws Exception {
        MainPanel mp=new MainPanel("C:\\Users\\ofrit\\IdeaProjects\\OOP-Ex4\\src\\data\\A5.json");
        /**
        * Tests that we made in order to check if the visual time really change
        */
        mp.painter.updateTime(30000000);
//        for(int i=30000000; i>0;i-=1){
//            mp.painter.updateTime(i);
//        }
    }

    public void UpdateGUI(int time, double score, HashMap<Integer, AgentV1> agents, HashSet<Pokemon> pokemons){
        this.painter.updateScore(score);
        this.painter.updateTime(time);
        this.painter.updateAgents(agents);
        this.painter.pokemonUpdate(pokemons);
    }
}
