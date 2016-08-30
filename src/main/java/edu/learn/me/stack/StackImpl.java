package edu.learn.me.stack;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ravirajmulasa on 8/26/16.
 */
public final class StackImpl<T> implements IStack<T> {

    private T container[] = null;

//    Initialize capacity to 256
    private int capacity  = 256;

//    Initialize top to -1
    private int top = -1;

    private final AtomicInteger count = new AtomicInteger(0);

    public StackImpl(final int capacity) {
        this.capacity   = capacity;
        this.container  = (T[]) new Number[this.capacity];
    }

    public StackImpl() {
       this(256);
    }

    public void push(T item) {
        if(null == this.container) {
            this.container = (T[]) new Number[this.capacity];
        }
        if(this.size() * 1.25 > this.capacity) {
            this.doubleCapacity();
        }
        this.count.incrementAndGet();
        this.container[++this.top] = item;
    }

    public T pop() {
        if(null == this.container) {
            return null;
        }
        T item = this.container[this.top];
        this.container[this.top] = null;
        this.top -= 1;
        this.count.getAndDecrement();
        return item;
    }

    public T peek() {
        if(null == this.container) {
            return null;
        }
        return this.container[top];
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.count.get();
    }

    public final int capacity(){
        return this.container.length;
    }

    public void clear() {
        if(this.container == null || this.isEmpty()){
            return;
        }
        for (int i = this.top; i >= 0; i--) {
            this.container[this.top--] = null;
            this.count.getAndDecrement();
        }
        this.container = null;
    }

    public void print() {
        if(this.container == null || this.isEmpty()){
            return;
        }
        for (int i = this.top; i >= 0; i--) {
            if(null != this.container[i]){
                System.out.println(this.container[i]);
            }
        }
    }

    private synchronized void doubleCapacity() {
        this.capacity *= 2;
        final T temp[] = Arrays.copyOf(this.container, this.capacity);
        this.container = temp;
    }
}
