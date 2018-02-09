package net.geekscore.problems;

public class Int2String {

    public static void main(String[] args) {
        System.out.println(int2Str(1234)); // 1234
        System.out.println(int2Str(0)); // 0
        System.out.println(int2Str(+1)); // 1
        System.out.println(int2Str(Integer.MAX_VALUE)); // 2147483647
        System.out.println(int2Str(Integer.MIN_VALUE)); // -2147483648
        System.out.println(int2Str(2147483647)); // 2147483647
        System.out.println(int2Str(-2147483647)); // -2147483647
    }

    private static String int2Str(int number) {
        final StringBuilder intStringBuilder  = new StringBuilder();

        final boolean negative = number < 0;
        number = negative ? -number : number;

        while (number > 0) {
            final int remainder = number % 10;
            intStringBuilder.append((char)(remainder+'0'));
            number /= 10;
        }

        if(intStringBuilder.length() == 0) return "0";
        if(negative) intStringBuilder.append('-');
        return intStringBuilder.reverse().toString();
    }
}
