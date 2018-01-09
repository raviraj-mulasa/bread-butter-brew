package net.geekscore.math;

/**
 *
 * Write a program to check whether a given number is an ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 *
 * Note that 1 is typically treated as an ugly number.
 */
public class UglyNumber {

    public static void main(String[] args) {
        System.out.println(isUgly(45));
        System.out.println(isUgly(12));
        System.out.println(isUgly(30));
        System.out.println(isUgly(9));
        System.out.println(isUgly(50));
        System.out.println(isUgly(6));
        System.out.println(isUgly(8));
        System.out.println(isUgly(51));
        System.out.println(isUgly(Integer.MIN_VALUE));
        System.out.println(isUgly(Integer.MAX_VALUE));
        System.out.println(isUgly(1));
        System.out.println("===========================");
        System.out.println(isUgly1(45));
        System.out.println(isUgly1(12));
        System.out.println(isUgly1(30));
        System.out.println(isUgly1(9));
        System.out.println(isUgly1(50));
        System.out.println(isUgly1(6));
        System.out.println(isUgly1(8));
        System.out.println(isUgly1(51));
        System.out.println(isUgly1(Integer.MIN_VALUE));
        System.out.println(isUgly1(Integer.MAX_VALUE));
        System.out.println(isUgly1(1));
    }

    private static final boolean isUgly(int x) {
        System.out.print(x+" Ugly ? ");
        if(1 == x){
            return true;
        }
        if(x < 2){
            return false;
        }
        while (x%2 == 0){ // First check with 2
            x = x/2;
        }
        for (int i = 3; (i * i) <= x && i<6 ; i+=2) { // Then check with odd number starting at 3
            while (x % i == 0){
                if(i > 5) return false;
                x = x / i;
            }
        }
        return x <= 5;
    }

    private static final boolean isUgly1(int x) {
        System.out.print(x+" Ugly Efficient ? ");
        if(x < 2 && x!= 1){
            return false;
        }
        int increment = 1;
        for (int i = 2; i < 6 && x > 0; i+=increment) {
            while (x % i == 0) x = x / i;
            if(i%2 != 0) increment = 2;
        }
        return x == 1;
    }
}
