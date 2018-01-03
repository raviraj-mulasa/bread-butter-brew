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

//        System.out.println("modify Bit "+modifyBit(70, 6)); // true
//        System.out.println("modify Bit "+modifyBit(70, 6));
//        System.out.println("modify Bit "+modifyBit(70, 6));
//        System.out.println("modify Bit "+modifyBit(70, 6));


        final int x = -5 , y = 5;
        System.out.println( ((x ^ y) < 0));
        System.out.println( 5 ^ 5);
        System.out.println( ((5 ^ 5) < 0));
        System.out.println( ((-5 ^ -5) < 0));
    }

    private static final int setBit(final int x, final int position) {
        final int mask = 1 << position;
        return x | mask;
    }

    private static final int clearBit(final int x, final int position) {
        final int mask = 1 << position;
        return x & ~mask;
    }

    private static final int flipBit(final int x, final int position) {
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
        int mask = 1 << position;
        int state = newState ? 1 : 0 ;
        return (x & ~mask) | (-state & mask);
    }
}
