package net.geekscore.tree.nary;
import net.geekscore.tree.TreeNode;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * Given an N-ary tree where every node has at-most N children. How to serialize and deserialze it?
 * Serialization is to store tree in a file so that it can be later restored. The structure of tree
 * must be maintained. Deserialization is reading tree back from file.
 *
 * This post is mainly an extension of below post.
 * Serialize and Deserialize a Binary Tree
 *
 * In an N-ary tree, there are no designated left and right children. An N-ary tree is represented by
 * storing an array or list of child pointers with every node.
 * The idea is to store an ‘end of children’ marker with every node. The following diagram shows
 * serialization where ‘)’ is used as end of children marker.
 *
 */
public class SerializeDeserializeNAryTree {

    private static final  Random RANDOM = new SecureRandom();

    private static class Codec {

        private String serialize(TreeNode<Integer> root) {
            return this.serializeHelper(root);
        }

        private String serializeHelper(TreeNode<Integer> root) {
            if (null == root) return "";
            String delimiter = root.children == null || root.children.isEmpty() ? "" : ",";
            StringBuilder result =  new StringBuilder();
            if(root.children != null && !root.children.isEmpty()) {
                for (final TreeNode<Integer> child: root.children) {
                    result.append(this.serializeHelper(child));
                }
            }
            return root.data
                    + delimiter
                    + result.toString()
                    + "),";
        }

        // Decodes your encoded data to tree.
        private TreeNode<Integer> deserialize(String data) {

            if (data == null || data.length() == 0) return null;

            final String[] items = data.split(",");
            System.out.println(Arrays.toString(items));

            TreeNode<Integer> root = new TreeNode<>(Integer.valueOf(items[0]));
            final Deque<TreeNode<Integer>> stack = new LinkedList<>();
            stack.push(root);


            for (int i = 1; i < items.length; i++) {

                final String item = items[i].trim();

                if(item.length() > 0) {

                    final int index = item.lastIndexOf(')');

                    if(item.length() == 1 && index == item.length()-1) {
                        stack.pop();
                        continue;
                    }

                    TreeNode<Integer> child = null;
                    TreeNode<Integer> parent= stack.peek();

                    if(index == -1) {
                       child = new TreeNode<>(Integer.valueOf(item));
                    } else {
                        child = new TreeNode<>(Integer.valueOf(item.substring(0, index)));
                    }
                    parent.addChild(child);
                    stack.push(child);

                    System.out.println(stack);
                }
            }
            return stack.pop();

        }

    }

    public static void main(String[] args) {

        TreeNode<Integer> root = constructNAryTree(3, 2);
        System.out.println(root);

        final Codec codec = new Codec();
        final String serialized = codec.serialize(root);
        System.out.println(serialized);
        System.out.println(codec.deserialize(serialized));
    }

    private static TreeNode<Integer> constructNAryTree(int maxChildren, int maxLevels) {
        if(maxLevels == 0 || maxChildren <= 0 ) return null;
        final Deque<TreeNode<Integer>> queue = new LinkedList<>();
        final TreeNode<Integer> root = new TreeNode<>(RANDOM.nextInt(100));
        queue.offerFirst(root);
        int max = root.data;
        int level = maxLevels;
        while (level > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                final TreeNode<Integer> top = queue.removeLast();
                int childrenCount = RANDOM.nextInt(maxChildren)+1;
                for (int k = 1; k <= Math.min(childrenCount, maxChildren); k++) {
                    max = max+k;
                    final TreeNode<Integer> child = new TreeNode<>(max+k);
                    top.addChild(child);
                    queue.offerFirst(child);
                }
            }
            level--;
        }
        return root;
    }


}
