package net.geekscore.recursion;

/**
 * Created by ravirajmulasa on 6/10/17.
 *
 * Given a non-negative int n, return the sum of its digits recursively (no loops). Note that mod (%) by 10 yields the rightmost digit (126 % 10 is 6), while divide (/) by 10 removes the rightmost digit (126 / 10 is 12).
 *  sumDigits(126) → 9
 *  sumDigits(49) → 13
 *  sumDigits(12) → 3
 */
public class SumDigits {

    private static final boolean compareInt(Integer num2Compare, Integer num) {
        return Integer.compare(num, num2Compare) == 0;
    }


    public int sumDigits(int n) {
        if(n < 10 ){
            return n;
        }
        return sumDigits((n % 10) + sumDigits(n/10));
    }




    /**
     *
     * @param n
     * @param k
     * @return
     *
     * super_digit(9875) = super_digit(9+8+7+5)
     * = super_digit(29)
     * = super_digit(2+9)
     * = super_digit(11)
     * = super_digit(1+1)
     * = super_digit(2)
     * = 2.
     *
     * Recursive Digit Sum : https://www.hackerrank.com/challenges/recursive-digit-sum
     * super_digit(P) = super_digit(148148148)
     * = super_digit(1+4+8+1+4+8+1+4+8)
     * = super_digit(39)
     * = super_digit(3+9)
     * = super_digit(12)
     * = super_digit(1+2)
     * = super_digit(3)
     * = 3.
     */

    public int recSumDigits(int n, int k) {
        StringBuffer pStr = new StringBuffer();
        for (int i = 0; i < k; i++) {
            pStr.append(String.valueOf(n));
        }
        final int p = k > 0 ? Integer.valueOf(pStr.toString().trim()) : n;
        return recSumDigitsHelper(p);
    }

    private int recSumDigitsHelper(int n) {
        if(n < 10){
            return n;
        }
        int digitSum = 0;
        for(int i = n ; i > 0; i = i/10) {
            digitSum += (i % 10);
        }
        return recSumDigitsHelper(digitSum);
    }


    public int count7s(int n) {
        if( n <= 0) return 0;
        int currDigit =  n%10 ;
        if(compareInt(currDigit, 7)){
            return 1 + count7s(n/10);
        }
        return count7s(n/10);
    }


    /**
     *
     * @param n
     * @return
     *
     * Given a non-negative int n, compute recursively (no loops) the count of the occurrences of 8 as a digit,
     * except that an 8 with another 8 immediately to its left counts double, so 8818 yields 4.
     * Note that mod (%) by 10 yields the rightmost digit (126 % 10 is 6), while divide (/) by 10 removes the rightmost digit (126 / 10 is 12).
     * count8(8) → 1
     * count8(818) → 2
     * count8(8818) → 4
     */
    public int count8s(int n) {
        return count8sHelper(n, 0);
    }

    private int count8sHelper(int n, int prevDigit) {
        if( n <= 0) return 0;
        int currDigit =  n%10;
        if(compareInt(currDigit, 8) && 8 == prevDigit){
            return 2 + count8sHelper(n/10, currDigit);
        }
        if(compareInt(currDigit, 8) && 8 != prevDigit){
            return 1 + count8sHelper(n/10, currDigit);
        }
        return count8sHelper(n/10, currDigit);
    }



    /*
    *
    *  Given base and n that are both 1 or more, compute recursively (no loops) the value of base to the n power, so powerN(3, 2) is 9 (3 squared).
    *  powerN(3, 1) → 3
    *  powerN(3, 2) → 9
    *  powerN(3, 3) → 27
    */
    public int power(int base, int n) {
       if(base == 0) return 0;
       if (n == 0 ) return 1;
       return  base * power(base, n-1);
    }

    /**
     *
     *
     *   Given a string, compute recursively (no loops) the number of lowercase 'x' chars in the string.
     *   countX("xxhixx") → 4
     *   countX("xhixhix") → 3
     *   countX("hi") → 0
     *
     * @param str
     * @return
     */
    public int countX(String str) {
        return countHelper(str, "x");
    }

    private int countHelper(String str, String subStr) {
        if(str.length() == 0) return 0;
        if (str.startsWith(subStr)) return 1 + countHelper(str.substring(subStr.length(), str.length()), subStr);
        return countHelper(str.substring(1, str.length()), subStr);
    }

    /**
     * Given a string, compute recursively (no loops) the number of times lowercase "hi" appears in the string.
     * countHi("xxhixx") → 1
     * countHi("xhixhix") → 2
     * countHi("hi") → 1
     */
    public int countHi(String str) {
        return countHelper(str, "hi");
    }

//
//    Given a string, compute recursively (no loops) a new string where all the lowercase 'x' chars have been changed to 'y' chars.
//
//            changeXY("codex") → "codey"
//    changeXY("xxhixx") → "yyhiyy"
//    changeXY("xhixhix") → "yhiyhiy"
//
//    changeXY



//    Given a string and a non-empty substring sub, compute recursively the number of times that sub appears in the string, without the sub strings overlapping.
//
//    strCount("catcowcat", "cat") → 2
//    strCount("catcowcat", "cow") → 1
//    strCount("catcowcat", "dog") → 0
//
//    public int strCount()
}
