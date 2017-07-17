package net.geekscore.algo.dynamic;


import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 *
 * https://people.cs.clemson.edu/~bcdean/dp_practice/
 *
 * Created by ravirajmulasa on 9/12/16.
 */
public class LongestIncreasingSubsequence {




    /**
     *
     * @param a
     * @return  length of longest increasing sub-sequence that ends at j.
     *
     * The longest increasing sub-sequence ending at j extends a sub-sequence from i, we need to max length of
     * all the possible combinations - O(N)
     */
    public static Integer maxListLengthEndingAt(final Integer a[], final Integer j) {
//        Every element is a sub sequence
        Integer maxLengthEndingAt = 1;
        for (int i = 0; i < j; i++) {
            final Integer lengthEndingAtI = maxListLengthEndingAt(a, i);
            if(a[i] < a[j] && maxLengthEndingAt < lengthEndingAtI + 1) {
                maxLengthEndingAt =  lengthEndingAtI + 1;
            }
        }
        return maxLengthEndingAt;
    }

    /**
     *
     * @param a
     * @return  length of longest increasing sub-sequence that ends at j.
     *
     * The longest increasing sub-sequence ending at j extends a sub-sequence from i, we need to max length of
     * all the possible combinations - O(logN)
     */
    public static Integer lisLog(final Integer a[]) {
        if(null == a){
            return 0;
        }
        System.out.println("Given Sequence :"+ Arrays.stream(a).map(i -> i.toString()).collect(Collectors.joining(", ")));
        Integer maxLength = 0;
        for (int j = 1; j < a.length; j++) {
            ;
        }
        return maxLength;
    }


    public static Integer lis(Integer a[]) {
        if(null == a){
            return 0;
        }
        System.out.println("Given Sequence :"+ Arrays.stream(a).map(i -> i.toString()).collect(Collectors.joining(", ")));
        Integer maxLength = 0;
        for (int j = 1; j < a.length; j++) {
            maxLength = Math.max(maxLength, maxListLengthEndingAt(a, j));
        }
        return maxLength;
    }


    public static Integer lisEndingMemoization(final Integer a[]) {
        if(null == a || 0 == a.length){
            return 0;
        }
        System.out.println(" Given Sequence :"+ Arrays.stream(a).map(i -> i.toString()).collect(Collectors.joining(", ")));
        final Integer[] L = new Integer[a.length];
//        Every element is a sub sequence
        Arrays.fill(L, 1);

        for (int j = 1; j < a.length; j++) {
            for (int i = 0; i < j; i++) {
                if (a[i] < a[j] && L[j] < L[i] + 1) {
                    L[j] = L[i] + 1;
                }
            }
        }
        System.out.println("Length Sequence :"+Arrays.stream(L).map(i -> i.toString()).collect(Collectors.joining(", ")));
        return Collections.max(Arrays.asList(L));
    }






    public static void main(String[] args) {
        System.out.println("===========================================");

        System.out.println("Max Length :"+lis(null));

        System.out.println("Max Length :"+lis(new Integer[]{}));

        System.out.println("Max Length :"+lis(new Integer[]{6, 3, 5, 2, 7, 8, 1}));

        System.out.println("Max Length :"+lis(new Integer[]{15, 27, 14, 38, 26, 55, 46, 65, 85}));

        System.out.println("Max Length :"+lis(new Integer[]{3, 2, 6, 4, 5, 1}));

        System.out.println("Max Length :"+lis(new Integer[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}));

        System.out.println("===========================================");

        System.out.println("Max Length :"+lisEndingMemoization(null));

        System.out.println("Max Length :"+lisEndingMemoization(new Integer[]{}));

        System.out.println("Max Length :"+lisEndingMemoization(new Integer[]{6, 3, 5, 2, 7, 8, 1}));

        System.out.println("Max Length :"+lisEndingMemoization(new Integer[]{15, 27, 14, 38, 26, 55, 46, 65, 85}));

        System.out.println("Max Length :"+lisEndingMemoization(new Integer[]{3, 2, 6, 4, 5, 1}));

        System.out.println("Max Length :"+lisEndingMemoization(new Integer[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}));
    }

}
