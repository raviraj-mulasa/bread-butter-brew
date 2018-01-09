package net.geekscore.math;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorsOfNumber {


    public static void main(String[] args) {
        System.out.println(primeFactors(2));
        System.out.println(primeFactors(3));
        System.out.println(primeFactors(5));
        System.out.println(primeFactors(6));
        System.out.println(primeFactors(7));
        System.out.println(primeFactors(9));
        System.out.println(primeFactors(12));
        System.out.println(primeFactors(441));
        System.out.println(primeFactors(315));
        System.out.println(primeFactors(121));
        System.out.println(primeFactors(12246));

        System.out.println("-------------------");

        System.out.println(primeFactorsEfficient(2));
        System.out.println(primeFactorsEfficient(3));
        System.out.println(primeFactorsEfficient(5));
        System.out.println(primeFactorsEfficient(6));
        System.out.println(primeFactorsEfficient(7));
        System.out.println(primeFactorsEfficient(9));
        System.out.println(primeFactorsEfficient(12));
        System.out.println(primeFactorsEfficient(441));
        System.out.println(primeFactorsEfficient(315));
        System.out.println(primeFactorsEfficient(121));
        System.out.println(primeFactorsEfficient(12246));
    }


    private static final List<Integer> primeFactors(int x) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; (i * i) <= x ; i++) { // Factor of n cannot be > sqrt(n) except for itself
            while (x % i == 0){
                factors.add(i);
                x = x / i;
            }
        }
        if(x >= 2){
            factors.add(x);
        }
        return factors;
    }

    private static final List<Integer> primeFactorsEfficient(int x) {
        List<Integer> factors = new ArrayList<>();
        while (x%2 == 0){ // First check with 2
            factors.add(2);
            x = x/2;
        }
        for (int i = 3; (i * i) <= x ; i+=2) { // Then check with odd number starting at 3
            while (x % i == 0){
                System.out.println(" i "+i);
                factors.add(i);
                x = x / i;
            }
        }
        if(x > 2){
            factors.add(x);
        }
        return factors;
    }
}
