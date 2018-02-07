package net.geekscore.math;

import java.util.*;

/**
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
        System.out.println(nthUglyNumber(10)); // 12
        System.out.println(nthUglyNumber(1)); // 1
        System.out.println(nthUglyNumber(2)); // 2
//        System.out.println(nthUglyNumber(1690)); // TIME OUT HERE

        System.out.println("---------------------------");

        System.out.println(nthUglyNumberEfficient(10)); // 12
        System.out.println(nthUglyNumberEfficient(1)); // 1
        System.out.println(nthUglyNumberEfficient(2)); // 2
        System.out.println(nthUglyNumberEfficient(1690)); // 2123366400
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

    private static final int nthUglyNumberEfficient(int n){
        if(n < 0){
            return 1;
        }
        List<Integer> uglyNumbers = new ArrayList<>(n);
        uglyNumbers.add(1);
        Integer i2 = 0, i3 = i2, i5 = i3;
        while (n > 1) { // we already have 1 in the list , so check n > 1
            Integer u2= 2 * uglyNumbers.get(i2), u3 = 3 * uglyNumbers.get(i3), u5 = 5 * uglyNumbers.get(i5);
            final Integer min = Math.min(u2, Math.min(u3, u5));
            if(min.compareTo(u2) == 0) i2++;
            if(min.compareTo(u3) == 0) i3++;
            if(min.compareTo(u5) == 0) i5++;
            uglyNumbers.add(min);
            n--;
        }
        return uglyNumbers.get(uglyNumbers.size() - 1);
    }
}
