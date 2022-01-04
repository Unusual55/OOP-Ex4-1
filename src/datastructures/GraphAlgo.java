package datastructures;

import org.jgrapht.alg.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class GraphAlgo {
    private DWGraph graph;

    public GraphAlgo(DWGraph g) {
        this.graph = g;
    }

    public GraphAlgo() {
        this.graph = new DWGraph();
    }

    public DWGraph getGraph() {
        return this.graph;
    }

    public boolean loadGraph(String file) throws Exception {
        JsonControl jc = new JsonControl();
        try {
            jc.loadGraphJson(file);
            this.graph = jc.g;
            return true;
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }

    public DijkstreeData Dijkstra(int src) {
        HashMap<Integer, Double> distance = new HashMap<>();
        HashMap<Integer, Integer> prev = new HashMap<>();
        for (int key : this.graph.nodes.keySet()) {
            if (key == src) {
                distance.put(src, 0d);
                prev.put(src, src);
            } else {
                distance.put(key, Double.MAX_VALUE);
                prev.put(src, -1);
            }
        }
        PriorityQueue<Vertex> pq = new PriorityQueue<>(this.graph.nodes.size());
        pq.add(new Vertex(src, 0.0));
        HashSet<Integer> settled = new HashSet<>();

        while (settled.size() != this.graph.nodes.size()) {
            if (pq.isEmpty()) {
                return new DijkstreeData(distance, prev);
            }
            int u = pq.remove().getID();
            if (settled.contains(u)) {
                continue;
            }
            settled.add(u);
            double edgeDistance = -1, newDistance = -1;

            for (Edge e : this.graph.graph.outgoingEdgesOf(u)) {
                Vertex v = this.graph.getNode(e.getDestination());
                int vid = v.getID();
                edgeDistance = e.getWeight();
                newDistance = edgeDistance + distance.get(u);
                if (newDistance < distance.get(vid)) {
                    distance.put(vid, newDistance);
                    prev.put(vid, u);
                }

                pq.add(new Vertex(vid, distance.get(vid)));
            }
        }
        return new DijkstreeData(distance, prev);
    }

    public Pair<Integer, HashMap<Integer, DijkstreeData>> Center() {
        int minid = -1;
        double mindist = Double.MAX_VALUE;
        HashMap<Integer, DijkstreeData> dijkstree = new HashMap<>();
        for (int key : this.graph.nodes.keySet()) {
            DijkstreeData djd = this.Dijkstra(key);
            double mincurr = Double.MIN_VALUE;
            for (int dijkey : djd.distance.keySet()) {
                if (key == dijkey) continue;
                mincurr = Math.max(mincurr, djd.distance.get(dijkey));
            }
            if (mincurr < mindist) {
                minid = key;
                mindist = mincurr;
            }
            dijkstree.put(key, djd);
        }
        return new Pair(minid, dijkstree);
    }

    public LinkedList<Integer> getPath(DijkstreeData dd, int src, int dest){
        int index=dest;
        LinkedList<Integer> ret=new LinkedList<>();
        while(index!=src){
            ret.addFirst(index);
            index=dd.path.get(dd);
        }
        return ret;
    }
}
