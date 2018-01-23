package net.geekscore.bit;

public class ParityOfNumber {

    public static void main(String[] args) {
        System.out.println(parity1(0xfL)); // 0
        System.out.println(parity1(0xffffL)); //  0
        System.out.println(parity1(Long.MAX_VALUE)); // 1 - 0x7fffffffffffffffL
        System.out.println(parity1(0xfffffffffffffffL)); // 0
    }

    private static byte parity1(long  x) {
        byte pairty = 0;
        while (x > 0) {
            pairty ^= (x & 1); // parity will be 0 if even 1's
            x = x >> 1;
        }
        return pairty;
    }
}
