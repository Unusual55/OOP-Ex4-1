package datastructures;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class represent the data we want to use in our algorithms.
 * Each DijkstreeData contains HashMap of distances and HashMap of path, which represent the distance of the
 * shortest path of a vertex to any other vertex, and the shortest path to it
 */
public class DijkstreeData {
    public HashMap<Integer, Double> distance;
    public HashMap<Integer, Integer> path;
    public DijkstreeData(HashMap<Integer, Double> distance, HashMap<Integer, Integer> path){
        this.distance=distance;
        this.path=path;
    }

    /**
     * This function calculates the path backwards from the destination to the source using the path HashMap
     * @param src The id of the source node
     * @param dest The id of the destination node
     * @return LinkedList which contains the id of the nodes in the path
     */
    public LinkedList<Integer> getPath(int src, int dest){
        int index=dest;
        LinkedList<Integer> ret=new LinkedList<>();
        while(index!=src){
            ret.addFirst(index);
            index=this.path.get(index);
        }
        if(ret.size()>0&&ret.getFirst()!=src){
            ret.addFirst(src);
        }
        return ret;
    }
}
