package net.geekscore.search;

/**
 * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target,
 * find the smallest element in the list that is larger than the given target.
 *
 * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'],
 * the answer is 'a'.
 *
 * Examples:
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "a"
 * Output: "c"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "c"
 * Output: "f"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "d"
 * Output: "f"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "g"
 * Output: "j"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "j"
 * Output: "c"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "k"
 * Output: "c"
 *
 * Note:
 * letters has a length in range [2, 10000].
 * letters consists of lowercase letters, and contains at least 2 unique letters.
 * target is a lowercase letter.
 */
public class FindSmallestLetterGreaterThanTarget {

    public static void main(String[] args) {
        System.out.println(smallestLetterGreaterThanTarget(
                new char[] {'a', 'b'}
                ,'z'
        )); // a

        System.out.println(smallestLetterGreaterThanTarget(
                new char[] {'c', 'f', 'j'}
                ,'k'
        )); // c

        System.out.println(smallestLetterGreaterThanTarget(
                new char[] {'c', 'f', 'j'}
                ,'g'
        )); // j

        System.out.println(smallestLetterGreaterThanTarget(
                new char[] {'c', 'f', 'j'}
                ,'j'
        )); // c

        System.out.println(smallestLetterGreaterThanTarget(
                new char[] {'e','e','e','e','e','e','n','n','n','n'}
                ,'e'
        )); // n
    }

    private static char smallestLetterGreaterThanTarget(final char[] chars, char target) {
        int left = 0, right = chars.length - 1;
        while (left <= right) {
            final int mid = left + (right - left) / 2;
            if(target <  chars[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return chars[left%chars.length] == target ? chars[(left+1)%chars.length] : chars[left%chars.length];
    }
}
