package edu.learn.me.heap;

import java.util.Comparator;

/**
 *
 * https://courses.csail.mit.edu/6.006/fall10/lectures/lec09.pdf
 *
 * Min Heaps satisfy the Min-Heap Property : for all i, A[i] ≤ min{ A[left(i)] , A[right(i)]}
 *
 * Created by ravirajmulasa on 9/12/16.
 */
public final class MinHeap<T extends Comparable> extends AbstractBinaryHeap<T> {

    public MinHeap() {
        this.comparator= new MinHeapComparator<>();
    }

    public MinHeap(final T[] items) {
        this.items      = items;
        this.comparator = new MinHeapComparator();
    }

    private static class MinHeapComparator<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o2.compareTo(o1);
        }
    }
}
