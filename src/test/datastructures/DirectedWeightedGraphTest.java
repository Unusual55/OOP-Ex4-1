package datastructures;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphTest {
    
    private static final int sampleSize = 20;
    private static final long seed = 42069;
    
    private DirectedWeightedGraph graph1;
    
    @BeforeAll
    static void setUp() {
        Utils.setSeed(DirectedWeightedGraphTest.seed);
    }
    
    @BeforeEach
    void setUpEach() {
        // https://i.stack.imgur.com/hF3mQ.png
        this.graph1 = new DirectedWeightedGraph();
        this.graph1.newNode('A', 1)
                .newNode('B', 2)
                .newNode('C', 3)
                .newNode('D', 4)
                .newNode('E', 5)
                .newNode('F', 6);
        this.graph1.addEdge('A', 'E', 0.1);
        this.graph1.addEdge('A', 'F', 0.9);
        this.graph1.addEdge('B', 'A', 0.3);
        this.graph1.addEdge('B', 'C', 0.3);
        this.graph1.addEdge('B', 'D', 0.4);
        this.graph1.addEdge('C', 'D', 0.6);
        this.graph1.addEdge('C', 'E', 0.4);
        this.graph1.addEdge('D', 'E', 1);
        this.graph1.addEdge('E', 'A', 0.55);
        this.graph1.addEdge('E', 'F', 0.45);
        this.graph1.addEdge('F', 'D', 1);
    }
    
    @Test
    void hasNode() {
        assertTrue(this.graph1.hasNode('A'));
        assertTrue(this.graph1.hasNode('B'));
        assertTrue(this.graph1.hasNode('C'));
        assertTrue(this.graph1.hasNode('D'));
        assertTrue(this.graph1.hasNode('E'));
        assertTrue(this.graph1.hasNode('F'));
        assertFalse(this.graph1.hasNode('G'));
        assertFalse(this.graph1.hasNode('H'));
        assertFalse(this.graph1.hasNode(999));
    }
    
    @Test
    void hasEdge() {
        assertFalse(this.graph1.hasEdge('A', 'B'));
        assertTrue(this.graph1.hasEdge('A', 'E'));
        assertTrue(this.graph1.hasEdge('A', 'F'));
        assertTrue(this.graph1.hasEdge('B', 'A'));
        assertTrue(this.graph1.hasEdge('B', 'C'));
        assertTrue(this.graph1.hasEdge('B', 'D'));
        assertTrue(this.graph1.hasEdge('C', 'D'));
        assertTrue(this.graph1.hasEdge('C', 'E'));
        assertTrue(this.graph1.hasEdge('D', 'E'));
        assertTrue(this.graph1.hasEdge('E', 'A'));
        assertTrue(this.graph1.hasEdge('E', 'F'));
        assertTrue(this.graph1.hasEdge('F', 'D'));
        assertFalse(this.graph1.hasEdge('A', 'G'));
        assertFalse(this.graph1.hasEdge('A', 'H'));
        assertFalse(this.graph1.hasEdge('A', 999));
    }
    
    @Test
    void getNode() {
        assertEquals(new Node('A', 1), this.graph1.getNode('A'));
        assertEquals(new Node('B', 2), this.graph1.getNode('B'));
        assertEquals(new Node('C', 3), this.graph1.getNode('C'));
        assertEquals(new Node('D', 4), this.graph1.getNode('D'));
        assertEquals(new Node('E', 5), this.graph1.getNode('E'));
        assertEquals(new Node('F', 6), this.graph1.getNode('F'));
        assertNull(this.graph1.getNode('G'));
        assertNull(this.graph1.getNode('H'));
    }
    
    @Test
    void getEdge() {
        assertEquals(new Edge('A', 'E', 0.1), this.graph1.getEdge('A', 'E'));
        assertEquals(new Edge('A', 'F', 0.9), this.graph1.getEdge('A', 'F'));
        assertEquals(new Edge('B', 'A', 0.3), this.graph1.getEdge('B', 'A'));
        assertEquals(new Edge('B', 'C', 0.3), this.graph1.getEdge('B', 'C'));
        assertEquals(new Edge('B', 'D', 0.4), this.graph1.getEdge('B', 'D'));
        assertEquals(new Edge('C', 'D', 0.6), this.graph1.getEdge('C', 'D'));
        assertEquals(new Edge('C', 'E', 0.4), this.graph1.getEdge('C', 'E'));
        assertEquals(new Edge('D', 'E', 1), this.graph1.getEdge('D', 'E'));
        assertEquals(new Edge('E', 'A', 0.55), this.graph1.getEdge('E', 'A'));
        assertEquals(new Edge('E', 'F', 0.45), this.graph1.getEdge('E', 'F'));
        assertEquals(new Edge('F', 'D', 1), this.graph1.getEdge('F', 'D'));
        assertNull(this.graph1.getEdge('A', 'G'));
        assertNull(this.graph1.getEdge('A', 'H'));
    }
    
    @Test
    void addNode() {
        assertTrue(this.graph1.addNode('G', 7));
        assertTrue(this.graph1.hasNode('G'));
        assertEquals(new Node('G', 7), this.graph1.getNode('G'));
        assertFalse(this.graph1.addNode('G', 99));
        assertEquals(new Node('G', 7), this.graph1.getNode('G'));
        
        assertTrue(this.graph1.addNode('H', 8));
        assertTrue(this.graph1.hasNode('H'));
        assertEquals(new Node('H', 8), this.graph1.getNode('H'));
        assertFalse(this.graph1.addNode('H', 99));
        assertEquals(new Node('H', 8), this.graph1.getNode('H'));
        
        assertEquals(8, this.graph1.getNodeCount());
    }
    
    @Test
    void newNode() {
        this.graph1.newNode('G', 7).newNode('G', 99);
        assertTrue(this.graph1.hasNode('G'));
        assertEquals(new Node('G', 7), this.graph1.getNode('G'));
        
        this.graph1.newNode('H', 8).newNode('H', 99);
        assertTrue(this.graph1.hasNode('H'));
        assertEquals(new Node('H', 8), this.graph1.getNode('H'));
        
        this.graph1.newNode('I', 9).newNode('J', 10);
        assertTrue(this.graph1.hasNode('I'));
        assertTrue(this.graph1.hasNode('J'));
        assertEquals(new Node('I', 9), this.graph1.getNode('I'));
        assertEquals(new Node('J', 10), this.graph1.getNode('J'));
        
        assertEquals(10, this.graph1.getNodeCount());
    }
    
    @Test
    void addEdge() {
        assertEquals(11, this.graph1.getEdgeCount());
        assertTrue(this.graph1.addEdge('A', 'C', 0.1));
        assertTrue(this.graph1.hasEdge('A', 'C'));
        assertEquals(new Edge('A', 'C', 0.1), this.graph1.getEdge('A', 'C'));
        assertFalse(this.graph1.addEdge('A', 'C', 4));
        assertFalse(this.graph1.addEdge('A', 'C', 0.1));
        assertEquals(new Edge('A', 'C', 0.1), this.graph1.getEdge('A', 'C'));
        assertEquals(12, this.graph1.getEdgeCount());
        
        assertTrue(this.graph1.addEdge('A', 'D', 0.2));
        assertTrue(this.graph1.hasEdge('A', 'D'));
        assertEquals(new Edge('A', 'D', 0.2), this.graph1.getEdge('A', 'D'));
        assertFalse(this.graph1.addEdge('A', 'D', 4));
        assertFalse(this.graph1.addEdge('A', 'D', 0.2));
        assertEquals(new Edge('A', 'D', 0.2), this.graph1.getEdge('A', 'D'));
        assertEquals(13, this.graph1.getEdgeCount());
        
        assertFalse(this.graph1.addEdge('A', 'E', 0.3));
        assertFalse(this.graph1.addEdge('A', 'E', 0.4));
        assertFalse(this.graph1.addEdge('A', 'E', 0.5));
        assertEquals(13, this.graph1.getEdgeCount());
    }
    
    @Test
    void connect() {
        assertEquals(11, this.graph1.getEdgeCount());
        this.graph1.connect('A', 'C', 0.1)
                .connect('A', 'C', 4)
                .connect('A', 'C', 0.1);
        assertTrue(this.graph1.hasEdge('A', 'C'));
        assertEquals(new Edge('A', 'C', 0.1), this.graph1.getEdge('A', 'C'));
        assertEquals(12, this.graph1.getEdgeCount());
        
        this.graph1.connect('A', 'D', 0.2)
                .connect('A', 'D', 4)
                .connect('A', 'D', 0.2);
        assertTrue(this.graph1.hasEdge('A', 'D'));
        assertEquals(new Edge('A', 'D', 0.2), this.graph1.getEdge('A', 'D'));
        assertEquals(13, this.graph1.getEdgeCount());
        
        this.graph1.connect('A', 'E', 0.3)
                .connect('A', 'E', 0.4)
                .connect('A', 'E', 0.5);
        assertEquals(13, this.graph1.getEdgeCount());
    }
    
    @Test
    void removeNode() {
        assertEquals(6, this.graph1.getNodeCount());
        assertEquals(11, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeNode('A'));
        assertFalse(this.graph1.hasNode('A'));
        assertEquals(5, this.graph1.getNodeCount());
        assertEquals(7, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeNode('B'));
        assertFalse(this.graph1.hasNode('B'));
        assertEquals(4, this.graph1.getNodeCount());
        assertEquals(5, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeNode('C'));
        assertFalse(this.graph1.hasNode('C'));
        assertEquals(3, this.graph1.getNodeCount());
        assertEquals(3, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeNode('D'));
        assertFalse(this.graph1.hasNode('D'));
        assertEquals(2, this.graph1.getNodeCount());
        assertEquals(1, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeNode('E'));
        assertFalse(this.graph1.hasNode('E'));
        assertEquals(1, this.graph1.getNodeCount());
        assertEquals(0, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeNode('F'));
        assertFalse(this.graph1.hasNode('F'));
        assertEquals(0, this.graph1.getNodeCount());
        assertEquals(0, this.graph1.getEdgeCount());
        
    }
    
    @Test
    void removeNODE() {
        assertEquals(6, this.graph1.getNodeCount());
        assertEquals(11, this.graph1.getEdgeCount());
        this.graph1.removeNODE('A').removeNODE('B');
        assertEquals(4, this.graph1.getNodeCount());
        assertEquals(5, this.graph1.getEdgeCount());
        assertTrue(!this.graph1.hasNode('A') && !this.graph1.hasNode('B'));
        this.graph1.removeNODE('C').removeNODE('D');
        assertEquals(2, this.graph1.getNodeCount());
        assertEquals(1, this.graph1.getEdgeCount());
        assertTrue(!this.graph1.hasNode('C') && !this.graph1.hasNode('D'));
        this.graph1.removeNODE('E').removeNODE('F');
        assertEquals(0, this.graph1.getNodeCount());
        assertEquals(0, this.graph1.getEdgeCount());
        assertTrue(!this.graph1.hasNode('E') && !this.graph1.hasNode('F'));
    }
    
    @Test
    void removeEdge() {
        assertEquals(11, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeEdge('A', 'C'));
        assertEquals(10, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeEdge('A', 'D'));
        assertEquals(9, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeEdge('A', 'E'));
        assertEquals(8, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeEdge('B', 'C'));
        assertEquals(7, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeEdge('B', 'D'));
        assertEquals(6, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeEdge('B', 'E'));
        assertEquals(5, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeEdge('C', 'D'));
        assertEquals(4, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeEdge('C', 'E'));
        assertEquals(3, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeEdge('D', 'E'));
        assertEquals(2, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeEdge('F', 'A'));
        assertEquals(1, this.graph1.getEdgeCount());
        assertTrue(this.graph1.removeEdge('F', 'B'));
        assertEquals(0, this.graph1.getEdgeCount());
    }
    
    @Test
    void removeEDGE() {
    }
    
    @Test
    void getAdjacentNodes() {
    }
    
    @Test
    void getInEdges() {
    }
    
    @Test
    void getOutEdges() {
    }
    
    @Test
    void getModCounter() {
    }
    
    @Test
    void getNodes() {
    }
    
    @Test
    void getEdges() {
    }
    
    @Test
    void inDegree() {
    }
    
    @Test
    void outDegree() {
    }
    
    @Test
    void getNodeCount() {
    }
    
    @Test
    void getEdgeCount() {
    }
    
    @Test
    void testEquals() {
    }
}