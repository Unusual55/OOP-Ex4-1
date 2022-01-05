package ex4_java_client;

import datastructures.Edge;
import datastructures.EdgeComparator;

import java.util.*;

public class PositioningAlgorithms {
    private static int instance=0;
    public PositioningAlgorithms(){
        instance++;
    }
    private HashMap<Integer, Integer> setPositions(int CenterId, int AgentsNumber, Set<Edge> edges, HashSet<Pokemon> pokemon){
        HashMap<Integer, Integer> ret=new HashMap<>();
        HashSet<Integer> done=new HashSet<>();
        int counter=0;
        LinkedList<Edge> pq=new LinkedList<>();
        for(Edge e: edges){
            pq.add(e);
        }
        Collections.sort(pq, new EdgeComparator());
        while(!pq.isEmpty()&&counter<AgentsNumber){
            Edge p=pq.poll();
            ret.put(counter,p.getSource());
            counter++;
        }
        if(counter<AgentsNumber){
            for(Pokemon p:pokemon){
                if(ret.containsKey(p.getEsrc())){
                    continue;
                }
                ret.put(counter, p.getEsrc());
                counter++;
            }
        }
        while (counter<AgentsNumber){
            ret.put(counter, CenterId);
            counter++;
        }
        return ret;
    }

    public HashMap<Integer, Integer> getPositions(int centerId, int num, HashSet<Pokemon> pokemons, Set<Edge> edges){//make this object a singleton
        if(instance>1){
            return null;
        }
        return this.setPositions(centerId, num, edges, pokemons);
    }
}
