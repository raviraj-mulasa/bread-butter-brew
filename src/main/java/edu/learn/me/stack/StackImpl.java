package edu.learn.me.stack;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ravirajmulasa on 8/26/16.
 */
public final class StackImpl<T extends Comparable> implements IStack<T> {

    private T items[] = (T[]) new Comparable[0];

//    Initialize capacity to 256
    private int capacity  = 256;

//    Initialize top to -1
    private int top = -1;

    private final AtomicInteger count = new AtomicInteger(0);

    public StackImpl(final int capacity) {
        this.capacity   = capacity;
        this.items = (T[]) new Comparable[this.capacity];
    }

    public StackImpl() {
       this(256);
    }

    public void push(T item) {
        if(null == this.items) {
            this.items = (T[]) new Comparable[this.capacity];
        }
        if(this.size() * 1.25 > this.capacity) {
            this.resize(2.0);
        }
        this.count.incrementAndGet();
        this.items[++this.top] = item;
    }

    public T pop() {
        if(null == this.items) {
            return null;
        }
        T item = this.items[this.top];
        this.items[this.top] = null;
        this.top -= 1;
        this.count.getAndDecrement();
        return item;
    }

    public T peek() {
        if(null == this.items) {
            return null;
        }
        return this.items[top];
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.count.get();
    }

    public final int capacity(){
        return this.items.length;
    }

    public void clear() {
        if(this.items == null || this.isEmpty()){
            return;
        }
        for (int i = this.top; i >= 0; i--) {
            this.items[this.top--] = null;
            this.count.getAndDecrement();
        }
        this.items = null;
    }

    public void print() {
        if(this.items == null || this.isEmpty()){
            return;
        }
        for (int i = this.top; i >= 0; i--) {
            if(null != this.items[i]){
                System.out.println(this.items[i]);
            }
        }
    }

    private void resize(final double resizeFactor) {
        this.capacity *= resizeFactor;
        this.items = Arrays.copyOf(this.items, this.capacity);
    }
}
