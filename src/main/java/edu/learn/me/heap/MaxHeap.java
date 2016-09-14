package edu.learn.me.heap;

import java.util.Comparator;

/**
 *
 * https://courses.csail.mit.edu/6.006/fall10/lectures/lec09.pdf
 *
 * Max Heaps satisfy the Max-Heap Property :for all i, A[i] ≥ max{ A[left(i)] , A[right(i)]}
 *
 * Created by ravirajmulasa on 9/12/16.
 */
public final class MaxHeap<T extends Comparable> extends AbstractBinaryHeap<T> {

    public MaxHeap() {
        this.comparator = new MaxHeapComparator();
    }

    public MaxHeap(final T[] items) {
        this.items      = items;
        this.comparator = new MaxHeapComparator();
    }

    private static class MaxHeapComparator<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }


}
