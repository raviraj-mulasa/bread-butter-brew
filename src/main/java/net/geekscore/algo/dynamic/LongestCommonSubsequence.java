package net.geekscore.algo.dynamic;

/**
 * Suppose we have a sequence of letters ACCGGTC. Then a subsequence of this sequence would
 * be like ACCG or ACTC or CCC. To get ACCG, we pick the first four letters. To get ACTC, we pick
 * letters 1, 2, 6, and 7. To get CCC, we pick letters 2, 3, and 7, etc.
 *
 * Formally, given a sequence X = x1, x2, . . . , xm, another sequence Z = z1, . . . , zk is a
 * subsequence of X if there exists a strictly increasing sequence i1, i2, . . . , ik of indices
 * of X such that for all j = 1, 2, . . . , k, we have xij = zj.
 *
 * In the longest-common-subsequence problem, we are given two sequences X and Y , and want to find
 * the longest possible sequence that is a subsequence of both X and Y .
 *
 * For example, if
 * X = ABCBDAB and
 * Y = BDCABA,
 * the sequence BCA is a common sequence of both X and Y .
 * However, it is not a longest common subsequence, because BCBA is a longer sequence that is also
 * common to both X and Y .
 * Both BCBA and BDAB are longest common subsequences, since there are no common sequences of
 * length 5 or greater.
 *
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        System.out.println("------------------");
        System.out.println(longestCommonSubsequenceRec("ABCBDAB", "BDCABA")); // 4 - "BCBA" OR "BDAB"
        System.out.println(longestCommonSubsequenceRec("springtime", "pioneer")); // 4 - "pine"
        System.out.println(longestCommonSubsequenceRec("horseback", "snowflake")); // 3 - "oak"
        System.out.println(longestCommonSubsequenceRec("maelstrom", "becalm")); // 3 - "elm"
        System.out.println(longestCommonSubsequenceRec("heroically", "scholarly")); // 5 - "holly"


        System.out.println("------------------");
        System.out.println(longestCommonSubsequenceDP("ABCBDAB", "BDCABA")); // 4 - "BCBA" OR "BDAB"
        System.out.println(longestCommonSubsequenceDP("springtime", "pioneer")); // 4 - "pine"
        System.out.println(longestCommonSubsequenceDP("horseback", "snowflake")); // 3 - "oak"
        System.out.println(longestCommonSubsequenceDP("maelstrom", "becalm")); // 3 - "elm"
        System.out.println(longestCommonSubsequenceDP("heroically", "scholarly")); // 5 - "holly"
    }

    private static final int longestCommonSubsequenceRec(final String s1, final String s2) {
        if(null == s1 || s1.length() == 0 || s2 == null || s2.length() == 0) return 0;
        return longestCommonSubsequenceRecHelper(s1, 0, s2, 0);
    }

    private static final int longestCommonSubsequenceRecHelper(final String s1, int i, final String s2, int j) {
        if(i == s1.length() || j == s2.length()) return 0;
        if(s1.charAt(i) == s2.charAt(j))  return  1 + longestCommonSubsequenceRecHelper(s1, i+1, s2, j+1);
        else return Math.max(
                longestCommonSubsequenceRecHelper(s1, i+1, s2, j)
                ,longestCommonSubsequenceRecHelper(s1, i, s2, j+1)
        );
    }

    private static final int longestCommonSubsequenceDP(final String s1, final String s2) {
        if(null == s1 || s1.length() == 0 || s2 == null || s2.length() == 0) return 0;
        final int[][] commonSubSeqLen = new int[s1.length()+1][s2.length()+1];
        for (int i = 1; i <= s1.length(); i++) {
            commonSubSeqLen[i][0] = 0;
        }
        for (int j = 1; j <= s2.length(); j++) {
            commonSubSeqLen[0][j] = 0;
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    commonSubSeqLen[i][j] = 1 + commonSubSeqLen[i-1][j-1];
                } else {
                    commonSubSeqLen[i][j] = Math.max(commonSubSeqLen[i-1][j], commonSubSeqLen[i][j-1]);
                }
            }
        }
        return commonSubSeqLen[s1.length()][s2.length()];
    }
}
