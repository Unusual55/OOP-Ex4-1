package ex4_java_client;

import datastructures.DWGraph;
import datastructures.DijkstreeData;

import java.util.*;

/**
 * This class is runs the allocation algorithms which allocate each agent target and control the agents
 * cooperation
 */
public class AllocationAlgorithm {
    private HashMap<Integer, AgentV1> agents;
    private HashMap<String, Pokemon> pokemons;
    private HashMap<Integer, DijkstreeData> data;
    private DWGraph graph;
    private HashMap<Integer, LinkedList<Integer>> agentslog;
    private HashSet<Double> SPEED;

    public AllocationAlgorithm(DWGraph g, HashMap<Integer, AgentV1> agents, HashSet<Pokemon> pokemons, HashMap<Integer, DijkstreeData> data) {
        this.agents = agents;
        this.SPEED=new HashSet<>();
        this.data = data;
        this.graph = g;
        this.pokemons = new HashMap<>();
        for (Pokemon p : pokemons) {
            this.pokemons.put(p.toString(), p);
        }
        this.agentslog = new HashMap<>();
        for (int id : agents.keySet()) {
            agentslog.put(id, new LinkedList<>());
        }
    }

    /**
     * This function updates the current agents and pokemons
     * @param agents The
     * @param pokemon
     */
    public void update(HashMap<Integer, AgentV1> agents, HashSet<Pokemon> pokemon) {
        HashMap<String, Pokemon> pokemons = new HashMap<>();
        SPEED=new HashSet<>();
        for (Pokemon p : pokemon) {
            pokemons.put(p.toString(), p);
        }
        for (AgentV1 a : agents.values()) {
            SPEED.add(a.getSpeed());
            this.agents.get(a.getId()).update(a);
            Pokemon target = this.agents.get(a.getId()).getTarget();
            if (target == null) {//if the agent target was caught or not allocated yet
                continue;
            }
            //if the pokemon already caught, we would like to remove the target of the agent before set a new one
            else if (!pokemons.containsKey(target.toString())) {
                this.agents.get(a.getId()).removeTarget();
            }

        }
        HashMap<String, Pokemon> ret = new HashMap<>();
        Iterator<Pokemon> pokerator = pokemons.values().iterator();
        while (pokerator.hasNext()) {
            Pokemon p = pokerator.next();
            //if the pokemon already exist in the last pokemon hashmap, we don't need to change it or remove it
            if (this.pokemons.containsKey(p.toString())) {
                ret.put(p.toString(), this.pokemons.get(p.toString()));
            } else {
                //the pokemon doesn't exist in the last pokemon hashmap, we need to add it to the current map
                ret.put(p.toString(), p);
            }
        }
        this.pokemons = ret;
    }

    /**
     * This function runs the allocation algorithms of the agents.
     * @return HashMap of the agent's current situation
     */
    public HashMap<Integer, AgentV1> AllocatePokemons() {
        /**
         * Create new HashMap which will be used to save the information about the alloctaions
         */
        HashMap<String, AgentV1> allocated = new HashMap<>();
        HashSet<AgentV1> alloc = new HashSet<>();
        for (AgentV1 a : this.agents.values()) {
            Pokemon banned = null;
            /**
             * In any case the agent is not available, which means he's on an edge, don't change his target
             */
            if (!a.isAvailable()) {
                continue;
            }
            /**
             * In any case it took more than the estimated time to catch the target pokemon, remove the target
             * and mark it so the allocation algorithms won't allocate it in the next during this function
             */
            if (System.currentTimeMillis() > a.getCatchTime() && a.getCatchTime() != -1) {
                banned = a.getTarget();
                a.removeTarget();
//                if(agents.size()==1&&banned!=null){
//                    this.pokemons.remove(banned.toString());
//                }
            }
            int agentid = a.getId();
            String pokemonstr = " ";
            double mindist = Double.MAX_VALUE, speed = a.getSpeed();
            List<Pokemon> list=new LinkedList<>();
            list.addAll(this.pokemons.values().stream().toList());
            for (Pokemon p : list) {

                //In any case the pokemon is the banned pokemon of this agent, skip to the next iteration

                if (banned != null && banned == p) {
                    continue;
                }

                int src = p.getEsrc(), dest = p.getEdest(), agentpos = a.getSrc();
                //In any case the pokemon already been assigned and switch from this agent to another,
                //skip to the next iteration
                if (pokemons.size() <= agents.size()&&agents.size()>1&&a.getTarget()!=null) {
                    if (a.getTarget()!=p&&p.containsPastAllocation(agentid)) {
                        continue;
                    }
                }
                //In any case the pokemon is the agent's target, skip to the next iteration
                if (a.getTarget() == p) {
                    allocated.put(p.toString(), a);
                }
                //In any case the pokemon is the closest pokemon to this agent, keep its distance from the agent
                //and keep his String identifier.
                double dist = (data.get(agentpos).distance.get(src) + this.graph.getEdge(src, dest).getWeight()) / speed;
                if (dist < mindist) {
                    mindist = dist;
                    pokemonstr = p.toString();
                }
            }
            //In any case no pokemon was selected for the agent, skip to the next iteration
            if(pokemonstr==" "){
                continue;
            }

            Pokemon p = this.pokemons.get(pokemonstr);
            if (this.agents.size() > 1) {
                //If the pokemon is a target of another agent, check who is the best agent to assign for the task
                if (allocated.containsKey(p.toString())) {
                    int betterAgent = this.getBetterAgent(allocated.get(p.toString()), a, p);
                    //If the current agent is the better one
                    if (betterAgent == allocated.get(p.toString()).getId()){
                        continue;
                    }
                    //If the other agent is better, switch between the current and other agents targets
                    a.removeTarget();
                    if (this.agents.get(betterAgent).getTarget() != null) {
                        Pokemon t = this.agents.get(betterAgent).getTarget();
                        LinkedList setpath = data.get(a.getDest()).getPath(a.getDest(), t.getEsrc());
                        double dis = this.data.get(a.getDest()).distance.get(t.getEsrc()) + this.graph.
                                getEdge(t.getEsrc(), t.getEdest()).getWeight();
                        long catcht = (long) (System.currentTimeMillis() + dis * 1000);

                        a.setCatchTime(catcht);
                        setpath.addLast(t.getEdest());
                        a.setPath(setpath);
                        a.setTarget(t);
                        this.agents.put(a.getId(), a);
                        alloc.remove(betterAgent);
                        alloc.add(this.agents.get(a.getId()));
                        allocated.put(p.toString(), a);
                        p.addPastAllocation(a.getId());
                        t.addPastAllocation(betterAgent);
                    }
                    a.advanceNextMove();
                    a = this.agents.get(betterAgent);
                }
            }
            //If the best pokemon to assign is the current target, skip to the next iteration
            if(a.getTarget()==p){
                continue;
            }
            //Set the new target for the agent
            a.setTarget(p);
            int src = p.getEsrc(), dest = p.getEdest(), agentpos = a.getSrc();
            LinkedList<Integer> l = data.get(agentpos).getPath(agentpos, src);
            long catchtime = (long) (System.currentTimeMillis() + 500 + mindist * 1000);
            a.setCatchTime(catchtime);
            l.addLast(dest);
            a.setPath(l);
            this.agents.put(a.getId(), a);
            alloc.add(this.agents.get(a.getId()));
            allocated.put(p.toString(), a);
            p.addPastAllocation(a.getId());
            a.advanceNextMove();
        }
        return this.agents;
    }

    /**
     * This function return a string which contain the next move of an agent in the valid format in order
     * to send it as update to the client
     * @param id The id of the agent
     * @return The agent's next move
     */
    public String AgentNextMove(int id) {
        AgentV1 a = this.agents.get(id);
        int next = a.getNextMove();
        this.agentslog.get(id).addLast(next);
        return "{\"agent_id\":" + id + ", \"next_node_id\": " + next + "}";
    }

    /**
     * This function get 2 agents and a pokemon and returns the id of the faster agent to reach the pokemon
     * @param agent1 The first agent
     * @param agent2 The second agent
     * @param p The pokemon
     * @return The id of the better agent
     */
    public int getBetterAgent(AgentV1 agent1, AgentV1 agent2, Pokemon p) {
        int src1 = agent1.getSrc(), src2 = agent2.getSrc();
        if(agent1.getDest()!=-1){
            src1=agent1.getDest();
        }
        if(agent2.getDest()!=-1){
            src2=agent2.getDest();
        }
        double dist1 = this.data.get(src1).distance.get(p.getEsrc()) + this.graph.getEdge(p.getEsrc(), p.getEdest()).getWeight();
        double dist2 = this.data.get(src2).distance.get(p.getEsrc()) + this.graph.getEdge(p.getEsrc(), p.getEdest()).getWeight();
        dist1 = dist1 / agent1.getSpeed();
        dist2 = dist2 / agent2.getSpeed();
        if (dist1 - 0.2 > dist2) {
            return agent2.getId();
        }
        return agent1.getId();
    }
}
