package net.geekscore.bit;

/**
 *
 * TODO
 *
 * Given a positive integer, check whether it has alternating bits: namely,
 * if two adjacent bits will always have different values.
 *
 * Example 1:
 * Input: 5: 101
 * Output: True
 *
 * Example 2:
 * Input: 7:  111
 * Output: False
 *
 * Example 3:
 * Input: 11 : 1011
 * Output: False
 *
 * Example 4:
 * Input: 10 : 1010
 * Output: True
 *
 **/
public class BinaryNumberWithAlternatingBits {

    public static void main(String[] args) {
        System.out.println(alternatingBits(4)); // false
        System.out.println(alternatingBits(5)); // true
        System.out.println(alternatingBits(7)); // false
        System.out.println(alternatingBits(11)); // false
        System.out.println(alternatingBits(10)); // true
        System.out.println(alternatingBits(4369)); // false
        System.out.println(alternatingBits(170)); // true
        System.out.println(alternatingBits(169)); // false
        System.out.println(alternatingBits(171)); // false

        System.out.println("-----------------------"); // false


        System.out.println(alternatingBits1(4)); // false
        System.out.println(alternatingBits1(5)); // true
        System.out.println(alternatingBits1(7)); // false
        System.out.println(alternatingBits1(11)); // false
        System.out.println(alternatingBits1(10)); // true
        System.out.println(alternatingBits1(4369)); // false
        System.out.println(alternatingBits1(170)); // true
        System.out.println(alternatingBits1(169)); // false
        System.out.println(alternatingBits1(171)); // false

    }

    private static boolean alternatingBits(int x){
        System.out.printf("%10d: ", x);
        int num = x;
        int prev = num & 1;
        num >>= 1;
        while (num > 0) {
            final int curr = num & 1;
            if((prev ^ curr) == 0) return false;
            num >>= 1;
            prev = curr;
        }
        return true;
    }


    private static boolean alternatingBits1(int x){
        System.out.printf("%10d: ", x);
        int num = x;
        int prev = num%2; // We can get the last bit and the rest of the bits via n % 2 and n // 2 operations
        num /= 2;
        while (num > 0) {
            final int curr = num%2; // We can get the last bit and the rest of the bits via n % 2 and n // 2 operations
            if(prev == curr) return false;
            num /= 2;
            prev = curr;
        }
        return true;
    }
}
