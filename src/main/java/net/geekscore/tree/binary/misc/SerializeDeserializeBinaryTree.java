package net.geekscore.tree.binary.misc;

import com.sun.tools.javac.jvm.Code;
import net.geekscore.tree.TreeNode;
import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

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

    private static class Codec {
        // Encodes a tree to a single string.
        private String serialize(BTreeNode<Integer> root) {
            return "";
        }

        // Decodes your encoded data to tree.
        private BTreeNode<Integer> deserialize(String data) {
            return null;
        }
    }

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,null,5,1});
        TreeUtil.print(tree);

        Codec codec = new Codec();
        System.out.println(codec.serialize(tree.root()));
    }


}
