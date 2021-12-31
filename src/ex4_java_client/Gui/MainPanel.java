package ex4_java_client.Gui;

import javax.swing.*;
import datastructures.DWGraph;
import datastructures.GraphAlgo;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainPanel extends JFrame {
    private GraphAlgo algo;
    private DWGraph graph;
    private GraphDisplay painter;
    public MainPanel(String json) throws Exception {
        algo=new GraphAlgo();
        algo.loadGraph(json);
        this.graph=algo.getGraph();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setBounds(0,0,800,600);
        this.painter=new GraphDisplay(this.graph, this.getHeight(), this.getWidth(), this);
        this.setLayout(new BorderLayout());
        JLabel bg = new JLabel(new ImageIcon("Media/Pokeball.png"));
        bg.setSize(this.getSize());
        this.add(bg);
        bg.setLayout(new FlowLayout());
//        this.setLayout(new FlowLayout());
        this.setResizable(true);
//        this.pack();
        this.add(painter);
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
    }
}
