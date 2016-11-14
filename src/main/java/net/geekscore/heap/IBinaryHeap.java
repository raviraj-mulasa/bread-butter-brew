package net.geekscore.heap;

/**
 * Created by ravirajmulasa on 9/12/16.
 */
public interface IBinaryHeap<T> extends IHeap<T> {

    int parent(final int position);
    int left(final int position);
    int right(final int position);
}
