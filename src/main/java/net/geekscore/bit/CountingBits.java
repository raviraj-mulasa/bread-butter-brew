package net.geekscore.bit;


import java.util.Arrays;

/**
 *
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num
 * calculate the number of 1's in their binary representation and return them as an array.
 *
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 *
 *
 * Follow up:
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)).
 * But can you do it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++
 * or in any other language.

 */
public class CountingBits {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countingBits1(5)));
        System.out.println(Arrays.toString(countingBits1(10)));
        System.out.println(Arrays.toString(countingBits1(0)));
        System.out.println(Arrays.toString(countingBits1(1)));

        System.out.println("---------------------------------");

        System.out.println(Arrays.toString(countingBits(5)));
        System.out.println(Arrays.toString(countingBits(10)));
        System.out.println(Arrays.toString(countingBits(0)));
        System.out.println(Arrays.toString(countingBits(1)));
    }


    private static int[] countingBits(final int n) {
        final int[] bits = new int[n+1];
        if( n > 0) {
            for (int i = 1; i <= n; i++) {
                /*
                 * i & (i-1) - clears LSB , so i extends the counter by 1 for a
                 */
                bits[i] = bits[(i & (i-1))] + 1;
            }
        }
        return bits;
    }

    private static int[] countingBits1(final int n) {
        final int[] bits = new int[n+1];
        if( n > 0) {
            for (int i = 0; i <= n; i++) {
                int weight = 0;
                for (int j = 0; j < 32; j++) {
                    weight += ((i >> j) & 1) == 1 ? 1 : 0;
                }
                bits[i] = weight;
            }
        }
        return bits;
    }


}
