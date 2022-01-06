package ex4_java_client.Gui;

import datastructures.DWGraph;
import datastructures.GraphAlgo;
import ex4_java_client.AgentV1;
import ex4_java_client.Client;
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
    public MainPanel(String json, Client client) throws Exception {
        this.setTitle("Ofri and Nir PokeGUI");
        algo=new GraphAlgo();
        algo.loadGraph(json);
        this.graph=algo.getGraph();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setBounds(0,0,800,600);
        this.painter=new GraphDisplay(this.graph, this.getHeight(), this.getWidth(), this, client);
        this.setLayout(new BorderLayout());
        this.add(painter,BorderLayout.CENTER);
        this.painter.setLayout(new BorderLayout());
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

//    public static void main(String[] args) throws Exception {
//        MainPanel mp=new MainPanel("C:\\Users\\ofrit\\IdeaProjects\\OOP-Ex4\\src\\data\\A5.json");
//        /**
//        * Tests that we made in order to check if the visual time really change
//        */
//        mp.painter.updateTime(30000000);
////        for(int i=30000000; i>0;i-=1){
////            mp.painter.updateTime(i);
////        }
//    }

    public void UpdateGUI(int time, double score, HashMap<Integer, AgentV1> agents, HashSet<Pokemon> pokemons, int moves){
        this.painter.updateScore(score);
        this.painter.updateTime(time);
        this.painter.updateAgents(agents);
        this.painter.pokemonUpdate(pokemons);
        this.painter.updateMoves(moves);
    }
}
