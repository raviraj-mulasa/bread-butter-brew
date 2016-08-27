package edu.learn.me.stack;

/**
 * Created by ravirajmulasa on 8/25/16.
 */
public interface IStack<T> {

    void push(T item);
    T pop();
    T peek();
    boolean isEmpty();
    int size();
    void clear();
}
