package net.geekscore.search;

import java.security.SecureRandom;
import java.util.Random;

public class GuessNumberHigherLower {

    private static class GuessGame {
        protected final int limit;
        private final Random random;
        private final int number;

        private GuessGame(int n){
            this.limit = n;
            this.random = new SecureRandom();
            this.number = this.random.nextInt(n-1+1)+1;
            System.out.println("Guessed Number "+this.number);
        }
        protected int guess(int num) {
            return num-this.number;
        }
    }

    private static class GuessNumber extends  GuessGame   {

        private GuessNumber(int n) {
            super(n);
        }
        private int guessNumber() {
            int low = 1;
            int high = this.limit;
            while (low <= high) {
                final int mid = low+(high-low)/2;
                if(this.guess(mid) < 0) low = mid+1;
                else high = mid-1;
            }
            return low;
        }
    }

    public static void main(String[] args) {
        final GuessNumber guessNumber = new GuessNumber(10);
        System.out.println(guessNumber.guessNumber());
    }

}
