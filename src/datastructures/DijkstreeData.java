package datastructures;

import java.util.HashMap;

public class DijkstreeData {
    public HashMap<Integer, Double> distance;
    public HashMap<Integer, Integer> path;
    public DijkstreeData(HashMap<Integer, Double> distance, HashMap<Integer, Integer> path){
        this.distance=distance;
        this.path=path;
    }
}
