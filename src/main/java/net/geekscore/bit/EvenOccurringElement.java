package net.geekscore.bit;

import java.util.*;

/**
 *
 * Given an integer array, one element occurs even number of times and all others have
 * odd occurrences. Find the element with even occurrences.
 *
 * Input:
 * [2, 1, 3, 1, 3, 3, 1, 2].
 *
 * Output:
 * 2
 *
 */
public class EvenOccurringElement {

    public static void main(String[] args) {
        System.out.println(evenOccurringElement(new Integer[]{2, 1, 3, 1, 3, 3, 1, 2})); // 2

    }

    private static int evenOccurringElement(final Integer[] nums) {

        final Set<Integer> numsSet = new HashSet<>();
        for (final int num: nums) numsSet.add(num);

        final List<Integer> numsList = new ArrayList<>(nums.length + numsSet.size());
        Collections.addAll(numsList, nums);
        numsList.addAll(numsSet);

        int number = 0;
        for (final Integer num: numsList) number ^= num;
        return number;
    }
}
