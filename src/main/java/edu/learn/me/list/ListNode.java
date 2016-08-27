package edu.learn.me.list;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by ravirajmulasa on 8/23/16.
 */
public class ListNode<T> {

    private T data;

    private ListNode<T> next;

    private ListNode<T> prev;

    public ListNode(final T data, final ListNode<T> prev, final ListNode<T> next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public ListNode(final T data) {
        this(data, null, null);
    }

    public final T getData() {
        return this.data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public ListNode<T> getNext() {
        return this.next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public ListNode<T> getPrev() {
        return this.prev;
    }

    public void setPrev(ListNode<T> prev) {
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
}
