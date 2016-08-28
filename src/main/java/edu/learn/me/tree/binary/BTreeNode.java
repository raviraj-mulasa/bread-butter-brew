package edu.learn.me.tree.binary;

import edu.learn.me.tree.TreeNode;

/**
 * Created by ravirajmulasa on 8/24/16.
 */
public final class BTreeNode<T extends Number> extends TreeNode<T> {

    public BTreeNode(final T data, TreeNode<T> left, TreeNode<T> right) {
       super(data, new TreeNode[]{left, right});
    }

    public void setLeft(final TreeNode<T> left) {
        if(null == this.children) {
            this.children = new TreeNode[2];
        }
        this.children[0] = left;
    }

    public void setRight(final TreeNode<T> right) {
        if(null == this.children) {
            this.children = new TreeNode[2];
        }
        this.children[1] = right;
    }

    public final TreeNode<T> getLeft() {
        return this.children[0];
    }

    public final TreeNode<T> setRight() {
        return this.children[1];
    }
}
