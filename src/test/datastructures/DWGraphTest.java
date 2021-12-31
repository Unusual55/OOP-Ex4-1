package test.datastructures;

import datastructures.DWGraph;
import datastructures.Edge;
import datastructures.JsonControl;
import datastructures.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DWGraphTest {

    @Test
    void getNode() {
        JsonControl jc = new JsonControl();
        jc.load("C:\\Users\\ofrit\\IdeaProjects\\OOP-Ex4\\src\\data\\A5.json");
        DWGraph g = jc.g;
        Assertions.assertTrue(g.getNode(0) instanceof Vertex);
    }

    @Test
    void getEdge() {
        JsonControl jc = new JsonControl();
        jc.load("C:\\Users\\ofrit\\IdeaProjects\\OOP-Ex4\\src\\data\\A5.json");
        DWGraph g = jc.g;
        Assertions.assertTrue(g.getEdge(0, 1) instanceof Edge);
    }

    @Test
    void addNode() {
        JsonControl jc = new JsonControl();
        jc.load("C:\\Users\\ofrit\\IdeaProjects\\OOP-Ex4\\src\\data\\A5.json");
        DWGraph g = jc.g;
        g.addNode(new Vertex(49, 0d, 0, 0, 0));
        Assertions.assertTrue(g.getNode(49) instanceof Vertex);
    }

    @Test
    void addEdge() {
        JsonControl jc = new JsonControl();
        jc.load("C:\\Users\\ofrit\\IdeaProjects\\OOP-Ex4\\src\\data\\A5.json");
        DWGraph g = jc.g;
        g.addEdge(3, 18, 1.6);
        Assertions.assertTrue(g.getEdge(3, 18) instanceof Edge);
    }

    @Test
    void removeNode() {
        JsonControl jc = new JsonControl();
        jc.load("C:\\Users\\ofrit\\IdeaProjects\\OOP-Ex4\\src\\data\\A5.json");
        DWGraph g = jc.g;
        g.removeNode(0);
        Assertions.assertNull(g.getNode(0));
    }

    @Test
    void removeEdge() {
        JsonControl jc = new JsonControl();
        jc.load("C:\\Users\\ofrit\\IdeaProjects\\OOP-Ex4\\src\\data\\A5.json");
        DWGraph g = jc.g;
        g.removeEdge(0, 1);
        Assertions.assertNull(g.getEdge(0, 1));
    }
}