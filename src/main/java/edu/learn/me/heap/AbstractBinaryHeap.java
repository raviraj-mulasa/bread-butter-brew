package edu.learn.me.heap;

import java.util.*;

/**
 * Created by ravirajmulasa on 9/12/16.
 */
public abstract class AbstractBinaryHeap<T extends Comparable> implements IBinaryHeap<T> {

    protected T[] items = (T[]) new Comparable[0];
    protected Comparator<T> comparator = Comparator.<T>naturalOrder();


    @Override
    public int parent(int position) {
        if(position == 1){
            return -1;
        }
        return (int)Math.floor(((double)position)/2);
    }

    @Override
    public int left(int position) {
        if (2*position <= items.length) {
            return 2*position;
        }
        return -1;
    }

    @Override
    public int right(int position) {
        if (2*position + 1 <= items.length) {
            return 2*position + 1;
        }
        return -1;
    }


    /**
     * Convert A[1…n] to a heap.
     * Observation:
     *  Elements A[ Math.floor(n/2) + 1  … n] are leaves of the tree.
     *  because 2i > n, for all i >= Math.floor(n/2) + 1
     *  so heap property may only be violated at nodes 1… Math.floor(n/2) of the tree
     *
     *
     */
    @Override
    public void buildHeap() {

    }

    @Override
    public void heapify(int position) {

        final int left  = this.left(position);
        final int right = this.right(position);

        int largest     = position;

        if(left <= this.items.length && Objects.compare(this.items[left], this.items[position], this.comparator) < 0) {
            largest     = left;
        }

        if(right <= this.items.length && Objects.compare(this.items[right], this.items[position], this.comparator) < 0) {
            largest     = right;
        }

        if(position != largest) {
            swap(position, largest);
            heapify(largest);
        }
    }

    @Override
    public void updateKey(int position, T item) {

    }

    @Override
    public void insert(T item) {

    }

    @Override
    public T extract() {
        return null;
    }

    public Collection<T> items() {
        return Arrays.asList(this.items);
    }

    private void swap(final int index1, final int index2) {
        if(Math.min(index1, index2) < 0 || Math.max(index1, index2) >= this.items.length) {
            return;
        }
        T temp                  = this.items[index1];
        this.items[index1]  = this.items[index2];
        this.items[index2]  = temp;
    }
}
