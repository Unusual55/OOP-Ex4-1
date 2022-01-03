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


public class GraphDisplay extends JPanel {
    private double[] BoundingBox;
    private DWGraph graph;
    private int height, width;
    private String bgImagePath = "Media/Pokeball.png";
    private double fOffsetX = 0.0, fOffsetY = 0.0, fScaleX = 1.0, fScaleY = 1.0, fStartPanX = 0.0, fStartPanY = 0.0;
    private double Wnode = 10, Hnode = 10;
    private JFrame frame;
    private HashMap<Integer, AgentV1> agents;
    private HashSet<Pokemon> pokemons;
    private PokeRandom pokeRandom;
    public GraphDisplay(DWGraph g, int h, int w, JFrame frame) {
        this.BoundingBox=new double[4];
        this.graph = g;
        this.height = h;
        this.width = w;
        this.frame = frame;
        this.setBounds(frame.getBounds());
        this.SetBoundingBox();
        this.agents=new HashMap<>();
        this.pokemons=new HashSet<>();
        this.pokeRandom=new PokeRandom();
    }

    public void setPokemons(HashMap<Integer, AgentV1> agents, HashSet<Pokemon> pokemons) {
        this.agents = agents;
        this.pokemons = pokemons;
    }

    public void addAgent(AgentV1 a){
        this.agents.put(a.getId(), a);
    }

    public void addPokemon(Pokemon p){
        this.pokemons.add(p);
    }

    public void Resize() {
        this.setBounds(this.frame.getBounds());
        this.setSize(this.frame.getSize());
        this.width=this.frame.getWidth();
        this.height=this.frame.getHeight();
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

        gfx.setColor(Color.BLUE);
        final GeneralPath polygon = new GeneralPath();
        polygon.moveTo(end.getX(), end.getY());
        polygon.lineTo(end.getX() + x1, end.getY() + y1);
        polygon.lineTo(end.getX() + x2, end.getY() + y2);
        polygon.closePath();
        gfx.fill(polygon);

        gfx.setColor(Color.BLACK);
        gfx.setStroke(lineStroke);
        gfx.drawLine((int) startx, (int) starty, (int) (end.getX() + cx), (int) (end.getY() + cy));
    }

    public void drawGraph(Graphics g) throws IOException {
        Stroke stroke = new BasicStroke(1.5f);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(stroke);
        Font font=new Font(Font.SANS_SERIF,Font.BOLD,16);
        g2d.setFont(font );
//        g2d.setColor(Color.RED);
        File file = new File("Media/Pokeball.png");
        BufferedImage image = ImageIO.read(file);

        int Him=image.getHeight();
        int Wim=image.getWidth();
        Him=(int)(this.height-Him)/2;
        Wim=(int)(this.width-Wim)/2;
        g2d.drawImage(image, Wim/2, Him/2 ,this);
        for (Vertex v : this.graph.nodes.values()) {
            double x = v.getX();
            double y = v.getY();
            double[] asrc=this.CoordinatesTransformation(v);
            Point2D src = new Point2D.Double(asrc[0], asrc[1]);
            g2d.setStroke(new BasicStroke(4f));
            g2d.fillOval((int) src.getX(), (int) src.getY(), (int) this.Wnode, (int) this.Hnode);
            g2d.drawString(""+v.getID(), (int)src.getX(), (int)src.getY());
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2.5f));
            for (Edge e : this.graph.graph.outgoingEdgesOf(v.getID())) {
                Vertex destV = this.graph.getNode(e.getDestination());
                double x2 = destV.getX();
                double y2 = destV.getY();
//                Point2D dest = this.WorldToScreen(x2, y2);
                double[] adest=this.CoordinatesTransformation(destV);
                Point2D dest = new Point2D.Double(adest[0], adest[1]);
                this.drawArrow(g2d, src, dest, stroke, stroke, 16);
            }
        }
    }

    public void drawAgents(Graphics g) throws IOException {
        Graphics2D g2d=(Graphics2D) g;
        for (AgentV1 agent : this.agents.values()) {
            double x = agent.getX();
            double y = agent.getY();
            double[] coor= CoordinatesTransformation(x, y);
            File file = new File("Media/MasterBall.png");
            BufferedImage image = ImageIO.read(file);
            g2d.drawImage(image, (int)coor[0], (int)coor[1], this.height/25, this.height/25, this);
        }
    }

    public void drawPokemons(Graphics g) throws IOException {
        Graphics2D g2d=(Graphics2D) g;
        for(Pokemon pikachu: this.pokemons){
            double x=pikachu.getX();
            double y=pikachu.getY();
            double[] coor=CoordinatesTransformation(x,y);
            String path="Media/sprites"+String.valueOf(this.pokeRandom.randompokemon());
            File file=new File(path);
            BufferedImage image=ImageIO.read(file);
            g2d.drawImage(image, (int)coor[0], (int)coor[1], this.width/10, this.height/10, this);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        paintComponent(g);
        Image scenario = createImage((int) (this.width*0.9), (int) (this.height*0.9));
        Graphics gr = scenario.getGraphics();
        paintComponent(gr);
        g.drawImage(scenario, 0, 0, this.width, this.height, this);
    }

    public void paintComponent(Graphics g) {
        try {
            this.drawGraph(g);
            this.drawAgents(g);
            this.drawPokemons(g);
        } catch (IOException e) {
            System.out.println("something went wrong");
        }
    }

    public void SetBoundingBox() {
        Collection<Vertex> set= this.graph.nodes.values();
        Vertex curr = null;
        double Xmin = 0, Xmax = 0, Ymin = 0, Ymax = 0;
        Iterator<Vertex> nodeiter=set.iterator();
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
        if(this.graph.nodes.size()<=3){
            BoundingBox[0]-=10;
            BoundingBox[1]-=10;
            BoundingBox[2]+=10;
            BoundingBox[3]+=10;
        }
    }

    public double[] CoordinatesTransformation(Vertex p) {
        double dpx = this.BoundingBox[2] - this.BoundingBox[0];
        double dpy = this.BoundingBox[3] - this.BoundingBox[1];
        double dcx = this.BoundingBox[2] - p.getX();
        double dcy = this.BoundingBox[3] - p.getY();
        double xfixed=(dcx/dpx*this.width*0.8+0.01*this.height);
        double yfixed=(dcy/dpy*this.height*0.8+0.01*this.width);
        return new double[]{xfixed, yfixed};
    }

    public double[] CoordinatesTransformation(double x, double y) {
        double dpx = this.BoundingBox[2] - this.BoundingBox[0];
        double dpy = this.BoundingBox[3] - this.BoundingBox[1];
        double dcx = this.BoundingBox[2] - x;
        double dcy = this.BoundingBox[3] - y;
        double xfixed=(dcx/dpx*this.width*0.8);
        double yfixed=(dcy/dpy*this.height*0.8);
        return new double[]{xfixed, yfixed};
    }

    public static void music() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Clip clip= AudioSystem.getClip();
        File f=new File("Media/champion.wav");
        AudioInputStream ais = AudioSystem.getAudioInputStream(f);
        clip.open(ais);
        clip.start();
        SwingUtilities.invokeLater(()->{
            JOptionPane.showMessageDialog(null, "Close on exit!");
        });
    }

    public static void music2() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        Clip clip=AudioSystem.getClip();
        File f=new File("Media/op1.wav");
        AudioInputStream ais=AudioSystem.getAudioInputStream(f);
        clip.open(ais);
    }
}

