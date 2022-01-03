package datastructures;

import api.AbstractDirectedWeightedGraph;
import api.AbstractEdge;
import api.AbstractNode;

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
     * @return The number of modification operations performed on the graph.
     */
    @Override
    public int getModCounter() {
        return this.modificationCounter;
    }
    
    /**
     * @param node The node to check.
     * @return If the node is in the graph.
     */
    @Override
    public boolean hasNode(AbstractNode node) {
        return this.nodes.containsKey(node.getID());
    }
    
    /**
     * @param edge The edge to check.
     * @return If the edge is in the graph.
     */
    @Override
    public boolean hasEdge(AbstractEdge edge) {
        return this.outEdges.containsKey(edge.getSource()) && this.outEdges.get(edge.getSource()).containsKey(edge.getDestination());
    }
    
    /**
     * @param node The node to get.
     * @return The node instance. Null if the node does not exist in the graph.
     */
    @Override
    public AbstractNode getNode(AbstractNode node) {
        return this.nodes.get(node.getID());
    }
    
    /**
     * @param edge The edge to get.
     * @return The edge instance. Null if the edge does not exist in the graph.
     */
    @Override
    public AbstractEdge getEdge(AbstractEdge edge) {
        if (this.hasEdge(edge)) {
            return this.outEdges.get(edge.getSource()).get(edge.getDestination());
        }
        return null;
    }
    
    /**
     * @param node The node to add.
     * @return True if the node was added, false otherwise (If the node already exists).
     */
    @Override
    public boolean addNode(AbstractNode node) {
        if (this.hasNode(node)) {
            return false;
        }
        this.nodes.put(node.getID(), node);
        this.modificationCounter++;
        return true;
    }
    
    /**
     * @param edge The edge to add.
     * @return True if the edge was added, false otherwise (If the edge already exists).
     */
    @Override
    public boolean addEdge(AbstractEdge edge) {
        int source = edge.getSource();
        int destination = edge.getDestination();
        if (this.hasNode(source) && this.hasNode(destination)) {
            if (this.hasEdge(edge)) {
                return false;
            }
            
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
     * @param node The node to remove.
     * @return True if the node was removed, false otherwise (If the node does not exist).
     */
    @Override
    public boolean removeNode(AbstractNode node) {
        if (!this.hasNode(node)) {
            return false;
        }
        int ID = node.getID();
        AbstractNode removed = this.nodes.remove(ID);
        HashSet<Integer> inEdges = this.inEdges.getOrDefault(ID, new HashSet<>());
        for (int inEdge : inEdges) {
            this.outEdges.get(inEdge).remove(ID);
            if (this.outEdges.get(inEdge).isEmpty()) {
                this.outEdges.remove(inEdge);
            }
        }
        this.edgeCounter -= inEdges.size();
        this.inEdges.remove(ID);
        
        HashMap<Integer, AbstractEdge> outEdges = this.outEdges.getOrDefault(ID, new HashMap<>());
        for (AbstractEdge outEdge : outEdges.values()) {
            this.inEdges.get(outEdge.getDestination()).remove(ID);
            if (this.inEdges.get(outEdge.getDestination()).isEmpty()) {
                this.inEdges.remove(outEdge.getDestination());
            }
        }
        this.edgeCounter -= outEdges.size();
        this.outEdges.remove(ID);
        
        this.modificationCounter++;
        return true;
    }
    
    
    /**
     * @param edge The edge to remove.
     * @return True if the edge was removed, false otherwise (If the edge does not exist).
     */
    @Override
    public boolean removeEdge(AbstractEdge edge) {
        if (!this.hasEdge(edge)) {
            return false;
        }
        int source = edge.getSource();
        int destination = edge.getDestination();
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
     * @param node The node.
     * @return An iterator over the neighbors of the node, or null if the node does not exist.
     */
    @Override
    public Iterator<AbstractNode> getAdjacentNodes(AbstractNode node) {
        if (!this.hasNode(node)) {
            return null;
        }
        return new Iterator<AbstractNode>() {
            private int modCount = DirectedWeightedGraph.this.modificationCounter;
            private final Iterator<AbstractEdge> iterator = DirectedWeightedGraph.this.outEdges.getOrDefault(node.getID(), new HashMap<>()).values().iterator();
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
     * @param node The node.
     * @return An iterator over the in-edges of the node, or null if the node does not exist.
     */
    @Override
    public Iterator<AbstractEdge> getInEdges(AbstractNode node) {
        if (!this.hasNode(node)) {
            return null;
        }
        final int ID = node.getID();
        return new Iterator<AbstractEdge>() {
            private int modCount = DirectedWeightedGraph.this.modificationCounter;
            private final Iterator<Integer> iterator = DirectedWeightedGraph.this.inEdges.getOrDefault(ID, new HashSet<>()).iterator();
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
                return DirectedWeightedGraph.this.outEdges.get(this.next).get(ID);
            }
            
            @Override
            public void remove() {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                if (this.next == null) {
                    throw new IllegalStateException();
                }
                DirectedWeightedGraph.this.removeEdge(this.next, ID);
                this.modCount = DirectedWeightedGraph.this.modificationCounter;
            }
            
            @Override
            public void forEachRemaining(Consumer<? super AbstractEdge> action) {
                if (this.modCount != DirectedWeightedGraph.this.modificationCounter) {
                    throw new ConcurrentModificationException();
                }
                this.iterator.forEachRemaining((e) -> action.accept(DirectedWeightedGraph.this.outEdges.get(e).get(ID)));
            }
        };
    }
    
    /**
     * @param node The node.
     * @return An iterator over the out-edges of the node, or null if the node does not exist.
     */
    @Override
    public Iterator<AbstractEdge> getOutEdges(AbstractNode node) {
        if (!this.hasNode(node)) {
            return null;
        }
        final int ID = node.getID();
        return new Iterator<AbstractEdge>() {
            private int modCount = DirectedWeightedGraph.this.modificationCounter;
            private final Iterator<AbstractEdge> iterator = DirectedWeightedGraph.this.outEdges.getOrDefault(ID, new HashMap<>()).values().iterator();
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
                DirectedWeightedGraph.this.removeEdge(ID, this.next.getDestination());
                this.modCount = DirectedWeightedGraph.this.modificationCounter;
            }
        };
    }
    
    /**
     * @param node The node.
     * @return The in-degree of the node, or 0 if the node does not exist. (The in-degree of a node is the number of edges that have the node as their destination.)
     */
    @Override
    public int inDegree(AbstractNode node) {
        return this.inEdges.getOrDefault(node.getID(), new HashSet<>()).size();
    }
    
    /**
     * @param node The node.
     * @return The out-degree of the node, or 0 if the node does not exist. (The out-degree of a node is the number of edges that have the node as their source.)
     */
    @Override
    public int outDegree(AbstractNode node) {
        return this.outEdges.getOrDefault(node.getID(), new HashMap<>()).size();
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
        if (!(o instanceof DirectedWeightedGraph that)) return false;
        return this.nodes.equals(that.nodes) && this.outEdges.equals(that.outEdges) && this.inEdges.equals(that.inEdges);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.nodes, this.outEdges, this.inEdges);
    }
}
