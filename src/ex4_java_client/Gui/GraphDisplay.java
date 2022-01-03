package ex4_java_client.Gui;

import datastructures.DWGraph;
import datastructures.Edge;
import datastructures.Vertex;
import ex4_java_client.AgentV1;
import ex4_java_client.Pokemon;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class controlls the graphic section of out GUI visualisation. In this class we draw the graph, agents
 * and of course the pokemons. This class contain it's own graph, map of agents and map of pokemons and few
 * parameters that we need in order to visualise better like the height and width of the main JFrame, the
 * remaining time and the current score. We used and improved functions from our third Assignment Ex2 in our GUI.
 */
public class GraphDisplay extends JPanel {
    final private double[] BoundingBox;
    final private DWGraph graph;
    private int height, width, time;
    final private String bgImagePath = "Media/Pokeball.png";
    private double fOffsetX = 0.0, fOffsetY = 0.0, fScaleX = 1.0, fScaleY = 1.0, fStartPanX = 0.0, fStartPanY = 0.0;
    final private double Wnode = 10;
    final private double Hnode = 10;
    private double score = 0;
    private JFrame frame;
    private HashMap<Integer, AgentV1> agents;
    private HashMap<Pokemon, VisPokemon> pokemons;
    final private PokeRandom pokeRandom;

    public GraphDisplay(DWGraph g, int h, int w, JFrame frame) {
        this.BoundingBox = new double[4];
        this.graph = g;
        this.height = h;
        this.width = w;
        this.frame = frame;
        this.setBounds(frame.getBounds());
        this.SetBoundingBox();
        this.agents = new HashMap<>();
        this.pokemons = new HashMap<>();
        this.pokeRandom = new PokeRandom();
    }

    /**
     * This function get a map of agents and set of pokemon and set them as the current pokemon and agents for
     * the simulation in the beginning of the simulation
     * @param agents
     * @param pokemons
     */
    public void setPokemons(HashMap<Integer, AgentV1> agents, HashMap<Pokemon, Pokemon> pokemons) {
        this.agents = agents;
        for (Pokemon p : pokemons.values()) {
            VisPokemon visualp = new VisPokemon(pokeRandom.randompokemon(), p);
            this.pokemons.put(p, visualp);
        }
    }

    /**
     * This function get an Agent as an input and add it to our agents map. We used this function mainly for
     * testing the GUI.
     * @param a
     */
    public void addAgent(AgentV1 a) {
        this.agents.put(a.getId(), a);
    }

    /**
     * This function get an Pokemon as an input and add it to our pokemon map. We used this function mainly for
     * testing the GUI.
     * @param p
     */
    public void addPokemon(Pokemon p) {
        VisPokemon visp = new VisPokemon(pokeRandom.randompokemon(), p);
        this.pokemons.put(p, visp);
    }

    /**
     * This function relate to the MainPanel Resize event. When the user resize the window, we will set the
     * GraphDisplay bounds and size it it will match the MainPanel.
     */
    public void Resize() {
        this.setBounds(this.frame.getBounds());
        this.setSize(this.frame.getSize());
        this.width = this.frame.getWidth();
        this.height = this.frame.getHeight();
    }

    /**
     * This function get the needed paremeters and draw an arrow between the coordinates, we used took this function
     * from our second assignment Ex2.
     * @param gfx
     * @param start
     * @param end
     * @param lineStroke
     * @param arrowStroke
     * @param arrowSize
     */
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

        gfx.setStroke(lineStroke);
        gfx.drawLine((int) startx, (int) starty, (int) (end.getX() + cx), (int) (end.getY() + cy));

        gfx.setColor(Color.BLUE);
        final GeneralPath polygon = new GeneralPath();
        polygon.moveTo(end.getX(), end.getY());
        polygon.lineTo(end.getX() + x1, end.getY() + y1);
        polygon.lineTo(end.getX() + x2, end.getY() + y2);
        polygon.closePath();
        gfx.fill(polygon);

        gfx.setColor(Color.BLACK);
    }

    /**
     * This function get a graphics object as input and draw the graph with it.
     * @param g
     * @throws IOException
     */
    public void drawGraph(Graphics g) throws IOException {
        Stroke stroke = new BasicStroke(3.5f);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(stroke);
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        g2d.setFont(font);
//        g2d.setColor(Color.RED);
        File file = new File("Media/Pokeball.png");
        BufferedImage image = ImageIO.read(file);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(rh);
        int Him = image.getHeight();
        int Wim = image.getWidth();
        Him = (int) (this.height - Him) / 2;
        Wim = (int) (this.width - Wim) / 2;
        g2d.drawImage(image, Wim / 2, Him / 2, this);
        for (Vertex v : this.graph.nodes.values()) {
            double x = v.getX();
            double y = v.getY();
            double[] asrc = this.CoordinatesTransformation(v);
            Point2D src = new Point2D.Double(asrc[0], asrc[1]);
            g2d.setStroke(new BasicStroke(6f));
            g2d.fillOval((int) src.getX(), (int) src.getY(), (int) this.Wnode, (int) this.Hnode);
            g2d.setColor(Color.RED);
            g2d.drawString("" + v.getID(), (int) src.getX(), (int) src.getY());
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(3.5f));
            for (Edge e : this.graph.graph.outgoingEdgesOf(v.getID())) {
                Vertex destV = this.graph.getNode(e.getDestination());
                double x2 = destV.getX();
                double y2 = destV.getY();
//                Point2D dest = this.WorldToScreen(x2, y2);
                double[] adest = this.CoordinatesTransformation(destV);
                Point2D dest = new Point2D.Double(adest[0], adest[1]);
                this.drawArrow(g2d, src, dest, stroke, stroke, 13);
            }
        }
    }

    /**
     * This function draw the Agents on the graph based on their position.
     * @param g
     * @throws IOException
     */
    public void drawAgents(Graphics g) throws IOException {
        Graphics2D g2d = (Graphics2D) g;
        for (AgentV1 agent : this.agents.values()) {
            double x = agent.getX();
            double y = agent.getY();
            double[] coor = CoordinatesTransformation(x, y);
            File file = new File("Media/MasterBall.png");
            BufferedImage image = ImageIO.read(file);
            g2d.drawImage(image, (int) coor[0], (int) coor[1], this.height / 25, this.height / 25, this);
        }
    }

    /**
     * This function draw the pokemons on the graph.
     * When creating a VisPokemon object, we create a random path to a pokemon image using PokeRandom and in this
     * function we use this path in order to create a buffer image which we draw on the graph at the wanted
     * position.
     * @param g
     * @throws IOException
     */
    public void drawPokemons(Graphics g) throws IOException {
        Graphics2D g2d = (Graphics2D) g;
        for (VisPokemon pikachu : this.pokemons.values()) {
            double x = pikachu.getX();
            double y = pikachu.getY();
            String path = pikachu.getPath();
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            double[] coor = CoordinatesTransformation(x, y);
            g2d.drawImage(image, (int) coor[0] - ((int) image.getWidth() / 10), (int) coor[1] - (int) (image.getHeight() / 10), this.width / 10, this.height / 10, this);
        }
    }

    /**
     * This fuction draw the remaining time for the simulation at the left upper corner of the screen
     * @param g
     */
    public void drawTime(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int afterdecimal = (int) (this.mod(time, 1000));
        int predecimal = (int) (time / 1000);
        float x = (float) ((1. / 40) * (this.width));
        float y = (float) ((2. / 20) * (this.height));
        g2d.drawString(predecimal + ":" + afterdecimal, x, y);
    }

    /**
     * This function gets an integer input which represents the remaining time for the simulation, set it as the
     * time and redraw it.
     * @param newTime
     */
    public void updateTime(int newTime) {
        this.time = newTime;
        repaint();
    }

    /**
     * This function draw the score on the left upper corner of the screen
     * @param g
     */
    public void drawScore(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        float x = (float) ((1. / 40) * (this.width));
        float y = (float) ((1. / 20) * (this.height));
        g2d.drawString("Score: " + score, x, y);
    }

    /**
     * This function get the current score, set it as the class property score and redraw the score
     * @param score
     */
    public void updateScore(double score) {
        this.score = score;
        repaint();
    }

    /**
     * This function used as Modulo operator: a%n
     * @param a
     * @param n
     * @return
     */
    public int mod(int a, int n) {
        return ((a % n) + n) % n;
    }

    /**
     * This function is the main drawing function of the GUI, This function create a buffer image and draw it
     * on the panel when it's ready
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        paintComponent(g);
        Image scenario = createImage((int) (this.width * 0.9), (int) (this.height * 0.9));
        Graphics gr = scenario.getGraphics();
        paintComponent(gr);
        g.drawImage(scenario, 0, 0, this.width, this.height, this);
    }

    /**
     * This function calls all of the relevant draw functions
     * @param g
     */
    public void paintComponent(Graphics g) {
        try {
            this.drawGraph(g);
            this.drawAgents(g);
            this.drawPokemons(g);
            this.drawTime(g);
            this.drawScore(g);
        } catch (IOException e) {
            System.out.println("something went wrong");
        }
    }

    /**
     * This function set the bounding box of the panel in order to support the linear transformations functions.
     */
    public void SetBoundingBox() {
        Collection<Vertex> set = this.graph.nodes.values();
        Vertex curr = null;
        double Xmin = 0, Xmax = 0, Ymin = 0, Ymax = 0;
        Iterator<Vertex> nodeiter = set.iterator();
        if (nodeiter.hasNext()) {
            curr = nodeiter.next();
            Xmin = curr.getX();
            Xmax = curr.getX();
            Ymin = curr.getY();
            Ymax = curr.getY();
        }
        while (nodeiter.hasNext()) {
            curr = nodeiter.next();
            Xmin = Math.min(Xmin, curr.getX());
            Xmax = Math.max(Xmax, curr.getX());
            Ymin = Math.min(Ymin, curr.getY());
            Ymax = Math.max(Ymax, curr.getY());
        }
        BoundingBox[0] = Xmin;
        BoundingBox[1] = Ymin;
        BoundingBox[2] = Xmax;
        BoundingBox[3] = Ymax;
        if (this.graph.nodes.size() <= 3) {
            BoundingBox[0] -= 10;
            BoundingBox[1] -= 10;
            BoundingBox[2] += 10;
            BoundingBox[3] += 10;
        }
    }

    /**
     * This function create new coordinates for the input vertex using liner transformation and return it's new
     * coordinates
     * @param p
     * @return
     */
    public double[] CoordinatesTransformation(Vertex p) {
        double dpx = this.BoundingBox[2] - this.BoundingBox[0];
        double dpy = this.BoundingBox[3] - this.BoundingBox[1];
        double dcx = this.BoundingBox[2] - p.getX();
        double dcy = this.BoundingBox[3] - p.getY();
        double xfixed = (dcx / dpx * this.width * 0.8 + 0.01 * this.height);
        double yfixed = (dcy / dpy * this.height * 0.8 + 0.01 * this.width);
        return new double[]{xfixed, yfixed};
    }

    /**
     * This function create new coordinates for the inputs x and y using liner transformation and return it's new
     * coordinates
     * @param x
     * @param y
     * @return
     */
    public double[] CoordinatesTransformation(double x, double y) {
        double dpx = this.BoundingBox[2] - this.BoundingBox[0];
        double dpy = this.BoundingBox[3] - this.BoundingBox[1];
        double dcx = this.BoundingBox[2] - x;
        double dcy = this.BoundingBox[3] - y;
        double xfixed = (dcx / dpx * this.width * 0.8 + 0.01 * this.height);
        double yfixed = (dcy / dpy * this.height * 0.8 + 0.01 * this.width);
        return new double[]{xfixed, yfixed};
    }
//
//    public double[] CoordinatesTransformation(double x, double y, Image image) {
//        double dpx = this.BoundingBox[2] - this.BoundingBox[0];
//        double dpy = this.BoundingBox[3] - this.BoundingBox[1];
//        double dcx = this.BoundingBox[2] - x;
//        double dcy = this.BoundingBox[3] - y;
//        double xfixed = (dcx / dpx * this.width * 0.8 - (image.getWidth(this) / 2));
//        double yfixed = (dcy / dpy * this.height * 0.8 - image.getHeight(this) / 2);
//        return new double[]{xfixed, yfixed};
//    }

    /**
     * This static function plays the first opening song of pokemon in the background
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    public static void music() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Clip clip = AudioSystem.getClip();
        File f = new File("Media/op1.wav");
        AudioInputStream ais = AudioSystem.getAudioInputStream(f);
        clip.open(ais);
        clip.start();
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, "Close on exit!");
        });
    }

//    public static void music2() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
//        Clip clip = AudioSystem.getClip();
//        File f = new File("Media/op1.wav");
//        AudioInputStream ais = AudioSystem.getAudioInputStream(f);
//        clip.open(ais);
//    }

    /**
     * This function get the current active pokemon, and recreate the pokemon map so it will match the input set
     * @param newset
     */
    public void pokemonUpdate(HashSet<Pokemon> newset) {
        HashMap<Pokemon, VisPokemon> ret = new HashMap<>();
        Iterator<Pokemon> pokerator = newset.iterator();
        while (pokerator.hasNext()) {
            Pokemon p = pokerator.next();
            if (this.pokemons.containsKey(p)) {
                ret.put(p, this.pokemons.get(p));
            } else {
                ret.put(p, new VisPokemon(pokeRandom.randompokemon(), p));
            }
        }
        this.pokemons = ret;
    }

    /**
     * This function update the agents map, using an input agent map
     * @param agents
     */
    public void updateAgents(HashMap<Integer, AgentV1> agents) {
        for (int id : agents.keySet()) {
            this.agents.put(id, agents.get(id));
        }
    }
}

