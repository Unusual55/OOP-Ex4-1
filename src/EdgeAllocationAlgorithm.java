//import datastructures.DWGraph;
//import datastructures.DijkstreeData;
//import datastructures.Edge;
//import datastructures.EdgeComparator;
//import ex4_java_client.AgentV1;
//import ex4_java_client.AgentV2;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.Set;
//
//public class EdgeAllocationAlgorithm {
//    private DWGraph g;
//    private HashMap<Integer, AgentV1> agents;
//    private HashMap<Integer, DijkstreeData> data;
//
//    public EdgeAllocationAlgorithm(DWGraph g, HashMap<Integer, AgentV1> agents, HashMap<Integer, DijkstreeData> data) {
//        this.g = g;
//        this.agents = agents;
//        this.data = data;
//    }
//
//    public void update(HashMap<Integer, AgentV1> agents) {
//        this.agents = agents;
//    }
//
//    public HashMap<Integer, AgentV1> allocateAgents() {
//        Set<Edge> set = this.g.getAllEdges();
//        LinkedList<Edge> edges = new LinkedList<>();
//        edges.addAll(set);
//        Collections.sort(edges, new EdgeComparator());
//        for (Edge e : edges) {
//            int id = -1;
//            double mindist = Double.MAX_VALUE;
//            for (AgentV1 a : this.agents.values()) {
//                if (!(a instanceof AgentV2)) {
//                    continue;
//                }
//                if (((AgentV2) a).getGoal() != null) {
//                    if (e.getValue() > ((AgentV2) a).getGoal().getValue() &&
//                            this.data.get(a.getId()).distance.get(e.getSource()) < this.data.get(a.getSrc())
//                                    .distance.get(((AgentV2) a).getGoal().getSource())) {
//                        if(mindist>)
//
//                    }
//                }
//            }
//        }
//    }
//}
