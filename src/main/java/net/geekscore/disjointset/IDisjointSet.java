package net.geekscore.disjointset;

public interface IDisjointSet<T> {

    void makeSet(T value);
    void union(T value1, T value2);
    T findSet(T value);
}
