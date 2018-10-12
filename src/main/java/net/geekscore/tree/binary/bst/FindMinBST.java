package net.geekscore.tree.binary.bst;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

public class FindMinBST {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{8, 3, 10, 1, 6, null, 14, -3, 2, 4, 7, null, null, 13, null});
        TreeUtil.print(tree);

        System.out.println("--------------");
        System.out.println("Min " + minRec(tree.root())); // -3
        System.out.println("Min " + min(tree.root())); // -3

        tree = TreeUtil.treeOf(new Integer[]{8, 3, 10, 1, 6, null, 14, null, 2, 4, 7, null, null, 13, 18});
        TreeUtil.print(tree);

        System.out.println("--------------");
        System.out.println("Min " + minRec(tree.root())); // 1
        System.out.println("Min " + min(tree.root())); // 1


        tree = TreeUtil.treeOf(new Integer[]{8, null, 10, null, null, null, 14, null, null, null, null, null, null, 13, null});
        TreeUtil.print(tree);
        System.out.println("--------------");
        System.out.println("Min " + minRec(tree.root())); // 8
        System.out.println("Min " + min(tree.root())); // 8

    }


    private static Integer minRec(BTreeNode<Integer> node) {
        if(node == null) throw  new IllegalArgumentException("Not a valid tree");
        if(node.left != null) return minRec(node.left);
        return node.data;
    }


    private static Integer min(BTreeNode<Integer> node) {
        if(node == null) throw  new IllegalArgumentException("Not a valid tree");
        while (node.left != null) node = node.left;
        return node.data;
    }
}
