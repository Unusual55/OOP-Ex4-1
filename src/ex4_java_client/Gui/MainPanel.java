package ex4_java_client.Gui;

import datastructures.DWGraph;
import datastructures.GraphAlgo;
import ex4_java_client.AgentV1;
import ex4_java_client.Pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainPanel extends JFrame {
    private GraphAlgo algo;
    private DWGraph graph;
    private GraphDisplay painter;
    private JLabel bg;
    public MainPanel(String json) throws Exception {
        algo=new GraphAlgo();
        algo.loadGraph(json);
        this.graph=algo.getGraph();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setBounds(0,0,800,600);
        this.painter=new GraphDisplay(this.graph, this.getHeight(), this.getWidth(), this);
        this.setLayout(new BorderLayout());
        painter.addAgent(new AgentV1(0, 24, 1.0, 35.19797411945117,32.102764564705886));
        painter.addPokemon(new Pokemon(35.201752489104116,32.104636394957986, 5.0, 1));
        painter.addPokemon(new Pokemon(35.193304224374494,32.10634466722689, 5.0, 1));
        painter.addPokemon(new Pokemon(35.211283320419696,32.10476360672269, 5.0, 1));
        painter.addPokemon(new Pokemon(35.207993167070214,32.10467274117647, 5.0, 1));
        painter.addPokemon(new Pokemon(35.212217299435025,32.106235628571426, 5.0, 1));
        painter.addPokemon(new Pokemon(35.203259591606134,32.1031462, 5.0, 1));
        painter.addPokemon(new Pokemon(35.19943876836158,32.10038388739496, 5.0, 1));
        painter.addPokemon(new Pokemon(35.20364167393059,32.109325057142854, 5.0, 1));

        bg = new JLabel(new ImageIcon("Media/Pokeball.png"));
        bg.setSize(this.getSize());
//        this.add(bg);
        GraphDisplay.music();
        this.add(painter);
        this.painter.setLayout(new BorderLayout());
//        bg.setLayout(new FlowLayout());
//        this.setLayout(new FlowLayout());
        this.setResizable(true);
//        this.pack();
        repaint();
        bg.transferFocus();
        this.setVisible(true);


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                painter.Resize();
                painter.repaint();
                bg.setSize(getSize());
            }
        });
    }

    public static void main(String[] args) throws Exception {
        MainPanel mp=new MainPanel("C:\\Users\\ofrit\\IdeaProjects\\OOP-Ex4\\src\\data\\A5.json");
    }
}
