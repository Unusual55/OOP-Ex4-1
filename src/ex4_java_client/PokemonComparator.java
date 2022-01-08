package ex4_java_client;

import java.util.Comparator;

/**
 * This function is a comparator which compare the pokemons by their value property.
 */
public class PokemonComparator implements Comparator<Pokemon> {

    /**
     * This function compare between two pokemon by their values, if o1 values if bigger than o2's, we will
     * return 1, if o2 value is bigger than o1, we will return -1, otherwise we will return 0
     * @param o1 The first pokemon
     * @param o2 The second pokemon
     * @return The result
     */
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
