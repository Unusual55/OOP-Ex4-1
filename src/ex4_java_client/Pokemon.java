package ex4_java_client;

/**
 * This function represent a pokemon. Each pokemon contain few fields of information:
 * 1. Value: How many points the pokemon worth
 * 2. X, Y: The position of the pokemon in 2D space.
 * 3, Type: The type of the pokemon, if Esrc<Edest, then the type is 1, otherwise the type will be -1
 * 4. Esrc, Edest: The ids of the source and destenation nodes of the edge the pokemon is standing on.
 */
public class Pokemon implements Comparable<Pokemon>{
    private double value, x, y;
    private int type;
    private int Esrc, Edest;

    public Pokemon(double x, double y, double value, int type){
        this.x = x;
        this.y = y;
        this.value=value;
        this.type=type;
    }

    public Pokemon(Pokemon p){
        this.x=p.x;
        this.y=p.y;
        this.value=p.value;
        this.type=p.type;
    }

    /**
     * This function returns the value of the pokemon
     * @return value
     */
    public double getValue(){
        return this.value;
    }

    /**
     * This function returns the X coordinate of the pokemon
     * @return X
     */
    public double getX(){
        return this.x;
    }

    /**
     * This function returns the Y coordinate of the pokemon
     * @return Y
     */
    public double getY(){
        return this.y;
    }

    /**
     * This function returns the type of the pokemon
     * @return type
     */
    public int type(){
        return this.type;
    }

    /**
     * This function get an object as input and check if it's equal to this Pokemon.
     * The function will return true iff the input is Pokemon which its fields are equal to this pokemon fields,
     * otherwise the function will return false.
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(!(o instanceof Pokemon)){
            return false;
        }
        boolean b1=this.type==((Pokemon) o).type, b2=this.value==((Pokemon) o).value;
        boolean b3=this.x==((Pokemon) o).x, b4=this.y==((Pokemon) o).y;
        return b1&&b2&&b3&&b4;
    }

    /**
     * This function get pokemon p as input and compare it's value to this pokemon value and return a result:
     * -1: p's value is higher than this pokemon value
     * 0: Both values are equal
     * 1: This pokemon's value is higher than p's value
     * @param p A Pokemon
     * @return result
     */
    @Override
    public int compareTo(Pokemon p){
        double res=this.value-p.value;
        if(res==0){
            return 0;
        }
        if(res>0){
            return 1;
        }
        return -1;
    }
}
