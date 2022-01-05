package ex4_java_client;

import java.util.Comparator;

public class PokemonComparator implements Comparator<Pokemon> {

    @Override
    public int compare(Pokemon o1, Pokemon o2) {
        double v1 = o1.getValue(), v2 = o2.getValue();
        if (v1 > v2) {
            return -1;
        } else if (v1 < v2) {
            return 1;
        }
        return 0;
    }
}
