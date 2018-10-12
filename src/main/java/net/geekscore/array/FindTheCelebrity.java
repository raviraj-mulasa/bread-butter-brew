package net.geekscore.array;

import java.time.Duration;
import java.time.Instant;

/**
 *  Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist
 *  one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but
 *  he/she does not know any of them.
 *
 *  Now you want to find out who the celebrity is or verify that there is not one. The only thing you are
 *  allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B.
 *  You need to find out the celebrity (or verify there is not one) by asking as few questions as possible
 *  (in the asymptotic sense).
 *
 *  You are given a helper function bool knows(a, b) which tells you whether A knows B.
 *  Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.
 *
 *  Note: There will be exactly one celebrity if he/she is in the party.
 *  Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 */
public class FindTheCelebrity {

    public static void main(String[] args) {

        final boolean[][] knowsMatrix = new boolean[][] {
                {false, true, true, false}
                ,{false, false, true, false}
                ,{false, false, false, false} // index 2 is celebrity
                ,{true, false, true, false}
        };
        final boolean[][] knowsMatrix1 = new boolean[][] {
                {false, true, true, false, true}
                ,{false, false, true, false, true}
                ,{false, false, false, false, true}
                ,{true, false, true, false, true}
                ,{false, false, false, false, false} // index 4 is celebrity
        };
        final boolean[][] knowsMatrix2 = new boolean[][] {
                {false, false}
                ,{false, false}
        };
        Instant start = Instant.now();
        System.out.println(findCelebrity(knowsMatrix)); // 3
        System.out.println(findCelebrity(knowsMatrix1)); // 5
        System.out.println(findCelebrity(knowsMatrix2)); // -1, no one knows the other person
        System.out.println("In nanos "+ Duration.between(start, Instant.now()).toNanos());

        System.out.println("----------------");

        start = Instant.now();
        System.out.println(findCelebrityEliminateVerify(knowsMatrix)); // 3
        System.out.println(findCelebrityEliminateVerify(knowsMatrix1)); // 5
        System.out.println(findCelebrityEliminateVerify(knowsMatrix2)); // -1, no one knows the other person
        System.out.println("In nanos "+ Duration.between(start, Instant.now()).toNanos());

    }

    private static int findCelebrity(final boolean[][] knowsMatrix) {
        int left = 1;
        int right = knowsMatrix.length;
        while (left < right) {
            if(knows(left, right, knowsMatrix)) {
                // left person is not celebrity
                left++;
            } else {
                // right knows left when left is a celebrity
                right--;
            }
        }
        // Verify if left is actually a celebrity or not
        for (int i = 1; i <= knowsMatrix.length; i++)  {
            if (i != left && (knows(left, i, knowsMatrix) || !knows(i, left, knowsMatrix))) return -1;
        }
        return left;
    }

    private static int findCelebrityEliminateVerify(final boolean[][] knowsMatrix) {
        int candidate = 1;
        for (int i = 2; i <= knowsMatrix.length; i++) {
            if(knows(candidate, i, knowsMatrix)) {
                candidate = i;
            }
        }
        // Verify if candidate is actually a celebrity or not
        for (int i = 1; i <= knowsMatrix.length; i++)  {
            if (i != candidate && (knows(candidate, i, knowsMatrix) || !knows(i, candidate, knowsMatrix))) return -1;
        }
        return candidate;

    }

    private static boolean knows(int a, int b, final boolean[][] knowsMatrix) {
        return knowsMatrix[a-1][b-1];
    }


}
