package net.geekscore.math;

import java.util.BitSet;

/**
 * Description: Count the number of prime numbers less than a non-negative number, n.
 */
public class CountPrimes {

    public static void main(String[] args) {
        System.out.println(countPrimes(31)); // 10
        System.out.println(countPrimes(11)); // 4
        System.out.println(countPrimes(121)); // 30
        System.out.println(countPrimes(0)); // 0
        System.out.println(countPrimes(1)); // 0
        System.out.println(countPrimes(2)); // 0
        System.out.println(countPrimes(3)); // 1


        System.out.println("--------------------");
        System.out.println(sieveOfEratosthenes(31)); // 10
        System.out.println(sieveOfEratosthenes(11)); // 4
        System.out.println(sieveOfEratosthenes(121)); // 30
        System.out.println(sieveOfEratosthenes(0)); // 0
        System.out.println(sieveOfEratosthenes(1)); // 0
        System.out.println(sieveOfEratosthenes(2)); // 0
        System.out.println(sieveOfEratosthenes(3)); // 1

        System.out.println("--------------------");
        System.out.println(sieveOfEratosthenesEfficient(31)); // 10
        System.out.println(sieveOfEratosthenesEfficient(11)); // 4
        System.out.println(sieveOfEratosthenesEfficient(121)); // 30
        System.out.println(sieveOfEratosthenesEfficient(0)); // 0
        System.out.println(sieveOfEratosthenesEfficient(1)); // 0
        System.out.println(sieveOfEratosthenesEfficient(2)); // 0
        System.out.println(sieveOfEratosthenesEfficient(3)); // 1

    }

    // O(n1.5)
    private static final int countPrimes(int n) {
        if(n < 2) return 0;
        int count = 0;
        for (int i = 2; i < n ; i++) {
            if (isPrimeEfficient(i)) {
                count++;
            }
        }
        return count;
    }

    private static final boolean  isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if(n % i == 0) return false;
        }
        return Boolean.TRUE;
    }

    private static final boolean  isPrimeEfficient(int n) {
        for (int i = 2; (i * i) <= n; i++) { // Factor of n cannot be > sqrt(n) except for itself
            if(n % i == 0) return false;
        }
        return Boolean.TRUE;
    }

    /**
     *
     * @param n
     * @return Number of primes less than n using  sieve of Eratosthenes
     *
     * The Sieve of Eratosthenes uses an extra O(n) memory and its runtime complexity is O(n log log n).
     */
    private static final int sieveOfEratosthenes(int n){
        if(n <= 2) return 0;
        final BitSet isPrime = new BitSet(n);
        isPrime.set(2, n, Boolean.TRUE); // remove 0 & 1 from bit set
        for (int i = 2; (i * i) < n; i++) {
            for (int j = (i * i); j < n; j+=i) {
                isPrime.set(j, Boolean.FALSE);
            }
        }
        return isPrime.cardinality();
    }

    private static final int sieveOfEratosthenesEfficient(int n){
        if(n <= 2) return 0;
        final BitSet isComposite = new BitSet(n);
        int primes = n/2 ; // we assume half the numbers (odds) are primes, even cannot be prime except for 2.
        for (int i = 3; (i * i) < n; i+=2) { // Start with an odd prime-candidate above 2, increment by two
            if(isComposite.get(i)){ // we already counted this composite number
                continue;
            }
            for (int j = (i * i); j < n; j+= (2 * i)) {
//                i * i + a*i is composite when i is composite since they share a common factor of i.
                if(!isComposite.get(j)){
                    isComposite.set(j, Boolean.TRUE); // mark the number as composite
                    primes--;
                }
            }
        }

//        printPrimes(n, isComposite);
        return primes;
    }

    private static void printPrimes(int n, BitSet isComposite) {
        System.out.println("Prime number: 2");
        for (int i = 3; i < n; i+=2) {
            if(!isComposite.get(i)) {
                System.out.println("Prime number: "+ i);
            }
        }
    }
}
