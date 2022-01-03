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
     * This fucntion randimize a number between 1 and 898 and return a path to a pokemon which it's id is that number
     * @return
     */
    public String randompokemon() {
        Random rnd = new Random();
        int id = rnd.nextInt(898-1)+1;
        return "/"+id+".png";
    }
}
