package net.geekscore.tree.binary.misc;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeImpl;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

public class TrimBinarySearchTree {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{8,3,10,1,6,null,14,null,null,4,7,null,null,13,null});
        TreeUtil.print(tree);
        System.out.println("-----");
        TreeUtil.print(trim(tree, 5, 13));
    }

    private static IBTree<Integer> trim(IBTree<Integer> tree, Integer min, Integer max) {
        if(tree == null || tree.root() == null) return null;
        return new BTreeImpl<>(trim(tree.root(), min, max));
    }

    // trim using post order (lrR) traversal.
    private static BTreeNode<Integer> trim(BTreeNode<Integer> node, Integer min, Integer max) {
        if(node == null) return null;
        if(min.compareTo(node.data) > 0) return node.right;
        if(max.compareTo(node.data) < 0) return node.left;
        node.left = trim(node.left, min, max);
        node.right =trim(node.right, min, max);
        return node;
    }


}
