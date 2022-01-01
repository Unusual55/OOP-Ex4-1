package datastructures;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
    
    private static final int sampleSize = 20;
    private static final long seed = 42069;
    
    @BeforeAll
    static void setUp() {
        Utils.setSeed(EdgeTest.seed);
    }
    
    @Test
    @Order(1)
    void constructor() {
        Edge edge;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            edge = new Edge(i, i + 1, v);
            assertEquals(i, edge.getSource());
            assertEquals(i + 1, edge.getDestination());
            assertEquals(v, edge.getWeight(), 0.0001);
            edge = new Edge(2 * i, i);
            assertEquals(2 * i, edge.getSource());
            assertEquals(i, edge.getDestination());
            assertEquals(1.0, edge.getWeight(), 0.0001);
        }
        Edge edge2 = new Edge(sampleSize, sampleSize + 1, 0.123456789);
        edge = new Edge(edge2);
        assertEquals(sampleSize, edge.getSource());
        assertEquals(sampleSize + 1, edge.getDestination());
        assertEquals(0.123456789, edge.getWeight(), 0.0001);
    }
    
    @Test
    void getSource() {
        Edge edge;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            edge = new Edge(i, i + 1, v);
            assertEquals(i, edge.getSource());
            edge = new Edge(2 * i, i);
            assertEquals(2 * i, edge.getSource());
        }
    }
    
    @Test
    void getWeight() {
        Edge edge;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            edge = new Edge(i, i + 1, v);
            assertEquals(v, edge.getWeight(), 0.0001);
            edge = new Edge(2 * i, i);
            assertEquals(1.0, edge.getWeight(), 0.0001);
        }
    }
    
    @Test
    void getDestination() {
        Edge edge;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            edge = new Edge(i, i + 1, v);
            assertEquals(i + 1, edge.getDestination());
            edge = new Edge(2 * i, i);
            assertEquals(i, edge.getDestination());
        }
    }
    
    @Test
    void getType() {
        Edge edge;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            edge = new Edge(i, i + 1, v);
            assertEquals(1, edge.getType());
            edge = new Edge(2 * i, i);
            assertEquals(-1, edge.getType());
        }
    }
    
    @Test
    void setSource() {
        Edge edge;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            edge = new Edge(i, i + 1, v);
            assertEquals(i, edge.getSource());
            edge.setSource(2 * i);
            assertEquals(2 * i, edge.getSource());
        }
    }
    
    @Test
    void setWeight() {
        Edge edge;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            edge = new Edge(i, i + 1, v);
            assertEquals(v, edge.getWeight(), 0.0001);
            edge.setWeight(2 * v);
            assertEquals(2 * v, edge.getWeight(), 0.0001);
        }
    }
    
    @Test
    void setDestination() {
        Edge edge;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            edge = new Edge(i, i + 1, v);
            assertEquals(i + 1, edge.getDestination());
            edge.setDestination(2 * i);
            assertEquals(2 * i, edge.getDestination());
        }
    }
    
    @Test
    void testEquals() {
        Edge edge1, edge2;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            edge1 = new Edge(i, i + 1, v);
            edge2 = new Edge(i, i + 1, v);
            assertEquals(edge1, edge2);
            edge2 = new Edge(2 * i, i);
            assertNotEquals(edge1, edge2);
            edge2 = new Edge(i, 2 * i + 1, v);
            assertNotEquals(edge1, edge2);
            edge2 = new Edge(i, i + 1, 2 * v);
            assertNotEquals(edge1, edge2);
        }
    }
}