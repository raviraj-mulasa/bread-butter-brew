package edu.learn.me.sort;

/**
 * Created by ravirajmulasa on 8/26/16.
 */

import edu.learn.me.heap.AbstractBinaryHeap;
import edu.learn.me.heap.MaxHeap;
import edu.learn.me.heap.MinHeap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Heap: Nearly Completed Binary tree - Tree is completely filled from left to right except at lowest level.
 */
public final class HeapSort<T extends Comparable> implements ISort<T>{

    @Override
    public List<T> sort(T[] elements2sort, boolean asc) {

        if(elements2sort == null || elements2sort.length <= 0) {
            Collections.emptyList();
        }

        AbstractBinaryHeap heap = null;
        if(asc) {
            heap = new MaxHeap<>(elements2sort);
        } else {
            heap = new MinHeap<>(elements2sort);
        }
        heap.buildHeap();

        final int length = elements2sort.length;
        final List<T> elementsSorted = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            elementsSorted.add((T) heap.extract());
        }
        return elementsSorted;
    }
}
