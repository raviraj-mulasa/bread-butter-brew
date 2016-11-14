package net.geekscore.heap;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by ravirajmulasa on 9/12/16.
 */
public final class MaxHeapTest {

    @Test
    public void testBuildHeap() {

        final Integer[] array                       = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
        final AbstractBinaryHeap<Integer> maxHeap   = new MaxHeap<>(array);
        maxHeap.buildHeap();
        final Integer[] expected                    = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
        Assert.assertThat(maxHeap.items(), CoreMatchers.is(expected));
    }

    @Test
    public void testHeapify() {
        final Integer[] array                       = {1, 4, 3};
        final AbstractBinaryHeap<Integer> maxHeap   = new MaxHeap<>(array);
        maxHeap.heapify(0);
        final Integer[] expected                    = {4, 1, 3};
        Assert.assertThat(maxHeap.items(), CoreMatchers.is(expected));
    }


    @Test
    public void testUpdateKey() {
        final Integer[] array                       = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
        final AbstractBinaryHeap<Integer> maxHeap   = new MaxHeap<>(array);
        maxHeap.buildHeap();
        maxHeap.updateKey(5, 20);

        final int length = maxHeap.size();
        final Integer[] itemsInDescOrder            = new Integer[length];
        for (int i = 0; i < length; i++) {
            itemsInDescOrder[i] = maxHeap.extract();
        }

        final Integer[] expected                    = {20, 16, 14, 10, 8, 7, 4, 3, 2, 1};
        Assert.assertThat(itemsInDescOrder, CoreMatchers.is(expected));

    }


    @Test
    public void testInsert() {
        final Integer[] array                       = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        final AbstractBinaryHeap<Integer> maxHeap   = new MaxHeap<>();
        for(Integer item: array){
            maxHeap.insert(item);
        }
        final int length = maxHeap.size();
        final Integer[] itemsInDescOrder            = new Integer[length];
        for (int i = 0; i < length; i++) {
            itemsInDescOrder[i] = maxHeap.extract();
        }
        final Integer[] expected                    = {16, 14, 10, 9, 8, 7, 4, 3, 2, 1};
        Assert.assertThat(itemsInDescOrder, CoreMatchers.is(expected));
    }



    @Test
    public void testExtract() {
        final Integer[] array                       = {14, 17, 13, 15, 19, 10, 3, 16, 9, 12};
        final AbstractBinaryHeap<Integer> maxHeap   = new MaxHeap<>(array);
        maxHeap.buildHeap();
        int x = 0;
        Assert.assertTrue(19 == maxHeap.extract());
        Assert.assertTrue(9  == maxHeap.items().length);
    }


}

