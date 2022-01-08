package test.ex4_java_client;

import ex4_java_client.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {

    @Test
    void testEquals() {
        Pokemon p1=new Pokemon(3d, 4d, 5d, 1);
        Pokemon p2=new Pokemon(4.6, 13.4, 10d, -1);
        Assertions.assertFalse(p1.equals(p2));
        Pokemon p3=new Pokemon(3d, 4d, 5d, 1);
        Assertions.assertTrue(p1.equals(p3));
    }

    @Test
    void testToString() {
        Pokemon p1=new Pokemon(3d, 4d, 5d, 1);
        String expected="(3.0,4.0) value: 5.0 type: 1";
        Assertions.assertEquals(expected, p1.toString());
    }

    @Test
    void TestPastAllocation() {
        Pokemon p1=new Pokemon(3d, 4d, 5d, 1);
        p1.addPastAllocation(0);
        Assertions.assertTrue(p1.containsPastAllocation(0));
        Assertions.assertFalse(p1.containsPastAllocation(1));
        p1.addPastAllocation(1);
        Assertions.assertTrue(p1.containsPastAllocation(1));
        Assertions.assertEquals(2, p1.getPastSize());
    }
}