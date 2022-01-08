package datastructures;

import org.jgrapht.alg.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * This class is the class which run the graph algorithms on the wanted graph
 */
public class GraphAlgo {
    private DWGraph graph;

    public GraphAlgo(DWGraph g) {
        this.graph = g;
    }

    public GraphAlgo() {
        this.graph = new DWGraph();
    }

    /**
     * This function returns the current graph.
     * @return The current graph
     */
    public DWGraph getGraph() {
        return this.graph;
    }

    /**
     * This function get a json string which represent the graph and parse it to DWGraph object, if the
     * parsing was a success, it will set it as the current graph and return true, otherwise it will throw
     * an exception and return false
     * @param file The string which contain the graph information
     * @return True if the parsing was a success, otherwise false
     * @throws Exception In any case the parsing was not successful
     */
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

    /**
     * This function calculates the shortest paths from a vertex to any other vertex
     * @param src The id of the source node
     * @return DijkstraData which contains the distances and the paths.
     */
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

    /**
     * Max shortest path: The longest path among all of the shortest paths of vertex v. ->M_i, where i is the
     *     index of the node
     *     Let M be a set of Max shortest paths of every vertex in the graph. Assuming k is the key of the center
     *     node, then foreach m in M M_k<m. In this case we can say that k is the center node, since it's max
     *     shortest path is the minimal among all of the vertices in the graph.
     *     The function using dijkstra algorithm calculate all of the shortest path to all of the vertices and check
     *     which node has the minimal max shortest path.
     *     Time Complexity: O(|V|*(|E| +|V|log|V|)) where V is the number of vertices and E is the number of edges
     * @return Pair which contains the id of the center node and HashMap of every DijkstreeData we got from
     * running Dijkstra function
     */
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
}
