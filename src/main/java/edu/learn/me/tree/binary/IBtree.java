package edu.learn.me.tree.binary;

/**
 * Created by ravirajmulasa on 8/30/16.
 */
public interface IBTree<T> {
    void insert(T item);
    BTreeNode<T> delete(T item);
    BTreeNode<T> find(T item);


    String preOrder(BTreeNode<T> node);
    String postOrder(BTreeNode<T> node);
    String inOrder(BTreeNode<T> node);

    int height(BTreeNode<T> node);

}
