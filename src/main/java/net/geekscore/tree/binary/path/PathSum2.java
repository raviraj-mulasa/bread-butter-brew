package net.geekscore.tree.binary.path;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * For example:
 * Given the below binary tree and sum = 22,
 *              5
 *             / \
 *            4   8
 *           /   / \
 *          11  13  4
 *         /  \    / \
 *        7    2  5   1
 *
 *  returns [[5,4,11,2],[5,8,4,5]]
 */
public class PathSum2 {
    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,null,5,1});
        TreeUtil.print(tree);
        System.out.println(pathSumPaths(tree, 22)); // [[5, 4, 11, 2], [5, 8, 4, 5]]
        System.out.println(pathSumPaths(tree, 12)); // []
        System.out.println(pathSumPaths(tree, 21)); // []
        System.out.println(pathSumPaths(tree, 26)); // [[5, 8, 13]]

    }

    private static List<List<Integer>> pathSumPaths(final IBTree<Integer> tree, int sum) {
        if (null == tree || tree.root() == null) return Collections.emptyList();
        List<List<Integer>> paths = new LinkedList<>();
        pathSumPathsHelper(tree.root(), sum, paths, new LinkedList<>());
        return paths;
    }

    private static void pathSumPathsHelper(final BTreeNode<Integer> node, int sum, List<List<Integer>> paths, List<Integer> pathSoFar) {
        if (node == null) return;
        // choose
        pathSoFar.add(node.data);
        sum -= node.data;
        if(node.left == null && node.right == null && sum == 0) {
            paths.add(new ArrayList<Integer>(pathSoFar));
        }
        // Explore
        pathSumPathsHelper(node.left, sum, paths, pathSoFar);
        pathSumPathsHelper(node.right, sum, paths, pathSoFar);
        // Un choose
        pathSoFar.remove(pathSoFar.size() - 1);

    }

}
