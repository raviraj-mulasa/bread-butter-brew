package net.geekscore.tree.binary.misc;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeImpl;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.io.StringReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so
 * that it can be stored in a file or memory buffer, or transmitted across a network connection link
 * to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how
 * your serialization/deserialization algorithm should work. You just need to ensure that a binary tree
 * can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * For example, you may serialize the following tree
 *           1
 *          / \
 *         2   3
 *            / \
 *           4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative and come up with different
 * approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and
 * deserialize algorithms should be stateless.
 *
 */
public class SerializeDeserializeBinaryTree {

    private static class CodecPreOrder {

        private int index = 0;
        // Encodes a tree to a single string.
        private String serialize(BTreeNode<Integer> root) {
            StringBuilder serializedBuilder = new StringBuilder();
            this.serializeHelper(serializedBuilder, root);
            return serializedBuilder.toString();
        }

        private void serializeHelper(final StringBuilder serializedBuilder, final BTreeNode<Integer> root) {
            if(root == null) {
                serializedBuilder.append(".,");
                return;
            }
            serializedBuilder.append(root.data);
            serializedBuilder.append(",");
            serializeHelper(serializedBuilder, root.left);
            serializeHelper(serializedBuilder, root.right);
        }

        // Decodes your encoded data to tree.
        private BTreeNode<Integer> deserialize(String data) {
            if(data == null || data.length() == 0 )return null;
            final String[] items = data.split(",");
            return deserialize(items);
        }

        private BTreeNode<Integer> deserialize(String[] items) {
            if(items == null || items.length == 0 || index == items.length || items[index].equals(".")) {
                index++;
                return null;
            }
            final Integer value =  Integer.valueOf(items[index++]);
            final BTreeNode<Integer> root = new BTreeNode<>(value);
            root.left = deserialize(items);
            root.right = deserialize(items);
            return root;
        }

    }

    private static class CodecPostOrder {

        private int index;

        // Encodes a tree to a single string.
        private String serialize(BTreeNode<Integer> root) {
            StringBuilder serializedBuilder = new StringBuilder();
            this.serializeHelper(serializedBuilder, root);
            return serializedBuilder.toString();
        }

        private void serializeHelper(final StringBuilder serializedBuilder, final BTreeNode<Integer> root) {
            if (root == null) {
                serializedBuilder.append(".,");
                return;
            }
            serializeHelper(serializedBuilder, root.left);
            serializeHelper(serializedBuilder, root.right);
            serializedBuilder.append(root.data).append(",");
        }

        // Decodes your encoded data to tree.
        private BTreeNode<Integer> deserialize(String data) {
            if (data == null || data.length() == 0) return null;
            final String[] items = data.split(",");
            this.index = items.length - 1;
            return deserialize(items);
        }

        private BTreeNode<Integer> deserialize(String[] items) {

            if (items == null || items.length == 0 || index < 0 || items[index].equals(".")) {
                index--;
                return null;
            }
            final BTreeNode<Integer> root = new BTreeNode<>(Integer.valueOf(items[index--]));
            root.right = deserialize(items);
            root.left = deserialize(items);
            return root;
        }
    }

    private static class CodecLevelOrder {

        // Encodes a tree to a single string.
        private String serialize(BTreeNode<Integer> root) {
            if(root == null) return "";
            final StringBuilder serializedBuilder = new StringBuilder();
            final Deque<BTreeNode<Integer>> queue = new LinkedList<>();
            queue.offerFirst(root);
            while (!queue.isEmpty()) {
                final int size = queue.size();
                for (int i = 0; i < size; i++) {
                    final BTreeNode<Integer> top = queue.removeLast();
                    if(top != null) {
                        serializedBuilder.append(top.data);
                        queue.offerFirst(top.left);
                        queue.offerFirst(top.right);
                    } else {
                        serializedBuilder.append(".");
                    }
                    serializedBuilder.append(","); // intra level delimiter
                }
                serializedBuilder.deleteCharAt(serializedBuilder.length()-1);
                serializedBuilder.append("#"); // inter level delimiter
            }
            return serializedBuilder.toString();
        }

        // Decodes your encoded data to tree.
        private BTreeNode<Integer> deserialize(String data) {
            if(data == null || data.length() == 0 ) return null;
            final String[] items = data.split("#");
            int level = 0;
            final BTreeNode<Integer> root = new BTreeNode<>(Integer.valueOf(items[level++]));
            final Deque<BTreeNode<Integer>> queue = new LinkedList<>();
            queue.offerFirst(root);
            while (level < items.length) {
                final String[] levelItems = items[level].split(",");
                for (int index = 0; index < levelItems.length; index+=2) {
                    final BTreeNode<Integer> top = queue.removeLast();
                    if(levelItems[index].equals(".")) {
                        top.left = null;
                    } else {
                        top.left = new BTreeNode<>(Integer.valueOf(levelItems[index]));
                        queue.offerFirst(top.left);
                    }
                    if(index+1 >= levelItems.length || levelItems[index+1].equals(".")) {
                        top.right = null;
                    } else {
                        top.right = new BTreeNode<>(Integer.valueOf(levelItems[index+1]));
                        queue.offerFirst(top.right);
                    }
                }
                level++;
            }
            return root;
        }
    }


    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,null,5,1});
        TreeUtil.print(tree);

        CodecPreOrder codecPreOrder = new CodecPreOrder();
        String serialized = codecPreOrder.serialize(tree.root());
        System.out.println(serialized);
        TreeUtil.print(new BTreeImpl<>(codecPreOrder.deserialize(serialized)));

        CodecPostOrder codecPostOrder = new CodecPostOrder();
        serialized = codecPostOrder.serialize(tree.root());
        System.out.println(serialized);
        TreeUtil.print(new BTreeImpl<>(codecPostOrder.deserialize(serialized)));

        CodecLevelOrder codecLevelOrder = new CodecLevelOrder();
        serialized = codecLevelOrder.serialize(tree.root());
        System.out.println(serialized);
        TreeUtil.print(new BTreeImpl<>(codecLevelOrder.deserialize(serialized)));


        System.out.println("----------");

        tree = TreeUtil.treeOf(new Integer[]{1,2});
        TreeUtil.print(tree);

        codecPreOrder = new CodecPreOrder();
        serialized = codecPreOrder.serialize(tree.root());
        System.out.println(serialized);
        TreeUtil.print(new BTreeImpl<>(codecPreOrder.deserialize(serialized)));

        codecPostOrder = new CodecPostOrder();
        serialized = codecPostOrder.serialize(tree.root());
        System.out.println(serialized);
        TreeUtil.print(new BTreeImpl<>(codecPostOrder.deserialize(serialized)));

        codecLevelOrder = new CodecLevelOrder();
        serialized = codecLevelOrder.serialize(tree.root());
        System.out.println(serialized);
        TreeUtil.print(new BTreeImpl<>(codecLevelOrder.deserialize(serialized)));


        System.out.println("----------");

        tree = TreeUtil.treeOf(new Integer[]{1,6,2,7,5,null,null,8,null,6,4,7,9,null,null,3,5,null,null,10,8,4,2,null,null,9,11,null,null,1});
        TreeUtil.print(tree);

        codecPreOrder = new CodecPreOrder();
        serialized = codecPreOrder.serialize(tree.root());
        System.out.println(serialized);
        TreeUtil.print(new BTreeImpl<>(codecPreOrder.deserialize(serialized)));

        codecPostOrder = new CodecPostOrder();
        serialized = codecPostOrder.serialize(tree.root());
        System.out.println(serialized);
        TreeUtil.print(new BTreeImpl<>(codecPostOrder.deserialize(serialized)));

        codecLevelOrder = new CodecLevelOrder();
        serialized = codecLevelOrder.serialize(tree.root());
        System.out.println(serialized);
        TreeUtil.print(new BTreeImpl<>(codecLevelOrder.deserialize(serialized)));


    }


}
