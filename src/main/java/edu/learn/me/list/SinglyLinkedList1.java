package edu.learn.me.list;

import com.google.common.base.Objects;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ravirajmulasa on 8/24/16.
 */
public final class SinglyLinkedList1<T  extends Number> implements IList<T> {

    private AtomicInteger count  = new AtomicInteger(0);

    private ListNode<T> head = null;

    public SinglyLinkedList1() {
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
        for(;i<this.size() && curr != null;) {
            if(Objects.equal(value, curr.getData())){
                break;
            }
            curr = curr.getNext();
            i+=1;
        }
        return (null == curr ? -1 : i);
    }

    public T get(int position) {
        if(position < 0){
            return null;
        }
        ListNode<T> curr   = this.head;
        for(int i=0; i<position && curr != null; curr = curr.getNext(), i+=1);
        return (null == curr ? null : curr.getData());
    }

    public boolean set(int position, T newVal) {
        if(position < 0){
            return false;
        }
        ListNode<T> curr   = this.head;
        for(int i=0; i<position && curr != null; curr = curr.getNext(), i+=1);
        if(null != curr) {
            final ListNode<T> next   = curr.getNext();
            final ListNode<T> newEle = new ListNode<T>(newVal , null, next);
            curr.setNext(newEle);
            return true;
        }
        return false;
    }

    public boolean add(int position, T value) {
        return this.set(position, value);
    }

    public boolean add(T value) {
        return this.add(this.size(), value);
    }

    public T delete(int position) {
        if(position < 0){
            return null;
        }
        ListNode<T> curr   = this.head;
        ListNode<T> prev   = this.head;
        for(int i=0; i<position && curr != null; prev = curr, curr = curr.getNext(), i+=1);
        if(null != curr) {
            final ListNode<T> next = curr.getNext();
            prev.setNext(next);
            return curr.getData();
        }
        return null;
    }

}
