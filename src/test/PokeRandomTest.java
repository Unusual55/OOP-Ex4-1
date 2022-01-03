package test;

import ex4_java_client.Gui.PokeRandom;
import org.junit.jupiter.api.Test;

class PokeRandomTest {

    @Test
    void Happy_Elevator_Test(){
        PokeRandom pokeRandom=new PokeRandom();
        for(int i=0; i<100; i++){
            pokeRandom.randompokemon();
        }
    }
}