package edu.learn.me.heap;

/**
 * Created by ravirajmulasa on 9/11/16.
 */
public interface IHeap<T> {



    /**
     *
     * Produce a heap from an unordered array in O(n);
     */
    void buildHeap();


    /**
     * position roots a heap
     * @param position
     */
    void heapify(final int position);

    /**
     * Input:  position: an array index, item: a new key greater than A[position]
     * Output: A heap where the key of A[position] was increased/decreased from previous value
     * @param position
     * @param item
     */
    void updateKey(final int position, final T item);

    /**
     * Insert item in the heap
     * @param item
     */
    void insert(final T item);

    /**
     * Removes the root from the heap
     * @return root of the heap
     */
    T extract();
}
