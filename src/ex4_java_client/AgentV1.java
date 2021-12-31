package ex4_java_client;

public class AgentV1 {
    private int id;
    private double score;
    private int src, dest;
    private double speed, x, y;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    //pokemon
    public AgentV1(int id, int src, double speed, double x, double y){
        this.id=id;
        this.score=0;
        this.src=src;
        this.dest=-1;
        this.speed=speed;
        this.x=x;
        this.y=y;
    }

    public AgentV1(int id, int src, int dest, double x, double y, double value, double speed){
        this.id=id;
        this.src=src;
        this.dest=dest;
        this.score=value;
        this.x=x;
        this.y=y;
        this.speed=speed;
    }

    public void update(double score, double speed, double x, double y, int dest){
        this.score=score;
        this.speed=speed;
        this.x=x;
        this.y=y;
        this.dest=dest;
    }

    public boolean isAvailable(){
        return this.dest!=-1;
    }
}
