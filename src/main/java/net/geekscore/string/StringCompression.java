package net.geekscore.string;

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

}

