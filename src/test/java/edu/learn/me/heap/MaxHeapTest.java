package edu.learn.me.heap;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ravirajmulasa on 9/12/16.
 */
public final class MaxHeapTest {

    @Test
    public void testHeapify() {

        final Integer[] array                       = {35, 33, 42, 10, 14, 19, 27, 44, 26, 31};
        final AbstractBinaryHeap<Integer> maxHeap   = new MaxHeap<>(array);
        maxHeap.buildHeap();


    }
}
