package net.geekscore.bit;

/**
 * Swap the indices at i & j of a 64-bit integer
 */
public class SwapBits {

    public static void main(String[] args) {
        System.out.println(swap(0xA, 1, 2)); // 12
        System.out.println(swap(0xA, 3, 1)); // 10 - Equal
    }

    private static int swap(int num, int i, int j) {
        if(((num >> i) & 1) != ((num >> j) & 1)) {
            int maskI = 1 << i;
            int maskJ = 1 << j;
            return num ^ (maskI | maskJ); // XOR with 1 - Flip bits
        }
//        System.out.println("Equal");
        return num;


    }
}
