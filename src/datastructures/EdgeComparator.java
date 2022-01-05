package datastructures;

import ex4_java_client.Pokemon;

import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge> {
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
