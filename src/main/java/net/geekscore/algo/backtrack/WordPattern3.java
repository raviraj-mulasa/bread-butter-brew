package net.geekscore.algo.backtrack;

import java.util.HashMap;
import java.util.Map;

/**
 * todo
 *
 * Given a string, find out if string follows a given pattern or not
 * without using any regular expressions.
 *
 *
 * Input:
 *  string - GraphTreesGraph
 *  pattern - aba
 * Output:
 *  a->Graph
 *  b->Trees
 *
 * Input:
 *  string - airairair
 *  pattern - ccc
 * Output:
 *  c->air
 *
 *  Input:
 *  string - airbnbairbn
 *  pattern - xyzxy
 * Output:
 *  x->airb
 *  y->n
 *  z->b
 */
public class WordPattern3 {

    public static void main(String[] args) {
        patterns("ababa", "xxy");
        patterns("ababa", "xyy");
    }

    private static void patterns(final String str, final String pattern) {
        if(null == str ||  null == pattern) {
            return;
        }
        final Map<String, String> patternsMap = new HashMap<>();
        patternsHelper(str, pattern, patternsMap);


    }

    private static void patternsHelper(String str,String pattern, Map<String, String> patternsMap) {
//        System.out.println("patternsHelper("+str+","+pattern+","+patternsMap+")");
//        if(str.length() == 0 || pattern.length() == 0){
//            System.out.println(patternsMap);
//            return;
//        }
//        for (int i = 0; i < pattern.length(); i++) {
//            final String patternKey =  String.valueOf(pattern.charAt(i));
//            String patternVal       = patternsMap.get(patternKey);
//            if(patternVal == null) {
//                patternVal = str.substring(0, Math.min(maxPatternMatchLength + 1, str.length()));
//            }
//            patternsMap.put(patternKey, patternVal);
//            patternsHelper(str.substring(patternVal.length()), pattern.substring(i + 1), patternsMap);
//            patternsMap.remove(patternKey);
//        }
    }



}
