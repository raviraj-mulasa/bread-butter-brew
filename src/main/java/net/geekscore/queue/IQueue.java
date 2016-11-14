package net.geekscore.queue;

/**
 * Created by ravirajmulasa on 8/27/16.
 */
public interface IQueue<T> {

    void enqueue(T item);
    T dequeue();
    T peek();

    boolean isEmpty();
    int size();
    int capacity();
    void clear();

    void print();
}

