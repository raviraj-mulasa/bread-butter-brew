package net.geekscore.tree;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ravirajmulasa on 8/24/16.
 */
public final class TreeNode<T> {

    public T data;

    public TreeNode<T> parent;

    public List<TreeNode<T>> children = Collections.emptyList();

    public TreeNode(final T data, List<TreeNode<T>> children) {
        this.parent     = null;
        this.data       = data;
        this.addChildren(children);
    }

    public TreeNode(final T data) {
        this(data, Collections.emptyList());
    }

    public void addChild(TreeNode<T> child) {
        this.addChildren(Collections.singletonList(child));
    }

    public void addChildren(List<TreeNode<T>> children) {
       if(null != children && !children.isEmpty()) {
           for (TreeNode child : children)  child.parent = this;
           if(this.children == null || this.children.isEmpty()) this.children = new LinkedList<>();
           this.children.addAll(children);
       }
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
