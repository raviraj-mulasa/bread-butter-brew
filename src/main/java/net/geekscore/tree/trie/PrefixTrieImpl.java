package net.geekscore.tree.trie;


/**
 *
 * ALSO KNOWN AS TRIE
 *
 * Created by ravirajmulasa on 9/20/16.
 */
public class PrefixTrieImpl implements ITrie{

    private StrNode root = new StrNode();

    @Override
    public void put(String key, Object value) {
        this.root = this.put(root, key, value, -1);
    }


    private StrNode put(StrNode node, String key, final Object value, final int depth) {

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
        final StrNode node = get(this.root, key, -1);
        return (node == null ? null : node.getValue());
    }



    private StrNode get(StrNode node, String key, final int depth) {

        if(node == null) {
            return null;
        }

        if(depth == key.length() - 1) {
            return node;
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
        final StrNode root = this.delete(this.root, key, -1);
//        System.out.println("Root Node :"+ root.getKey());
    }


    private StrNode delete(StrNode node, String key, final int depth) {

        if(node == null) {
            return null;
        }

        if(depth == key.length() - 1) {
//            Step 1: Find the node corresponding to key and set value to null
            node.setValue(null);
            return node;
        }

        final String keyAtChild = Character.toString(key.charAt(depth + 1));
        final StrNode child     = delete(node.getChild(keyAtChild), key, depth + 1);
        if(null != child) {
//            Step 2: If node has null value and all null links, remove that node (and recur)
            if (child.getValue() == null && (child.getChildren() == null || child.getChildren().isEmpty())) {
                if(node.deleteChild(child)) {
//                    System.out.println(String.format("deleted node with key and value: %s => %s", child.getKey(), child.getValue()));
                }
            }
        }
        return node;
    }


    @Override
    public boolean contains(String key) {
        return this.get(key) != null;

    }
}
