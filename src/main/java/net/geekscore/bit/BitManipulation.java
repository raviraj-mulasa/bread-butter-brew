package net.geekscore.bit;

public class BitManipulation {

    public static void main(String[] args) {

        System.out.println("Set Bit "+setBit(6, 6)); // 70
        System.out.println("----------------");

        System.out.println("Clear Bit "+clearBit(70, 6)); // 6
        System.out.println("----------------");

        System.out.println("Flip Bit "+flipBit(70, 6)); // 6
        System.out.println("----------------");

        System.out.println("is Bit set "+isBitSet(70, 6)); // true
        System.out.println("is Bit set "+isBitSet(70, 5)); // false
        System.out.println("is Bit set "+isBitSet(7, 0)); // true
        System.out.println("is Bit set "+isBitSet(7, 1)); // true
        System.out.println("is Bit set "+isBitSet(7, 2)); // true
        System.out.println("is Bit set "+isBitSet(7, 3)); // false
        System.out.println("----------------");

        System.out.println("is Bit set 2 "+isBitSet2(70, 6)); // true
        System.out.println("is Bit set 2 "+isBitSet2(70, 5)); // false
        System.out.println("is Bit set 2 "+isBitSet2(7, 0)); // true
        System.out.println("is Bit set 2 "+isBitSet2(7, 1)); // true
        System.out.println("is Bit set 2 "+isBitSet2(7, 2)); // true
        System.out.println("is Bit set 2 "+isBitSet2(7, 3)); // false
        System.out.println("----------------");

        System.out.println("modify Bit "+modifyBit(70, 6, true)); // 70
        System.out.println("modify Bit "+modifyBit(70, 6, false)); // 6
        System.out.println("modify Bit "+modifyBit(9, 0, false)); // 8
        System.out.println("modify Bit "+modifyBit(10, 3, false)); // 2
        System.out.println("modify Bit "+modifyBit(7, 3, true)); // 15
        System.out.println("----------------");


        System.out.println("Gray code of 9: "+grayCode(9)); // 13
        System.out.println("Gray code of 10: "+grayCode(10)); // 15

        System.out.println("Clear LSB of 9: "+clearLSB(9)); // 8
        System.out.println("Clear LSB of 13: "+clearLSB(13)); // 12
        System.out.println("Clear LSB of 8: "+clearLSB(8)); // 0 Since even result is 0


        System.out.println("LSB of 9: "+extractLSB(9)); // 1
        System.out.println("LSB of 13: "+extractLSB(13)); // 1
        System.out.println("LSB of 8: "+extractLSB(8)); // Since even result is 8


    }

    private static final int setBit(final int x, final int position) { // Set - OR with 1
        final int mask = 1 << position;
        return x | mask;
    }

    private static final int clearBit(final int x, final int position) { // Clear - AND with 0
        final int mask = 1 << position;
        return x & ~mask;
    }

    private static final int flipBit(final int x, final int position) { // Flip - XOR with 1, Read - XOR with 0
        final int mask = 1 << position;
        return x ^ mask;
    }

    private static final boolean isBitSet(final int x, final int position) {
        final int mask = 1 << position;
        return (x & mask) != 0;
    }

    private static final boolean isBitSet2(final int x, final int position) {
        final int xShftRght = x >> position;
        return (xShftRght & 1) != 0;
    }

    private static final int modifyBit(final int x, final int position, final boolean newState) {
        // Create a mask that sets the  bit at a given position
        final int mask = 1 << position;
        final int state = newState ? 1 : 0 ;
        // x & ~mask - clear the bit at a given position i.e; 0
        // state << position & mask - change the bit to the required state
        return (x & ~mask) | ( state << position & mask);
    }

    /**
     *
     * @param x
     * @return gray code
     *
     * Let Gray Code be g3  g2  g1  g0.
     * Then the respective Binary Code can be obtained as follows:
     *  Conversion from Gray Code to Binary Code i.e.
     *  b3 = g3
     *  b2 = b3 ^ g2
     *  b1 = b2 ^ g1
     *  b0 = b1 ^ g0
     */


    // Read - XOR with 0, hence bn-1 = gn-1
    private static final int grayCode(final int x) {
        return (x ^ (x >> 1));
    }


    // even - LSB is 0, odd - LSB is 1. even & odd = LSB ic clear
    private static final int clearLSB(final int x) {
        return x & (x - 1);
    }

    private static final int extractLSB(final int x) {
        return (x & ~(x - 1));
    }




}
