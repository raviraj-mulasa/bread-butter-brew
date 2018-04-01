package net.geekscore.tree.trie;

import java.util.Arrays;

public final class TrieUtil {

    private TrieUtil(){}

    public static void print(TrieNode node) {
        print(node, 0, "$", 1);
    }

    private static void print(TrieNode node, int offset, String val, int step) {
        final char[] dots = new char[offset];
        Arrays.fill(dots,'-');
        if(node.getValue() != null) {
            System.out.println(String.valueOf(dots)+val+"("+node.getValue()+")");
        } else {
            System.out.println(String.valueOf(dots)+val);
        }
        for(final String child : node.getChildren().keySet()) {
            print(node.getChild(child), offset + step, child, step);
        }
    }
}
