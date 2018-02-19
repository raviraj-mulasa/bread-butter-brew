package net.geekscore.algo.backtrack;

import java.util.*;

/**
 *
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 * 0	none (on some telephones, "OPERATOR" or "OPER")
 * 1	none (on some older telephones, QZ)
 * 2	ABC
 * 3	DEF
 * 4	GHI
 * 5	JKL
 * 6	MNO
 * 7	PQRS (on older telephones, PRS)
 * 8	TUV
 * 9	WXYZ (on older telephones, WXY)
 *
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinationsPhoneNumber {

    private static final Map<Character, List<Character>> TELEPHONE_BUTTONS =
            Collections.unmodifiableMap(new HashMap<Character, List<Character>>() {
        {
            put('0', Collections.emptyList());
            put('1', Collections.emptyList());
            put('2', Arrays.asList('A', 'B', 'C'));
            put('3', Arrays.asList('D', 'E', 'F'));
            put('4', Arrays.asList('G', 'H', 'I'));
            put('5', Arrays.asList('J', 'K', 'L'));
            put('6', Arrays.asList('M', 'N', 'O'));
            put('7', Arrays.asList('P', 'Q', 'R', 'S'));
            put('8', Arrays.asList('T', 'U', 'V'));
            put('9', Arrays.asList('W', 'X', 'Y', 'Z'));
        }
    });

    public static void main(String[] args) {
        System.out.println(letterCombinations("75992255"));
        System.out.println(letterCombinations("23"));
    }

    private static List<String> letterCombinations(final String digits) {
        if(null == digits || digits.length() == 0) return Collections.emptyList();
        final List<String> combinations = new LinkedList<>();
        letterCombinationsHelper(digits, 0, combinations, new StringBuilder());
        return combinations;
    }

    private static void letterCombinationsHelper(final String digits, int digitIdx, final List<String> combinations, StringBuilder combinationSoFar) {
        if(combinationSoFar.length() == digits.length()) {
            combinations.add(combinationSoFar.toString().toLowerCase().trim());
            return;
        }
        if(digitIdx >= digits.length()) return;
        final List<Character> mappings = TELEPHONE_BUTTONS.get(digits.charAt(digitIdx));
        for (final Character mappedChar: mappings) {
            // Choose
            combinationSoFar.append(mappedChar);
            // Explore
            letterCombinationsHelper(digits, digitIdx+1, combinations, combinationSoFar);
            // Un choose
            combinationSoFar.deleteCharAt(combinationSoFar.length() - 1);
        }
    }
}
