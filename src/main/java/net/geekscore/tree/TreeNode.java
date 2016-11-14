package net.geekscore.tree;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ravirajmulasa on 8/24/16.
 */
public class TreeNode<T> {

    protected T data;

    private TreeNode<T> parent;

    protected List<TreeNode<T>> children = Collections.emptyList();

    public TreeNode(final T data, List<TreeNode<T>> children) {
        this.parent     = null;
        this.data       = data;
        this.children   = children;
    }

    public TreeNode(final T data) {
        this(data, Collections.EMPTY_LIST);
    }


    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getParent() {
        return this.parent;
    }

    public void setParent(final TreeNode<T> parent) {
        this.parent = parent;
    }

    public final List<TreeNode<T>> getChildren() {
        return this.children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public void addChild(TreeNode<T> child) {
        if(this.children.isEmpty()) {
            this.children = new LinkedList<TreeNode<T>>();
        }
        this.children.add(child);
    }

    public final boolean isLeaf() {
        return this.children.isEmpty();
    }

    public final int numberOfChildren() {
        return this.children.size();
    }

    @Override
    public final String toString(){
        final StringJoiner sj = new StringJoiner(", ", "{", "}");
        String childrenStr = "";
        if(this.children != null) {
            childrenStr = this.children.stream().map(i -> i.toString()).collect(Collectors.joining(", "));
        }
        return sj.add("data:" + this.data).add("parent:" + this.parent).add("children:" + childrenStr).toString();
    }
}
