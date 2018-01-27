package net.geekscore.problems;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 *
 * Median is the middle value in an ordered integer list. If the size of the list is even,
 * there is no middle value. So the median is the mean of the two middle value.
 *
 * Examples:
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array
 * to the very right. You can only see the k numbers in the window. Each time the sliding window moves right
 * by one position. Your job is to output the median array for each window in the original array.
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1 [3  -1  -3] 5  3  6  7       -1
 * 1  3 [-1  -3  5] 3  6  7       -1
 * 1  3  -1 [-3  5  3] 6  7       3
 * 1  3  -1  -3 [5  3  6] 7       5
 * 1  3  -1  -3  5 [3  6  7]      6
 *
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 */
public class SlidingWindowMedian {

    public static void main(String[] args) {

        // [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7} , 3)));
        // [2.50000]
        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1,4,2,3} , 4)));
        //[1073741824.00000,1.50000,2.50000,3.50000,4.50000,5.50000,6.50000,1073741827.00000]
        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{Integer.MAX_VALUE,1,2,3,4,5,6,7,Integer.MAX_VALUE}, 2)));

        // [3.00000,3.00000]
        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{5,2,2,7,3,7,9,0,2,3}, 9)));


        System.out.println("------------------------------");

        // [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
        System.out.println(Arrays.toString(medianSlidingWindowClass(new int[]{1,3,-1,-3,5,3,6,7} , 3)));
        // [2.50000]
        System.out.println(Arrays.toString(medianSlidingWindowClass(new int[]{1,4,2,3} , 4)));
        //[1073741824.00000,1.50000,2.50000,3.50000,4.50000,5.50000,6.50000,1073741827.00000]
        System.out.println(Arrays.toString(medianSlidingWindowClass(new int[]{Integer.MAX_VALUE,1,2,3,4,5,6,7,Integer.MAX_VALUE}, 2)));

        // [3.00000,3.00000] todo: Handle duplicates as well
        System.out.println(Arrays.toString(medianSlidingWindowClass(new int[]{5,2,2,7,3,7,9,0,2,3}, 9)));
    }



    private static final double[] medianSlidingWindowClass(int[] nums, int k) {
        if(null == nums || nums.length == 0 || k < 1) return new double[0];
        final double medians[] = new double[nums.length - k  + 1];
        final MedianIntegerStream.MedianFinderSet medianFinderSet = new MedianIntegerStream.MedianFinderSet();
        for (int i = 0,  r = 0; i <= nums.length - k && r < medians.length; i++, r++) {
            for (int j = i; j < i + k; j++) medianFinderSet.add(nums[j]);
            medians[r] = medianFinderSet.median();
            medianFinderSet.clear();
        }
        return medians;
    }

    private static final double[] medianSlidingWindow(int[] nums, int k) {
        if(null == nums || nums.length == 0 || k < 1) return new double[0];
        final SortedSet<Integer> maxSet = new TreeSet<>(); // elems < running Median
        final SortedSet<Integer> minSet = new TreeSet<>(Comparator.reverseOrder()); // elems > running Median
        final double medians[] = new double[nums.length - k  + 1];
        for (int i = 0,  r = 0; i <= nums.length - k && r < medians.length; i++, r++) {
            for (int j = i; j < i + k; j++) add(nums[j], maxSet, minSet);
            medians[r] = median(maxSet, minSet);
            maxSet.clear();
            minSet.clear();
        }
        return medians;
    }


    private static final double median(final SortedSet<Integer> maxSet, final SortedSet<Integer> minSet) {
        return maxSet.size() == minSet.size()
                ?
                BigInteger.valueOf(minSet.last()).add(BigInteger.valueOf(maxSet.last())).doubleValue()/2.0
                : minSet.last();
    }

    private static void add(int num, final SortedSet<Integer> maxSet, final SortedSet<Integer> minSet) {
        minSet.add(num);
        final int minMinSet = minSet.last();
        maxSet.add(minMinSet);
        minSet.remove(minMinSet);
        while (maxSet.size() > minSet.size()) {
            int maxOfMaxSet = maxSet.last();
            minSet.add(maxOfMaxSet);
            maxSet.remove(maxOfMaxSet);
        }
    }


}
