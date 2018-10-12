package net.geekscore.graph;

import java.util.*;

public class Graph<T extends Comparable> implements IGraph<T> {

    private final Map<Vertex, List<Edge>> adjList;

    private final Map<T, Vertex> vertexMap;

    private final boolean directed;

    public Graph(){
        this(false);
    }

    public Graph(final boolean directed){
        this.adjList   = new HashMap<>();
        this.vertexMap = new HashMap<>();
        this.directed  = directed;
    }


    @Override
    public boolean adjacent(T x, T y) {
        final Optional<Edge> edge = this.findEdge(x, y);
        return edge.isPresent();
    }

    @Override
    public List<T> neighbors(T v) {
        final Vertex<T> vertex = this.vertexMap.get(v);
        if(null != vertex){
            final List<Edge> edges = this.adjList.get(vertex);
            if(null != edges){
                final List<T> neighbors = new ArrayList<>(edges.size());
                for (final Edge edge: edges){
                    final Vertex<T> to = edge.to();
                    neighbors.add(to.getValue());
                }
                return neighbors;
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Vertex<T> addVertex(T v) {
        Vertex<T> vertex = this.vertexMap.get(v);
        if(null != vertex){
            throw new IllegalArgumentException(String.format("Vertex:%s ALREADY present", v));
        }
        vertex = new Vertex<>(v);
        this.vertexMap.put(v, vertex);
        this.adjList.put(vertex, new LinkedList<>());
        return vertex;
    }

    @Override
    public Vertex<T> removeVertex(T v) {
        final Vertex<T> vertex = this.vertexMap.get(v);
        if(null != vertex){
            final Collection<Edge> edges = this.adjList.remove(vertex);
            if(!this.directed && edges != null) {
                for (final Edge edge: edges){
                    final Vertex<T> neighbor = edge.to();
                    this.removeEdge(neighbor, vertex);
                }
            }
            return this.vertexMap.remove(v);
        }
        throw new IllegalArgumentException(String.format("Vertex:%s NOT present", v));
    }

    @Override
    public void setVertexValue(T currValue, T newValue) {
        Vertex<T> vertex = this.vertexMap.get(currValue);
        if(null != vertex)vertex.setValue(newValue);
        else throw new IllegalArgumentException(String.format("Vertex:%s NOT present", currValue));
    }

    @Override
    public void addEdge(T x, T y, Comparable weight) {
        Vertex<T> vertex = this.vertexMap.get(x);
        if(null == vertex) {
            vertex = this.addVertex(x);
        }
        Vertex<T> neighbor = this.vertexMap.get(y);
        if(null == neighbor) {
            neighbor = this.addVertex(y);
        }
        List<Edge> edges = this.adjList.get(vertex);
        if(edges == null || edges == Collections.EMPTY_LIST) {
            edges = new LinkedList<>();
        }
        edges.add(new Edge(vertex, neighbor, weight));
        this.adjList.put(vertex, edges);
        if(!this.directed) {
            edges = this.adjList.get(neighbor);
            if(edges == null || edges == Collections.EMPTY_LIST) {
                edges = new LinkedList<>();
            }
            edges.add(new Edge(neighbor, vertex, weight));
            this.adjList.put(neighbor, edges);
        }
    }

    @Override
    public void removeEdge(T x, T y) {
        this.removeEdge(this.vertexMap.get(x), this.vertexMap.get(y));

    }

    @Override
    public Comparable getEdgeWeight(T x, T y) {
        final Optional<Edge> edge = this.findEdge(x, y);
        if(edge.isPresent()){
            return edge.get().getWeight();
        }
        throw new IllegalArgumentException(String.format("Edge from %s --> %s NOT present", x, y));
    }

    @Override
    public void setEdgeWeight(T x, T y, Comparable newValue) {
        Optional<Edge> edge = this.findEdge(x, y);
        if(edge.isPresent())edge.get().setWeight(newValue);
        else throw new IllegalArgumentException(String.format("Edge from %s --> %s NOT present", x, y));
        if(!this.directed) {
            edge = this.findEdge(y, x);
            if(edge.isPresent())edge.get().setWeight(newValue);
            else throw new IllegalArgumentException(String.format("Edge from %s --> %s NOT present", x, y));
        }
    }

    @Override
    public Set<T> vertices() {
        return this.vertexMap.keySet();
    }

    @Override
    public List<Edge> edges() {
        final List<Edge> edges = new LinkedList<>();
        for(Collection<Edge> vertexEdges: adjList.values()) edges.addAll(vertexEdges);
        return edges;
    }

    private Optional<Edge> findEdge(T x, T y) {
        final Vertex<T> vertex = this.vertexMap.get(x);
        if(vertex != null){
            final List<Edge> edges = this.adjList.get(vertex);
            if(null != edges) {
                for (final Edge edge: edges){
                    final Vertex<T> neighbor = edge.to();
                    if(neighbor.getValue().compareTo(y) == 0){
                        return Optional.of(edge);
                    }
                }
            }
        }
        return Optional.empty();
    }

    private void removeEdge(Vertex<T> from, Vertex<T> to) {
        if(from != null){
            final List<Edge> edges = this.adjList.get(from);
            final Iterator<Edge> neighborsIterator = edges.listIterator();
            Edge edge = null;
            while (neighborsIterator.hasNext()){
                edge = neighborsIterator.next();
                if(edge.to().getValue().compareTo(to.getValue()) == 0){
                    neighborsIterator.remove();
                }
            }
        }
        throw new IllegalArgumentException(String.format("Edge from %s --> %s NOT present", from.getValue(), to.getValue()));
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        final Collection<Vertex> vertices = this.adjList.keySet();
        for (final Vertex vertex : vertices) {
            stringBuilder.append(vertex.getValue()).append(" --> ");
            final List<Edge> edges = this.adjList.get(vertex);
            if(null != edges) {
                for (Edge edge: edges) {
                    stringBuilder.append(edge.to().getValue()).append("(").append(edge.getWeight()).append(")");
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString().trim();
    }
}
