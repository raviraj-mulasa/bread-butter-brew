package net.geekscore.graph;

import java.util.List;
import java.util.Set;

public interface IGraph<T extends Comparable> {

    public boolean adjacent(T x, T y); // tests whether there is an edge from the vertex x to the vertex y;
    public List<T> neighbors(T v); // lists all vertices y such that there is an edge from the vertex x to the vertex y;

    public Vertex<T> addVertex(T v); // adds the vertex x, if it is not there;
    public Vertex<T> removeVertex(T v); // removes the vertex x, if it is there;


    public void addEdge(T x, T y, Number weight); // adds the edge from the vertex x to the vertex y, if it is not there;
    public void removeEdge(T x, T y); // removes the edge from the vertex x to the vertex y, if it is there;

    public void setVertexValue(T currValue, T newValue); // removes the edge from the vertex x to the vertex y, if it is there;

    public Number getEdgeWeight(T x, T y); // returns the value associated with the edge (x, y);
    public void setEdgeWeight(T x, T y, Number newValue); // sets the value associated with the edge (x, y) to v.

    public Set<T> vertices();

}
