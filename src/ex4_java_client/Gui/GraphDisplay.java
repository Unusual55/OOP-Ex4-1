package ex4_java_client.Gui;

import datastructures.DWGraph;
import datastructures.Edge;
import datastructures.Vertex;
import ex4_java_client.AgentV1;
import ex4_java_client.Pokemon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class GraphDisplay extends JPanel {
    private DWGraph graph;
    private int height, width;
    private String bgImagePath = "Media/Pokeball.png";
    private double fOffsetX = 0.0, fOffsetY = 0.0, fScaleX = 1.0, fScaleY = 1.0, fStartPanX = 0.0, fStartPanY = 0.0;
    private double Wnode = 5, Hnode = 5;
    private JFrame frame;
    private HashMap<Integer, AgentV1> agents;
    private HashSet<Pokemon> pokemons;

    public GraphDisplay(DWGraph g, int h, int w, JFrame frame) {
        this.graph = g;
        this.height = h;
        this.width = w;
        this.frame = frame;
        this.setBounds(frame.getBounds());
    }

    public void setPokemons(HashMap<Integer, AgentV1> agents, HashSet<Pokemon> pokemons) {
        this.agents = agents;
        this.pokemons = pokemons;
    }

    public void Resize() {
        this.setBounds(this.frame.getBounds());
        this.setSize(this.frame.getSize());
    }

    public Point WorldToScreen(double fWorldX, double fWorldY) {
        int iScreenX = (int) ((fWorldX - this.fOffsetX) * this.fScaleX);
        int iScreenY = (int) ((fWorldY - this.fOffsetY) * this.fScaleY);
        return new Point(iScreenX, iScreenY);
    }

    public static void drawArrow(final Graphics2D gfx, final Point2D start, final Point2D end, final Stroke lineStroke, final Stroke arrowStroke, final float arrowSize) {

        final double startx = start.getX();
        final double starty = start.getY();

        gfx.setStroke(arrowStroke);
        final double deltax = startx - end.getX();
        final double result;
        if (deltax == 0.0d) {
            result = Math.PI / 2;
        } else {
            result = Math.atan((starty - end.getY()) / deltax) + (startx < end.getX() ? Math.PI : 0);
        }

        final double angle = result;

        final double arrowAngle = Math.PI / 12.0d;

        final double x1 = arrowSize * Math.cos(angle - arrowAngle);
        final double y1 = arrowSize * Math.sin(angle - arrowAngle);
        final double x2 = arrowSize * Math.cos(angle + arrowAngle);
        final double y2 = arrowSize * Math.sin(angle + arrowAngle);

        final double cx = (arrowSize / 2.0f) * Math.cos(angle);
        final double cy = (arrowSize / 2.0f) * Math.sin(angle);

        final GeneralPath polygon = new GeneralPath();
        polygon.moveTo(end.getX(), end.getY());
        polygon.lineTo(end.getX() + x1, end.getY() + y1);
        polygon.lineTo(end.getX() + x2, end.getY() + y2);
        polygon.closePath();
        gfx.fill(polygon);

        gfx.setStroke(lineStroke);
        gfx.drawLine((int) startx, (int) starty, (int) (end.getX() + cx), (int) (end.getY() + cy));
    }

    public void drawGraph(Graphics g) throws IOException {
        Stroke stroke = new BasicStroke(5);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(stroke);
        g2d.setColor(Color.RED);
        File file = new File("Media/Pokeball.png");
        BufferedImage image = ImageIO.read(file);
        for (Vertex v : this.graph.nodes.values()) {
            double x = v.getX();
            double y = v.getY();
            Point2D src = this.WorldToScreen(x, y);
            g2d.fillOval((int) src.getX(), (int) src.getY(), (int) this.Wnode, (int) this.Hnode);
            g2d.setColor(Color.BLACK);
            for (Edge e : this.graph.graph.outgoingEdgesOf(v.getID())) {
                Vertex destV = this.graph.getNode(e.getDestination());
                double x2 = destV.getX();
                double y2 = destV.getY();
                Point2D dest = this.WorldToScreen(x2, y2);
                this.drawArrow(g2d, src, dest, stroke, stroke, 13);
            }
        }
    }

    public void drawAgents(Graphics g) {
        LinkedList<JLabel> ash = new LinkedList<>();
        for (AgentV1 agent : this.agents.values()) {
            double x = agent.getX();
            double y = agent.getY();
            Point2D pos = WorldToScreen(x, y);
            JLabel l = new JLabel(new ImageIcon("Media/Kantoash.png"));
            l.setBounds((int) pos.getX(), (int) pos.getY(), (int) 0.05 * this.width, (int) 0.05 * this.height);
            ash.add(l);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintComponents(g);
        Image scenario = createImage(this.width, this.height);
        Graphics gr = scenario.getGraphics();
        paintComponents(gr);
        g.drawImage(scenario, 0, 0, this.width, (int) this.height, this);
    }

    public void paintComponents(Graphics g) {
        try {
            this.drawGraph(g);
//            this.drawAgents(g);
        } catch (IOException e) {
            System.out.println("something went wrong");
        }
    }
}

