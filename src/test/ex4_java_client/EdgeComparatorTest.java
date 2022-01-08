package test.ex4_java_client;

import datastructures.Edge;
import datastructures.EdgeComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeComparatorTest {
    @Test
    void TestCompare(){
        Edge e1=new Edge(0, 1, 1.5);
        e1.updateValue(2d);
        Edge e2=new Edge(1,0, 2.3);
        e2.updateValue(17d);
        Edge e3=new Edge(2,1,2.5);
        e3.updateValue(1d);
        Edge e4=new Edge(1,2,3.5);
        e4.updateValue(2d);
        EdgeComparator edgeComparator=new EdgeComparator();
        Assertions.assertEquals(-1, edgeComparator.compare(e1, e3));
        Assertions.assertEquals(1, edgeComparator.compare(e1, e2));
        Assertions.assertEquals(0, edgeComparator.compare(e1, e4));
    }

}