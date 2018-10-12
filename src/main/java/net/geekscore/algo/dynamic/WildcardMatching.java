package net.geekscore.algo.dynamic;

/**
 *
 * Implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "*") → true
 isMatch("aa", "a*") → true
 isMatch("ab", "?*") → true
 isMatch("aab", "c*a*b") → false
 *
 */
public class WildcardMatching {

    public static void main(String[] args) {
        System.out.println( isMatch("aa","a")); // false
        System.out.println( isMatch("aa","aa")); // true
        System.out.println( isMatch("aaa","aa")); // false
        System.out.println( isMatch("aa", "*") );// true
        System.out.println( isMatch("aa", "a*") );// true
        System.out.println( isMatch("ab", "?*") );// true
        System.out.println( isMatch("aab", "*a*") );// true
        System.out.println( isMatch("aab", "*a?b") );// true
        System.out.println( isMatch("aab", "c*a*b") );// false
        System.out.println( isMatch("aab", "***ab") );// true
        System.out.println( isMatch("aab", "***a**b") );// true
    }
    
    private static boolean isMatch(final String str, final String pattern) {
        if (str != null && (pattern == null || pattern.length() == 0)) return false;

        System.out.print("Matching '"+str+"' and '"+pattern+"' : ");

        final String compressedPattern = pattern.contains("*") ? compressPattern(pattern) : pattern;
        return str != null &&
                (str.equals(compressedPattern) ||
                        isMatchDP(str, compressedPattern)
                );
    }

    private static String compressPattern(String pattern) {
        char prev = pattern.charAt(0);
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < pattern.length(); i++) {
            final char curr = pattern.charAt(i);
            if(prev == '*' && prev == curr) continue;
            stringBuilder.append(prev);
            prev = curr;
        }
        stringBuilder.append(prev);
        return stringBuilder.toString();
    }

    private static boolean isMatchDP(final String str, final String pattern) {

        final boolean[][] match = new boolean[str.length()+1][pattern.length()+1];
        match[0][0] = true; // empty str and empty pattern
        for (int j = 1; j <= pattern.length(); j++) { // row 1  - empty str
            if(pattern.charAt(j-1) == '*') match[0][j] = match[0][j-1];
        }

        for (int i = 1; i <= str.length(); i++) {
            for (int j = 1; j <= pattern.length(); j++) {
                if(pattern.charAt(j-1) == '?' || pattern.charAt(j-1) == str.charAt(i-1)) {
                    match[i][j] = match[i-1][j-1];
                }
                if(pattern.charAt(j-1) == '*' ) {
                    match[i][j] = match[i-1][j] || /* 1 or more case */
                            match[i-1][j-1]; /* zero case */
                }
            }
        }
        return match[str.length()][pattern.length()];
    }
}
