package datastructures;
import ex4_java_client.Pokemon;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.HashMap;
import java.util.Set;

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

    public Vertex getNode(int key){
        if(!this.nodes.containsKey(key)){
            return null;
        }
        return nodes.get(key);
    }

    public Edge getEdge(int src, int dest){
        if(!this.nodes.containsKey(src)||!this.nodes.containsKey(dest)||!this.graph.containsEdge(src, dest)){
            return null;
        }
        return this.graph.getEdge(src, dest);
    }

    public boolean addNode(Vertex v){
        int id=v.getID();
        if(this.nodes.containsKey(id)||id<0){
            return false;
        }
        this.graph.addVertex(id);
        this.nodes.put(id, v);
        return true;
    }

    public boolean addEdge(int src, int dest, double weight){
        if(!this.nodes.containsKey(src)||!this.nodes.containsKey(dest)||weight<=0){
            return false;
        }
        this.graph.addEdge(src, dest, new Edge(src, dest, weight));
        return true;
    }

    public boolean removeNode(int key){
        if(!this.nodes.containsKey(key)){
            return false;
        }
        this.nodes.remove(key);
        this.graph.removeVertex(key);
        return true;
    }

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

    public int[] findWantedEdge(Pokemon p){
        Set<Edge> edges=this.graph.edgeSet();
        int type=p.type();
        for (Edge e:edges) {
            if((e.getSource()<e.getDestination() && type==-1)||e.getSource()>e.getDestination() && type==1){
                continue;
            }
            Vertex src=this.nodes.get(e.getSource());
            Vertex dest=this.nodes.get(e.getDestination());
            double x1=src.getX(), x2=dest.getX(), x3=p.getX();
            double y1=src.getY(), y2=dest.getY(), y3=p.getY();
            if((y2 - y1) * (x3 - x2) == (y3 - y2) * (x2 - x1)){
                return new int[]{src.getID(), dest.getID()};
            }
        }
        return new int[]{-1};
    }
}
