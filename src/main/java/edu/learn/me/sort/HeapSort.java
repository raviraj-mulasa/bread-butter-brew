package edu.learn.me.sort;

/**
 * Created by ravirajmulasa on 8/26/16.
 */

import java.util.Collections;
import java.util.List;

/**
 * Heap: Nearly Completed Binary tree - Tree is completely filled from left to right except at lowest level.
 */
public final class HeapSort<T extends Comparable> implements ISort<T>{

    @Override
    public List<T> sort(T[] elements2sort, boolean asc) {
        if(elements2sort == null) {
            Collections.emptyList();
        }
        return null;
    }
}
