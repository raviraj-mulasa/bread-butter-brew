package edu.learn.me.tree.binary;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by ravirajmulasa on 8/24/16.
 */
public final class BTreeNode {

    private Integer data;

    private BTreeNode left;

    private BTreeNode right;

    public BTreeNode(final Integer data, final BTreeNode left, final BTreeNode right) {
        this.data = data;
        this.left = left;
        this.right= right;
    }

    public BTreeNode(final Integer data) {
        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public final BTreeNode getLeft() {
        return this.left;
    }

    public void setLeft(BTreeNode left) {
        this.left = left;
    }

    public final BTreeNode getRight() {
        return this.right;
    }

    public void setRight(BTreeNode right) {
        this.right = right;
    }

    @Override
    public final String toString(){
        return MoreObjects.toStringHelper(this)
                .add("data", this.data)
                .add("left", this.left)
                .add("right",this.right)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final BTreeNode other = (BTreeNode) obj;
        return (Integer.compare(this.data, other.data) == 0) &&
                Objects.equal(this.left, other.left) &&
                Objects.equal(this.right, other.right);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.data, this.left, this.right);
    }
}
