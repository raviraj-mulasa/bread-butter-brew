package net.geekscore.algo.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * Created by ravirajmulasa on 8/11/17.
 *
 * SAME AS COINS IN A LINE
 *
 * https://www.hackerrank.com/challenges/game-of-stones-1/problem
 *
 * Two players (numbered  and ) are playing a game with  stones.
 * Player  always plays first, and the two players move in alternating turns. The game's rules are as follows:
 *
 *  In a single move, a player can remove either , , or  stones from the game board.
 *  If a player is unable to make a move, that player loses the game.
 *
 *  Given the number of stones, find and print the name of the winner (i.e., First or Second ) on a new line.
 *  Each player plays optimally, meaning they will not make a move that causes them to lose the game if some better,
 *  winning move exists.
 *
 *  Input Format:
 *  The first line contains an integer, , denoting the number of test cases.
 *  Each of the  subsequent lines contains a single integer, , denoting the number of stones in a test case.
 *
 *  Constraints
 *  Output Format
 *      On a new line for each test case, print  if the first player is the winner; otherwise, print .
 *
 *      Sample Input
 *      8
 *      1
 *      2
 *      3
 *      4
 *      5
 *      6
 *      7
 *      10
 *
 *      Sample Output
 *      Second
 *      First
 *      First
 *      First
 *      First
 *      First
 *      Second
 *      First
 *
 **/
public class GameOfStones {

    public static void main(String[] args) {
        Stream<String> testCases = readFromStdIn();
        System.out.println("Output");
        testCases.forEachOrdered(
                testCase -> {
                    final int stones = Integer.valueOf(testCase);
                    if(stones % 7 < 2){
                        System.out.println("Second");
                    } else {
                        System.out.println("First");
                    }
                }
        );
    }

    private static final Stream<String> readFromStdIn() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        return in.lines().limit(100);
    }

}


