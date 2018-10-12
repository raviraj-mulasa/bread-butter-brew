package net.geekscore.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
    }

    private  static  final int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        final int[] maxWindows = new int[nums.length-k+1];
        for (int i = 0; i < nums.length-k+1; i++) {
            for (int l = 0; l < k; l++) {
                if(maxHeap.isEmpty()) {
                    maxHeap.offer(nums[i+l]);
                    continue;
                }
                if(maxHeap.peek() < nums[i+l]) {
                    maxHeap.offer(nums[i+l]);
                }
            }
            maxWindows[i] = maxHeap.poll();
        }
        return maxWindows;
    }
}
