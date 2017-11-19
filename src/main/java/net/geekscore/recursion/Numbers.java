package net.geekscore.recursion;

public class Numbers {

    public static void main(String[] args) {
        convert2Binary(43);
        System.out.println();
        convert2Binary(-500);
        System.out.println();

        generateNumbers(2, 3);  // Binary Numbers
        generateNumbers(10, 2); // Decimal numbers
    }

    private static final void convert2Binary(int n) {
        if(n < 0){
            System.out.print("-");
            convert2Binary(-n);
        } else if ( n < 2){
            System.out.print(n);
        } else {
            convert2Binary(n /2);
            System.out.print(n % 2);
        }

    }

    private static final void generateNumbers(final int base, int digits) {
        generateNumbersHelper(base, digits, "");
    }


    private static final void generateNumbersHelper(final int base, int digits, String generatedSoFar) {
//        System.out.println("generatedSoFar("+base+","+digits+","+generatedSoFar+")");
        if(digits == 0){
            System.out.println(generatedSoFar);
        } else {
            for (int i = 0; i < base; i++) {
                generateNumbersHelper(base, digits - 1, generatedSoFar + i);
            }
        }

    }
}
