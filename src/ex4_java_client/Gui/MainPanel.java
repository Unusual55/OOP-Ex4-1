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

/**
 *This class is the main JFrame of the GUI, it contains the graph display and send updates to the graph
 * display.
 */
public class MainPanel extends JFrame {
    private GraphAlgo algo;
    private DWGraph graph;
    private GraphDisplay painter;

    public MainPanel(String json, Client client) throws Exception {
        this.setTitle("Ofri and Nir PokeGUI");
        algo = new GraphAlgo();
        algo.loadGraph(json);
        this.graph = algo.getGraph();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setBounds(0, 0, 800, 600);
        this.painter = new GraphDisplay(this.graph, this.getHeight(), this.getWidth(), this, client);
        this.setLayout(new BorderLayout());
        this.add(painter, BorderLayout.CENTER);
        this.painter.setLayout(new BorderLayout());
        this.setResizable(true);
        repaint();
        this.setVisible(true);

        /**
         * This event trigger the resize function of GraphDisplay which match its bounds and size
         * to the MainPanel bounds and size.
         */
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                painter.Resize();
                painter.repaint();
            }
        });
    }

    /**
     * This function get updates from the main's client and send them to the gui in order to update the screen
     * to match the current situation
     *
     * @param time     The current time
     * @param score    The current score
     * @param agents   The current agents
     * @param pokemons The current pokemons
     * @param moves    The current move number
     */
    public void UpdateGUI(int time, double score, HashMap<Integer, AgentV1> agents, HashSet<Pokemon> pokemons, int moves) {
        this.painter.updateScore(score);
        this.painter.updateTime(time);
        this.painter.updateAgents(agents);
        this.painter.pokemonUpdate(pokemons);
        this.painter.updateMoves(moves);
    }
}
