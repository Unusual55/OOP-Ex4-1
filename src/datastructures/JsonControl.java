package datastructures;

import datastructures.serializers.*;
import com.google.gson.*;
import org.jgrapht.alg.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public void loadGraphJson(String graphStr){
        GsonBuilder builder=new GsonBuilder()
                .registerTypeAdapter(Edge.class, new EdgeAdapter())
                .registerTypeAdapter(Vertex.class, new NodeAdapter())
                .setPrettyPrinting();
        Gson g=builder.create();
        JSONObject jsonArray=new JSONObject(graphStr);
        JSONArray nodes=jsonArray.getJSONArray("Nodes");
        JSONArray edges=jsonArray.getJSONArray("Edges");
        this.g=new DWGraph();
        for(int i=0; i<nodes.length();i++){
            JSONObject obj=nodes.getJSONObject(i);
            int id=obj.getInt("id");
            String pos=obj.getString("pos");
            String[] position=pos.split(",");
            double x=Double.parseDouble(position[0]);
            double y=Double.parseDouble(position[1]);
            Vertex v=new Vertex(id,0d,x,y,0d);
            this.g.addNode(v);
        }
        for(int i=0; i<edges.length();i++){
            JSONObject obj=edges.getJSONObject(i);
            int src=obj.getInt("src");
            int dest=obj.getInt("dest");
            double weight=obj.getDouble("w");
            this.g.addEdge(src, dest, weight);
        }
    }
}
