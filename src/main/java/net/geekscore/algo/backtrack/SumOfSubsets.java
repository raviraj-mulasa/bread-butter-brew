package net.geekscore.algo.backtrack;

import java.util.*;

/**
 *
 * Created by ravirajmulasa on 9/14/16.
 *
 * Problem:
 *  We are given a distinct positive NUMBERS, find the SUBSETS of these NUMBERS that sums up to 'M'
 *
 *
 **/
public final class SumOfSubsets {

    private SumOfSubsets(){}

    public static void main(String[] args) {

        System.out.println("----");
        sumOfSubsets(new Integer[]{11,6,5,1,7,13,12}, Long.valueOf(15));
        System.out.println("----");
        sumOfSubsets(new Integer[]{11,6,5,1,7,13,12}, Long.valueOf(12));
        System.out.println("----");
        sumOfSubsets(new Integer[]{4, 3, 5, 2, 7}, Long.valueOf(7));
        System.out.println("----");
        sumOfSubsets(new Integer[]{15, 22, 14, 26, 32, 9, 16, 8}, Long.valueOf(53));
    }


    public static void sumOfSubsets(final Integer a[], final Long sum) {
        sumOfSubsetsHelper(a, sum, new LinkedList<>());
    }

    public static void sumOfSubsetsHelper(final Integer a[], final Long sum, final List<Integer> subsetSoFar) {
//        System.out.println("sumOfSubsetsHelper("+Arrays.toString(a)+","+sum+","+subsetSoFar+")");
        if((a.length == 0 && sum > 0) || sum < 0) {
            return;
        }
        if(sum == 0) {
            System.out.println(subsetSoFar);
        } else {

            //Choose
            final Integer chosen = a[0];

            // Explore with the  chosen element in the subset
            subsetSoFar.add(chosen);
            sumOfSubsetsHelper(Arrays.copyOfRange(a, 1, a.length), sum - chosen, subsetSoFar);

            // Explore without the chosen element in the subset
            subsetSoFar.remove(subsetSoFar.size() - 1);
            sumOfSubsetsHelper(Arrays.copyOfRange(a, 1, a.length), sum , subsetSoFar);

        }


    }



}
