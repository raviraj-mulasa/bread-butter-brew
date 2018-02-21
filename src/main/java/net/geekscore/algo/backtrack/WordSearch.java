package net.geekscore.algo.backtrack;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * For example,
 * Given board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 *]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */
public class WordSearch {

    private static final int[][] MOVES = {
             {0,1}
            ,{1,0}
             ,{0,-1}
            ,{-1,0}
    };

    public static void main(String[] args) {

        final char[][] board = new char[][] {
                {'A','B','C','E'}
                ,{'S','F','C','S'}
                ,{'A','D','E','E'}
        };
        final char[][] board1 = new char[][] {
                {'a'}
        };
        final char[][] board2 = new char[][] {
                {'C','A','A'}
                ,{'A','A','A'}
                ,{'B','C','D'}
        };
        final char[][] board3 = new char[][] {
                {'A','B','C','E'}
                ,{'S','F','E','S'}
                ,{'A','D','E','E'}
        };

        Instant start = Instant.now();
        System.out.println(wordExists("ABCCED", board)); // true
        System.out.println(wordExists("SEE", board)); // true
        System.out.println(wordExists("ABCB", board)); // false
        System.out.println(wordExists("SFCS", board)); // true
        System.out.println(wordExists("ABCE", board)); // true
        System.out.println(wordExists("ASAD", board)); // true
        System.out.println(wordExists("AFEE", board)); // false
        System.out.println("-------------------");
        System.out.println(wordExists("b", board1)); // false
        System.out.println("-------------------");
        System.out.println(wordExists("AAB", board2)); // true
        System.out.println("-------------------");
        System.out.println(wordExists("a", board1)); // true
        System.out.println("-------------------");
        System.out.println(wordExists("ab", board1)); // false
        System.out.println("-------------------");
        System.out.println(wordExists("ABCESEEEFS", board3)); // true
        System.out.println("-------------------");
        System.out.println(wordExists("ABCEFSADEESE", board3)); // true
        System.out.println(Duration.between(start, Instant.now()).toMillis());

        System.out.println("############# NO EXTRA MEMORY #############");

        start = Instant.now();
        System.out.println(wordExistsNoExtraMemory("ABCCED", board)); // true
        System.out.println(wordExistsNoExtraMemory("SEE", board)); // true
        System.out.println(wordExistsNoExtraMemory("ABCB", board)); // false
        System.out.println(wordExistsNoExtraMemory("SFCS", board)); // true
        System.out.println(wordExistsNoExtraMemory("ABCE", board)); // true
        System.out.println(wordExistsNoExtraMemory("ASAD", board)); // true
        System.out.println(wordExistsNoExtraMemory("AFEE", board)); // false
        System.out.println("-------------------");
        System.out.println(wordExistsNoExtraMemory("b", board1)); // false
        System.out.println(wordExistsNoExtraMemory("AAB", board2)); // true
        System.out.println("-------------------");
        System.out.println(wordExistsNoExtraMemory("a", board1)); // true
        System.out.println("-------------------");
        System.out.println(wordExistsNoExtraMemory("ab", board1)); // false
        System.out.println("-------------------");
        System.out.println(wordExistsNoExtraMemory("ABCESEEEFS", board3)); // true
        System.out.println("-------------------");
        System.out.println(wordExistsNoExtraMemory("ABCEFSADEESE", board3)); // true
        System.out.println(Duration.between(start, Instant.now()).toMillis());



    }

    private static final boolean wordExists(String word, char[][] board) {

        if(word == null || word.length() == 0) return true;
        if(null == board || board.length == 0) return false;

        final boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(dfs(word, 0, board, visited, i, j)) return true;
            }
        }
        return false;
    }

    private static final boolean dfs(String word, int wordIdx, char[][] board,  boolean[][] visited, int i, int j) {
        if(!visited[i][j] &&  board[i][j] == word.charAt(wordIdx)) {
            if (wordIdx == word.length()-1) return true;
            visited[i][j] = true; // choose
            // Explore
            for (final int[] move : MOVES) {
                final int nextI = i + move[0];
                final int nextJ = j + move[1];
                if (nextI < 0 || nextI >= board.length || nextJ < 0 || nextJ >= board[0].length || visited[nextI][nextJ]) continue;
                if(dfs(word, wordIdx + 1, board, visited, nextI, nextJ)) return true;
            }
            visited[i][j] = false; // Un choose
        }
        return false;
    }

    private static final boolean wordExistsNoExtraMemory(final String word,  final char[][] board) {
        if(word == null || word.length() == 0) return true;
        if(null == board || board.length == 0) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(dfs(word, 0, board, i, j)) return true;
            }
        }
        return false;
    }

    private static final boolean dfs(String word, int wordIdx, char[][] board, int i,  int j) {
        boolean result = false;
        if(board[i][j] == word.charAt(wordIdx)) {
            if(wordIdx == word.length()-1) return true;
            // choose
            final char temp = board[i][j];
            board[i][j] = '#';
            // Explore
            for (final int[] move : MOVES) {
                final int nextI = i + move[0];
                final int nextJ = j + move[1];
                if (nextI < 0 || nextI >= board.length || nextJ < 0 || nextJ >= board[nextI].length || board[nextI][nextJ] == '#') continue;
                result = dfs(word, wordIdx + 1, board, nextI, nextJ);
                if(result) break; // found a match BREAK, if NOT continue with next valid neighbor
            }
            board[i][j]  = temp; // Un choose
        }
        return result;
    }
}
