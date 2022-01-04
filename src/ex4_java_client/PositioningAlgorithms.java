package ex4_java_client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class PositioningAlgorithms {
    private static int instance=0;
    public PositioningAlgorithms(){
        instance++;
    }
    private HashMap<Integer, Integer> setPositions(int CenterId, int AgentsNumber, HashSet<Pokemon> pokemons){
        HashMap<Integer, Integer> ret=new HashMap<>();
        HashSet<Integer> done=new HashSet<>();
        int counter=0;
        Iterator<Pokemon> pokerator=pokemons.iterator();
        while(pokerator.hasNext()&&counter<AgentsNumber){
            Pokemon p=pokerator.next();
            ret.put(counter, p.getEsrc());
            counter++;
        }
        if(counter<AgentsNumber){
            while(counter<AgentsNumber){
                ret.put(counter, CenterId);
                counter++;
            }
        }
        return ret;
    }
    public HashMap<Integer, Integer> getPositions(int centerId, int num, HashSet<Pokemon> pokemons){//make this object a singleton
        if(instance>1){
            return null;
        }
        return this.setPositions(centerId, num, pokemons);
    }
}
