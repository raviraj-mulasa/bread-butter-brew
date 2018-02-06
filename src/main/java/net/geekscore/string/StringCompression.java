package net.geekscore.string;

import java.util.Arrays;

public class StringCompression {

    public static void main(String[] args) {


        System.out.println(compressString(new char[]{})); // 0
        System.out.println(compressString(new char[]{'a','a','b','b','c','c','c'})); // 6 - "a2c2c3"
        System.out.println(compressString(new char[]{'a'})); // 1 - "a"
        System.out.println(compressString(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'})); // 4 - "ab12"

        System.out.println("--------------------");

        System.out.println(compress(new char[]{})); // 0
        System.out.println(compress(new char[]{'a','a','b','b','c','c','c'})); // 6 - "a2c2c3"
        System.out.println(compress(new char[]{'a'})); // 1 - "a"
        System.out.println(compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'})); // 4 - "ab12"


        System.out.println("--------------------");

        System.out.println(Arrays.toString(compressStringInPlace(new char[]{}))); // 0
        System.out.println(Arrays.toString(compressStringInPlace(new char[]{'a','a','b','b','c','c','c'}))); // 6 - "a2c2c3"
        System.out.println(Arrays.toString(compressStringInPlace(new char[]{'a'}))); // 1 - "a"
        System.out.println(Arrays.toString(compressStringInPlace(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'}))); // 4 - "ab12"
        System.out.println(Arrays.toString(compressStringInPlace(new char[]{'b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}))); // "b26"


        System.out.println("--------------------");

        System.out.println(compressStringInPlaceLength(new char[]{})); // 0
        System.out.println(compressStringInPlaceLength(new char[]{'a','a','b','b','c','c','c'})); // 6 - "a2c2c3"
        System.out.println(compressStringInPlaceLength(new char[]{'a'})); // 1 - "a"
        System.out.println(compressStringInPlaceLength(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'})); // 4 - "ab12"
        System.out.println(compressStringInPlaceLength(new char[]{'b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'})); // 3 - "b26"

    }

    private static int compress(final char[] chars) {
        if(chars == null) return 0;
        int count = 1, length = 0;
        for (int i = 1; i < chars.length+1; i++) {
            if(i == chars.length || chars[i-1] != chars[i]) {
                final StringBuilder compressStrBuilder = new StringBuilder();
                compressStrBuilder.append(chars[i-1]);
                if (count > 1) compressStrBuilder.append(count);
                length += compressStrBuilder.toString().length();
                count=1;
            } else count++;
        }
        return length;
    }

    private static String compressString(final char[] chars) {
        if(chars == null) return "";
        int count = 1;
        final StringBuilder compressStrBuilder = new StringBuilder();
        for (int i = 1; i < chars.length+1; i++) {
            if(i == chars.length || chars[i-1] != chars[i]) {
                compressStrBuilder.append(chars[i-1]);
                if (count > 1) compressStrBuilder.append(count);
                count=1;
            } else count++;
        }
        return compressStrBuilder.toString();
    }

    private static char[] compressStringInPlace(final char[] chars) {
        if(null == chars || chars.length == 0) return chars;
        int length = 0;
        int count = 1;
        for (int i = 1; i <= chars.length; i++) {
            if(i == chars.length || chars[i-1] != chars[i]) {
                length += 1;
                if (count > 1) {
                    length += String.valueOf(count).length();
                    int k = count-1;
                    while (count > 10 && k > 1) {
                        chars[i-k] = (char)((count / Math.pow(10, String.valueOf(count).length() - 1)) +'0');
                        count = count % 10;
                        k--;
                    }
                    chars[i-k] = (char)(count +'0');
                }
                count=1;
            } else count++;
        }
        return Arrays.copyOfRange(chars, 0, length);
    }

    private static int compressStringInPlaceLength(final char[] chars) {
        if(null == chars || chars.length == 0) return 0;
        int length = 0;
        int count = 1;
        for (int i = 1; i <= chars.length; i++) {
            if(i == chars.length || chars[i-1] != chars[i]) {
                length += 1;
                if (count > 1) {
                    length += String.valueOf(count).length();
                }
                count=1;
            } else count++;
        }
        return length;
    }

}

