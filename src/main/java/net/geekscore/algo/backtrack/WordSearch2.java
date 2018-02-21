package net.geekscore.algo.backtrack;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * TODO
 *
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 *
 * The same letter cell may not be used more than once in a word.
 *
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 *
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 *
 * Return ["eat","oath"].
 * Note: You may assume that all inputs are consist of lowercase letters a-z.
 */
public class WordSearch2 {


    private static final int[][] MOVES = {
            {0,1}
            ,{0,-1}
            ,{1,0}
            ,{-1,0}
    };

    public static void main(String[] args) {

        final char[][] baord = new char[][]{
                {'o','a','a','n'}
                ,{'e','t','a','e'}
                ,{'i','h','k','r'}
                ,{'i','f','l','v'}
        };
        final char[][] baord1 = new char[][]{
                {'a','b'}
                ,{'c','d'}
        };
        final char[][] baord2 = new char[][]{
                {'b','b','a','a','b','a'},
                {'b','b','a','b','a','a'},
                {'b','b','b','b','b','b'},
                {'a','a','a','b','a','a'},
                {'a','b','a','a','b','b'}
        };

        Instant start = Instant.now();
        // ["eat","oath"]
        System.out.println(wordsInBoard(Arrays.asList("oath","pea","eat","rain"), baord));
        // ["ab","ac","bd","ca","db"]
        System.out.println(wordsInBoard(Arrays.asList("ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"), baord1));
        // [abbbababaa]
        System.out.println(wordsInBoard(Arrays.asList("abbbababaa"), baord2));
        System.out.println(Duration.between(start, Instant.now()).toMillis());

        System.out.println("############# NO EXTRA MEMORY #############");

        start = Instant.now();
        // ["eat","oath"]
        System.out.println(wordsInBoardNoExtraMemory(Arrays.asList("oath","pea","eat","rain"), baord));
        // ["ab","ac","bd","ca","db"]
        System.out.println(wordsInBoardNoExtraMemory(Arrays.asList("ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"), baord1));
        // [abbbababaa]
        System.out.println(wordsInBoardNoExtraMemory(Arrays.asList("abbbababaa"), baord2));
        System.out.println(Duration.between(start, Instant.now()).toMillis());


        System.out.println("############# USING TRIE #############");

        start = Instant.now();
        // ["eat","oath"]
        System.out.println(wordsInBoardUsingTrie(Arrays.asList("oath","pea","eat","rain"), baord));
        // ["ab","ac","bd","ca","db"]
        System.out.println(wordsInBoardUsingTrie(Arrays.asList("ab","cb","ad","bd","ac","ca","da","bc","db","adcb","dabc","abb","acb"), baord1));
        // [abbbababaa]
        System.out.println(wordsInBoardUsingTrie(Arrays.asList("abbbababaa"), baord2));
        System.out.println(Duration.between(start, Instant.now()).toMillis());


    }

    private static final List<String> wordsInBoard(final List<String> words, final char[][] board) {

        final boolean visited[][] = new boolean[board.length][board[0].length];
        final Set<String> wordsSet = new HashSet<>(words);
        final List<String> list = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(wordsSet, board, visited, i, j, new StringBuilder(), list);
            }
        }
        Collections.sort(list);
        return list;
    }


    private static void dfs(final Set<String> words, final char[][] board, final boolean visited[][], final int i, final int j, final StringBuilder wordSoFar, final List<String> wordsPresent) {
        if(!visited[i][j]) {
            // Choose
            visited[i][j] = true;
            wordSoFar.append(board[i][j]);

            final String word = wordSoFar.toString();
            if(words.contains(word)) {
                wordsPresent.add(word);
                words.remove(word);
            }
            // Explore
            for (final int[] move: MOVES) {
                final int x = i + move[0];
                final int y = j + move[1];
                if( x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) continue;
                dfs(words, board, visited, x, y, wordSoFar, wordsPresent);
            }
            // Un choose
            visited[i][j] = false;
            wordSoFar.deleteCharAt(wordSoFar.length()-1);
        }
    }


    private static final List<String> wordsInBoardNoExtraMemory(final List<String> words, final char[][] board) {
        final Set<String> wordsSet = new HashSet<>(words);
        final List<String> list = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(wordsSet, board, i, j, new StringBuilder(), list);
            }
        }
        Collections.sort(list);
        return list;
    }


    private static void dfs(final Set<String> words, final char[][] board, final int i, final int j, final StringBuilder wordSoFar, final List<String> wordsPresent) {
        // Choose
        final char temp = board[i][j];
        wordSoFar.append(board[i][j]);
        board[i][j] = '#';

        final String word = wordSoFar.toString();
        if(words.contains(word)) {
            wordsPresent.add(word);
            words.remove(word);
        }

        // Explore
        for (final int[] move: MOVES) {
            final int x = i + move[0];
            final int y = j + move[1];
            if( x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == '#') continue;
            dfs(words, board, x, y, wordSoFar, wordsPresent);
        }
        // Un Choose
        board[i][j] = temp;
        wordSoFar.deleteCharAt(wordSoFar.length()-1);
    }

    private static final TrieNode createPrefixTree(final Set<String> words) {
        final TrieNode root = new TrieNode();
        for (final String word : words) {
            TrieNode curr = root;
            for (final char ch: word.toCharArray()) {
                final int index = ch-'a';
                if(null == curr.children[index]) curr.children[index] = new TrieNode();
                curr = curr.children[index];
            }
            curr.word = word;
        }
        return root;
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    private static final List<String> wordsInBoardUsingTrie(final List<String> words, final char[][] board) {
        final Set<String> wordsSet = new HashSet<>(words);
        final TrieNode root = createPrefixTree(wordsSet);
        final List<String> list = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(root, board, i, j, list);
            }
        }
        Collections.sort(list);
        return list;
    }


    private static void dfs(TrieNode node, final char[][] board, final int i, final int j, final List<String> wordsPresent) {
        // Choose
        final char temp = board[i][j];

        if(null == node || node.children[temp - 'a'] == null) return;
        node = node.children[temp - 'a'];
        if(null != node.word) {
            wordsPresent.add(node.word);
            node.word = null; // de-duplicate, very imp
        }

        // Choose
        board[i][j] = '#';
        // Explore
        for (final int[] move: MOVES) {
            final int x = i + move[0];
            final int y = j + move[1];
            if(x < 0 || x >= board.length || y < 0 || y >= board[x].length || board[x][y] == '#') continue;
            dfs(node, board, x, y, wordsPresent);
        }

        // Un Choose
        board[i][j] = temp;
    }
}
