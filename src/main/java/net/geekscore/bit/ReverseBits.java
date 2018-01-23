package net.geekscore.bit;

/**
 *
 *
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * For example, given input 43261596 (represented in binary as 00000010100101000001111010011100),
 * return 964176192 (represented in binary as 00111001011110000010100101000000).
 *
 **/
public class ReverseBits {

    public static void main(String[] args) {
        System.out.println(reverseBits(43261596)); // 964176192
        System.out.println(reverseBits(964176192)); // 43261596
        System.out.println(reverseBits(0xffff)); // -65536
        System.out.println(reverseBits(0x7fffffff)); // -2 Integer.MAX_VALUE - 0x7fffffff
        System.out.println(reverseBits(Integer.MAX_VALUE)); // -2
        System.out.println(reverseBits(-2)); // 2147483647 - Integer.MAX_VALUE
    }

    private static int reverseBits(final int x){
        int num = x;
        int i = 0, j = 31;
        while (i < j) {
            if(((x >> i) & 1) != ((x >> j) & 1))  num = swap(num, i, j); /* Not Equal */
            i++;
            j--;
        }
        return num;
    }

    private static int swap(int num, int i, int j) {
        return num ^ ((1 << i) | (1 << j));
    }
}
