package net.geekscore.graph;

public class Edge<T extends Comparable, E extends Number> {

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

}
