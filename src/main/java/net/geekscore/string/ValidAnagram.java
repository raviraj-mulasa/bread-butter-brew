package net.geekscore.string;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * An anagram is word or phrase formed by rearranging the letters of a different word or phrase, typically using all
 * the original letters exactly once.[1] For example, the word anagram can be rearranged into "nag a ram".
 *
 * "William Shakespeare" = "I am a weakish speller"
 * "Madam Curie" = "Radium came"
 * "rail safety" = "fairy tales"
 * "silent" = "listen"
 *
 * Given two string s and t, write a function to determine if t is an anagram of s
 * For example,
 *  s = "anagram", t = "nagaram", return true.
 *  s = "rat", t = "car", return false
 *
 */
public class ValidAnagram {

    public static void main(String[] args) {
        System.out.println(isAnagram("Madam Curie" ,"Radium came"));
        System.out.println(isAnagram("rail safety" ,"fairy tales"));
        System.out.println(isAnagram("silent" ,"listen"));
        System.out.println(isAnagram("rat" ,"car"));
        System.out.println(isAnagram("anagram" ,"nagaram"));
        System.out.println(isAnagram("aacc", "ccac"));
        System.out.println(isAnagram("漢字会意字", "会意字漢字"));
        System.out.println(isAnagram("读写汉字 - 学中文", "学中文 - 读写汉字"));
        System.out.println(isAnagram("读写汉字 - 学中文", "学中文读写汉字"));
        System.out.println("---------------------");
        System.out.println(isAnagram1("Madam Curie" ,"Radium came"));
        System.out.println(isAnagram1("rail safety" ,"fairy tales"));
        System.out.println(isAnagram1("silent" ,"listen"));
        System.out.println(isAnagram1("rat" ,"car"));
        System.out.println(isAnagram1("anagram" ,"nagaram"));
        System.out.println(isAnagram1("aacc", "ccac"));
        System.out.println(isAnagram1("漢字会意字", "会意字漢字"));
        System.out.println(isAnagram1("读写汉字 - 学中文", "学中文 - 读写汉字"));
        System.out.println(isAnagram1("读写汉字 - 学中文", "学中文读写汉字"));
    }

    private static final boolean isAnagram(String s, String t) {
        if(s == null || t == null || s.length() != t.length() ) {
            return false;
        }
        s = s.toLowerCase();
        t = t.toLowerCase();
        final Map<Character, Integer> charMap4S = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            final Character charInStr = Character.valueOf(s.charAt(i));
            Integer count = charMap4S.get(charInStr);
            if(null == count) {
                count = 0;
            }
            charMap4S.put(charInStr, count + 1);
        }

        for (int j = 0; j < t.length(); j++) {
            final Character charInStr = Character.valueOf(t.charAt(j));
            if(charMap4S.containsKey(charInStr)) {
                Integer count = charMap4S.get(charInStr);
                if(count == 0) {
                    return false;
                }
                if(count > 0) {
                    charMap4S.put(charInStr, count - 1);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static final boolean isAnagram1(String s, String t) {
        if(s == null || t == null || s.length() != t.length() ) {
            return false;
        }
        final char[] sCharArr = s.toLowerCase().toCharArray();
        final char[] tCharArr = t.toLowerCase().toCharArray();
        Arrays.sort(sCharArr);
        Arrays.sort(tCharArr);
        return Arrays.equals(sCharArr, tCharArr);
    }
}
