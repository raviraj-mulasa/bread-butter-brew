package net.geekscore.tree.trie;


/**
 *
 * ALSO KNOWN AS TRIE
 *
 * Created by ravirajmulasa on 9/20/16.
 */
public class PrefixTreeRecImpl implements ITrie {

    private TrieNode root = new TrieNode();

    @Override
    public void put(String key, Object value) {
        this.root = this.put(root, key, value, -1);
    }


    private TrieNode put(TrieNode node, String key, final Object value, final int depth) {
        if (null == key) throw new IllegalArgumentException("Key cannot be null!");
        if(node == null) {
            node = new TrieNode(Character.toString(key.charAt(depth)));
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
        if (null == key) throw new IllegalArgumentException("Key cannot be null!");
        final TrieNode node = get(this.root, key, -1);
        return (node == null ? null : node.getValue());
    }



    private TrieNode get(TrieNode node, String key, final int depth) {

        if(node == null) {
            return null;
        }

        if(depth == key.length() - 1) {
            return node;
        }

        final String keyAtChild = Character.toString(key.charAt(depth + 1));
        return get(node.getChild(keyAtChild), key, depth + 1);
    }


    @Override
    public void delete(String key) {
        final TrieNode root = this.delete(this.root, key, -1);
//        System.out.println("Root Node :"+ root.getKey());
    }

    private TrieNode delete(TrieNode node, String key, final int depth) {

        if(node == null) {
            return null;
        }

        if(depth == key.length() - 1) {
//            Step 1: Find the node corresponding to key and set value to null
            node.setValue(null);
            return node;
        }

        final String keyAtChild = Character.toString(key.charAt(depth + 1));
        final TrieNode child    = delete(node.getChild(keyAtChild), key, depth + 1);


//        Step 2: If node has null value and all null links, remove that node (and recur)
        if (null != child && child.getValue() == null && child.hasNoChildren()) {
            if(node.deleteChild(child)) {
//                System.out.println(String.format("deleted node with key and value: %s => %s", child.getKey(), child.getValue()));
            }
        }
        return node;
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
