package net.geekscore.tree;

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

    private static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return "";
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return null;
        }
    }

    public static void main(String[] args) {

    }


}
