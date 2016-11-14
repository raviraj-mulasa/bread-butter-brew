package net.geekscore.sort;


/**
 * Created by ravirajmulasa on 9/12/16.
 */
public interface IQuickSort<T> extends ISort<T> {

    T select(final T[] array, final int k);

    void threeWayPartitionSort(final T[] array, final int low, final int high);

    T medianOfMedians(T[] array, final int low, final int high) ;

    int partition(T[] array, final int low, final int high);
}

