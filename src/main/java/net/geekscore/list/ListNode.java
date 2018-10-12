package net.geekscore.list;

import java.util.StringJoiner;

/**
 * Created by ravirajmulasa on 8/23/16.
 */
public class ListNode<T extends Comparable> {

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
        final StringJoiner sj = new StringJoiner(", ", "{", "}");
        return sj.add("data:" + this.data).add("prev:" + this.prev).add("next:" + this.next).toString();
    }
}
