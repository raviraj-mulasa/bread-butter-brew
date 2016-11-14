package net.geekscore.algo.divideNconquer;

import java.math.BigDecimal;

/**
 * Created by ravirajmulasa on 9/2/16.
 */
public final class PowerOfNumber {

    public static final BigDecimal power(final double base, int exponent) {

        BigDecimal numberPoweredValue = BigDecimal.valueOf(1);
        if(exponent == 0) {
            return numberPoweredValue;
        }
        int x = exponent % 2;
        if(exponent % 2 == 0) {
            numberPoweredValue = numberPoweredValue.multiply(power(base, exponent/2));
        } else {
            numberPoweredValue =  numberPoweredValue.multiply(power(base, (exponent-1)/2).multiply(power(base, (exponent-1)/2).multiply(BigDecimal.valueOf(base))));
        }
        return numberPoweredValue;
    }


    public static void main(String args[]) {
        System.out.println(power(2,0));
        System.out.println(power(2.0, 3));
        System.out.println(power(2.2, 8));
    }
}
