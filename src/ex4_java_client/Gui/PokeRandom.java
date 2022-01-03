package ex4_java_client.Gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Random;

/**
 * This class represent a pokemon randomizer which supports out gui.
 * we read a JSON file which contain all of the data, and we use it in order to create a valid path to a random
 * photo of a random pokemon,
 */
public class PokeRandom {
    private HashMap<Integer, String> pokemap;
    private static final double boazProb = 0.1;
    private static final int pokeAmount = 898;
    private final Random rnd = new Random();


    public PokeRandom() {
        pokemap = new HashMap<>();
        try {
            final JsonParser obj = new JsonParser();
            File file = new File("Media/all.json");
            FileReader fr = new FileReader(file);
            JsonReader jr = new JsonReader(fr);
            JsonElement je = obj.parse(jr);
            final JsonArray pokarray = je.getAsJsonObject().getAsJsonArray("All");
            for (int i = 0; i < pokarray.size(); i++) {
                final JsonElement pokemon = pokarray.get(i);

                int id = pokarray.get(i).getAsJsonObject().get("id").getAsJsonPrimitive().getAsInt();
                pokemap.put(id, String.valueOf(id) + ".png");
            }
        } catch (Exception e) {
            System.out.println("1");
        }
    }

    /**
     * This fucntion randimize a number between 1 and 898 and return a path to a pokemon which it's id is that number.
     * If the random number is a product of six, we will randomize another id, if its a product of 6, we will do it
     * one more time, if the third id is product of c we will put a suprise photo of a legendary pokemon.
     * @return
     */
    public String randompokemon() {
        int id = rnd.nextInt(pokeAmount - 1) + 1;
        if (id % 6 == 0){
            int id2= rnd.nextInt(pokeAmount-1)+1;
            if(id2%6==0){
                int id3=rnd.nextInt(pokeAmount-1)+1;
                if(id3%6==0) {
                    return "/boaz.png";
                }
            }
        }
        return "/" + id + ".png";
    }
}
