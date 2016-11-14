package net.geekscore.stack;

/**
 * Created by ravirajmulasa on 8/25/16.
 */
public interface IStack<T> {

    void push(T item);
    T pop();
    T peek();

    boolean isEmpty();
    int size();
    int capacity();
    void clear();

    void print();
}
