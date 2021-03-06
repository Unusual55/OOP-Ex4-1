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
    private final String Path;

    public VisPokemon(String path, Pokemon p) {
        super(p);
        if (p.type() == 1) {
            this.Path = "Media/sprites/" + path;
        } else {
            this.Path = "Media/sprites/shiny/" + path;
        }
    }

    /**
     * This function mark this pokemon as drawn so we will know we don't need to draw it again until we catch it.
     */

    public String getPath() {
        return this.Path;
    }
}
