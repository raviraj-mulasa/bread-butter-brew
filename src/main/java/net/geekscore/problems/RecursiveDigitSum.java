package net.geekscore.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ravirajmulasa on 8/12/17.
 */
public class RecursiveDigitSum {

    public static void main(String[] args)  {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        final String input = readFromStdIn();
        if(input != null && input.length() > 0) {
            final String[] nK = getN_K(input);
            System.out.println(superDigitSum(nK[0], Long.valueOf(nK[1])));
        }
    }

    private static final String readFromStdIn() {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            input = in.readLine();
        }catch(IOException e) {
            input =  "";
        }
        return input;
    }

    private static final Long superDigitSum(final String n, final Long k) {
        Long digitSum = digitSum(n) * k;
        if(digitSum.compareTo(10L) >= 0) {
            digitSum = superDigitSum(digitSum.toString(), 1L);
        }
        return digitSum;
    }


    private static final Long digitSum(final String p) {
        if(p.length() == 1 && isNumber(p)) {
            return Long.valueOf(p);
        }
        Long digitSum                   = 0L;
        StringBuffer digitBuffer        = new StringBuffer();
        boolean stillReadingNegative    = false;
        for (final char digitChar: p.toCharArray()) {
            if(digitChar == '.') {
                continue;
            }
            digitBuffer.append(digitChar);
            if(digitChar == '-') {
                stillReadingNegative = true;
                continue;
            }
            if(digitChar == '0' && stillReadingNegative) {
                continue;
            }
            final String digit = digitBuffer.toString().trim();
            if(isNumber(digit)) {
                digitSum += Long.valueOf(digit);
            }
            digitBuffer.setLength(0);
            stillReadingNegative = false;
        }
        return digitSum;
    }



    private static final String[] getN_K(String input) {
        final String[] n_K          = new String[2];
        final String[] numberSplit  = input.split(" ", 2);
        String n                    = input;
        Long k                      = 0L;

        if(numberSplit!= null && numberSplit.length >= 2) {
            n = numberSplit[0];
            if(isNumber(numberSplit[1])) {
                k = Long.valueOf(numberSplit[1]);
            }
        }
        n_K[0] = n;
        n_K[1] = k.toString();
        if(k.compareTo(0L) <=0){
            n_K[1] = "0";
        }
        if((isNumber(n) && Long.valueOf(n).compareTo(0L) == 0)) {
            n_K[0] = "0";
        }
        return n_K;
    }



    private static final boolean isNumber(final String numStr) {
        boolean isNumber = false;
        try {
            long number = Long.valueOf(numStr);
            isNumber = true;
        } catch (NumberFormatException n) {
            n.printStackTrace();
            isNumber = false;
        }
        return isNumber;
    }
}