package test.datastructures;

import datastructures.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    
    private static final int sampleSize = 20;
    private static final long seed = 42069;
    
    @BeforeAll
    static void setUp() {
        Utils.setSeed(NodeTest.seed);
    }
    
    @Test
    @Order(1)
    void constructor() {
        Node node;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            node = new Node(i, v);
            assertEquals(i, node.getID());
            assertEquals(v, node.getValue(), 0.0001);
            node = new Node(2 * i);
            assertEquals(2 * i, node.getID());
            assertEquals(0.0, node.getValue(), 0.0001);
        }
        Node node2 = new Node(sampleSize, 0.123456789);
        node = new Node(node2);
        assertEquals(sampleSize, node.getID());
        assertEquals(0.123456789, node.getValue(), 0.0001);
    }
    
    @Test
    void getID() {
        Node node;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            node = new Node(i, v);
            assertEquals(i, node.getID());
            node = new Node(2 * i);
            assertEquals(2 * i, node.getID());
        }
    }
    
    @Test
    void getValue() {
        Node node;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            node = new Node(i, v);
            assertEquals(v, node.getValue(), 0.0001);
            node = new Node(2 * i);
            assertEquals(0.0, node.getValue(), 0.0001);
        }
    }
    
    @Test
    void setID() {
        Node node;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            node = new Node(i, v);
            assertEquals(i, node.getID());
            node.setID(2 * i);
            assertEquals(2 * i, node.getID());
        }
    }
    
    @Test
    void setValue() {
        Node node;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            node = new Node(i, v);
            assertEquals(v, node.getValue(), 0.0001);
            node.setValue(2 * v);
            assertEquals(2 * v, node.getValue(), 0.0001);
        }
    }
    
    @Test
    void compareTo() {
        Node node1, node2;
        for (int i = 1; i <= sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            node1 = new Node(i, v);
            node2 = new Node(i, v);
            assertEquals(0, node1.compareTo(node2));
            node2 = new Node(2 * i, v);
            assertEquals(0, node1.compareTo(node2));
            node2 = new Node(i, 2 * v);
            assertEquals((int)Math.signum(-v), node1.compareTo(node2));
        }
    }
    
    @Test
    void testEquals() {
        Node node1, node2;
        for (int i = 1; i < sampleSize; i++) {
            double v = i + ((double)i / ((double)sampleSize + 1));
            node1 = new Node(i, v);
            node2 = new Node(i, v);
            assertEquals(node1, node2);
            node2 = new Node(2 * i, v);
            assertNotEquals(node1, node2);
            node2 = new Node(i, 2 * v);
            assertNotEquals(node1, node2);
        }
    }
}