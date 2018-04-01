package net.geekscore.tree.trie;

import java.util.Stack;

public class PrefixTreeImpl implements ITrie {

    private final TrieNode root = new TrieNode();

    @Override
    public void put(String key, Object value) {
        if (null == key) throw new IllegalArgumentException("Key cannot be null!");
        TrieNode curr = root;
        for (final char ch: key.toCharArray()) {
            TrieNode child = curr.getChild(String.valueOf(ch));
            if(null == child){
                child = new TrieNode(String.valueOf(ch));
                curr.addChild(child);
            }
            curr = child;
        }
        curr.setValue(value);
    }

    @Override
    public Object get(String key) {
        if (null == key) throw new IllegalArgumentException("Key cannot be null!");
        TrieNode curr = root;
        for (final char ch: key.toCharArray()) {
            TrieNode child = curr.getChild(String.valueOf(ch));
            if(null == child) return null;
            curr = child;
        }
        return curr.getValue();
    }

    @Override
    public void delete(String key) {
        if(contains(key)) {
            Stack<String> childValueStack   = new Stack<>();
            Stack<TrieNode> parentStack     = new Stack<>();
            TrieNode curr = root;
            for (final char ch: key.toCharArray()) {
                childValueStack.push(String.valueOf(ch));
                parentStack.push(curr);
                TrieNode child = curr.getChild(String.valueOf(ch));
                curr = child;
            }
            curr.setValue(null);
            while(!parentStack.isEmpty()) {
                final TrieNode parent   = parentStack.pop();
                final String childValue = childValueStack.pop();
                final TrieNode child    = parent.getChild(childValue);
                if(child != null && child.hasNoChildren()) {
                    parent.deleteChild(child);
                } else break;
            }
        }
    }

    @Override
    public boolean contains(String key) {
        return this.get(key) != null;
    }

    @Override
    public TrieNode root() {
        return this.root;
    }
}
