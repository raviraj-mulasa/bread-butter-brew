package net.geekscore.backtrack;

import java.util.stream.IntStream;

/**
 * Created by ravirajmulasa on 12/10/16.
 *
 *
 * Given a number n, create an array of size 2n such that the array contains 2 instances of every number from 1 to n,
 * and the number of elements between two instances of a number i is equal to i.
 *
 * If such a configuration is not possible, then print the same
 *
 * Examples:
 *  Input: n = 3, Output: res[] = {3, 1, 2, 1, 3, 2}
 *  Input: n = 2, Output: Not Possible
 *
 *  Input: n = 4, Output: res[] = {4, 1, 3, 1, 2, 4, 3, 2}
 */
public class Fill2NArray {

    private static void fill(final Integer curr, final Integer n, final Integer _2n[]) {

//        if(curr == 0 ?

        for (int i = 0; i < n; i++) {

            if(curr == 0) {
                _2n[curr] = i;
            }

            fill(curr, i, _2n);

        }




    }


    public static void main(String[] args) {
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
    }




}
