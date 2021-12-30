package test.datastructures;

import datastructures.JsonControl;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonControlTest {

    @Test
    void load() {
        JsonControl jc = new JsonControl();
        jc.load("C:\\Users\\ofrit\\IdeaProjects\\OOP-Ex4\\src\\data\\A5.json");
//        jc.g.getDijkstree();
        System.out.println("Hello world");
    }
}