package edu.learn.me.tree.binary;

/**
 * Created by ravirajmulasa on 9/3/16.
 */
public interface IBTree<T> {

    boolean insert(T item);
    BTreeNode<T> delete(T item);
    BTreeNode<T> find(T item);

    BTreeNode<T> root();
}
