package net.geekscore.tree.binary.bst;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

public class FindKeyBST {

    public static void main(String[] args) {
        final IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{8,3,10,1,6,null,14,null,null,4,7,null,null,13,null});
        TreeUtil.print(tree);

        System.out.println("Found 7 "+findRec(tree.root(), 7)); // true
        System.out.println("Found 27 "+findRec(tree.root(), 27)); // false
        System.out.println("Found 11 "+findRec(tree.root(), 11)); // false
        System.out.println("Found 13 "+findRec(tree.root(), 13)); // true

        System.out.println("--------------");

        System.out.println("Found 7 "+find(tree.root(), 7)); // true
        System.out.println("Found 27 "+find(tree.root(), 27)); // false
        System.out.println("Found 11 "+find(tree.root(), 11)); // false
        System.out.println("Found 13 "+find(tree.root(), 13)); // true
    }

    private static boolean findRec(BTreeNode<Integer> node, Integer key) {
        if(node != null) {
            final int comparison = key.compareTo(node.data);
            if(comparison == 0) return true;
            if(comparison < 0) return findRec(node.left, key);
            else return findRec(node.right, key);
        }
        return false;
    }


    private static boolean find(BTreeNode<Integer> node, Integer key) {
        while (node != null) {
            final int comparison = key.compareTo(node.data);
            if(0 == comparison) return true;
            if(comparison < 0) node=node.left;
            else node=node.right;
        }
        return false;
    }

}
