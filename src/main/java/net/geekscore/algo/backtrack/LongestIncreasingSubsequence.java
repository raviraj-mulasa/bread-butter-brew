package net.geekscore.algo.backtrack;

/**
 * Created by ravirajmulasa on 11/28/16.
 */

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/***
 *
 * Now suppose we are given a sequence of integers,
 * and we want to find the longest subsequence whose elements are in increasing order.
 *
 * More concretely, the input is an array A[1 .. n] of integers, and we want to find the longest sequence of indices 1 ≤ i1 < i2 < ···ik ≤ n such that A[ij] < A[ij+1] for all j.
 *
 */
public final class LongestIncreasingSubsequence {

    private LongestIncreasingSubsequence(){}

    private static Predicate<Integer> isGreaterThanFirst(final Integer firstEle)  {
        return ele -> ele > firstEle;
    }


    private static Predicate<Integer> isSmallerThanLast(final Integer lastEle)  {
        return ele -> ele < lastEle;
    }


    public static Integer lisBigger(final Integer prior, final Integer a[]) {

        if(null == a || a.length == 0) {
            return 0;
        }

//        Ignore first element, find LIS
        final Integer lengthOfSubsequenceNoFirstEle = lisBigger(prior, Arrays.copyOfRange(a, 1, a.length));
        Integer lengthOfSubsequenceWithFirstEle     = Integer.MIN_VALUE;


        final Integer firstEle = a[0];
//        First array element is greater than prior element in the subsequence, then included it in the subsequence
        if(firstEle > prior) {
            final List<Integer> allElemsGreaterFirstEle = Arrays.asList(Arrays.copyOfRange(a, 1, a.length))
                    .stream()
                    .filter(isGreaterThanFirst(firstEle))
                    .collect(Collectors.toList());

            lengthOfSubsequenceWithFirstEle = 1 + lisBigger(firstEle, allElemsGreaterFirstEle.toArray(new Integer[0]));
        }
        return Math.max(lengthOfSubsequenceNoFirstEle, lengthOfSubsequenceWithFirstEle);
    }


    public static Integer lisSmaller(final Integer a[], final  Integer next) {

        if(null == a || a.length == 0) {
            return 0;
        }

        // Ignore last element, find LIS
        final Integer lengthOfSubsequenceNoLastEle = lisSmaller(Arrays.copyOfRange(a, 0, a.length - 1), next);
        Integer lengthOfSubsequenceWithLastEle     = Integer.MIN_VALUE;

        final Integer lastEle = a[a.length - 1];
//        Last element is smaller than next element in the subsequence, then included it in the subsequence
        if(lastEle < next) {
            final List<Integer> allElemsSmallerrLastEle = Arrays.asList(Arrays.copyOfRange(a, 0, a.length - 1 ))
                    .stream()
                    .filter(isSmallerThanLast(lastEle))
                    .collect(Collectors.toList());

            lengthOfSubsequenceWithLastEle = 1 + lisSmaller(allElemsSmallerrLastEle.toArray(new Integer[0]), lastEle);
        }
        return Math.max(lengthOfSubsequenceNoLastEle, lengthOfSubsequenceWithLastEle);
    }


    public static void main(String[] args) {
        System.out.println(lisBigger(Integer.MIN_VALUE, new Integer[]{6, 3, 5, 2, 7, 8, 1}));
        System.out.println(lisBigger(Integer.MIN_VALUE, new Integer[]{15, 27, 14, 38, 26, 55, 46, 65, 85}));
        System.out.println(lisBigger(Integer.MIN_VALUE, new Integer[]{3, 2, 6, 4, 5, 1}));
        System.out.println(lisBigger(Integer.MIN_VALUE, new Integer[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}));
        System.out.println("===========================================");
        System.out.println(lisSmaller(new Integer[]{6, 3, 5, 2, 7, 8, 1}, Integer.MAX_VALUE));
        System.out.println(lisSmaller(new Integer[]{15, 27, 14, 38, 26, 55, 46, 65, 85}, Integer.MAX_VALUE));
        System.out.println(lisSmaller(new Integer[]{3, 2, 6, 4, 5, 1}, Integer.MAX_VALUE));
        System.out.println(lisSmaller(new Integer[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}, Integer.MAX_VALUE));
    }

}
