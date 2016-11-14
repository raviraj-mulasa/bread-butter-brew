package net.geekscore.tree.trie;

/**
 * Created by ravirajmulasa on 9/26/16.
 */
public final class SuffixTrieImpl implements ITrie {

    private static final String DOLLAR = String.valueOf('\u0000');

    private StrNode root = new StrNode();

    public SuffixTrieImpl(final String text) {
        final String textDollar = text+DOLLAR;
        for (int i = 0; i < textDollar.length(); i++) {
            this.put(textDollar.substring(i), i);
        }
    }

    @Override
    public void put(String key, Object value) {
        this.root = put(this.root, key, value);
    }


    private StrNode put(StrNode node, String key, Object value) {
        if(!key.equals(DOLLAR) && key.length() <= 0) {
            return null;
        }
        final String childKey   = String.valueOf(key.charAt(0));
        StrNode child           = node.getChild(childKey);
        if(null == child) {
            child = new StrNode(childKey);
        }
        node.addChild(child);
        if(childKey.equals(DOLLAR)) {
           child.setValue(value);
        }else {
            put(child, key.substring(1), value);
        }
        return node;
    }


    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public boolean contains(String key) {
        return false;
    }
}
