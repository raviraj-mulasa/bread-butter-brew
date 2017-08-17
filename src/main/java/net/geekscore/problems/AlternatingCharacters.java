package net.geekscore.problems;

import java.util.Scanner;

/**
 * Created by ravirajmulasa on 8/16/17.
 *
 *
 * You are given a string containing characters and only,
 * your task is to change it into a string such that every two consecutive characters are different.
 * To do this, you are allowed to delete one or more characters in the string.
 *
 * Your task is to find the minimum number of required deletions.
 * For example, string should be changed to by deleting one character .
 *
 * Input Format
 *
 * The first line contains an integer , i.e. the number of test cases.
 * The next lines contain a string .
 *
 * Constraints
 * Output Format
 * For each test case, print the minimum number of deletions required in a new line.
 *
 * Sample Input
 *
 * 5
 * AAAA
 * BBBBB
 * ABABABAB
 * BABABA
 * AAABBB
 *
 * Sample Output
 * 3
 * 4
 * 0
 * 0
 * 4

 */
public class AlternatingCharacters {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            int result = alternatingCharacters(s);
            System.out.println(result);
        }
    }

    private static final int alternatingCharacters(String s){
        int minDeletions = 0;
        final char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if(chars[i] == chars[i -1]){
                minDeletions += 1;
            }
        }
        return minDeletions;
    }
}
