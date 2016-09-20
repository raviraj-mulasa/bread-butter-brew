package edu.learn.me.tree.trie;

/**
 *
 * Created by ravirajmulasa on 9/20/16.
 */
public final class TrieImpl implements ITrie{

    private StrNode root = null;

    @Override
    public void put(String key, Object value) {
        this.root = this.put(root, key, value, 0);
    }


    public StrNode put(StrNode node, String key, final Object value, final int depth) {

        if(node == null) {
            node = new StrNode(Character.toString(key.charAt(depth)));
        }

        if(depth == key.length() - 1) {
            node.setValue(value);
            return node;
        }

        final String keyAtChild = Character.toString(key.charAt(depth + 1));
        node.addChild(put(node.getChild(keyAtChild), key, value, depth + 1));
        return node;
    }



    @Override
    public Object get(String key) {
        return get(this.root, key, 0);
    }



    public Object get(StrNode node, String key, final int depth) {

        if(node == null) {
            return null;
        }

        if(depth == key.length() - 1) {
            return node.getValue();
        }

        final String keyAtChild = Character.toString(key.charAt(depth + 1));
        return get(node.getChild(keyAtChild), key, depth + 1);
    }


    /**
     *
     * To delete a key-value pair:
     *  Step 1: Find the node corresponding to key and set value to null.
     *  Step 2: If node has null value and all null links, remove that node (and recur)
     *
     * @param key
     */

    @Override
    public void delete(String key) {

    }

    @Override
    public boolean contains(String key) {
        return this.get(key) != null;

    }
}
