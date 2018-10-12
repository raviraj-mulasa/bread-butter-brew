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
        return toStringHelper(this);


    }

    private final String toStringHelper(TreeNode<T> node){
        if(node == null) return "";
        final StringBuilder nodeStringBuilder = new StringBuilder();
        nodeStringBuilder.append("{data:").append(node.data);
        if(null != node.children && !node.children.isEmpty()) {
            nodeStringBuilder.append("[children:");
            final StringBuilder childrenStrBuilder = new StringBuilder();
            for (final TreeNode<T> child: node.children) {
                childrenStrBuilder.append(toStringHelper(child));
                childrenStrBuilder.append(",");
            }
            childrenStrBuilder.deleteCharAt(childrenStrBuilder.length()-1);
            nodeStringBuilder.append(childrenStrBuilder.toString());
            nodeStringBuilder.append("]");
        }
        nodeStringBuilder.append("}");
        return nodeStringBuilder.toString();


    }
}
