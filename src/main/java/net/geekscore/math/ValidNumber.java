package net.geekscore.math;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Validate if a given string is numeric.
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 */
public class ValidNumber {

    private static final Pattern VALID_NUMBER = Pattern.compile("[-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?");
    private static final Pattern VALID_NUMBER_1 = Pattern.compile("[-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?");

    public static void main(String[] args) {

        System.out.println("Valid Number Regex:  "+isNumberRegex("0")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex("-11")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex("0123")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex("-00")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex("-1.0")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex(" .1 ")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex("-.1 ")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex(" 0.1 ")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex(" -0.1 ")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex("2e10")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex("e10")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex("2e0")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex("2e+10")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex("0.2e-10")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex(" 005047e+6")); //true
        System.out.println("Valid Number Regex:  "+isNumberRegex("0e")); // true
        System.out.println("Valid Number Regex:  "+isNumberRegex(".e1")); // true

        System.out.println("Valid Number Regex:  "+isNumberRegex("e")); // false
        System.out.println("Valid Number Regex:  "+isNumberRegex("-")); // false
        System.out.println("Valid Number Regex:  "+isNumberRegex("+")); // false
        System.out.println("Valid Number Regex:  "+isNumberRegex(".")); // false
        System.out.println("Valid Number Regex:  "+isNumberRegex("+++")); // false
        System.out.println("Valid Number Regex:  "+isNumberRegex("+-")); // false
        System.out.println("Valid Number Regex:  "+isNumberRegex(" -.")); //false
        System.out.println("Valid Number Regex:  "+isNumberRegex("6+1")); // false
        System.out.println("Valid Number Regex:  "+isNumberRegex(".2e6+1")); // false
        System.out.println("Valid Number Regex:  "+isNumberRegex("..")); // false
        System.out.println("Valid Number Regex:  "+isNumberRegex(".1.")); // false
        System.out.println("Valid Number Regex:  "+isNumberRegex("4e+")); //false
        System.out.println("Valid Number Regex:  "+isNumberRegex("0.2e1.0")); // false
        System.out.println("Valid Number Regex:  "+isNumberRegex("0.2e-a")); // false
        System.out.println("Valid Number Regex:  "+isNumberRegex("abc")); // false
        System.out.println("Valid Number Regex:  "+isNumberRegex("1 a")); // false

        System.out.println("---------------------");


        System.out.println("Valid Number "+isNumber("0")); // true
        System.out.println("Valid Number "+isNumber("-11")); // true
        System.out.println("Valid Number "+isNumber("0123")); // true
        System.out.println("Valid Number "+isNumber("-00")); // true
        System.out.println("Valid Number "+isNumber("-1.0")); // true
        System.out.println("Valid Number "+isNumber(" .1 ")); // true
        System.out.println("Valid Number "+isNumber("-.1 ")); // true
        System.out.println("Valid Number "+isNumber(" 0.1 ")); // true
        System.out.println("Valid Number "+isNumber(" -0.1 ")); // true
        System.out.println("Valid Number "+isNumber("e10")); // true
        System.out.println("Valid Number "+isNumber("2e10")); // true
        System.out.println("Valid Number "+isNumber("2e0")); // true
        System.out.println("Valid Number "+isNumber("2e+10")); // true
        System.out.println("Valid Number "+isNumber("0.2e-10")); // true
        System.out.println("Valid Number "+isNumber(" 005047e+6")); //true
        System.out.println("Valid Number "+isNumber("89.e89")); // true
        System.out.println("Valid Number "+isNumber(".e1")); // true
        System.out.println("Valid Number "+isNumber("0e")); // false
        System.out.println("Valid Number "+isNumber("e")); // false
        System.out.println("Valid Number "+isNumber("-")); // false
        System.out.println("Valid Number "+isNumber("+")); // false
        System.out.println("Valid Number "+isNumber("+++")); // false
        System.out.println("Valid Number "+isNumber("6+1")); // false
        System.out.println("Valid Number "+isNumber(".2e6+1")); // false
        System.out.println("Valid Number "+isNumber("+-")); // false
        System.out.println("Valid Number "+isNumber(".")); // false
        System.out.println("Valid Number "+isNumber(" -.")); //false
        System.out.println("Valid Number "+isNumber("..")); // false
        System.out.println("Valid Number "+isNumber(".1.")); // false
        System.out.println("Valid Number "+isNumber("4e+")); //false
        System.out.println("Valid Number "+isNumber("0.2e1.0")); // false
        System.out.println("Valid Number "+isNumber("0.2e-a")); // false
        System.out.println("Valid Number "+isNumber("abc")); // false
        System.out.println("Valid Number "+isNumber("1 a")); // false
    }

    /**
     *
     * @param number
     * @return boolean (true or false)
     *
     * The following cases need to be handled in the code.
     *
     * Ignore the leading and trailing white spaces.
     * Ignore the ‘+’, ‘-‘ and’.’ at the start.
     * Ensure that the characters in the string belong to {+, -, ., e, [0-9]}
     * Ensure that no ‘.’ comes after ‘e’.
     * A dot character ‘.’ should be followed by a digit.
     * Signs should not be next to each other
     * The character ‘e’ should be followed either by ‘+’, ‘-‘, or a digit.
     *
     *
     */
    private static boolean isNumber(String number) {
        if(number == null || number.trim().length() <= 0 ) return false;
        number = number.trim();
        System.out.print("Number "+number+"("+number.length()+")\t");

        boolean seenPeriod = false;
        boolean seenE = false;
        boolean seenDigit = false;

        for (int i = 0; i < number.length(); i++) {
            final char ch = number.charAt(i);
            switch (ch) {
                case '.':
                    // Ensure that no ‘.’ comes after ‘e’.
                    // Ensure that dot '.' never comes twice.
                    if(seenPeriod || seenE) return false;
                    seenPeriod = true;
                    // A dot character ‘.’ should be followed by a digit or e.
                    if(i+1 < number.length() && !isDigit(number.charAt(i+1)) && !isE(number.charAt(i+1))) {
                        return false;
                    }
                    break;
                case 'e':
                    // The character ‘e’ should be seen once.
                    // Digit should be seen before e
                    if (!seenDigit || seenE) return false;
                    seenE = true;
                    // The character ‘e’ should be followed either by ‘+’, ‘-‘, or a digit.
                    // e is NOT the last character
                    if(i+1 >= number.length() || (!isDigit(number.charAt(i+1)) && !isSign(number.charAt(i+1)))) {
                        return false;
                    }
                    break;
                case '+':
                case '-':
                    // Sign NOT after another sign
                    // Sign is NOT the last character
                    if(i+1 >= number.length() || isSign(number.charAt(i+1))) return false;
                    break;
                default:
                    if(!isDigit(ch)) return false; //Ensure that the characters in the string belong to {+, -, ., e, [0-9]}
                    seenDigit = true;
                    // Sign NOT after a digit
                    if(i+1 < number.length() && isSign(number.charAt(i+1))) return false;
                    break;
            }
        }
        return seenDigit;

    }

    private static boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    private static boolean isE(char ch) {
        return ch=='e';
    }

    private static boolean isSign(char ch) {
        return ch=='-' || ch=='+';
    }

    private static boolean isNumberRegex(String number) {
        number = number.trim();
        System.out.print("Number "+number+"("+number.length()+")\t");
        final Matcher matcher = VALID_NUMBER.matcher(number);
        return matcher.matches();
    }
}
