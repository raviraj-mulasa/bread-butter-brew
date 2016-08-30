package edu.learn.me.queue;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ravirajmulasa on 8/27/16.
 */
public final class QueueImpl<T extends Number> implements IQueue<T> {

    private T container[]= null;

    //    Initialize capacity to 256
    private int capacity= 256;

    //    Initialize fornt to -1
    private int front   = -1;

    //    Initialize rear to -1
    private int rear    = -1;

    private final AtomicInteger count = new AtomicInteger(0);

    public QueueImpl(final int capacity) {
        this.capacity = capacity;
        this.container = (T[]) new Number[this.capacity];
    }

    public QueueImpl() {
        this(256);
    }


    public void enqueue(T item) {
        if (null == this.container) {
            this.container = (T[]) new Number[this.capacity];
        }
        if (this.size() * 1.25 > this.capacity) {
            this.doubleCapacity();
        }
        this.count.incrementAndGet();
        this.container[++this.rear] = item;
    }

    public T dequeue() {
        if (null == this.container || this.rear <= this.front) {
            return null;
        }
        T item = this.container[this.rear];
        this.container[this.rear] = null;
        this.count.getAndDecrement();
        this.rear -= 1;
        return item;
    }

    public T peek() {
        if (null == this.container || this.rear <= this.front) {
            return null;
        }
        return this.container[this.rear];
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.count.get();
    }

    public final int capacity() {
        return this.container.length;
    }

    public void clear() {
//        if (this.container == null || this.isEmpty()) {
//            return;
//        }
//        for (int i = this.top; i >= 0; i--) {
//            this.container[this.top--] = null;
//            this.count.getAndDecrement();
//        }
//        this.container = null;
    }

    public void print() {
//        if (this.container == null || this.isEmpty()) {
//            return;
//        }
//        for (int i = this.top; i >= 0; i--) {
//            if (null != this.container[i]) {
//                System.out.println(this.container[i]);
//            }
//        }
    }

    private synchronized void doubleCapacity() {
//        this.capacity *= 2;
//        final T temp[] = Arrays.copyOf(this.container, this.capacity);
//        this.container = temp;
    }
}
