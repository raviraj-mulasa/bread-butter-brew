package net.geekscore.backtrack;

import java.util.*;
import java.util.stream.IntStream;

/**
 *
 * Problem:
 *  We are given a distinct positive NUMBERS, find the SUBSETS of these NUMBERS that sums up to 'M'
 *
 * Created by ravirajmulasa on 9/14/16.
 *
 *
 *
 * Backtracking is really quite simple--we “explore” each node, as follows:
 *  To “explore” node N:
 *      1. If N is a goal node, return “success”
 *      2. If N is a leaf node, return “failure”
 *      3. For each child C of N,
 *          3.1. Explore C
 *              3.1.1. If C was successful, return “success”
 *      4. Return “failure”

 */
public final class SumOfSubsets {

    private SumOfSubsets(){}

//    private  static final int[] NUMBERS         = {4, 3, 5, 2, 7};
//    private  static final int M                 = 7;

    private  static final int[] NUMBERS         = {15, 22, 14, 26, 32, 9, 16, 8};
    private  static final int M                 = 53;


//    private  static final int[] NUMBERS         = {11,6,5,1,7,13,12};
//    private  static final int M                 = 15;

    private  static final BitSet SUBSET         = new BitSet(NUMBERS.length);
    private  static final List<BitSet> SUBSETS = new LinkedList<>();




    public static void main(String[] args) {
//        Sort the elements
//        Arrays.sort(NUMBERS);
//        sumOfSubsets(0, 0, IntStream.of(NUMBERS).sum());
//        printSubsets();

        System.out.println(subSetSum(IntStream.of( NUMBERS).boxed().toArray( Integer[]::new ), Long.valueOf(M)));
        constructSubSet(IntStream.of( NUMBERS).boxed().toArray( Integer[]::new ), Long.valueOf(M)).forEach(System.out::println);

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

    public static boolean subSetSum(final Integer a[], final Long sum) {
        if(Long.compare(sum, 0L)==0){
            return true;
        }
        if(Long.compare(sum, 0L) < 0 || null == a || a.length == 0){
            return false;
        }
        return //Current element is NOT part of the subset.
                subSetSum(Arrays.copyOfRange(a, 1, a.length), sum) ||
                //Current element is part of the subset and hence contributed to the sum.
                subSetSum(Arrays.copyOfRange(a, 1, a.length), sum - a[0]);
    }

    public static Set<Integer> constructSubSet(final Integer a[], final Long sum) {
        if(Long.compare(sum, 0L)==0){
            return Collections.emptySet();
        }
        if(Long.compare(sum, 0L) < 0 || null == a || a.length == 0){
            return null;
        }
        Set<Integer> subSet = constructSubSet(Arrays.copyOfRange(a, 1, a.length), sum);
        if(null != subSet) {
            return subSet;
        }
        subSet = constructSubSet(Arrays.copyOfRange(a, 1, a.length), sum - a[0]);
        if(null != subSet) {
            if(subSet == Collections.EMPTY_SET){
                subSet = new HashSet<>();
            }
            subSet.add(a[0]);
            return subSet;
        }
        return null;
    }



}
