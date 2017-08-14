package net.geekscore.problems;

/**
 * Created by ravirajmulasa on 8/13/17.
 */
public class Integer2String {

    public static void main(String[] args) throws Exception{

        System.out.println(integer2String(-1234567890));
        System.out.println(integer2String(1234567890));
        System.out.println(integer2String(245840598));
        System.out.println(integer2String(-245840598));
        System.out.println(string2Integer("-1234567890"));
        System.out.println(string2Integer("1234567890"));
        System.out.println(string2Integer("245840598"));
        System.out.println(string2Integer("-245840598"));
        System.out.println(string2Integer("-1234567.90"));
    }

    private static final String integer2String(final int val) {
        final boolean negative = val < 0;
        final StringBuffer intStringBuffer = new StringBuffer();
        int effectiveVal = val;
        if(negative){
            effectiveVal = -val;
        }
        while (effectiveVal > 0){
            final int valueAtDigit = effectiveVal % 10;
            intStringBuffer.append(valueAtDigit);
            effectiveVal /= 10;
        }
        if(negative){
            intStringBuffer.append("-");
        }
        return intStringBuffer.reverse().toString().trim();
    }


    private static final Integer string2Integer(final String intStr) throws Exception {
        final char[] intStrCharArray = intStr.toCharArray();
        final boolean negative  = intStrCharArray[0] == '-';
        int startIndex          = negative ? 1 : 0;
        int value               = 0;
        for (int i = startIndex; i < intStrCharArray.length; i++) {
            if(isDigit(intStrCharArray[i])){
                value = value * 10 + (intStrCharArray[i] % (int)'0');
            }else {
                throw new Exception("Illegal Number Exception");
            }
        }
        return negative ? -value : value;
    }

    private static final boolean isDigit(char val) {
        final int intCharVal = (int) val;
        return (48 <= intCharVal && intCharVal <=  57);
    }
}
