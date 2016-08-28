package edu.learn.me.list;

/**
 * Created by ravirajmulasa on 8/24/16.
 */
public interface IList<T> {

    boolean isEmpty();
    int size();
    void clear();

    void print();

    int find(T value);

    T get( int position );
    boolean set( int position, T newVal );

    boolean add( int position, T value );
    boolean add( T value);
    T delete(int position);

}
