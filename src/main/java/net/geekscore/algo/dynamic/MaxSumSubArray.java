package net.geekscore.algo.dynamic;

/**
 * Created by ravirajmulasa on 12/11/16.
 *
 * Given a sequence of n real numbers A(1) ... A(n),
 * Determine a contiguous subsequence A(i) ... A(j) for which the sum of elements in the subsequence is maximized.
 *
 * M(j)= max sum over all windows ending at j.
 * M(j) = Max { M(j-1) + A[j] , A[j]}
 * N sub-problems each take 0(1) time.
 *
 */
public class MaxSumSubArray {

    public static void main(String[] args) {
        System.out.println(maxValContSeq(new Integer[]{ -2, 11, -4, 13, -5, 2 })); // 20
        System.out.println(maxValContSeq(new Integer[]{-15, 29, -36, 3, -22, 11, 19, -5})); // 30
        System.out.println(maxValContSeq(new Integer[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
        System.out.println(maxValContSeq(new Integer[]{-2, -3, 4, -1, -2, 1, 5, -3})); // 7
        System.out.println(maxValContSeq(new Integer[]{-2, -3})); // -2

        System.out.println("----------------");


        System.out.println(maxValContSeqEfficient(new Integer[]{ -2, 11, -4, 13, -5, 2 })); // 20
        System.out.println(maxValContSeqEfficient(new Integer[]{-15, 29, -36, 3, -22, 11, 19, -5})); // 30
        System.out.println(maxValContSeqEfficient(new Integer[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
        System.out.println(maxValContSeqEfficient(new Integer[]{-2, -3, 4, -1, -2, 1, 5, -3})); // 7
        System.out.println(maxValContSeqEfficient(new Integer[]{-2, -3})); // -2

    }

    private static Long maxValContSeq(final Integer a[]) {
        if(a == null || a.length == 0) {
            return 0L;
        }
        long[] sum = new long[a.length];
        sum[0] = a[0].longValue();
        Long max  = sum[0];
        for (int i = 1; i < a.length; i++) {
            sum[i] = Math.max(a[i], sum[i-1] + a[i]);
            max    = Math.max(max, sum[i]);
        }
        return max;
    }

    private static Long maxValContSeqEfficient(final Integer a[]) {
        if(a == null || a.length == 0) {
            return 0L;
        }
        Long sumSoFar   = a[0].longValue();
        Long maxSum     = sumSoFar;
        for (int i = 1; i < a.length; i++) {
            sumSoFar    = Math.max(a[i], sumSoFar + a[i]);
            maxSum      = Math.max(sumSoFar, maxSum);
        }
        return maxSum;
    }
}
