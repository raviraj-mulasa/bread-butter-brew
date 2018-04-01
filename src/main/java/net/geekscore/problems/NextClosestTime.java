package net.geekscore.problems;

import java.time.Instant;
import java.util.Arrays;

/**
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits.
 * There is no limit on how many times a digit can be reused.
 *
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid.
 * "1:34", "12:9" are all invalid.
 *
 * Example 1:
 * Input: "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.
 * It is not 19:33, because this occurs 23 hours and 59 minutes later.
 *
 * Example 2:
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
 * It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.

 */
public class NextClosestTime {

    public static void main(String[] args) {
        
        System.out.println(nextClosestTime("19:34")); // 19:39
        System.out.println(nextClosestTime("23:59")); // 22:22
        System.out.println(nextClosestTime("17:38")); // 18:11

        System.out.println("------");

        System.out.println(nextClosestTimeEfficient("19:34")); // 19:39
        System.out.println(nextClosestTimeEfficient("23:59")); // 22:22
        System.out.println(nextClosestTimeEfficient("17:38")); // 18:11

    }


    private static String nextClosestTime(final String time) {
        int hour = Integer.parseInt(time.substring(0,2));
        int min = Integer.parseInt(time.substring(3,5));
        while (true) {
            if(++min == 60) {
                min = 0;
                ++hour;
                hour %= 24;
            }
            String curr = String.format("%02d:%02d", hour, min);
            boolean valid = true;
            for (int i = 0; i < curr.length(); i++) {
                if(time.indexOf(curr.charAt(i)) < 0) {
                    valid = false;
                    break;
                }
            }
            if(valid) return curr;
        }
    }

    private static String nextClosestTimeEfficient(final String time) {
        final int[] digits = digits(time);
        final int[] sorted = Arrays.copyOfRange(digits, 0, digits.length);
        Arrays.sort(sorted);

        for (int i = digits.length-1; i > -1 ; i--) {
            final int next = nextHighestDigit(digits[i], sorted);
            if(next > digits[i]) {
                digits[i] = next;
                if(isValid(digits)) {
                    break;
                } else {
                    digits[i] = sorted[0]; // smallest possible value
                }
            } else {
                digits[i] = sorted[0]; // smallest possible value
            }
        }
        return String.format("%02d:%02d", digits[0]*10+digits[1],  digits[2] * 10 + digits[3]);
    }

    private static boolean isValid(int[] digits) {
        return digits[0]*10+digits[1] <  24 && digits[2] * 10 + digits[3] < 60;
    }

    private static int nextHighestDigit(int digit, int[] sorted) {
        for (final int num: sorted) {
            if(num > digit) return num;
        }
        return digit;
    }

    private static int[] digits(final String time) {
        final int[] digits = new int[time.length()-1];
        int i = 0;
        for (final String timeDigit: time.split(":")) {
            for (final char ch: timeDigit.toCharArray()) {
                digits[i++] = ch - '0';
            }
        }
        return digits;
    }


}
