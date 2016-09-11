package edu.learn.me.sort;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ravirajmulasa on 9/9/16.
 */
public final class QuickSortTest {

    @Test
    public void testSort() {

        final Integer[] array           = {14, 17, 13, 15, 19, 10, 3, 16, 9, 12};
        final ISort<Integer> quickSort  = new QuickSort<>();

        final List<Integer> expected = Arrays.asList(3, 9, 10, 12, 13, 14, 15, 16, 17, 19);
        Assert.assertThat(quickSort.sort(array, false), CoreMatchers.is(expected));

        final List<Integer> expectedReverse = Arrays.asList(19, 17, 16, 15, 14, 13, 12, 10, 9, 3);
        Assert.assertThat(quickSort.sort(array, true), CoreMatchers.is(expectedReverse));

    }

    @Test
    public void testSelect() {

        final Integer[] array               = {14, 17, 13, 15, 19, 10, 3, 16, 9, 12};
        final QuickSort<Integer> quickSort  = new QuickSort<>();

        Assert.assertTrue(13 == quickSort.select(array, 5));
        Assert.assertTrue(19 == quickSort.select(array, 10));
        Assert.assertTrue(3 == quickSort.select(array, 1));

        Assert.assertNull(quickSort.select(null, 0));
        Assert.assertNull(quickSort.select(array, -1));
        Assert.assertNull(quickSort.select(array, array.length + 1));

    }


    @Test
    public void test3WayPartition() {

        final Character[] array                 = {'P', 'A', 'B', 'X', 'W', 'P', 'P', 'V', 'P', 'D', 'P', 'C', 'Y', 'Z'};
        final QuickSort<Character> quickSort    = new QuickSort<>();

        final Character[] expected              = {'A', 'B', 'C', 'D', 'P', 'P', 'P', 'P', 'P', 'V', 'W', 'X', 'Y', 'Z'};
        quickSort.threeWayPartitionSort(array, 0, array.length - 1);
        Assert.assertThat(array, CoreMatchers.is(expected));

        final Character[] shuffleDutchFlag      = {'R','B','W','W','R','W','B','R','R','W','B','R'};
        final Character[] partitionedDutchFlag  = {'B','B','B','R','R','R','R','R','W','W','W','W'};
        quickSort.threeWayPartitionSort(shuffleDutchFlag, 0, shuffleDutchFlag.length - 1);
        Assert.assertThat(shuffleDutchFlag, CoreMatchers.is(partitionedDutchFlag));

    }
}
