package net.geekscore.math;

import java.util.Arrays;

/**
 *
 * Write a program to find the nth super ugly number.
 *
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
 * For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers
 * given primes = [2, 7, 13, 19] of size 4.
 * Note:
 * (1) 1 is a super ugly number for any given primes.
 * (2) The given numbers in primes are in ascending order.
 * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 * (4) The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 *
 */
public class SuperUglyNumber {

    public static void main(String[] args) {
        System.out.println(nthSuperUglyNumber(12, new int[]{2, 7, 13, 19})); // 32
//        System.out.println(nthSuperUglyNumber(1, new int[]{2, 3, 5})); // 1
//        System.out.println(nthSuperUglyNumber(2, new int[]{2, 3, 5})); // 2
//        System.out.println(nthSuperUglyNumber(1690, new int[]{2, 3, 5})); // 2123366400
    }

    private static final int nthSuperUglyNumber(final int n, int[] primes) {
        if(n < 0 || primes == null || primes.length == 0){
            return 1;
        }
        final int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        final int[] pointers = new int[primes.length];
        Arrays.fill(pointers, 0); // all start at 0
        int counter = 1; // we already have 1 in the array
        while (counter < n) {
            final int[] multiplesOfPrimes = new int[primes.length];
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int i = 0; i < primes.length; i++) {
                multiplesOfPrimes[i] = primes[i] * uglyNumbers[pointers[i]];
                if(multiplesOfPrimes[i] < min) {
                    min = multiplesOfPrimes[i];
                    minIndex = i;
                }
            }
            pointers[minIndex]++; // Increment the pointer for prime at minIndex
            if(uglyNumbers[counter - 1] < min) { // Previous ugly number < calculated ugly number
                uglyNumbers[counter++] = min;
            }
        }
        return uglyNumbers[n - 1];
    }
}
