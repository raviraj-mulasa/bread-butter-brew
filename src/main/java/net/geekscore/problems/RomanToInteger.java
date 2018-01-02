package net.geekscore.problems;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    private static final Map<Character, Integer> ROMANS = new HashMap() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public static void main(String[] args) {
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("XL"));
        System.out.println(romanToInt("MCMIV"));
        System.out.println(romanToInt("DCXXI"));
        System.out.println(romanToInt("V"));
        System.out.println(romanToInt("CMXI"));
        System.out.println(romanToInt("MCMLX"));

        System.out.println("------------");

        System.out.println(romanToInt2("IX"));
        System.out.println(romanToInt2("XL"));
        System.out.println(romanToInt2("MCMIV"));
        System.out.println(romanToInt2("DCXXI"));
        System.out.println(romanToInt2("V"));
        System.out.println(romanToInt2("CMXI"));
        System.out.println(romanToInt2("MCMLX"));
    }


    private static final int romanToInt(String s) {

        if(null == s || s.length() == 0) {
            return 0;
        }
        int val = 0;
        for(final char romanChar: s.toCharArray()) {
            val += ROMANS.get(Character.valueOf(romanChar)).intValue();
        }
        if(s.indexOf("IV") != -1) {
            val -= 2;
        }
        if(s.indexOf("IX") != -1) {
            val -= 2;
        }
        if(s.indexOf("XL") != -1) {
            val -= 20;
        }
        if(s.indexOf("XC") != -1) {
            val -= 20;
        }
        if(s.indexOf("CD") != -1) {
            val -= 200;
        }
        if(s.indexOf("CM") != -1) {
            val -= 200;
        }
        return val;
    }

    
    private static final int romanToInt2(String s) {
        if(null == s || s.length() == 0) {
            return 0;
        }
        Integer val = 0;
        Integer prevVal = 0;
        for (int i = s.length() - 1; i >= 0 ; i--) {
            final Character curr = Character.valueOf(s.charAt(i));
            final Integer currVal= ROMANS.get(curr);
            if(currVal.compareTo(prevVal) < 0){
                val -= currVal;
            } else {
                val += currVal;
            }
            prevVal = currVal;
        }
        return val.intValue();
    }
}
