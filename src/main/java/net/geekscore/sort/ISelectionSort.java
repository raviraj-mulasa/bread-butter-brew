package net.geekscore.sort;

/**
 * Created by ravirajmulasa on 9/12/16.
 */
public interface ISelectionSort<T> extends ISort<T> {

    T kthSmallest(final int k, final T[] elements2sort);
    T kthLargest(final int k, final T[] elements2sort);
}
