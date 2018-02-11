package net.geekscore.algo.divideNconquer;

public class Power {

    public static void main(String[] args) {
        System.out.println(pow(0,0)); // 1
        System.out.println(pow(1,0)); // 1
        System.out.println(pow(-1,2)); // 1
        System.out.println(pow(-1,3)); // -1
        System.out.println(pow(1,-1)); // 1
        System.out.println(pow(2,-2)); // .25
        System.out.println(pow(2,-3)); // .125
        System.out.println(pow(2,3)); // 8
        System.out.println(pow(-2,3)); // -8
        System.out.println(pow(-2,4)); // 16
    }

    private static double pow(double x, int n) {
            if(n == 0) return 1.0;
            if(n == 1) return x;
            final boolean isNEven = n % 2 == 0;
            if(Double.compare(x, -1.0) == 0) return isNEven ? 1.0 : -1.0;
            if(Double.compare(x, 1.0) == 0) return 1.0;
            if(n == Integer.MIN_VALUE) return 0.0;
            if(n < 0) {
                n = Math.abs(n);
                x = 1/x;
            }
            final double half = pow(x, n/2);
            return isNEven ? half * half : half * half * x;
    }
}
