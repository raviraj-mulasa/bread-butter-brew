package net.geekscore.algo.dynamic;

import java.util.Arrays;

/**
 * TODO
 *
 * Created by ravirajmulasa on 12/11/16.
 *
 * You are given an integer array nums. The absolute sum of a subarray
 * [numsl, numsl+1, ..., numsr-1, numsr] is abs(numsl + numsl+1 + ... + numsr-1 + numsr).
 * Return the maximum absolute sum of any (possibly empty) subarray of nums.
 *
 * Note that abs(x) is defined as follows:
 *     If x is a negative integer, then abs(x) = -x.
 *     If x is a non-negative integer, then abs(x) = x.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-3,2,3,-4]
 * Output: 5
 * Explanation: The subarray [2,3] has absolute sum = abs(2+3) = abs(5) = 5.
 *
 * Example 2:
 *
 * Input: nums = [2,-5,1,-4,3,-2]
 * Output: 8
 * Explanation: The subarray [-5,1,-4] has absolute sum = abs(-5+1-4) = abs(-8) = 8.
 *
 * M(j)= max absolute sum over all windows ending at j.
 * M(j) = Max { M(j-1) + A[j] , A[j]}
 * N sub-problems each take 0(1) time.
 *
 */
public class MaxAbsSumSubArray {

    public static void main(String[] args) {
        System.out.println(maxValContSeqEfficient(new Integer[]{1, 3, 2})); // 6
        System.out.println(maxValContSeqEfficient(new Integer[]{-2, -3})); // 5
        System.out.println(maxValContSeqEfficient(new Integer[]{-1, 6, -2})); // 6
        System.out.println(maxValContSeqEfficient(new Integer[]{3, -18, 1})); // 16
        System.out.println(maxValContSeqEfficient(new Integer[]{-2,-6,-1,-10,-6,-6,8,-4,-9})); // 31

        System.out.println(maxValContSeqEfficient(new Integer[]{ 1,-3,2,3,-4 })); // 5
        System.out.println(maxValContSeqEfficient(new Integer[]{2,-5,1,-4,3,-2})); // 8
        System.out.println(maxValContSeqEfficient(new Integer[]{-7,-1,0,-2,1,3,8,-2,-6,-1,-10,-6,-6,8,-4,-9,-4,1,4,-9})); // 44

    }
//
//    private static Long maxValContSeq(final Integer a[]) {
//        if(a == null || a.length == 0) {
//            return 0L;
//        }
//        long[] sum = new long[a.length];
//        sum[0] = a[0].longValue();
//        Long max  = sum[0];
//        for (int i = 1; i < a.length; i++) {
//            sum[i] = Math.max(a[i], sum[i-1] + a[i]);
//            max    = Math.max(max, sum[i]);
//        }
//        return max;
//    }

    private static int maxValContSeqEfficient(final Integer nums[]) {
        if(null == nums || 0 == nums.length) return 0;

        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        int maxAbsSum = sums[0];

        System.out.println("Num "+nums[0]+" Sums "+Arrays.toString(sums)+" Max Abs Sum "+maxAbsSum);
        for (int i = 1; i < nums.length; i++) {
            sums[i] = Math.min(
                    sums[i-1] + nums[i]
                    , nums[i]
            );
            maxAbsSum = Math.max(Math.abs(sums[i]), maxAbsSum);
//            if(Math.abs(sums[i]) > nums[i]) {
//                maxAbsSum = Math.max(Math.abs(sums[i]), maxAbsSum);
//            }
            System.out.println("Num "+nums[i]+" Sums "+Arrays.toString(sums)+" Max Abs Sum "+maxAbsSum);
        }
        return maxAbsSum;
    }
}