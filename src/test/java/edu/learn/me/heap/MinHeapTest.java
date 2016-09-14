package edu.learn.me.heap;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ravirajmulasa on 9/13/16.
 */
public final class MinHeapTest {

    @Test
    public void testBuildHeap() {
        final Integer[] array                       = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        final AbstractBinaryHeap<Integer> minHeap   = new MinHeap<>(array);
        minHeap.buildHeap();
        final Integer[] expected                    = {1, 2, 3, 4, 7, 9, 10, 14, 8, 16};
        Assert.assertThat(minHeap.items(), CoreMatchers.is(expected));
    }

    @Test
    public void testHeapify() {
        final Integer[] array                       = {16, 4, 3};
        final AbstractBinaryHeap<Integer> minHeap   = new MinHeap<>(array);
        minHeap.heapify(0);
        final Integer[] expected                    = {3, 4, 16};
        Assert.assertThat(minHeap.items(), CoreMatchers.is(expected));
    }

    @Test
    public void testUpdateKey() {
        final Integer[] array                       = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        final AbstractBinaryHeap<Integer> minHeap   = new MinHeap<>(array);
        minHeap.buildHeap();
        minHeap.updateKey(4, -5);

        final int length = minHeap.size();
        final Integer[] itemsInAscOrder             = new Integer[length];
        for (int i = 0; i < length; i++) {
            itemsInAscOrder[i] = minHeap.extract();
        }

        final Integer[] expected                    = {-5, 1, 2, 3, 4, 8, 9, 10, 14, 16};
        Assert.assertThat(itemsInAscOrder, CoreMatchers.is(expected));
    }


    @Test
    public void testInsert() {
        final Integer[] array                       = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        final AbstractBinaryHeap<Integer> minHeap   = new MinHeap<>();
        for(Integer item: array){
            minHeap.insert(item);
        }
        final int length = minHeap.size();
        final Integer[] itemsInAscOrder             = new Integer[length];
        for (int i = 0; i < length; i++) {
            itemsInAscOrder[i] = minHeap.extract();
        }
        final Integer[] expected                    = {1, 2, 3, 4, 7, 8, 9, 10, 14, 16};
        Assert.assertThat(itemsInAscOrder, CoreMatchers.is(expected));
    }



    @Test
    public void testExtract() {

        final Integer[] array                       = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        final AbstractBinaryHeap<Integer> minHeap   = new MinHeap<>(array);
        minHeap.buildHeap();
        Assert.assertTrue(1 == minHeap.extract());
        Assert.assertTrue(9 == minHeap.items().length);

    }
}
