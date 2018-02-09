package net.geekscore.math;

public class BaseConversion {

    public static void main(String[] args) {

        System.out.println(convertBase("ff" , 16, 10)); // 255
        System.out.println(convertBase("255" , 10, 16)); // ff
        System.out.println(convertBase("+255" , 10, 16)); // ff

        System.out.println(convertBase("-ff" , 16, 10)); // -255
        System.out.println(convertBase("-255" , 10, 16)); // -ff


        System.out.println(convertBase("101" , 2, 10)); // 5
        System.out.println(convertBase("-101" , 2, 10)); // -5


        System.out.println(convertBase("5" , 10, 2)); // 101
        System.out.println(convertBase("-5" , 10, 2)); // -101

        System.out.println(convertBase("-101" , 10, 2)); // -1100101
    }

    private static String convertBase(final String strInBase1, int base1, int base2) {
        if(strInBase1 == null || strInBase1.length() == 0 || base1 < 2 || base2 > 16)
            throw new IllegalArgumentException("Invalid arguments, please check once!");

        final char[] strInBase1InLower = strInBase1.toLowerCase().toCharArray();

        final boolean negative = strInBase1InLower[0] == '-';
        final boolean sign = strInBase1InLower[0] == '+' || negative;
        int numberInBase1 = 0;
        for (int i = (sign ? 1: 0); i < strInBase1InLower.length; i++) {
            numberInBase1 *= base1;
            final char ch = strInBase1InLower[i];
            numberInBase1 += (isDigit(ch) ? (ch - '0') : (ch - 'a' + 10));
        }

        final StringBuilder strInBase2 = new StringBuilder();
        while (numberInBase1 > 0) {
            final int remainder = numberInBase1 % base2;
            strInBase2.append((char)(remainder >= 10 ? (remainder + 'a' - 10) : (remainder + '0')));
            numberInBase1 /= base2;
        }

        if(strInBase2.length() == 0) return  "0";
        if(negative) strInBase2.append("-");
        return strInBase2.reverse().toString().trim();

    }

    private static boolean isDigit(final char ch) {
        return (ch-'0')>= 0 && (ch-'0')<= 9;
    }
}
