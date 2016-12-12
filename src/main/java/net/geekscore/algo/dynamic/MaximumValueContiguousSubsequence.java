package net.geekscore.algo.dynamic;

/**
 * Created by ravirajmulasa on 12/11/16.
 *
 * Given a sequence of n real numbers A(1) ... A(n),
 * determine a contiguous subsequence A(i) ... A(j) for which the sum of elements in the subsequence is maximized.
 */
public class MaximumValueContiguousSubsequence {

    public static final Long maxValContSeq(final Integer a[]) {
        if(a == null || a.length == 0) {
            return 0L;
        }
        Long sum    = 0L;
        Long maxSum = sum;

        for (int i = 0; i < a.length; i++) {
            if (sum + a[i] > a[i]) {
//                Add the current element to the sum, IF the sum increases, count the current element
                sum += a[i];
            } else {
//                Else  start a new sum at the current element
                sum =  (long)a[i];
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxValContSeq(new Integer[]{ -2, 11, -4, 13, -5, 2 }));
        System.out.println(maxValContSeq(new Integer[]{-15, 29, -36, 3, -22, 11, 19, -5}));
    }
}
