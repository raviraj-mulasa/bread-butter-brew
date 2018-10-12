package net.geekscore.math;

/**
 * A PowerNumber is defined as a number greater than zero that  can be expressed as X^Y,
 * where X and Y are integers greater than one.
 *
 * Create a function takes in an integer i and returns the (zero-indexed) ith power number
 */
public class PowerNumber {


    public static void main(String[] args) {
        System.out.println(isPowerNumber(1)); // true
        System.out.println(isPowerNumber(8)); // true
        System.out.println(isPowerNumber(128)); // true
        System.out.println(isPowerNumber(1024)); // true
        System.out.println(isPowerNumber(48)); // false
        System.out.println(isPowerNumber(49)); // true

        System.out.println(isPowerNumber(256)); // true
        System.out.println(isPowerNumber(14641)); // true
        System.out.println(isPowerNumber(14041)); // false
        System.out.println(isPowerNumber(111)); // false

        System.out.println(isPowerNumber(729)); // true
        System.out.println(isPowerNumber(728)); // false

        System.out.println(isPowerNumber(1729)); // false
        System.out.println(isPowerNumber(1728)); // true

        System.out.println(isPowerNumber(625)); // true
        System.out.println(isPowerNumber(10000)); // true
        System.out.println(isPowerNumber(10001)); // false

        System.out.println(isPowerNumber(279936)); // true

        System.out.println(isPowerNumber(100000000000l)); // true
        System.out.println(isPowerNumber(100000000001l)); // false
        System.out.println(isPowerNumber(34522712143931l)); // true
    }


    private static boolean  isPowerNumber(long num) {
        System.out.print(num+" ");
        if(num == 1) return true;
        for (long base = (long) Math.sqrt(num), power = 2; base > 1 && power <= (long) Math.sqrt(num)+1;) {
            final long powered = Double.valueOf(Math.pow(base,power)).longValue();
//            System.out.println(base+"^"+power+": "+powered);
            if(powered == num) {
                System.out.print("== "+base+"^"+power+": ");
                return true;
            }
            if (powered < num) power++;
            else base--;
        }
        return false;
    }
}
