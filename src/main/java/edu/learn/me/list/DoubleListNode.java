package edu.learn.me.list;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by ravirajmulasa on 8/23/16.
 */
public class DoubleListNode {

    private Integer data;

    private DoubleListNode next;

    private DoubleListNode prev;

    public DoubleListNode(final Integer data, final DoubleListNode prev, final DoubleListNode next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public DoubleListNode(final Integer data) {
        this(data, null, null);
    }

    public DoubleListNode() {
        this(Integer.MIN_VALUE, null, null);
    }


    public final Integer getData() {
        return this.data;
    }

    public void setData(final Integer data) {
        this.data = data;
    }

    public DoubleListNode getNext() {
        return next;
    }

    public void setNext(DoubleListNode next) {
        this.next = next;
    }

    public DoubleListNode getPrev() {
        return prev;
    }

    public void setPrev(DoubleListNode prev) {
        this.prev = prev;
    }

    @Override
    public final String toString(){
        return MoreObjects.toStringHelper(this)
                .add("prev", prev)
                .add("data", data)
                .add("next", next)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final DoubleListNode other = (DoubleListNode) obj;
        return (Integer.compare(this.data, other.data) == 0)
                && Objects.equal(this.next, other.next)
                && Objects.equal(this.prev, other.prev);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.prev, this.data, this.next);
    }

}
