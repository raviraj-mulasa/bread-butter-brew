package net.geekscore.design;

import java.util.BitSet;

/**
 *
 * Design a Phone Directory which supports the following operations:
 *
 * get: Provide a number which is not assigned to anyone.
 * check: Check if a number is available or not.
 * release: Recycle or release a number.
 *
 * Example:
 * // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 * PhoneDirectory directory = new PhoneDirectory(3);
 *
 * // It can return any available phone number. Here we assume it returns 0.
 * directory.get();
 *
 * // Assume it returns 1.
 * directory.get();
 *
 * // The number 2 is available, so return true.
 * directory.check(2);
 *
 * // It returns 2, the only number that is left.
 * directory.get();
 *
 * // The number 2 is no longer available, so return false.
 * directory.check(2);
 *
 * // Release number 2 back to the pool.
 * directory.release(2);
 *
 * // Number 2 is available again, return true.
 * directory.check(2);
 */
public class PhoneDirectoryMain {

    private  static class PhoneDirectory {

        private final BitSet assigned;

        private int fromIdx = 0;

        private final int maxNumbers;

        /** Initialize your data structure here
         @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
        private PhoneDirectory(int maxNumbers) {
            this.assigned = new BitSet(maxNumbers);
            this.maxNumbers = maxNumbers;
        }

        /** Provide a number which is not assigned to anyone.
         @return - Return an available number. Return -1 if none is available. */
        private int get() {
            if(this.maxNumbers == this.assigned.cardinality()) return -1;
            int number = this.assigned.nextClearBit(fromIdx);
            this.assigned.set(number, true);
            this.fromIdx = number;
            return number;
        }

        /** Check if a number is available or not. */
        private boolean check(int number) {
            return !this.assigned.get(number);
        }

        /** Recycle or release a number. */
        private void release(int number) {
            this.assigned.set(number, false);
            if(this.fromIdx > number) {
                this.fromIdx = number;
            }
        }
    }

    public static void main(String[] args) {

        final PhoneDirectory directory = new PhoneDirectory(3);
        System.out.println(directory.get()); // returns 0
        System.out.println(directory.get());// returns 1
        System.out.println(directory.check(2)); // return true.
        System.out.println(directory.get()); // returns 2
        System.out.println(directory.check(2)); // return false.
        directory.release(2);
        System.out.println(directory.check(2)); // return true.

        System.out.println("---------------");

        final PhoneDirectory directory1 = new PhoneDirectory(1);
        System.out.println(directory1.check(0)); // returns true
        System.out.println(directory1.get());// returns 0
        System.out.println(directory1.check(0)); // return false.
        System.out.println(directory1.get()); // returns -1
        directory1.release(0);
        System.out.println(directory1.check(0)); // return true.
        System.out.println(directory1.get()); // returns 0
        System.out.println(directory1.get()); // returns -1
    }


}
