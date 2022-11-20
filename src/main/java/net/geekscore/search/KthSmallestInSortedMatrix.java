package net.geekscore.search;


/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order,
 * find the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * Example:
 *  matrix = [
 *   [1,  5,  9]
 *  ,[10, 11, 13]
 *  ,[12, 13, 15]
 * ],
 * k = 8,
 *
 * return 13.
 *
 * TODO
 */
public class KthSmallestInSortedMatrix {

    public static void main(String[] args) {
        System.out.println();
    }

    private static int kthSmallest(final int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0) return Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;

        return result;
    }
}
