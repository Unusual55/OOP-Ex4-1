package datastructures;
import ex4_java_client.Pokemon;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.HashMap;
import java.util.Set;

/**
 * This class represent graph data structure- G=(V,E) G- graph object, V- vertices, E- edges
 * We used JGraphT SimpleDirectedWeighted graph as the graph, and kept the Vertices data in different
 * HashMap.
 */

public class DWGraph{
    public SimpleDirectedWeightedGraph<Integer, Edge> graph;
    public HashMap<Integer, Vertex> nodes;
    public DWGraph() {
        this.graph=new SimpleDirectedWeightedGraph<>(Edge.class);
        this.nodes=new HashMap<>();
    }

    public DWGraph(DWGraph g){
        this.graph=g.graph;
        this.nodes=g.nodes;
    }

    /**
     * This function returns the node whom its key is the input key
     * @param key The id of the node
     * @return The wanted vertex, otherwise null
     */
    public Vertex getNode(int key){
        if(!this.nodes.containsKey(key)){
            return null;
        }
        return nodes.get(key);
    }

    /**
     * This function returns the edge (src,dest)
     * @param src The source of the edge
     * @param dest The destination of the edge
     * @return The wanted edge, otherwise null
     */
    public Edge getEdge(int src, int dest){
        if(!this.nodes.containsKey(src)||!this.nodes.containsKey(dest)||!this.graph.containsEdge(src, dest)){
            return null;
        }
        return this.graph.getEdge(src, dest);
    }

    /**
     * This fucntion add new vertex to the graph
     * @param v The new vertex
     * @return True if the vertex added successfully, otherwise false
     */
    public boolean addNode(Vertex v){
        int id=v.getID();
        if(this.nodes.containsKey(id)||id<0){
            return false;
        }
        this.graph.addVertex(id);
        this.nodes.put(id, v);
        return true;
    }

    /**
     * This function add new edge to the graph
     * @param src The source of the edge
     * @param dest The destination of the edge
     * @param weight The weight of the edge
     * @return True if the edge added successfully, otherwise false
     */
    public boolean addEdge(int src, int dest, double weight){
        if(!this.nodes.containsKey(src)||!this.nodes.containsKey(dest)||weight<=0){
            return false;
        }
        this.graph.addEdge(src, dest, new Edge(src, dest, weight));
        return true;
    }

    /**
     * This function remove a node from the graph
     * @param key The id of the node
     * @return True if the node successfully removed, otherwise false
     */
    public boolean removeNode(int key){
        if(!this.nodes.containsKey(key)){
            return false;
        }
        this.nodes.remove(key);
        this.graph.removeVertex(key);
        return true;
    }

    /**
     * This function remove an edge from the graph
     * @param src The id of the source node
     * @param dest The id of the destination node
     * @return True if the edge successfully removed, otherwise false
     */
    public boolean removeEdge(int src, int dest){
        if(!nodes.containsKey(src)||!nodes.containsKey(dest)||!graph.containsEdge(src, dest)){
            return false;
        }
        graph.removeEdge(src, dest);
        return true;
    }

    /**
     * This function returns a HashSet which contain all of the Edges in the graph
     * @return Set of Edges
     */
    public Set<Edge> getAllEdges(){
        return this.graph.edgeSet();
    }

    /**
     * This function calculates on which edge the pokemon was appeared, and after it found, it updates
     * it's value
     * @param p The pokemon
     * @return Array which contains the source and the destination of the edge.
     */
    public int[] findWantedEdge(Pokemon p){
        final double EPS=0.00000001;
        Set<Edge> edges=this.graph.edgeSet();
        int type=p.type();
        for (Edge e:edges) {
            if((e.getSource()<e.getDestination() && type<0)||e.getSource()>e.getDestination() && type>0){
                continue;
            }
            Vertex src=this.nodes.get(e.getSource());
            Vertex dest=this.nodes.get(e.getDestination());
            double x1=src.getX(), x2=dest.getX(), x3=p.getX();
            double y1=src.getY(), y2=dest.getY(), y3=p.getY();
            double slope;
            if(y2-y1==0){//if the segment is parallel to X axis
                slope=y2;
            }
            else if(x2-x1==0){//if the segment is parallel to Y axis
                if(x3==x2){
                    if((y2 - y1) * (x3 - x2) == (y3 - y2) * (x2 - x1)){
                        return new int[]{src.getID(), dest.getID()};
                    }
                }
                continue;
            }
            else{
                slope=(y2-y1)/(x2-x1);
                double ans=slope*(x3-x1)+y1;
                double ans2=slope*(x3-x2)+y2;
                double d1=src.distance(dest), d2=src.distance(x3,y3,0d), d3=dest.distance(x3,y3,0d);
                double ans3=d2+d3;
                if(y3==ans||ans2==y3||d1==ans3||Math.abs(ans3-d1)<=EPS){
                    e.updateValue(p.getValue());
                    return new int[]{src.getID(), dest.getID()};
                }
            }
        }
        return new int[]{-1};//if we didn't find any edge
    }

    /**
     * This function reset the values of all of the edges
     */
    public void resetEdgesValues(){
        for(Edge e:this.graph.edgeSet()){
            e.resetValue();
        }
    }
}
