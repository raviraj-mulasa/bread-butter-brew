package net.geekscore.sort;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ravirajmulasa on 9/11/16.
 */
public final class MergeSortTest {

    @Test
    public void testSort() {

        final Integer[] array           = {14, 17, 13, 15, 19, 10, 3, 16, 9, 12};
        final ISort<Integer> mergeSort  = new MergeSort<>();

        final List<Integer> expected = Arrays.asList(3, 9, 10, 12, 13, 14, 15, 16, 17, 19);
        Assert.assertThat(mergeSort.sort(array, false), CoreMatchers.is(expected));

        final List<Integer> expectedReverse = Arrays.asList(19, 17, 16, 15, 14, 13, 12, 10, 9, 3);
        Assert.assertThat(mergeSort.sort(array, true), CoreMatchers.is(expectedReverse));

    }

    @Test
    public void testBottomUpMergeSort() {

        final Integer[] array           = {14, 17, 13, 15, 19, 10, 3, 16, 9, 12};
        final IMergeSort<Integer> mergeSort  = new MergeSort<>();

        final Integer[] expected        = {3, 9, 10, 12, 13, 14, 15, 16, 17, 19};
        mergeSort.bottomUpMergeSort(array, false);
        Assert.assertEquals(Arrays.toString(array), Arrays.toString(expected));

        mergeSort.bottomUpMergeSort(array, true);
        final Integer[] expectedReverse = {19, 17, 16, 15, 14, 13, 12, 10, 9, 3};
        Assert.assertEquals(Arrays.toString(array), Arrays.toString(expectedReverse));

    }
}
