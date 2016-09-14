package edu.learn.me.queue;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ravirajmulasa on 8/27/16.
 */
public final class QueueImpl<T extends Number> implements IQueue<T> {

    private T items[]= null;

    //    Initialize capacity to 256
    private int capacity= 256;

    //    Initialize fornt to -1
    private int front   = -1;

    //    Initialize rear to -1
    private int rear    = -1;

    private final AtomicInteger count = new AtomicInteger(0);

    public QueueImpl(final int capacity) {
        this.capacity = capacity;
        this.items = (T[]) new Number[this.capacity];
    }

    public QueueImpl() {
        this(256);
    }


    public void enqueue(T item) {
        if (null == this.items) {
            this.items = (T[]) new Number[this.capacity];
        }
        if (this.size() * 1.25 > this.capacity) {
            this.doubleCapacity();
        }
        this.count.incrementAndGet();
        this.items[++this.rear] = item;
    }

    public T dequeue() {
        if (null == this.items || this.rear <= this.front) {
            return null;
        }
        T item = this.items[this.rear];
        this.items[this.rear] = null;
        this.count.getAndDecrement();
        this.rear -= 1;
        return item;
    }

    public T peek() {
        if (null == this.items || this.rear <= this.front) {
            return null;
        }
        return this.items[this.rear];
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.count.get();
    }

    public final int capacity() {
        return this.items.length;
    }

    public void clear() {
//        if (this.items == null || this.isEmpty()) {
//            return;
//        }
//        for (int i = this.top; i >= 0; i--) {
//            this.items[this.top--] = null;
//            this.count.getAndDecrement();
//        }
//        this.items = null;
    }

    public void print() {
//        if (this.items == null || this.isEmpty()) {
//            return;
//        }
//        for (int i = this.top; i >= 0; i--) {
//            if (null != this.items[i]) {
//                System.out.println(this.items[i]);
//            }
//        }
    }

    private synchronized void doubleCapacity() {
//        this.capacity *= 2;
//        final T temp[] = Arrays.copyOf(this.items, this.capacity);
//        this.items = temp;
    }
}
