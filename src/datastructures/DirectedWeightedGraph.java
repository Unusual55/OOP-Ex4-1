package datastructures;

import api.AbstractDirectedWeightedGraph;
import api.AbstractEdge;
import api.AbstractNode;
import utils.Utils;

import java.util.*;
import java.util.function.Consumer;

public class DirectedWeightedGraph implements AbstractDirectedWeightedGraph {
    
    
    private HashMap<Integer, HashMap<Integer, AbstractEdge>> outEdges;
    private HashMap<Integer, HashSet<Integer>> inEdges;
    private HashMap<Integer, AbstractNode> nodes;
    private int modificationCounter;
    private int edgeCounter;
    
    /**
     * Constructor for DirectedWeightedGraph
     */
    public DirectedWeightedGraph() {
        this.outEdges = new HashMap<>();
        this.inEdges = new HashMap<>();
        this.nodes = new HashMap<>();
        this.modificationCounter = 0;
        this.edgeCounter = 0;
    }
    
    /**
     * Cloning constructor for DirectedWeightedGraph
     * (Note: The modification counter may be different from the original graph's modification counter)
     *
     * @param graph The graph to clone.
     */
    public DirectedWeightedGraph(AbstractDirectedWeightedGraph graph) {
        this();
        AbstractNode node;
        Iterator<AbstractNode> nodes = graph.getNodes();
        while (nodes.hasNext()) {
            node = nodes.next();
            this.addNode(node.getID(), node.getValue());
        }
        AbstractEdge edge;
        Iterator<AbstractEdge> edges = graph.getEdges();
        while (edges.hasNext()) {
            edge = edges.next();
            this.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
        }
    }
    
    
    /**
     * @param node The id of the node.
     * @return If the node exists in the graph.
     */
    @Override
    public boolean hasNode(int node) {
        return this.nodes.containsKey(node);
    }
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @return If the edge exists in the graph.
     */
    @Override
    public boolean hasEdge(int source, int destination) {
        return this.outEdges.containsKey(source) && this.outEdges.get(source).containsKey(destination);
    }
    
    /**
     * @param node The id of the node.
     * @return The node with the given id.
     */
    @Override
    public AbstractNode getNode(int node) {
        return this.nodes.get(node);
    }
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @return The edge with the given source and destination.
     */
    @Override
    public AbstractEdge getEdge(int source, int destination) {
        if (this.hasEdge(source, destination)) {
            return this.outEdges.get(source).get(destination);
        }
        return null;
    }
    
    /**
     * @param id    The id of the node to add.
     * @param value The value of the node to add.
     * @return True if the node was added, false otherwise (If the node already exists).
     */
    @Override
    public boolean addNode(int id, double value) {
        if (this.hasNode(id)) {
            return false;
        }
        this.nodes.put(id, new Node(id, value));
        this.modificationCounter++;
        return true;
    }
    
    /**
     * For node addition chaining
     *
     * @param id    The id of the new node
     * @param value The value of the new node
     * @return The graph instance
     */
    public DirectedWeightedGraph addNODE(int id, double value) {
        this.addNode(id, value);
        return this;
    }
    
    /**
     * For node addition chaining
     *
     * @param id The id of the new node
     * @return The graph instance
     */
    public DirectedWeightedGraph addNODE(int id) {
        this.addNode(id);
        return this;
    }
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @param weight      The weight of the edge to add.
     * @return True if the edge was added, false otherwise
     */
    @Override
    public boolean addEdge(int source, int destination, double weight) {
        if (this.hasNode(source) && this.hasNode(destination)) {
            AbstractEdge edge = new Edge(source, destination, weight);
            if (!this.outEdges.containsKey(source)) {
                this.outEdges.put(source, new HashMap<>());
            }
            if (!this.inEdges.containsKey(destination)) {
                this.inEdges.put(destination, new HashSet<>());
            }
            
            this.outEdges.get(source).put(destination, edge);
            if (this.inEdges.get(destination).add(source)) {
                this.edgeCounter++;
            }
            
            this.modificationCounter++;
            return true;
        }
        return false;
    }
    
    /**
     * For edge addition chaining
     *
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @param weight      The weight of the edge to add.
     * @return The graph instance
     */
    public DirectedWeightedGraph addEDGE(int source, int destination, double weight) {
        this.addEdge(source, destination, weight);
        return this;
    }
    
    /**
     * For edge addition chaining
     *
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @return The graph instance
     */
    public DirectedWeightedGraph addEDGE(int source, int destination) {
        this.addEdge(source, destination);
        return this;
    }
    
    /**
     * @param node The id of the node to remove.
     * @return True if the node was removed, false otherwise (If the node does not exist).
     */
    @Override
    public boolean removeNode(int node) {
        if (!this.hasNode(node)) {
            return false;
        }
        AbstractNode removed = this.nodes.remove(node);
        for (int inEdge : Utils.emptyIfNull(this.inEdges.get(node))) {
            this.outEdges.get(inEdge).remove(node);
            if (this.outEdges.get(inEdge).isEmpty()) {
                this.outEdges.remove(inEdge);
            }
        }
        this.edgeCounter -= this.inEdges.getOrDefault(node, new HashSet<>()).size();
        this.inEdges.remove(node);
        
        for (AbstractEdge outEdge : Utils.emptyIfNull(this.outEdges.get(node).values())) {
            this.inEdges.get(outEdge.getDestination()).remove(node);
            if (this.inEdges.get(outEdge.getDestination()).isEmpty()) {
                this.inEdges.remove(outEdge.getDestination());
            }
        }
        this.edgeCounter -= this.outEdges.getOrDefault(node, new HashMap<>()).size();
        this.outEdges.remove(node);
        
        this.modificationCounter++;
        return true;
    }
    
    /**
     * For node removal chaining
     *
     * @param node The id of the node to remove.
     * @return The graph instance
     */
    public DirectedWeightedGraph removeNODE(int node) {
        this.removeNode(node);
        return this;
    }
    
    /**
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @return True if the edge was removed, false otherwise (If the edge does not exist).
     */
    @Override
    public boolean removeEdge(int source, int destination) {
        if (!this.hasEdge(source, destination)) {
            return false;
        }
        this.outEdges.get(source).remove(destination);
        if (this.outEdges.get(source).isEmpty()) {
            this.outEdges.remove(source);
        }
        this.inEdges.get(destination).remove(source);
        if (this.inEdges.get(destination).isEmpty()) {
            this.inEdges.remove(destination);
        }
        this.edgeCounter--;
        this.modificationCounter++;
        return true;
    }
    
    /**
     * For edge removal chaining
     *
     * @param source      The id of the source node.
     * @param destination The id of the destination node.
     * @return The graph instance
     */
    public DirectedWeightedGraph removeEDGE(int source, int destination) {
        this.removeEdge(source, destination);
        return this;
    }
    
    /**
     * @param node The id of the node
     * @return An iterator over the adjacent nodes of the given node, or null if the node does not exist.
     */
    @Override
    public Iterator<AbstractNode> getAdjacentNodes(int node) {
        if (!this.hasNode(node)) {
            return null;
        }
        return new Iterator<AbstractNode>() {
            private int modCount = DirectedWeightedGraph.this.modificationCounter;
            private final Iterator<AbstractEdge> iterator = Utils.emptyIfNull(DirectedWeightedGraph.this.outEdges.get(node).values()).iterator();
            private AbstractEdge next = null;
            
            @Override
            public boolean hasNext() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                return this.iterator.hasNext();
            }
            
            @Override
            public AbstractNode next() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                this.next = this.iterator.next();
                return DirectedWeightedGraph.this.nodes.get(this.next.getDestination());
            }
            
            @Override
            public void remove() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                if (this.next == null) {
                    throw new IllegalStateException();
                }
//                DirectedWeightedGraph.this.removeEdge(node, this.next.getDestination());
                DirectedWeightedGraph.this.removeNode(this.next.getDestination());
                this.modCount = DirectedWeightedGraph.this.modificationCounter;
            }
            
            @Override
            public void forEachRemaining(Consumer<? super AbstractNode> action) {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                this.iterator.forEachRemaining((e) -> action.accept(DirectedWeightedGraph.this.nodes.get(e.getDestination())));
            }
        };
    }
    
    /**
     * @param node The id of the node
     * @return An iterator over the in-edges of the node, or null if the node does not exist.
     */
    @Override
    public Iterator<AbstractEdge> getInEdges(int node) {
        if (!this.hasNode(node)) {
            return null;
        }
//        return this.inEdges.get(node).values().iterator();
        return new Iterator<AbstractEdge>() {
            private int modCount = DirectedWeightedGraph.this.modificationCounter;
            private final Iterator<Integer> iterator = Utils.emptyIfNull(DirectedWeightedGraph.this.inEdges.get(node)).iterator();
            private Integer next = null;
            
            @Override
            public boolean hasNext() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                return this.iterator.hasNext();
            }
            
            @Override
            public AbstractEdge next() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                this.next = this.iterator.next();
                return DirectedWeightedGraph.this.outEdges.get(this.next).get(node);
            }
            
            @Override
            public void remove() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                if (this.next == null) {
                    throw new IllegalStateException();
                }
                DirectedWeightedGraph.this.removeEdge(this.next, node);
                this.modCount = DirectedWeightedGraph.this.modificationCounter;
            }
            
            @Override
            public void forEachRemaining(Consumer<? super AbstractEdge> action) {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                this.iterator.forEachRemaining((e) -> action.accept(DirectedWeightedGraph.this.outEdges.get(e).get(node)));
            }
        };
    }
    
    /**
     * @param node The id of the node
     * @return An iterator over the out-edges of the node, or null if the node does not exist.
     */
    @Override
    public Iterator<AbstractEdge> getOutEdges(int node) {
        if (!this.hasNode(node)) {
            return null;
        }
        return new Iterator<AbstractEdge>() {
            private int modCount = DirectedWeightedGraph.this.modificationCounter;
            private final Iterator<AbstractEdge> iterator = Utils.emptyIfNull(DirectedWeightedGraph.this.outEdges.get(node).values()).iterator();
            private AbstractEdge next = null;
            
            @Override
            public boolean hasNext() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                return this.iterator.hasNext();
            }
            
            @Override
            public AbstractEdge next() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                return this.next = this.iterator.next();
            }
            
            @Override
            public void remove() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                if (this.next == null) {
                    throw new IllegalStateException();
                }
                DirectedWeightedGraph.this.removeEdge(node, this.next.getDestination());
                this.modCount = DirectedWeightedGraph.this.modificationCounter;
            }
        };
    }
    
    /**
     * @return The number of modification operations performed on the graph.
     */
    @Override
    public int getModCounter() {
        return this.modificationCounter;
    }
    
    /**
     * @return An iterator over the nodes in the graph.
     */
    @Override
    public Iterator<AbstractNode> getNodes() {
        return new Iterator<AbstractNode>() {
            private int modCount = DirectedWeightedGraph.this.modificationCounter;
            private final Iterator<AbstractNode> iterator = DirectedWeightedGraph.this.nodes.values().iterator();
            private AbstractNode next = null;
            
            @Override
            public boolean hasNext() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                return this.iterator.hasNext();
            }
            
            @Override
            public AbstractNode next() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                return this.next = this.iterator.next();
            }
            
            @Override
            public void remove() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                if (this.next == null) {
                    throw new IllegalStateException();
                }
                DirectedWeightedGraph.this.removeNode(this.next.getID());
                this.modCount = DirectedWeightedGraph.this.modificationCounter;
            }
            
            @Override
            public void forEachRemaining(Consumer<? super AbstractNode> action) {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                Iterator.super.forEachRemaining(action);
            }
        };
    }
    
    /**
     * @return An iterator over the edges in the graph.
     */
    @Override
    public Iterator<AbstractEdge> getEdges() {
        return new Iterator<AbstractEdge>() {
            private int modCount = DirectedWeightedGraph.this.modificationCounter;
            private final Iterator<HashMap<Integer, AbstractEdge>> outerIter = DirectedWeightedGraph.this.outEdges.values().iterator();
            private Iterator<AbstractEdge> innerIter = null;
            private AbstractEdge next = null;
            
            @Override
            public boolean hasNext() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                if (this.innerIter == null) return this.outerIter.hasNext();
                return this.outerIter.hasNext() || this.innerIter.hasNext();
            }
            
            @Override
            public AbstractEdge next() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                if (this.innerIter == null || !this.innerIter.hasNext()) {
                    this.innerIter = this.outerIter.next().values().iterator();
                }
                return this.next = this.innerIter.next();
            }
            
            @Override
            public void remove() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                if (this.next == null) {
                    throw new IllegalStateException();
                }
                DirectedWeightedGraph.this.removeEdge(this.next.getSource(), this.next.getDestination());
                this.modCount = DirectedWeightedGraph.this.modificationCounter;
            }
            
            @Override
            public void forEachRemaining(Consumer<? super AbstractEdge> action) {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                Iterator.super.forEachRemaining(action);
            }
        };
    }
    
    /**
     * @param node The id of the node
     * @return The in-degree of the node, or 0 if the node does not exist. (The in-degree of a node is the number of edges that have the node as their destination.)
     */
    public int inDegree(int node) {
        return this.inEdges.getOrDefault(node, new HashSet<>()).size();
    }
    
    /**
     * @param node The id of the node
     * @return The out-degree of the node, or 0 if the node does not exist. (The out-degree of a node is the number of edges that have the node as their source.)
     */
    public int outDegree(int node) {
        return this.outEdges.getOrDefault(node, new HashMap<>()).size();
    }
    
    
    /**
     * @return The number of nodes in the graph.
     */
    @Override
    public int getNodeCount() {
        return this.nodes.size();
    }
    
    /**
     * @return The number of edges in the graph.
     */
    @Override
    public int getEdgeCount() {
        return this.edgeCounter;
    }
    
    @Override
    public String toString() {
        return this.toJSON().toString(4);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DirectedWeightedGraph)) return false;
        DirectedWeightedGraph that = (DirectedWeightedGraph) o;
        return this.nodes.equals(that.nodes) && this.outEdges.equals(that.outEdges) && this.inEdges.equals(that.inEdges);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.nodes, this.outEdges, this.inEdges);
    }
}
