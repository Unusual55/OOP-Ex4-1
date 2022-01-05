package ex4_java_client;

import datastructures.Edge;

public class AgentV2 extends AgentV1{
    private Edge goal;
    public AgentV2(int id, int src, double speed, double x, double y) {
        super(id, src, speed, x, y);
        this.goal=null;
    }

    public AgentV2(int id, int src, int dest, double x, double y, double value, double speed, long catchtime) {
        super(id, src, dest, x, y, value, speed, catchtime);
        this.goal=null;
    }

    public Edge getGoal() {
        return goal;
    }

    public void setGoal(Edge goal) {
        this.goal = goal;
    }
}
