package net.geekscore.tree.binary.path;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given a binary tree, return all root-to-leaf paths.
 * For example, given the following binary tree:
 *
 *      1
 *    /   \
 *   2     3
 *   \
 *   5
 *
 * All root-to-leaf paths are:
 * ["1->2->5", "1->3"]
 */

public class BinaryTreePaths {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]
                {1,2,3,null,5,null,null});
        TreeUtil.print(tree);
        System.out.println("Paths: "+paths(tree.root()));

        System.out.println("----------");
        tree = TreeUtil.treeOf(new Integer[]
                {15,5,6,11,-8,1,3,9,2,6});
        TreeUtil.print(tree);
        System.out.println("Paths: "+paths(tree.root()));

    }

    private static List<String> paths(BTreeNode<Integer> root) {
        if(null == root) return Collections.emptyList();
        final List<String> paths = new LinkedList<>();
        pathsHelper(root, new StringBuilder(), paths);
        return paths;
    }

    private static void pathsHelper(BTreeNode<Integer> node, final StringBuilder pathSoFar, final List<String> paths) {
        if(null == node) return;
        final int temp = pathSoFar.length();
        pathSoFar.append(node.data);
        if(node.left == null && null == node.right) {
            paths.add(pathSoFar.toString());
            pathSoFar.delete(temp, pathSoFar.length());
            return;
        }
        pathSoFar.append("->");
        pathsHelper(node.left, pathSoFar, paths);
        pathsHelper(node.right, pathSoFar, paths);
        pathSoFar.delete(temp, pathSoFar.length());
    }


}
