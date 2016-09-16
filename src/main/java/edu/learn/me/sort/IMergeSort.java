package edu.learn.me.sort;

/**
 * Created by ravirajmulasa on 9/12/16.
 */
public interface IMergeSort<T> extends ISort<T> {

    void merge(final T[] left, final T[] right, final T[] array, final boolean asc);

    void bottomUpMergeSort(final T[] elements2sort, final boolean asc);
}
