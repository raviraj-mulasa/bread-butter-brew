package net.geekscore.bit;

/**
 * Created by ravirajmulasa on 8/13/17.
 *
 * Good solution without good explanation,it’s easy to find that power of 4 numbers have those 3 common features.
 * First, greater than 0.
 * Second, its a power of 2.
 * Third, the only ‘1’ bit should be locate at the odd location,
 *  for example: 16.- 00010000.
 *  So we can use  ‘0x55555555’ to check if the ‘1’ bit is in the right place.
 *
 */
public class IntPowerOf4 {

    public static void main(String[] args) {
        System.out.println("Is 1 power of 4 "+powerOf4(4));
        System.out.println("Is 4 power of 4 "+powerOf4(4));
        System.out.println("Is 8 power of 4 "+powerOf4(8));
        System.out.println("Is 16 power of 4 "+powerOf4(16));
        System.out.println("Is 32 power of 4 "+powerOf4(32));
        System.out.println("Is 64 power of 4 "+powerOf4(64));
    }

    public static boolean powerOf4(final int x) {
        return IntPowerOf2.powerOf2(x) && (x & 0x55555555) != 0;
    }
}
