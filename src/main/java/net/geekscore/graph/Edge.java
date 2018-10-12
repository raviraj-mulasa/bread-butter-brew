package net.geekscore.graph;

import java.io.Serializable;

public class Edge<T extends Comparable, E extends Comparable & Serializable> {

    private final Vertex<T> from;

    private final Vertex<T> to;

    private E weight;

    public Edge(Vertex<T> from, Vertex<T> to, E weight) {
        this.from   = from;
        this.to     = to;
        this.weight = weight;
    }

    public Edge(Vertex<T> from, Vertex<T> to) {
        this(from, to, null);
    }

    public Vertex<T> from() {
        return this.from;
    }

    public Vertex<T> to() {
        return this.to;
    }

    public final E getWeight() {
        return this.weight;
    }

    public void setWeight(final E weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        if(this.weight == null) {
            return String.format("{'from':%s , 'to':%s, 'weight':0)}" ,this.from, this.to);
        }
        return String.format("{'from':%s , 'to':%s, 'weight':%s)}" ,this.from, this.to, this.weight);
    }

}
