package edu.learn.me.sort;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ravirajmulasa on 9/9/16.
 */
public final class HeapSortTest {

    @Test
    public void testSort() {

        final ISort<Integer> heapSort   = new HeapSort<>();

        final Integer[] array           = {14, 17, 13, 15, 19, 10, 3, 16, 9, 12};
        final List<Integer> expectedReverse = Arrays.asList(19, 17, 16, 15, 14, 13, 12, 10, 9, 3);
        Assert.assertThat(heapSort.sort(array, true), CoreMatchers.is(expectedReverse));

        final Integer[] array1           = {14, 17, 13, 15, 19, 10, 3, 16, 9, 12};
        final List<Integer> expected = Arrays.asList(3, 9, 10, 12, 13, 14, 15, 16, 17, 19);
        Assert.assertThat(heapSort.sort(array1, false), CoreMatchers.is(expected));



    }
}
