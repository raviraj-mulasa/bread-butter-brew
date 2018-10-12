package net.geekscore.sort;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to
 * hold additional elements from nums2.
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        final int min = Integer.MIN_VALUE;
        int[] nums1 = new int[]{8, 9, 10, 11, min, min, min, min, min};
        int[] nums2 = new int[]{1,2,3,4,5};
        merge(nums1, 4,  nums2, 5);
        System.out.println(Arrays.toString(nums1)); // [1, 2, 3, 4, 5, 8, 9, 10, 11]

        nums1 = new int[]{1, 2, min};
        nums2 = new int[]{3};
        merge(nums1, 2,  nums2, 1);
        System.out.println(Arrays.toString(nums1)); // [1, 2, 3]

        nums1 = new int[]{1, 2, min, min, min};
        nums2 = new int[]{4, 5, 6};
        merge(nums1, 2,  nums2, 3);
        System.out.println(Arrays.toString(nums1)); // [1, 2, 4, 5, 6]
    }

    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1, j = n-1, k = m+n-1;
        while(i >=0 && j >=0) {
            nums1[k--] = nums1[i] < nums2[j] ? nums2[j--] : nums1[i--];
        }
        while(j >=0) {
            nums1[k--] = nums2[j--];
        }
    }
}
