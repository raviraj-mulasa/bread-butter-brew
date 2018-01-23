package net.geekscore.bit;

/**
 *
 * Hamming Weight
 *
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011,
 * so the function should return 3.
 */
public class HammingWeight {

    public static void main(String[] args) {

        System.out.println(noOf1Bits(11)); // 11- 1011
        System.out.println(noOf1Bits(0));
        System.out.println(noOf1Bits(1));
        System.out.println(noOf1Bits(3)); // 3 - 11
        System.out.println(noOf1Bits(7)); // 7 - 111
        System.out.println(noOf1Bits(16)); // 16 - 1000
        System.out.println(noOf1Bits(Integer.MAX_VALUE)); // 0111111111111111111111111111111 - Signed bit is 0
        System.out.println(noOf1Bits(Integer.MIN_VALUE)); // 1000000000000000000000000000000 - Signed bit is 1

        System.out.println("--------------------------");

        System.out.println(noOf1Bits1(11)); // 11- 1011
        System.out.println(noOf1Bits1(0));
        System.out.println(noOf1Bits1(1));
        System.out.println(noOf1Bits1(3)); // 3 - 11
        System.out.println(noOf1Bits1(7)); // 7 - 111
        System.out.println(noOf1Bits1(16)); // 16 - 1000
        System.out.println(noOf1Bits1(Integer.MAX_VALUE)); // 0111111111111111111111111111111 - Signed bit is 0
        System.out.println(noOf1Bits1(Integer.MIN_VALUE)); // 1000000000000000000000000000000 - Signed bit is 1


    }

    private static int noOf1Bits(final int x) {
        int tmp = x;
        int noOf1s = 0;
        while (tmp != 0){
            noOf1s += (tmp & 1);
            tmp = tmp >>> 1; //Bit shifting  >>> unsigned operation , >> depends on sign extension
        }
        return noOf1s;
    }

    private static int noOf1Bits1(int x) {
        int noOf1s = 0;
        while (x > 0) {
            x &= (x - 1); // Clear LSB
            noOf1s++;
        }
        return noOf1s;
    }

    private static int noOf1Bits2(int x) {
        int noOf1s = 0;
        for (int i = 0; i < 32; i++) {
            noOf1s = ((x >> i) & 1) == 1 ? 1 : 0;
        }
        return noOf1s;
    }

}
