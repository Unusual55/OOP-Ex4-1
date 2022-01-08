package ex4_java_client;

import datastructures.Edge;
import datastructures.EdgeComparator;

import java.util.*;

/**
 *This class is the positioning algorithms we use before the simulation.
 * This class is a singleton, which means we can create only one instance of this.
 */
public class PositioningAlgorithms {
    private static int instance=0;
    public PositioningAlgorithms(){
        instance++;
    }

    /**
     * This function runs the positioning algorithm.
     * @param CenterId The id of the center node
     * @param AgentsNumber The number of available agents
     * @param edges The edges of the graph
     * @param pokemon The available pokemon
     * @return HashMap of the agents starting position
     */
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

    /**
     * This function call the private function who runs the algorithms and return its output.
     * @param centerId The id of the center node
     * @param num The number of available agents
     * @param edges The edges of the graph
     * @param pokemons The available pokemon
     * @return HashMap of the agents starting position
     */
    public HashMap<Integer, Integer> getPositions(int centerId, int num, HashSet<Pokemon> pokemons, Set<Edge> edges){
        if(instance>1){
            return null;
        }
        return this.setPositions(centerId, num, edges, pokemons);
    }
}
