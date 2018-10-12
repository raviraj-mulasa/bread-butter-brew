package net.geekscore.tree.binary.bst;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

public class FindMaxBST {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{8, 3, 10, 1, 6, null, 14, null, null, 4, 7, null, null, 13, null});
        TreeUtil.print(tree);

        System.out.println("--------------");
        System.out.println("Max " + maxRec(tree.root())); // 14
        System.out.println("Max " + max(tree.root())); // 14

        tree = TreeUtil.treeOf(new Integer[]{8, 3, 10, 1, 6, null, 14, null, null, 4, 7, null, null, 13, 18});
        TreeUtil.print(tree);

        System.out.println("--------------");
        System.out.println("Max " + maxRec(tree.root())); // 18
        System.out.println("Max " + max(tree.root())); // 18


        tree = TreeUtil.treeOf(new Integer[]{8, 3, null, 1, 6, null, null, null, null, 4, 7, null, null, null, null});
        TreeUtil.print(tree);
        System.out.println("--------------");
        System.out.println("Max " + maxRec(tree.root())); // 8
        System.out.println("Max " + max(tree.root())); // 8

    }


    private static Integer maxRec(BTreeNode<Integer> node) {
        if(node == null) throw  new IllegalArgumentException("Not a valid tree");
        if(node.right != null) return maxRec(node.right);
        return node.data;
    }


    private static Integer max(BTreeNode<Integer> node) {
        if(node == null) throw  new IllegalArgumentException("Not a valid tree");
        while (node.right != null) node = node.right;
        return node.data;
    }
}
