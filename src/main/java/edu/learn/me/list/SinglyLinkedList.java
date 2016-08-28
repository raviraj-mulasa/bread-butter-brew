package edu.learn.me.list;

import com.google.common.base.Objects;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ravirajmulasa on 8/24/16.
 */
public final class SinglyLinkedList<T  extends Number> implements IList<T> {

    private AtomicInteger count  = new AtomicInteger(0);

    private ListNode<T> head = null;

    public SinglyLinkedList() {
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
        for(ListNode<T> curr = this.head; curr!=null;){
            final ListNode<T> next = curr.getNext();
            curr.setNext(null);
            curr = next;
        }
    }


    public int find(T value) {
        ListNode<T> curr   = this.head;
        int i=0;
        for(;i<this.size() && curr != null; curr = curr.getNext(), i+=1) {
            if(Objects.equal(value, curr.getData())){
                break;
            }
        }
        return (null == curr ? -1 : i);
    }

    public T get(int position) {
        if(position < 0 || position > this.size()){
            return null;
        }
        ListNode<T> curr   = this.head;
        for(int i=0; i<position && curr != null; curr = curr.getNext(), i+=1);
        return (null == curr ? null : curr.getData());
    }


    public boolean set(int position, T newVal) {
        if(position < 0 || position > this.size()){
            return false;
        }
        if(position == 0) {
            final ListNode<T> newEle = new ListNode<T>(newVal , null, null);
            this.head = newEle;
            this.count.incrementAndGet();
            return true;
        }
        ListNode<T> curr   = this.head;
        ListNode<T> prev   = curr;

        // Go to the position
        for(int i=0; i<position && curr!= null; prev = curr, curr = curr.getNext(), i+=1);

        final ListNode<T> newEle = new ListNode<T>(newVal , null, null);
        prev.setNext(newEle);
        this.count.incrementAndGet();
        return true;

    }

    public boolean add(int position, T value) {
        return this.set(position, value);
    }

    public boolean add(T value) {
        return this.add(this.size(), value);
    }

    public T delete(int position) {
        if(position < 0 || position > this.size()){
            return null;
        }
        if(position == 0 && this.head != null) {
            T headValue = this.head.getData();
            this.head   = this.head.getNext();
            this.count.decrementAndGet();
            return headValue;
        }
        if(this.head != null) {

            ListNode<T> curr   = this.head;
            ListNode<T> prev   = this.head;

            // Go to the position
            for(int i=0; i<position && curr != null; prev = curr, curr = curr.getNext(), i+=1);

            T value2Return = prev.getNext().getData();
            prev.setNext(curr.getNext());
            this.count.decrementAndGet();
            return value2Return;

        }
        return null;
    }


    public void print() {
        ListNode<T> curr = this.head;
        while (curr != null) {
            System.out.print(String.format("%d-->", curr.getData()));
            final ListNode<T> next = curr.getNext();
            if(next == this.head) {
                System.out.print(String.format("%d", next.getData()));
                break;
            }
            curr = next;
        }
    }

}
