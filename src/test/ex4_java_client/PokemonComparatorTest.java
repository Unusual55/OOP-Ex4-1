package test.ex4_java_client;
import datastructures.DWGraph;
import datastructures.GraphAlgo;
import ex4_java_client.GameJson;
import ex4_java_client.Pokemon;
import ex4_java_client.PokemonComparator;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class PokemonComparatorTest {

    @Test
    void compare() {
//        Pokemon p1=new Pokemon(0d, 0d, 5d, 1);
//        Pokemon p2=new Pokemon(5d, 6d, 8d, -1);
//        Pokemon p3=new Pokemon(19d, 3d, 1d, 1);
//        Pokemon p4=new Pokemon(15d, 4d, 25d, 1);
//        PriorityQueue<Pokemon> pq=new PriorityQueue<>(4, new PokemonComparator());
//        pq.add(p1);pq.add(p2);pq.add(p3);pq.add(p4);
//        for(int i=0;i<4;i++){
//            System.out.println(pq.poll());
//        }
        GraphAlgo ga=new GraphAlgo();
        try {
            ga.loadGraph("{\"Edges\":[{\"src\":0,\"w\":1.4004465106761335,\"dest\":1},{\"src\":0,\"w\":1.4620268165085584,\"dest\":10},{\"src\":1,\"w\":1.8884659521433524,\"dest\":0},{\"src\":1,\"w\":1.7646903245689283,\"dest\":2},{\"src\":2,\"w\":1.7155926739282625,\"dest\":1},{\"src\":2,\"w\":1.1435447583365383,\"dest\":3},{\"src\":3,\"w\":1.0980094622804095,\"dest\":2},{\"src\":3,\"w\":1.4301580756736283,\"dest\":4},{\"src\":4,\"w\":1.4899867265011255,\"dest\":3},{\"src\":4,\"w\":1.9442789961315767,\"dest\":5},{\"src\":5,\"w\":1.4622464066335845,\"dest\":4},{\"src\":5,\"w\":1.160662656360925,\"dest\":6},{\"src\":6,\"w\":1.6677173820549975,\"dest\":5},{\"src\":6,\"w\":1.3968360163668776,\"dest\":7},{\"src\":7,\"w\":1.0176531013725074,\"dest\":6},{\"src\":7,\"w\":1.354895648936991,\"dest\":8},{\"src\":8,\"w\":1.6449953452844968,\"dest\":7},{\"src\":8,\"w\":1.8526880332753517,\"dest\":9},{\"src\":9,\"w\":1.4575484853801393,\"dest\":8},{\"src\":9,\"w\":1.022651770039933,\"dest\":10},{\"src\":10,\"w\":1.1761238717867548,\"dest\":0},{\"src\":10,\"w\":1.0887225789883779,\"dest\":9}],\"Nodes\":[{\"pos\":\"35.18753053591606,32.10378225882353,0.0\",\"id\":0},{\"pos\":\"35.18958953510896,32.10785303529412,0.0\",\"id\":1},{\"pos\":\"35.19341035835351,32.10610841680672,0.0\",\"id\":2},{\"pos\":\"35.197528356739305,32.1053088,0.0\",\"id\":3},{\"pos\":\"35.2016888087167,32.10601755126051,0.0\",\"id\":4},{\"pos\":\"35.20582803389831,32.10625380168067,0.0\",\"id\":5},{\"pos\":\"35.20792948668281,32.10470908739496,0.0\",\"id\":6},{\"pos\":\"35.20746249717514,32.10254648739496,0.0\",\"id\":7},{\"pos\":\"35.20319591121872,32.1031462,0.0\",\"id\":8},{\"pos\":\"35.19597880064568,32.10154696638656,0.0\",\"id\":9},{\"pos\":\"35.18910131880549,32.103618700840336,0.0\",\"id\":10}]}");
            DWGraph g=ga.getGraph();
            GameJson gm=new GameJson(g);
            HashSet<Pokemon> pokemons =gm.JsonToPokemon("{\"Pokemons\":[{\"Pokemon\":{\"value\":5.0,\"type\":-1,\"pos\":\"35.188900353135324,32.105320110855615,0.0\"}},{\"Pokemon\":{\"value\":8.0,\"type\":-1,\"pos\":\"35.206679711961414,32.10571613186106,0.0\"}},{\"Pokemon\":{\"value\":13.0,\"type\":-1,\"pos\":\"35.212669424769075,32.105340746955505,0.0\"}},{\"Pokemon\":{\"value\":5.0,\"type\":-1,\"pos\":\"35.21120742821597,32.10240519983585,0.0\"}},{\"Pokemon\":{\"value\":9.0,\"type\":-1,\"pos\":\"35.2107064115802,32.10181728154006,0.0\"}},{\"Pokemon\":{\"value\":12.0,\"type\":-1,\"pos\":\"35.20704629752213,32.105471692111855,0.0\"}}]}\n");
//            PriorityQueue<Pokemon>pq=new PriorityQueue<>(pokemons.size(), new PokemonComparator());
            LinkedList<Pokemon> pq=new LinkedList<>();
            for(Pokemon p: pokemons){
                pq.add(p);
            }
            Collections.sort(pq, new PokemonComparator());
            System.out.println(pq);
            while(!pq.isEmpty()){
                System.out.println(pq.poll());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}