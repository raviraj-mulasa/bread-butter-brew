package edu.learn.me.backtrack;

import java.util.*;
import java.util.stream.IntStream;

/**
 *
 * Problem:
 *  We are given a distinct positive NUMBERS, find the SUBSETS of these NUMBERS that sums up to 'M'
 *
 * Created by ravirajmulasa on 9/14/16.
 */
public final class SumOfSubsets {

    private SumOfSubsets(){}

    private  static final int[] NUMBERS         = {4, 3, 5, 2, 7};
    private  static final int M                 = 7;

//    private  static final int[] NUMBERS         = {15, 22, 14, 26, 32, 9, 16, 8};
//    private  static final int M                 = 53;

    private  static final BitSet SUBSET         = new BitSet(NUMBERS.length);
    private  static final List<BitSet> SUBSETS = new LinkedList<>();




    public static void main(String[] args) {
//        Sort the elements
        Arrays.sort(NUMBERS);
        sumOfSubsets(0, 0, IntStream.of(NUMBERS).sum());
        printSubsets();
    }



    public static void sumOfSubsets(int sumTillNow, int k, int remainingElemsSum) {

//        Select the current element
        SUBSET.set(k, true);

        final int newSumWithCurrElem = sumTillNow + NUMBERS[k];

//        If we add the current element to the sum till now and the new resultant sum equals target sum 'M'
//            then we capture the path in the recursion tree that reached the target sum 'M'
        if (newSumWithCurrElem == M) {
            SUBSETS.add((BitSet) SUBSET.clone());
        }


//        If we add the current element,sum till now, and the next element in the recursion path <= M
//            then next element is useful , let use explore this path.
        if (k + 1 < NUMBERS.length && newSumWithCurrElem + NUMBERS[k + 1] <= M) {
            sumOfSubsets(sumTillNow + NUMBERS[k], k + 1, remainingElemsSum - NUMBERS[k]);
        }

//        Current element is not selected after we explored its feasibility, we will now explore other paths in recursion.
        SUBSET.set(k, false);

//        (sumTillNow + remainingElemsSum - NUMBERS[k]   >= M) ensures the total sum of all the elements
//        by excluding the current element may achieve the target sum.
//
//        if in case the resultant sum is less than the target  sum then exploring this path is of no use

//         sumTillNow + NUMBERS[k + 1]: this condition ensures selecting the next element is useful
//         and the total sum by including next element will not exceed the target sum.
        if (k + 1 < NUMBERS.length && sumTillNow + remainingElemsSum - NUMBERS[k] >= M && sumTillNow + NUMBERS[k + 1] <= M) {
            sumOfSubsets(sumTillNow, k + 1, remainingElemsSum - NUMBERS[k]);
        }
    }


    public static void printSubsets(){
        for (BitSet subset: SUBSETS){
            for (int i = subset.nextSetBit(0); i >= 0; i = subset.nextSetBit(i+1)) {
                System.out.print(NUMBERS[i] + "\t");
            }
            System.out.println();
        }
    }



}
