package net.geekscore.strings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.stream.Stream;

/**
 * Created by ravirajmulasa on 8/12/17.
 *
 *  A pangram (Greek: παν γράμμα, pan gramma, "every letter") or holoalphabetic sentence is a sentence using every letter
 *  of a given alphabet at least once.
 */
public class Pangram {

    private static final Stream<String> readFromStdIn() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        return in.lines().limit(1);
    }

    public static void main(String[] args) {
        Stream<String> testCases = readFromStdIn();
        testCases.forEach(
                testCase -> {
                    if(isPangram(testCase)) {
                        System.out.println("pangram");
                    } else {
                        System.out.println("not pangram");
                    }

                }
        );
    }


    private static final boolean isPangram(final String str) {
        if(null == str || str.length() == 0){
            return Boolean.FALSE;
        }
        final int asciiOfA          = (int) 'A';
        final BitSet alphabetBitSet = new BitSet(26);
        for(char charInStr: str.trim().toUpperCase().toCharArray()) {
            if(charInStr == ' '){
                continue;
            }
            alphabetBitSet.set(((int) charInStr) % asciiOfA);
        }
        return alphabetBitSet.cardinality() == 26;
    }
}
