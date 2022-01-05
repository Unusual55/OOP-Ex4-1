package ex4_java_client;

import datastructures.DWGraph;
import datastructures.DijkstreeData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class AllocationAlgorithm {
    private HashMap<Integer, AgentV1> agents;
    private HashMap<String, Pokemon> pokemons;
    private HashMap<Integer, DijkstreeData> data;
    private DWGraph graph;
    private HashMap<Integer, LinkedList<Integer>> agentslog;
    boolean flag=false;

    public AllocationAlgorithm(DWGraph g, HashMap<Integer, AgentV1> agents, HashSet<Pokemon> pokemons, HashMap<Integer, DijkstreeData> data) {
        this.agents = agents;
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

    public void update(HashMap<Integer, AgentV1> agents, HashSet<Pokemon> pokemon) {
        HashMap<String, Pokemon> pokemons = new HashMap<>();
        for (Pokemon p : pokemon) {
            pokemons.put(p.toString(), p);
        }
        for (AgentV1 a : agents.values()) {
            this.agents.get(a.getId()).update(a);
            Pokemon target = this.agents.get(a.getId()).getTarget();
            if (target == null) {//if the agent target was caught or not allocated yet
                continue;
            }
            //if the pokemon already caught, we would like to remove the target of the agent before set a new one
            else if (!pokemons.containsKey(target.toString())) {
                this.agents.get(a.getId()).removeTarget();
            } else {
                //if the agent reached his destination, prepare his next move
                a.advanceNextMove();
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

    public HashMap<Integer, AgentV1> AllocatePokemons() {
        HashSet<Integer> realloc=new HashSet<>();
        HashMap<String, AgentV1> allocated = new HashMap<>();
        HashSet<AgentV1> alloc = new HashSet<>();
        for (AgentV1 a : this.agents.values()) {
            Pokemon banned=null;
            if (!a.isAvailable()) {
                continue;
            }
            if(System.currentTimeMillis()>a.getCatchTime()&&a.getCatchTime()!=-1){
                banned=a.getTarget();
                a.removeTarget();
            }
            int agentid = a.getId();
            String pokemonstr = " ";
            double mindist = Double.MAX_VALUE, speed = a.getSpeed();
            for (Pokemon p : this.pokemons.values()) {
//                if(a.getTarget()==p){
//                    pokemonstr=" ";
//                    break;
//                }
//                if a.getDest()!=-1) {
//                    continue;
//                }
                if(banned!=null&&banned==p){
                    continue;
                }
                int src = p.getEsrc(), dest = p.getEdest(), agentpos = a.getSrc(), id = a.getId();
//                if (src == dest) {
//                    continue;
//                }
                if (a.getTarget() == p) {
                    allocated.put(p.toString(), a);
                }
                double dist = (data.get(agentpos).distance.get(src) + this.graph.getEdge(src, dest).getWeight()) / speed;
                if (dist < mindist) {
                    mindist = dist;
                    pokemonstr = p.toString();
                }
            }
            if (pokemonstr.equals(" ")) {
                continue;
            }
            Pokemon p = this.pokemons.get(pokemonstr);
            if(this.agents.size()>1) {
                if (allocated.containsKey(p.toString())) {
                    int betterAgent = this.getBetterAgent(allocated.get(p.toString()), a, p);
                    if (betterAgent == allocated.get(p.toString()).getId()) {
                        continue;
                    }
                    a.removeTarget();
                    a = this.agents.get(betterAgent);
                }
            }
            a.setTarget(p);
            int src = p.getEsrc(), dest = p.getEdest(), agentpos = a.getSrc();
            LinkedList<Integer> l = data.get(agentpos).getPath(agentpos, src);
            long catchtime=(long)(System.currentTimeMillis()+mindist*1000);
            a.setCatchTime(catchtime);
            l.addLast(dest);
            a.setPath(l);
            this.agents.put(a.getId(), a);
            alloc.add(this.agents.get(a.getId()));
            allocated.put(p.toString(), a);
        }
//        if(flag) {
//            this.reallocateAgent(allocated, realloc);
//        }
//        flag=true;
        return this.agents;
    }

    public String AgentNextMove(int id) {
        AgentV1 a = this.agents.get(id);
        int next = a.getNextMove();
        this.agentslog.get(id).addLast(next);
        return "{\"agent_id\":" + id + ", \"next_node_id\": " + next + "}";
    }

    public boolean checkifStuck(int id) {
        AgentV1 a = this.agents.get(id);
        LinkedList<Integer> log = this.agentslog.get(id);
        final int size = log.size();
        if (size < 6) {
            return false;
        }
        int a1 = log.get(size - 4), b = log.get(size - 3), c = log.get(size - 2), d = log.get(size - 1);
        int e = log.get(size - 5), f = log.get(size - 6);
        if (a1 == c && a1 == f && b == d && b == e) {
            return true;
        }
        return false;
    }

    public int getBetterAgent(AgentV1 agent1, AgentV1 agent2, Pokemon p) {
        int src1 = agent1.getSrc(), src2 = agent2.getSrc();
        double dist1 = this.data.get(src2).distance.get(p.getEsrc()) + this.graph.getEdge(p.getEsrc(), p.getEdest()).getWeight();
        double dist2 = this.data.get(src2).distance.get(p.getEsrc()) + this.graph.getEdge(p.getEsrc(), p.getEdest()).getWeight();
        dist1=dist2/agent1.getSpeed();
        dist2 = dist2/agent2.getSpeed();
        if (dist1-0.4 > dist2) {
            return agent2.getId();
        }
        return agent1.getId();
    }

    public void reallocateAgent(HashMap<String, AgentV1> allocated, HashSet<Integer> agents) {
        for (Pokemon p : this.pokemons.values()) {
            if (allocated.containsKey(p.toString())) {
                continue;
            }
            double mindist = Double.MAX_VALUE;
            int minid=-1;
            AgentV1 bestAgent=this.agents.get(0);
            for (int k : agents) {
                AgentV1 a = this.agents.get(k);
                double speed = a.getSpeed();
                int id = a.getId();
                String pokemonstr = " ";
                if (!a.isAvailable()) {
                    continue;
                }
                int src = p.getEsrc(), dest = p.getEdest(), agentpos = a.getSrc();
                if (src == dest) {
                    continue;
                }
                if (a.getTarget() == p && checkifStuck(id)) {
                    a.removeTarget();
                }
                double dist = (data.get(agentpos).distance.get(src) + this.graph.getEdge(src, dest).getWeight()) / speed;
                if (dist < mindist) {
                    mindist = dist;
                    pokemonstr = p.toString();
                    minid=a.getId();
                    bestAgent=a;
                }
            }
            bestAgent.setTarget(p);
            int src = p.getEsrc(), dest = p.getEdest(), agentpos = bestAgent.getSrc();
            LinkedList<Integer> l = data.get(agentpos).getPath(agentpos, src);
            l.addLast(dest);
            bestAgent.setPath(l);
            this.agents.put(bestAgent.getId(), bestAgent);
        }
    }
}
