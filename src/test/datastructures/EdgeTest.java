package test.datastructures;

import datastructures.Edge;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    @Test
    void getSource() {
        Edge e=new Edge(1, 2, 1d);
        Assertions.assertEquals(1, e.getSource());
    }

    @Test
    void getWeight() {
        Edge e=new Edge(1, 2, 1d);
        Assertions.assertEquals(1d, e.getWeight());
    }

    @Test
    void getDestination() {
        Edge e=new Edge(1, 2, 1d);
        Assertions.assertEquals(2, e.getDestination());
    }

    @Test
    void setSource() {
        Edge e=new Edge(1, 2, 1d);
        e.setSource(3);
        Assertions.assertEquals(3, e.getSource());
    }

    @Test
    void setWeight() {
        Edge e=new Edge(1, 2, 1d);
        e.setWeight(2d);
        Assertions.assertEquals(2d, e.getWeight());
    }

    @Test
    void setDestination() {
        Edge e=new Edge(1, 2, 1d);
        e.setDestination(3);
        Assertions.assertEquals(3, e.getDestination());
    }

    @Test
    void toJSON() {
        Edge e=new Edge(1, 2, 1d);
        Assertions.assertTrue(e.toJSON() instanceof JSONObject);
        System.out.println(e.toJSON());
    }

    @Test
    void compareTo() {
    }

    @Test
    void testEquals() {
        Edge e1=new Edge(1, 2, 1d);
        Edge e2=new Edge(2, 1, 3d);
        Assertions.assertFalse(e1.equals(e2));
        Assertions.assertFalse(e1.equals(13));
        Edge e3=new Edge(1, 2, 1d);
        Assertions.assertEquals(e1, e3);
    }

    @Test
    void getType() {
        Edge e1=new Edge(1, 2, 1d);
        Edge e2=new Edge(2, 1, 3d);
        Assertions.assertEquals(1, e1.getType());
        Assertions.assertEquals(-1, e2.getType());
    }

    @Test
    void testToString() {
        Edge e1=new Edge(1, 2, 1d);
        Assertions.assertEquals("(1, 2): 1.0", e1.toString());
    }
}