package net.geekscore.boggle;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * http://coursera.cs.princeton.edu/algs4/assignments/boggle.html
 *
 * Scoring. Words are scored according to their length, using this table:
 * word length  	  points
 *  0–2	                0
 *  3–4	                1
 *  5	                2
 *  6	                3
 *  7	                5
 *  8+	                11
 *
 *  The Qu special case.
 *  In the English language, the letter Q is almost always followed by the letter U.
 *  Consequently, the side of one die is printed with the two-letter sequence Qu instead of Q
 *  (and this two-letter sequence must be used together when forming words).
 *
 *  When scoring, Qu counts as two letters; for example, the word QuEUE scores as a 5-letter word
 *  even though it is formed by following a sequence of 4 dice.
 *
 *
 * dictionary-algs4.txt board4x4.txt
 * AID
 * DIE
 * END
 * ENDS
 * ...
 * YOU
 * Score = 33
 *
 * dictionary-algs4.txt board-q.txt
 * EQUATION
 * EQUATIONS
 * ...
 * QUERIES
 * QUESTION
 * QUESTIONS
 * ...
 * TRIES
 * Score = 84
 *
 */

public class BoggleSolver2 {


    private static final int[][] MOVES = {
            {-1,-1}
            ,{1,1}
            ,{-1,0}
            ,{1,0}
            ,{0,-1}
            ,{0,1}
            ,{1,-1}
            ,{-1,1}
    };

    public static void main(String[] args) {
        final char[][] board = readBoard("boggle/board4x4.txt");
        final TrieNode root = buildTrie("boggle/dictionary-algs4.txt");
        final List<String> words = boggle(board, root);
        totalScore(words);


    }

    private static final void totalScore(final List<String> words) {
        if(null == words || words.size() <= 0) return;
        int total = 0;
        for (final String word: words) {
            System.out.println(word);
            total += score(word);
        }
        System.out.println(total);
    }

    private static final int score(final String word) {
        if(null == word) return 0;
        if(word.length() > 8) return 11;
        int score = 0;
        switch (word.length()) {
            case 0:
            case 1:
            case 2:
                score = 0;
                break;
            case 3:
            case 4:
                score = 1;
                break;
            case 5:
                score = 2;
                break;
            case 6:
                score = 3;
                break;
            case 7:
                score = 5;
                break;
        }
        return score;
    }

    private static final char[][] readBoard(String path2Board) {
        char[][] board = new char[0][0];
        try(final BufferedReader br = new BufferedReader(new FileReader(path2Board))) {
            String line = br.readLine();
            if(line == null) return board;
            final String[] sizes = line.split(" ");
            if(sizes.length >= 2){
                final int rows = Integer.valueOf(sizes[0]);
                final int cols = Integer.valueOf(sizes[1]);
                board = new char[rows][cols];
            }
            int row = 0;
            while ((line = br.readLine()) != null){
                final String[] chars = line.split("  ");
                board[row] = new char[chars.length];
                int col = 0;
                for (final String ch: chars) {
                    board[row][col++] = ch.charAt(0);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return board;
    }


    private static final TrieNode buildTrie(String path2Dictionary) {
        final TrieNode root = new TrieNode();
        try(final BufferedReader br = new BufferedReader(new FileReader(path2Dictionary))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if(line.length() < 3) continue;
                TrieNode curr = root;
                for (final char ch: line.toCharArray()) {
                    TrieNode child = curr.children.get(ch);
                    if(null == child) {
                        child = new TrieNode();
                        curr.children.put(ch, child);
                    }
                    curr = child;
                }
                curr.word = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    private static final List<String> boggle(final char[][] board, final TrieNode root) {
        final List<String> wordsFound = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                wordsFound.addAll(dfs(i, j, board, root));
            }
        }
        return wordsFound;
    }

    private static final List<String> dfs(final int i, final int j, final char[][] board, final TrieNode node) {
        final List<String> words = new LinkedList<>();
        if(board[i][j] != '.') {
            // Choose
            final char temp = board[i][j];
            board[i][j] = '.';

            if(node.children.get(temp) != null)  {

                final TrieNode child = node.children.get(temp);
                if(!child.found && child.word != null) {
                    words.add(child.word);
                    child.found = true;
                }
                // Explore
                for (final int[] move:  MOVES) {
                    final int x = i + move[0];
                    final int y = j + move[1];
                    if(x < 0 || x >= board.length || y < 0 || y >= board[x].length) continue;
                    words.addAll(dfs(x, y, board, child));
                }
            }
            // Un choose
            board[i][j] = temp;
        }
        return words;
    }

    static class TrieNode {
        final Map<Character, TrieNode> children = new HashMap<>();
        String word;
        boolean found = false;
    }
}
