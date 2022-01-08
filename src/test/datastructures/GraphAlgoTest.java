package test.datastructures;

import datastructures.DijkstreeData;
import datastructures.GraphAlgo;
import org.jgrapht.alg.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GraphAlgoTest {

    @Test
    void dijkstra() {
    }

    @Test
    void center() throws Exception {
        GraphAlgo ga=new GraphAlgo();
        ga.loadGraph("C:\\Users\\ofrit\\IdeaProjects\\OOP-Ex4\\src\\data\\A5.json");
        Pair<Integer, HashMap<Integer, DijkstreeData>> output=ga.Center();
        System.out.println(output.getFirst());
        Assertions.assertEquals(40, output.getFirst());
    }
}