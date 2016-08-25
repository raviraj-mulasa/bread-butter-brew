package edu.learn.me.list;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;


/**
 * Created by ravirajmulasa on 8/23/16.
 */
public class SingleListNode {

    private Integer data;

    private SingleListNode next;

    public SingleListNode(final Integer data, final SingleListNode next) {
        this.data = data;
        this.next = next;
    }

    public SingleListNode(final Integer data) {
        this(data, null);
    }

    public SingleListNode() {
        this(Integer.MIN_VALUE, null);
    }

    public Integer getData() {
        return data;
    }

    public SingleListNode getNext() {
        return next;
    }

    public void setData(final Integer data) {
        this.data = data;
    }

    public void setNext(final SingleListNode next) {
        this.next = next;
    }

    @Override
    public final String toString(){
        return MoreObjects.toStringHelper(this)
                .add("data", data)
                .add("next", next)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final SingleListNode other = (SingleListNode) obj;
        return (Integer.compare(this.data, other.data) == 0) && Objects.equal(this.next, other.next);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.data, this.next);
    }

}
