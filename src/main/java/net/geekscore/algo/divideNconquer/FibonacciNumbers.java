package net.geekscore.algo.divideNconquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ravirajmulasa on 9/2/16.
 */
public final class FibonacciNumbers {


    public static final List<Long> naiveRecursive(final int n) {

        return Collections.emptyList();

//        if(n <= 0) {
//            return Collections.emptyList();
//        }
//
//        final List<Long> fibonacciSeries = new ArrayList<>(n);
//
//        if(n == 0) return fibonacciSeries.add(0L);
//        if(n == 1) return fibonacciSeries.add(1L);
//
//
//        fibonacciSeries.addAll(naiveRecursive(n-1).addAll(naiveRecursive(n-2)));
//
//        return fibbonaciSeries;
    }




    public static final List<Long> bottomUp(final int n) {


            return Collections.emptyList();

    }




    public static final List<Long> naiveRecursiveSquaring(final int n) {


            return Collections.emptyList();

    }



    public static final List<Long> recursiveSquaring(final int n) {


            return Collections.emptyList();

    }



    public static void main(String args[]) {
        System.out.println(naiveRecursive(9));
    }
}
