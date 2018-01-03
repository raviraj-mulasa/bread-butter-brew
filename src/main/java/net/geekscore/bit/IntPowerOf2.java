package net.geekscore.bit;

public class IntPowerOf2 {

    public static void main(String[] args) {

        System.out.println("1 is a power of 2 "+powerOf2IncludingZero(1));
        System.out.println("2 is a power of 2 "+powerOf2IncludingZero(2));
        System.out.println("4 is a power of 2 "+powerOf2IncludingZero(4));
        System.out.println("64 is a power of 2 "+powerOf2IncludingZero(64));
        System.out.println("5 is a power of 2 "+powerOf2IncludingZero(5));
        System.out.println(Integer.MAX_VALUE+" is a power of 2 "+powerOf2IncludingZero(Integer.MAX_VALUE));
        System.out.println();
        System.out.println("0 is a power of 2 "+powerOf2IncludingZero(0));
        System.out.println(Integer.MIN_VALUE+" is a power of 2 "+powerOf2IncludingZero(Integer.MIN_VALUE));


        System.out.println("-------------------------");

        System.out.println("1 is a power of 2 "+powerOf2(1));
        System.out.println("2 is a power of 2 "+powerOf2(2));
        System.out.println("4 is a power of 2 "+powerOf2(4));
        System.out.println("64 is a power of 2 "+powerOf2(64));
        System.out.println("5 is a power of 2 "+powerOf2(5));
        System.out.println(Integer.MAX_VALUE+" is a power of 2 "+powerOf2(Integer.MAX_VALUE));
        System.out.println();
        System.out.println("0 is a power of 2 "+powerOf2(0));
        System.out.println(Integer.MIN_VALUE+" is a power of 2 "+powerOf2(Integer.MIN_VALUE));

    }

    private static boolean powerOf2IncludingZero(final int x) {
        return (x & (x - 1)) == 0; // 0 & Integer.MIN_VALUE is incorrectly considered a power of 2 here
    }

    public static boolean powerOf2(final int x) {
        return (x > 0) && ((x & (x -1)) == 0);
    }
}
