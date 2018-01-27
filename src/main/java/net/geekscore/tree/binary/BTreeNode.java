package net.geekscore.tree.binary;
/**
 * Created by ravirajmulasa on 8/24/16.
 */
public final class BTreeNode<T> {

    public T data;

    public BTreeNode<T> left;

    public BTreeNode<T> right;

    public BTreeNode<T> parent;

    public BTreeNode(final T data, BTreeNode<T> left, BTreeNode<T> right) {
        this.parent = null;
        this.data = data;
        this.left = left;
        this.right = right;
        if(null != this.left) this.left.parent = this;
        if(null != this.right) this.right.parent = this;
    }

    public BTreeNode(final T data) {
        this(data, null, null);
    }

    public final boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}
