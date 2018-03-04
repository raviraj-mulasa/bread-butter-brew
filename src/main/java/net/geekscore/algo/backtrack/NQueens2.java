package net.geekscore.algo.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens
 * attack each other.
 * 2 Queens , Q1 at (i,j) and Q2 at (k,l) are attacking each other when
 *
 *  i == k              => same row
 *  j == l              => same column
 *  |i - k| == |j - l|  => same diagonal
 *
 *
 * Follow up for N-Queens problem.
 *
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 */
public class NQueens2 {

    public static void main(String[] args) {

        List<List<NQueens.Position>> positionsList = new LinkedList<>();
        System.out.println("---");
        NQueens.placeQueens(0, 2, new LinkedList<>(), positionsList);
        System.out.println(positionsList.size()); // 0

        System.out.println("---");
        positionsList = new LinkedList<>();
        NQueens.placeQueens(0, 4, new LinkedList<>(), positionsList);
        System.out.println(positionsList.size()); // 2

        System.out.println("---");
        positionsList = new LinkedList<>();
        NQueens.placeQueens(0, 6, new LinkedList<>(), positionsList);
        System.out.println(positionsList.size()); // 4

        System.out.println("---");
        positionsList = new LinkedList<>();
        NQueens.placeQueens(0, 8, new LinkedList<>(), positionsList);
        System.out.println(positionsList.size()); // 92

    }
}
