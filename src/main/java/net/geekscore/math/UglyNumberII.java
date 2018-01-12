package net.geekscore.math;

import java.util.Arrays;

/**
 *
 * TODO
 *
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 *
 * Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
 *
 */
public class UglyNumberII {

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
        System.out.println(nthUglyNumber(1));
        System.out.println(nthUglyNumber(2));
        System.out.println(nthUglyNumber(1690));
    }

    private static final int nthUglyNumber(final int n){
        if (n > 0 && n <= 2){
            return n;
        }
        int count = 2; // for 1 & 2
        int i = 3;
        while (true){
            if(isUgly(i)) count++;
            if(count == n) break;
            i++;
        }
        return i;
    }

    private static final boolean isUgly(int num) {
        for (int primeFactor: Arrays.asList(2,3,5)) {
            while (num % primeFactor == 0) num = num / primeFactor;
        }
        return num == 1;
    }

    private static final int nthUglyNumberEfficient(final int n){
        return 0;
    }
}
