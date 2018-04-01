package net.geekscore.tree.trie;

public final class LongestCommonPrefix {

    private static class Node {
        char ch;
        Node[] children;
        boolean isLeaf;
        int childCount;
        Node(final char ch) {
            this.ch = ch;
            this.children = new Node[26];
            this.isLeaf = false;
            this.childCount = 0;
        }
    }

    public static void main(String[] args) {

        // gee
        System.out.println(longestCommonPrefix(new String[]{"geeksforgeeks", "geeks", "geek", "geezer"}));
        // ap
        System.out.println(longestCommonPrefix(new String[]{"apple", "ape", "april"}));
    }

    private static final String longestCommonPrefix(final String[] words) {
        final StringBuilder prefixBuilder = new StringBuilder();
        Node curr = buildTrie(words);
        while (curr != null && !curr.isLeaf && curr.childCount == 1) {
            int i = 0;
            while (curr.children[i] == null) i++;
            prefixBuilder.append(curr.children[i].ch);
            curr = curr.children[i];
        }
        return prefixBuilder.toString();
    }

    private static Node buildTrie(final String[] words) {
        final Node root = new Node('$');
        for (final String word: words) {
            Node curr = root;
            for (final char ch: word.toCharArray()) {
                Node child = curr.children[ch-'a'];
                if(null == child) {
                    curr.children[ch-'a'] = new Node(ch);
                    child = curr.children[ch-'a'];
                    curr.childCount++;
                }
                curr = child;
            }
            curr.isLeaf = true;
        }
        return root;
    }



}
