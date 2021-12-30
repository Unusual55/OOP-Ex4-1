package datastructures;

import datastructures.serializers.*;
import com.google.gson.*;
import org.jgrapht.alg.util.Pair;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class JsonControl {
    public DWGraph g;
    public void load(String file){
        GsonBuilder builder=new GsonBuilder()
                .registerTypeAdapter(Edge.class, new EdgeAdapter())
                .registerTypeAdapter(Vertex.class, new NodeAdapter())
                .setPrettyPrinting();
        Gson g=builder.create();
        try {
            FileReader reader = new FileReader(file);
            Pair<HashMap<Integer, Vertex>, HashMap<Integer, HashMap<Integer, Edge>>> p = GraphAdapter.toGraph(g.fromJson(reader, GraphAdapter.class));
            this.g= new DWGraph();
            for(Vertex v: p.getFirst().values()){
                this.g.addNode(v);
            }
            for (Vertex v: p.getFirst().values()){
                HashMap<Integer, Edge> edges = p.getSecond().get(v.getID());
                int src=v.getID();
                for(int dest: edges.keySet()){
                    this.g.addEdge(src, dest, edges.get(dest).getWeight());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
