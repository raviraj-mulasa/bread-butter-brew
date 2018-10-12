package net.geekscore.algo.dynamic;


/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 *
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 */
public class InterleavingString {

    public static void main(String[] args) {

        System.out.println("------------------------");
        System.out.println(isInterleaveRec("", "", "")); // true
        System.out.println(isInterleaveRec("", "", "a")); // false
        System.out.println(isInterleaveRec("a", "", "a")); // true
        System.out.println(isInterleaveRec("a", "", "c")); // false
        System.out.println(isInterleaveRec("", "a", "a")); // true
        System.out.println(isInterleaveRec("aabcc", "dbbca", "aadbbcbcac")); // true
        System.out.println(isInterleaveRec("aabcc", "dbbca", "aadbbbaccc")); // false
        System.out.println(isInterleaveRec("aa", "bb", "abab")); // true
        System.out.println(isInterleaveRec("aa", "bb", "baba")); // true
        System.out.println(isInterleaveRec("aa", "bb", "bcba")); // false


        System.out.println("------------------------");
        System.out.println(isInterleaveDFS("", "", "")); // true
        System.out.println(isInterleaveDFS("", "", "a")); // false
        System.out.println(isInterleaveDFS("a", "", "a")); // true
        System.out.println(isInterleaveDFS("a", "", "c")); // false
        System.out.println(isInterleaveDFS("", "a", "a")); // true
        System.out.println(isInterleaveDFS("aabcc", "dbbca", "aadbbcbcac")); // true
        System.out.println(isInterleaveDFS("aabcc", "dbbca", "aadbbbaccc")); // false
        System.out.println(isInterleaveDFS("aa", "bb", "abab")); // true
        System.out.println(isInterleaveDFS("aa", "bb", "baba")); // true
        System.out.println(isInterleaveDFS("aa", "bb", "bcba")); // false


        System.out.println("------------------------");
        System.out.println(isInterleaveDP("", "", "")); // true
        System.out.println(isInterleaveDP("", "", "a")); // false
        System.out.println(isInterleaveDP("a", "", "a")); // true
        System.out.println(isInterleaveDP("a", "", "c")); // false
        System.out.println(isInterleaveDP("", "a", "a")); // true
        System.out.println(isInterleaveDP("aabcc", "dbbca", "aadbbcbcac")); // true
        System.out.println(isInterleaveDP("aabcc", "dbbca", "aadbbbaccc")); // false
        System.out.println(isInterleaveDP("aa", "bb", "abab")); // true
        System.out.println(isInterleaveDP("aa", "bb", "baba")); // true
        System.out.println(isInterleaveDP("aa", "bb", "bcba")); // false
    }



    private static boolean isInterleaveRec(String s1, String s2, String s3) {
        if(s1 == null && s2 == null && s3 != null) return false;
        if(s1 != null && s2 != null && s3 != null && s3.length() != s1.length() + s2.length()) return false;
        return isInterleaveRecHelper(s1, 0, s2, 0, s3, 0);
    }

    private static boolean isInterleaveRecHelper(String s1, int i, String s2, int j, String s3, int k) {
        if (i == s1.length()) return s3.substring(k).equals(s2.substring(j));
        if (j == s2.length()) return s3.substring(k).equals(s1.substring(i));
        return k == s3.length()
                || (i < s1.length() && s1.charAt(i) == s3.charAt(k) && isInterleaveRecHelper(s1, i + 1, s2, j, s3, k + 1))
                || (j < s2.length() && s2.charAt(j) == s3.charAt(k) && isInterleaveRecHelper(s1, i, s2, j + 1, s3, k + 1));
    }



    private static boolean isInterleaveDFS(String s1, String s2, String s3) {
        if(s1 == null && s2 == null && s3 != null) return false;
        if(s1 != null && s2 != null && s3 != null && s3.length() != s1.length() + s2.length()) return false;
        final boolean[][] inValid = new boolean[s1.length()+1][s2.length()+1];
        return isInterleaveDFSHelper(s1, 0, s2, 0, s3, 0, inValid);
    }

    private static boolean isInterleaveDFSHelper(String s1, int i, String s2, int j, String s3, int k, final boolean[][] inValid) {
        if (inValid[i][j]) return false;
        if (k == s3.length()) return true;
        boolean valid =
                (i < s1.length() && s1.charAt(i) == s3.charAt(k) && isInterleaveDFSHelper(s1, i + 1, s2, j, s3, k + 1, inValid))
                        || (j < s2.length() && s2.charAt(j) == s3.charAt(k) && isInterleaveDFSHelper(s1, i, s2, j + 1, s3, k + 1, inValid));

        if(!valid) inValid[i][j] = true;
        return valid;
    }


    private static boolean isInterleaveDP(String s1, String s2, String s3) {
        if(s1 == null && s2 == null && s3 != null) return false;
        final int m = s1.length();
        final int n = s2.length();
        final int p = s3.length();
        if(p != m + n) return false;
        // dp[i][j] = the first i chars in s1, the first j chars in s2
        // can or not can construct to the first (i+j) chars in s3
        final boolean[][] dp = new boolean[m+1][n+1];
        // base case
        for (int len = 0; len <= m; len++) {
            dp[len][0] = (s1.substring(0, len).equals(s3.substring(0, len)));
        }
        for (int len = 0; len <= n; len++) {
            dp[0][len] = (s2.substring(0, len).equals(s3.substring(0, len)));
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(s3.charAt(i+j-1) == s1.charAt(i-1) && dp[i-1][j])
                    dp[i][j] = true;
                if(s3.charAt(i+j-1) == s2.charAt(j-1) && dp[i][j-1])
                    dp[i][j] = true;
            }
        }
        return dp[m][n];
    }


}
