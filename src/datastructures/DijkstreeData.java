package datastructures;

import java.util.HashMap;
import java.util.LinkedList;

public class DijkstreeData {
    public HashMap<Integer, Double> distance;
    public HashMap<Integer, Integer> path;
    public DijkstreeData(HashMap<Integer, Double> distance, HashMap<Integer, Integer> path){
        this.distance=distance;
        this.path=path;
    }

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
