package edu.learn.me.tree.binary;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ravirajmulasa on 8/24/16.
 */
public final class BTreeNode<T> {

    private T data;

    private BTreeNode<T> parent;

    private List<BTreeNode<T>> children = Collections.emptyList();

    public BTreeNode(final T data, BTreeNode<T> left, BTreeNode<T> right) {
        this.parent = null;
        this.data   = data;
        if(null != left || null != right) {
            this.children = new ArrayList<BTreeNode<T>>(2);
            this.children.add(0, left);
            this.children.add(0, right);
        }
    }

    public BTreeNode(final T data) {
        this(data, null, null);
    }

    public T getData() {
        return this.data;
    }

    public BTreeNode<T> getParent() {
        return this.parent;
    }

    public void setParent(final BTreeNode<T> parent) {
        this.parent = parent;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(final BTreeNode<T> left) {
        if(this.children.isEmpty()) {
            this.children = new ArrayList<BTreeNode<T>>(2);
        }
        left.setParent(this);
        this.children.add(0, left);
    }

    public void setRight(final BTreeNode<T> right) {
        if(this.children.isEmpty()) {
            this.children = new ArrayList<BTreeNode<T>>(2);
        }
        right.setParent(this);
        this.children.add(1, right);
    }

    public final BTreeNode<T> getLeft() {
        return this.children.get(0);
    }

    public final BTreeNode<T> setRight() {
        return this.children.get(1);
    }

    public final boolean isLeaf() {
        return this.children.isEmpty();
    }

    @Override
    public final String toString() {
        final StringJoiner sj = new StringJoiner(", ", "{", "}");
        String childrenStr = "";
        if(this.children != null) {
            childrenStr = this.children.stream().map(i -> i.toString()).collect(Collectors.joining(", "));
        }
        return sj.add("data:"+this.data).add("parent:"+this.parent).add("children:"+childrenStr).toString();
    }
}
