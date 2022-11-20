package net.geekscore.boggle;


import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class BoggleSolver {

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

        final char[][] board ={
                 {'G','I','Z'}
                ,{'U','E','K'}
                ,{'Q','S','E'}
        };
        Instant start = Instant.now();
        List<String>  result = boggle(board, new HashSet<>(Arrays.asList("GEEKS", "GUES", "QUIZKI", "FOR", "QUIZ", "GO", "SEEK")));
        System.out.println(Duration.between(start, Instant.now()).toNanos());
        System.out.println(result); // ["GEEKS", "SEEK", "QUIZ"]


        start = Instant.now();
        result = boggleTrie(board, new HashSet<>(Arrays.asList("GEEKS", "GUES", "QUIZKI", "FOR", "QUIZ", "GO", "SEEK")));
        System.out.println(Duration.between(start, Instant.now()).toNanos());
        System.out.println(result); // ["GEEKS", "SEEK", "QUIZ"]


        final char[][] board1 = {
                {'P', 'L', 'A', 'Y'}
                ,{'T', 'H', 'I', 'S'}
                ,{'W', 'O', 'R', 'D'}
                ,{'G', 'A', 'M', 'E'}
        };

        start = Instant.now();
        result = boggleTrie(board1, new HashSet<>(Arrays.asList("TOWARDS", "HIREDRHOA","RAID", "HIDE", "RIDE", "WORDS", "LAIR", "LAHORE", "WARD", "ROAM", "TWO", "THROW", "ASIDE", "PLAYS", "TWO", "RAT", "SIR", "HISS", "HIS")));
        System.out.println(Duration.between(start, Instant.now()).toNanos());
        System.out.println(result);

        start = Instant.now();
        result = boggle(board1, new HashSet<>(Arrays.asList("TOWARDS", "HIREDRHOA", "RAID", "HIDE", "RIDE", "WORDS", "LAIR", "LAHORE", "WARD", "ROAM", "TWO", "THROW", "ASIDE", "PLAYS", "TWO", "RAT", "SIR", "HISS", "HIS")));
        System.out.println(Duration.between(start, Instant.now()).toNanos());
        System.out.println(result);



    }

    private static final List<String> boggle(final char[][] board, final Set<String> dictionary) {
        final List<String> wordsFound = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(wordsFound, i, j, board, dictionary, new StringBuilder());
            }
        }
        return wordsFound;
    }

    private static final void dfs(final List<String> words, final int i, final int j, final char[][] board, final Set<String> dictionary, final StringBuilder prefix) {
        if(board[i][j] != '.') {
            final String word = prefix.toString();
            if(dictionary.contains(word) && !words.contains(word)){
                words.add(word);
            }
            // Choose
            final char temp = board[i][j];
            prefix.append(temp);
            board[i][j] = '.';

            // Explore
            for (final int[] move:  MOVES) {
                final int x = i + move[0];
                final int y = j + move[1];
                if(x < 0 || x >= board.length || y < 0 || y >= board[x].length) continue;
                dfs(words, x, y, board, dictionary, prefix);
            }

            // Un choose
            prefix.deleteCharAt(prefix.length()-1);
            board[i][j] = temp;
        }

    }

    private static final List<String> boggleTrie(final char[][] board, final Set<String> dictionary) {
        final List<String> wordsFound = new LinkedList<>();
        final TrieNode root = buildTrie(dictionary);
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

            if(null != node && node.children[temp - 'A'] != null)  {
                final TrieNode child = node.children[temp - 'A'];
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

    private static final TrieNode buildTrie(final Set<String> dictionary) {
        final TrieNode root = new TrieNode();
        for (final String word: dictionary) {
            TrieNode curr = root;
            for (final char ch: word.toCharArray()) {
                final int index = ch-'A';
                if(curr.children[index] == null)  {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.word = word;
        }
        return root;
    }

    static class TrieNode {
        final TrieNode[] children = new TrieNode[26];
        String word;
        boolean found = false;
    }
}
