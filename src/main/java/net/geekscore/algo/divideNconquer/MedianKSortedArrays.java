package net.geekscore.algo.divideNconquer;

import net.geekscore.list.SingleLinkedList;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MedianKSortedArrays {

    public static void main(String[] args) {
        System.out.println(median(new int[][]{
                {1,3,5}
                ,{2,4,6}
                ,{7,8,10}
                ,{9,11}
        }));

        System.out.println(median(new int[][]{
                {1,3,5}
                ,{2,4,6}
                ,{7,8,10}
                ,{2,4,11}
                ,{9}
                ,{0}
        }));

        System.out.println(median(new int[][]{
                {1,3,5}
                ,{2,4,6}
                ,{7,8,10}
                ,{2,11}
                ,{9}
        }));
    }

    private static final double medianKArrays(int[][] arrays) {
        if(arrays == null || arrays.length == 0) throw new IllegalArgumentException("Invalid Input!");
        if(arrays.length == 1) return median(arrays[0]);
        return median(arrays);
    }
    // nums is already sorted
    private static final double median(int[] nums) {
        if(nums == null || nums.length == 0) throw new IllegalArgumentException("Invalid Input!");
        int mid = nums.length/2 ;
        return nums.length % 2 == 0 ? (nums[mid] + nums[mid-1])/2.0 : nums[mid]*1.0;
    }

    private static final double median(int[][] arrays) {
        int n = 0; // total size
        for (int [] array: arrays) if(array != null) n+=array.length;
        final PriorityQueue<Integer> minHeap = new PriorityQueue<>(arrays.length); // heap of no.of sorted arrays
        final int[] pointers = new int[arrays.length];
        for (int i = 0; i < pointers.length; i++) {
            minHeap.offer(arrays[i][0]);
        }
        int k = n/2;
        while (k > 0) {

        }
        return 0;
    }
}
