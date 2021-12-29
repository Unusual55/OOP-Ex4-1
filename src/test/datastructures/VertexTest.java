package datastructures;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {
    
    private static final int sampleSize = 20;
    private static final long seed = 42069;
    
    @BeforeAll
    static void setUp() {
        Utils.setSeed(VertexTest.seed);
    }
    
    @Test
    @Order(1)
    void constructor() {
        Vertex vertex;
        for (int i = 1; i <= sampleSize; i++) {
            double x = i + ((double)i / ((double)sampleSize + 1));
            double y = i + ((double)i / ((double)sampleSize + 2));
            double z = i + ((double)i / ((double)sampleSize + 3));
            vertex = new Vertex(i, x, y, z);
            assertEquals(i, vertex.getID());
            assertEquals(x, vertex.getX(), 0.0001);
            assertEquals(y, vertex.getY(), 0.0001);
            assertEquals(z, vertex.getZ(), 0.0001);
            vertex = new Vertex(i, x, y);
            assertEquals(i, vertex.getID());
            assertEquals(x, vertex.getX(), 0.0001);
            assertEquals(y, vertex.getY(), 0.0001);
            assertEquals(0, vertex.getZ());
            vertex = new Vertex(i, x + "," + y + "," + z);
            assertEquals(i, vertex.getID());
            assertEquals(x, vertex.getX(), 0.0001);
            assertEquals(y, vertex.getY(), 0.0001);
            assertEquals(z, vertex.getZ(), 0.0001);
        }
        Vertex vertex2 = new Vertex(1, 0.123, 0.456, 0.789);
        vertex = new Vertex(vertex2);
        assertEquals(vertex2.getID(), vertex.getID());
        assertEquals(vertex2.getX(), vertex.getX(), 0.0001);
        assertEquals(vertex2.getY(), vertex.getY(), 0.0001);
        assertEquals(vertex2.getZ(), vertex.getZ(), 0.0001);
    }
    
    @Test
    void getX() {
        Vertex vertex;
        for (int i = 1; i <= sampleSize; i++) {
            double x = i + ((double)i / ((double)sampleSize + 1));
            double y = i + ((double)i / ((double)sampleSize + 2));
            double z = i + ((double)i / ((double)sampleSize + 3));
            vertex = new Vertex(i, x, y, z);
            assertEquals(x, vertex.getX(), 0.0001);
        }
    }
    
    @Test
    void getY() {
        Vertex vertex;
        for (int i = 1; i <= sampleSize; i++) {
            double x = i + ((double)i / ((double)sampleSize + 1));
            double y = i + ((double)i / ((double)sampleSize + 2));
            double z = i + ((double)i / ((double)sampleSize + 3));
            vertex = new Vertex(i, x, y, z);
            assertEquals(y, vertex.getY(), 0.0001);
        }
    }
    
    @Test
    void getZ() {
        Vertex vertex;
        for (int i = 1; i <= sampleSize; i++) {
            double x = i + ((double)i / ((double)sampleSize + 1));
            double y = i + ((double)i / ((double)sampleSize + 2));
            double z = i + ((double)i / ((double)sampleSize + 3));
            vertex = new Vertex(i, x, y, z);
            assertEquals(z, vertex.getZ(), 0.0001);
        }
    }
    
    @Test
    void getXYZ() {
        Vertex vertex;
        for (int i = 1; i <= sampleSize; i++) {
            double x = i + ((double)i / ((double)sampleSize + 1));
            double y = i + ((double)i / ((double)sampleSize + 2));
            double z = i + ((double)i / ((double)sampleSize + 3));
            vertex = new Vertex(i, x, y, z);
            double[] xyz = vertex.getXYZ();
            assertEquals(x, xyz[0], 0.0001);
            assertEquals(y, xyz[1], 0.0001);
            assertEquals(z, xyz[2], 0.0001);
        }
    }
    
    @Test
    void getXYZString() {
        Vertex vertex;
        for (int i = 1; i <= sampleSize; i++) {
            double x = i + ((double)i / ((double)sampleSize + 1));
            double y = i + ((double)i / ((double)sampleSize + 2));
            double z = i + ((double)i / ((double)sampleSize + 3));
            vertex = new Vertex(i, x, y, z);
            String xyz = vertex.getXYZString();
            assertEquals(x + "," + y + "," + z, xyz);
        }
    }
    
    @Test
    void setX() {
        Vertex vertex;
        for (int i = 1; i <= sampleSize; i++) {
            double x = i + ((double)i / ((double)sampleSize + 1));
            double y = i + ((double)i / ((double)sampleSize + 2));
            double z = i + ((double)i / ((double)sampleSize + 3));
            vertex = new Vertex(i, x, y, z);
            vertex.setX(x + 1);
            assertEquals(x + 1, vertex.getX(), 0.0001);
        }
    }
    
    @Test
    void setY() {
        Vertex vertex;
        for (int i = 1; i <= sampleSize; i++) {
            double x = i + ((double)i / ((double)sampleSize + 1));
            double y = i + ((double)i / ((double)sampleSize + 2));
            double z = i + ((double)i / ((double)sampleSize + 3));
            vertex = new Vertex(i, x, y, z);
            vertex.setY(y + 1);
            assertEquals(y + 1, vertex.getY(), 0.0001);
        }
    }
    
    @Test
    void setZ() {
        Vertex vertex;
        for (int i = 1; i <= sampleSize; i++) {
            double x = i + ((double)i / ((double)sampleSize + 1));
            double y = i + ((double)i / ((double)sampleSize + 2));
            double z = i + ((double)i / ((double)sampleSize + 3));
            vertex = new Vertex(i, x, y, z);
            vertex.setZ(z + 1);
            assertEquals(z + 1, vertex.getZ(), 0.0001);
        }
    }
    
    @Test
    void setXYZ() {
        Vertex vertex;
        for (int i = 1; i <= sampleSize; i++) {
            double x = i + ((double)i / ((double)sampleSize + 1));
            double y = i + ((double)i / ((double)sampleSize + 2));
            double z = i + ((double)i / ((double)sampleSize + 3));
            vertex = new Vertex(i, x, y, z);
            vertex.setXYZ(x + 1, y + 1, z + 1);
            assertEquals(x + 1, vertex.getX(), 0.0001);
            assertEquals(y + 1, vertex.getY(), 0.0001);
            assertEquals(z + 1, vertex.getZ(), 0.0001);
            vertex.setXYZ((x + 2) + "," + (y + 2) + "," + (z + 2));
            assertEquals(x + 2, vertex.getX(), 0.0001);
            assertEquals(y + 2, vertex.getY(), 0.0001);
            assertEquals(z + 2, vertex.getZ(), 0.0001);
        }
    }
    
    @Test
    void squaredDistance() {
        Vertex vertex;
        for (int i = 1; i <= sampleSize; i++) {
            double x = i + ((double)i / ((double)sampleSize + 1));
            double y = i + ((double)i / ((double)sampleSize + 2));
            double z = i + ((double)i / ((double)sampleSize + 3));
            vertex = new Vertex(i, x, y, z);
            double distance = Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
            assertEquals(distance, vertex.squaredDistance(), 0.0001);
            Vertex vertex2 = new Vertex(i, x + 1, y + 1, z + 1);
            assertEquals(3, vertex.squaredDistance(vertex2), 0.0001);
            assertEquals(3, vertex.squaredDistance(x + 1, y + 1, z + 1), 0.0001);
        }
    }
    
    @Test
    void distance() {
        Vertex vertex;
        for (int i = 1; i <= sampleSize; i++) {
            double x = i + ((double)i / ((double)sampleSize + 1));
            double y = i + ((double)i / ((double)sampleSize + 2));
            double z = i + ((double)i / ((double)sampleSize + 3));
            vertex = new Vertex(i, x, y, z);
            double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
            assertEquals(distance, vertex.distance(), 0.0001);
            Vertex vertex2 = new Vertex(i, x + 1, y + 1, z + 1);
            assertEquals(Math.sqrt(3), vertex.distance(vertex2), 0.0001);
            assertEquals(Math.sqrt(3), vertex.distance(x + 1, y + 1, z + 1), 0.0001);
        }
    }
    
    @Test
    void testEquals() {
        Vertex vertex1, vertex2;
        for (int i = 1; i <= sampleSize; i++) {
            double x = i + ((double)i / ((double)sampleSize + 1));
            double y = i + ((double)i / ((double)sampleSize + 2));
            double z = i + ((double)i / ((double)sampleSize + 3));
            vertex1 = new Vertex(i, x, y, z);
            vertex2 = new Vertex(i, x, y, z);
            assertEquals(vertex1, vertex2);
            vertex2 = new Vertex(i, x + 1, y + 1, z + 1);
            assertNotEquals(vertex1, vertex2);
            vertex2 = new Vertex(2 * i, x, y, z);
            assertNotEquals(vertex1, vertex2);
        }
    }
}