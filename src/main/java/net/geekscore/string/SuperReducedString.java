package net.geekscore.string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * Created by ravirajmulasa on 8/12/17.
 *
 *
 * string- s , consisting of  lowercase English alphabetic letters.
 * In one operation, we delete any pair of adjacent letters with same value.
 * For example, string "aabcc" would become either "aab" or "bcc" after operation.
 *
 * Note: If the final string is empty, print Empty String .
 * Input Format
 *      A single string - s.
 * Output Format
 *      If the final string is empty, print Empty String; otherwise, print the final non-reducible string.
 *
 * Sample Input 0: aaabccddd
 * Sample Output 0: abd
 *
 *  aaabccddd → abccddd
 *  abccddd → abddd
 *  abddd → abd
 *  Thus, we print abd.

 Sample Input 1

 baab
 Sample Output 1

 Empty String
 Explanation 1


 baab → bb
 bb → Empty String

 *
 */
public class SuperReducedString {

    public static void main(String[] args) {
        Stream<String> testCases = readFromStdIn();
        testCases.forEach(
                testCase -> {
                    System.out.println(superReduce(testCase, 0));
                }
        );
    }

    public static final String superReduce(final String str, final int currPos) {
        if(null == str || str.length() == 0){
            return "Empty String";
        }
        if(str.length() == 1){
            return str;
        }
        final char currentChar  = str.charAt(currPos);
        if(currPos < str.length() - 1){
            final char nextChar     = str.charAt(currPos + 1);
            if(nextChar == currentChar) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str.substring(0, currPos));
                stringBuffer.append(str.substring(currPos + 2));
                return superReduce(stringBuffer.toString().trim(), 0);
            } else {
                return superReduce(str, currPos + 1);
            }
        }
        return str;
    }

    private static final Stream<String> readFromStdIn() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        return in.lines().limit(100);
    }
}
