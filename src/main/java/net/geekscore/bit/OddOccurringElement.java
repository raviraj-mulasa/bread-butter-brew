package net.geekscore.bit;

/**
 *
 * Given an integer array, one element occurs odd number of times and all others
 * have even occurrences. Find the element with odd occurrences.
 *
 *
 * Example:
 * Input: [1, 2, 3, 1, 2, 3, 1]
 * Output: 1
 */
public class OddOccurringElement {

    public static void main(String[] args) {
        System.out.println(oddOccurringElement(new Integer[]{1, 2, 3, 1, 2, 3, 1}));  // 1
    }

    private static final int oddOccurringElement(final Integer[] nums) {
        int num =0;
        for (final int _num: nums) num ^= _num;
        return num;
    }
}
