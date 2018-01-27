package net.geekscore.tree.binary;

public class BTreeImpl<T extends Comparable<T>> implements IBTree<T> {

    private final BTreeNode<T> root;

    public BTreeImpl(final BTreeNode<T> root){ this.root = root; }

    @Override
    public boolean insert(T item) {
        return false;
    }

    @Override
    public BTreeNode<T> delete(T item) {
        return null;
    }

    @Override
    public BTreeNode<T> find(T item) {
        return null;
    }

    @Override
    public BTreeNode<T> root() {
        return this.root;
    }
}
