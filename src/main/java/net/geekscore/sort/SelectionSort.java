package net.geekscore.sort;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 * This method is called selection sort because it works by repeatedly selecting the smallest remaining item.
 * Selection sort uses ~n2/2 compares and n exchanges to sort an array of length n.
 *
 * Invariant:
 *  a[0...i-1] is sorted
 *  all entries in a[i..n-1] are larger than or equal to the entries in a[0..i-1]
 *
 * Created by ravirajmulasa on 9/12/16.
 */
public final class SelectionSort<T extends Comparable> implements ISelectionSort<T> {

    @Override
    public Collection<T> sort(T[] elements2sort, boolean asc) {

        for (int i = 0; i < elements2sort.length; i++) {
//            Find the i th smallest/largest element in the array

        }
        return Arrays.asList(elements2sort);
    }


    @Override
    public T kthSmallest(final int k, final T[] elements2sort) {
        return null;
    }


    @Override
    public T kthLargest(final int k, final T[] elements2sort) {
        return null;
    }


    private void swap(final T[] array, final int index1, final int index2) {
        if(null == array || Math.min(index1, index2) < 0 || Math.max(index1, index2) >= array.length) {
            return;
        }
        T temp          = array[index1];
        array[index1]   = array[index2];
        array[index2]   = temp;
    }
}
