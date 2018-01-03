package net.geekscore.bit;

/**
 *
 * Given a positive integer, check whether it has alternating bits: namely,
 * if two adjacent bits will always have different values.
 *
 * Example 1:
 * Input: 5: 101
 * Output: True
 *
 * Example 2:
 * Input: 7:  111
 * Output: False
 *
 * Example 3:
 * Input: 11 : 1011
 * Output: False
 *
 * Example 4:
 * Input: 10 : 1010
 * Output: True
 *
 **/
public class BinaryNumberWithAlternatingBits {

    public static void main(String[] args) {
        System.out.println(alternatingBits(4)); // false
        System.out.println(alternatingBits(5)); // true
        System.out.println(alternatingBits(7)); // false
        System.out.println(alternatingBits(11)); // false
        System.out.println(alternatingBits(10)); // true
    }

    private static final boolean alternatingBits(int x){
        final int mask = x >> 1;
        return ((x & mask) == 0);
    }
}
