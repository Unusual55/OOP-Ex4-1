package ex4_java_client.Gui;

import ex4_java_client.Pokemon;

/**
 * This class represent a visual pokemon. We would like to keep some of its visual data as well as the non-visual
 * data.
 * We will use this class mostly in order to enchance the GUI experience, and prevent a case where we will repaint
 * all of the pokemon all of the time, we would like to repaint only the new ones, and only to remove the caught
 * ones. As we all know... Gotta catch'em all!
 */
public class VisPokemon extends Pokemon {
    private String Path;

    public VisPokemon(String path, double x, double y, int type, double value){
        super(x,y,value,type);
        this.Path="Media/sprites/"+path;
    }

    public VisPokemon(String path, Pokemon p){
        super(p);
        this.Path="Media/sprites/"+path;
    }

    /**
     * This function mark this pokemon as drawn so we will know we don't need to draw it again until we catch it.
     */

    public boolean equals(Object o){
        return super.equals(o);
    }

    public String getPath(){
        return this.Path;
    }
}
