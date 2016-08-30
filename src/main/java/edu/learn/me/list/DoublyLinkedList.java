package edu.learn.me.list;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ravirajmulasa on 8/24/16.
 */
public final class DoublyLinkedList<T> implements IList<T> {

    private AtomicInteger count  = new AtomicInteger(0);

    private ListNode<T> head = null;

    public DoublyLinkedList() {
        this.head = null;
        this.count.set(0);
    }

    public final ListNode<T> getHead() {
        return this.head;
    }


    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.count.get();
    }

    public void clear() {

    }

    public void print() {

    }

    public int find(T value) {
        return 0;
    }

    public T get(int position) {
        return null;
    }

    public boolean set(int position, T newVal) {
        return false;
    }

    public boolean add(int position, T value) {
        return false;
    }

    public boolean add(T value) {
        return false;
    }

    public T delete(int position) {
        return null;
    }

}
