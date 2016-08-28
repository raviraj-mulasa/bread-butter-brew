package edu.learn.me.tree;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Arrays;

/**
 * Created by ravirajmulasa on 8/24/16.
 */
public class TreeNode<T> {

    protected T data;

    protected TreeNode<T>[] children = null;

    public TreeNode(final T data, TreeNode<T>... children) {
        this.data       = data;
        this.children   = children;
    }


    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public final TreeNode<T>[] getChildren() {
        return this.children;
    }

    public void setChildren(TreeNode<T>... children) {
        this.children = children;
    }

    public final boolean isLeaf() {
        if(null == this.children){
            return true;
        }
        int i = 0;
        int childrenCount = this.children.length;
        for (; i < childrenCount; i++) {
            if(this.children[i] != null) {
                return false;
            }
        }
        return (i == childrenCount);
    }

    @Override
    public final String toString(){
        String childrenStr = "";
        if(this.children != null) {
            childrenStr = Joiner.on(',').skipNulls().join(this.children);
        }
        return MoreObjects.toStringHelper(this)
                .add("data", this.data)
                .add("children", childrenStr)
                .toString();
    }
}
