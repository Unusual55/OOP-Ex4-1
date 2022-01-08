package datastructures;

import ex4_java_client.Pokemon;

import java.util.Comparator;

/**
 * This class is a comparator of edges, which compare between the edges by their values.
 */
public class EdgeComparator implements Comparator<Edge> {
    /**
     * This function compare between two edges by their values, if o1 values if bigger than o2's, we will
     * return 1, if o2 value is bigger than o1, we will return -1, otherwise we will return 0
     * @param o1 The first edge
     * @param o2 The second edge
     * @return The result
     */
    @Override
    public int compare(Edge o1, Edge o2) {
        double v1=o1.getValue(), v2=o2.getValue();
        if (v1 > v2) {
            return -1;
        } else if (v1 < v2) {
            return 1;
        }
        return 0;
    }
}
