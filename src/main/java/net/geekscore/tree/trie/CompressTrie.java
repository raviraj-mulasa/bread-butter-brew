package net.geekscore.tree.trie;

import java.util.*;

public final class CompressTrie {

    public static void main(String[] args) {
        final ITrie trie    = new PrefixTreeImpl();
        trie.put("hat" , 1);
        trie.put("hello" , 2);
        trie.put("have" , 3);
        trie.put("haven" , 4);
        trie.put("happy" , 30);
        TrieUtil.print(trie.root());

        compress(trie.root());
        TrieUtil.print(trie.root());
    }

    private static void compress(final TrieNode node) {
        compressHelper(node);
    }


    private static void compressHelper(final TrieNode node) {
        if(null != node) {
            for (final TrieNode child: node.getChildren().values()) {
                compressHelper(child);
                if (node.childrenCount() <=1 && child.childrenCount() <= 1) {
                    if(node.getValue() == null && child.getValue() != null) {
                        node.setValue(child.getValue());
                        node.deleteChild(child);
                        if(child.childrenCount() == 0) {
                            System.out.println("Merge "+child.getKey());
                        } else {
                            System.out.println("Merge "+node.getKey());
                        }
                    }


                }
            }
        }
    }
}
