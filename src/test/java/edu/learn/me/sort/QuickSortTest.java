package edu.learn.me.sort;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        final Integer[] zeroOneTwos                     = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        final Integer[] zeroOneTwosInOrder              = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2};
        final QuickSort<Integer> quickSortZeroOneTwos   = new QuickSort<>();
        quickSortZeroOneTwos.threeWayPartitionSort(zeroOneTwos, 0, zeroOneTwos.length - 1);
        Assert.assertThat(zeroOneTwos, CoreMatchers.is(zeroOneTwosInOrder));


    }

    @Test
    public void testMedianOfMedians() {

        final Integer[] integers            = {-9,0,2,5,9,19,24,54,5,87,9,-8,10,44,32,21,13,24,18,26,16,19,25,39,47,56,71,91,100};
        final QuickSort<Integer> quickSort  = new QuickSort<>();
        final Integer medianOfMedians       = quickSort.medianOfMedians(integers, 0, integers.length - 1);
//        System.out.println(medianOfMedians);

        Arrays.sort(integers);
//        System.out.println(Arrays.asList(integers).stream().map(i -> i.toString()).collect(Collectors.joining(", ")));

        int medianOfMediansIndex  = Arrays.binarySearch(integers, medianOfMedians);
//        System.out.println(medianOfMediansIndex);

//        Median splits the elements somewhere between 30 %/70 % and 70 %/30 %, which assures worst - case linear behavior of the algorithm
        Assert.assertTrue(integers.length * .3 <= medianOfMediansIndex && integers.length * .7 >= medianOfMediansIndex);


    }
}
