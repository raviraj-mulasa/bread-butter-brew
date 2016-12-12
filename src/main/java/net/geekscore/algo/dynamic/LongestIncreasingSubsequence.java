package net.geekscore.algo.dynamic;


import java.util.Arrays;
import java.util.Collections;

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
     * all the possible combinations
     */
    public static Integer lisEnding(final Integer a[], final Integer j) {
        Integer maxLength = 1;
        for (int i = 0; i < j; i++) {
            if(a[i] < a[j]) {
                maxLength = Math.max(maxLength, lisEnding(a, i) + 1);
            }
        }
        return maxLength;
    }


    public static Integer lis(Integer a[]) {
        Integer maxLength = Integer.MIN_VALUE;
        for (int j = 0; j < a.length; j++) {
            maxLength = Math.max(maxLength, lisEnding(a, j));
        }
        return maxLength;
    }


    public static Integer lisEndingMemoization(final Integer a[]) {

        final Integer[] L = new Integer[a.length];
//        Every element is a sub sequence
        Arrays.fill(L, 1);

        for (int j = 0; j < a.length; j++) {
            for (int i = 0; i < j; i++) {
                if (a[i] < a[j]) {
                    L[j] = Math.max(L[j], L[i] + 1);
                }
            }
        }
//        Arrays.stream(L).forEach(System.out::print);
//        System.out.println();
        return Collections.max(Arrays.asList(L));
    }






    public static void main(String[] args) {
        System.out.println("===========================================");

        System.out.println(lis(new Integer[]{6, 3, 5, 2, 7, 8, 1}));

        System.out.println(lis(new Integer[]{15, 27, 14, 38, 26, 55, 46, 65, 85}));

        System.out.println(lis(new Integer[]{3, 2, 6, 4, 5, 1}));

        System.out.println(lis(new Integer[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}));

        System.out.println("===========================================");

        System.out.println(lisEndingMemoization(new Integer[]{6, 3, 5, 2, 7, 8, 1}));

        System.out.println(lisEndingMemoization(new Integer[]{15, 27, 14, 38, 26, 55, 46, 65, 85}));

        System.out.println(lisEndingMemoization(new Integer[]{3, 2, 6, 4, 5, 1}));

        System.out.println(lisEndingMemoization(new Integer[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}));
    }

}
