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
    private boolean Drawn;
    private String Path;

    public VisPokemon(boolean drawn, String path, double x, double y, int type, double value){
        super(x,y,value,type);
        this.Drawn=drawn;
        this.Path="Media/sprites/"+path;
    }

    public VisPokemon(boolean drawn, String path, Pokemon p){
        super(p);
        this.Drawn=drawn;
        this.Path="Media/sprites/"+path;
    }

    /**
     * This function mark this pokemon as drawn so we will know we don't need to draw it again until we catch it.
     */
    public void MarkAsDrawn(){
        this.Drawn=true;
    }

    public boolean equals(Object o){
        return super.equals(o);
    }

    public String getPath(){
        return this.Path;
    }
}
