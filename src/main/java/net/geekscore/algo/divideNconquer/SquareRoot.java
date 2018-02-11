package net.geekscore.algo.divideNconquer;


/**
 * Implement int sqrt(int x).
 * Compute and return the square root of x. x is guaranteed to be a non-negative integer.
 *
 * Example 1:
 *  Input: 4 Output: 2
 *
 * Example 2:
 *  Input: 8 Output: 2
 *  Explanation: The square root of 8 is 2.82842..., and since we want to return an integer,
 *  the decimal part will be truncated.
 */
public class SquareRoot {

    public static void main(String[] args) {
        System.out.println(mySqrt(2));
        System.out.println(mySqrt(3));
        System.out.println(mySqrt(4));
        System.out.println(mySqrt(8));
        System.out.println(mySqrt(9));
        System.out.println(mySqrt(16));
        System.out.println(mySqrt(27));
        System.out.println(mySqrt(181));
        System.out.println(mySqrt(256));
        System.out.println(mySqrt(625));
        System.out.println(mySqrt(626));
        System.out.println(mySqrt(2147395599));
        System.out.println(mySqrt(Integer.MAX_VALUE));

        System.out.println("-----------------------");

        System.out.println(mySqrt1(2));
        System.out.println(mySqrt1(3));
        System.out.println(mySqrt1(4));
        System.out.println(mySqrt1(8));
        System.out.println(mySqrt1(9));
        System.out.println(mySqrt1(16));
        System.out.println(mySqrt1(27));
        System.out.println(mySqrt1(181));
        System.out.println(mySqrt1(256));
        System.out.println(mySqrt1(625));
        System.out.println(mySqrt1(626));
        System.out.println(mySqrt1(2147395599));
        System.out.println(mySqrt1(Integer.MAX_VALUE));
    }

//    Newton method
    private static int mySqrt(int x) {
        if(x == 1 || x == 0) return x;
        int sqrt = x >> 1;
        while (sqrt > x/sqrt) sqrt = (sqrt + x/sqrt) / 2;
        return sqrt;
    }

//    Binary Search
    private static int mySqrt1(int x) {
        if(x == 1 || x == 0) return x;
        int low = 1, high = x;
        while (low+1 < high) {
            final int mid   = low + ((high - low)/2);
            if (mid <= x/mid) low = mid;
            else high = mid;
        }
        return low;
    }
}
