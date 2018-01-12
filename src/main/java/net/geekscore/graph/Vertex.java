package net.geekscore.graph;

/**
 * Created by ravirajmulasa on 9/7/16.
 */
public class Vertex<T extends Comparable> {

    private T value;

    public Vertex(T value) {
        this.value = value;
    }

    public final T getValue(){
        return this.value;
    }

    public void setValue(final T value) {
        this.value = value;
    }
}
