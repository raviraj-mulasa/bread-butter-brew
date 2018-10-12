package net.geekscore.tree.binary.path;

import net.geekscore.tree.TreeUtil;
import net.geekscore.tree.binary.BTreeNode;
import net.geekscore.tree.binary.IBTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (traveling only from parent nodes to child nodes).
 *
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * Example:
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *          10
 *        /  \
 *       5   -3
 *      / \    \
 *     3   2   11
 *    / \   \
 *   3  -2   1

 Return 3. The paths that sum to 8 are:

 1.  5 -> 3
 2.  5 -> 2 -> 1
 3. -3 -> 11
 */
public class PathSum3 {

    public static void main(String[] args) {
        IBTree<Integer> tree = TreeUtil.treeOf(new Integer[]{10,5,-3,3,2,null,11,3,-2,null,1,null,null});
        TreeUtil.print(tree);
        System.out.println("Paths count: "+pathSumCount(tree, 8)); // 3 - [[5, 3], [5, 2, 1], [-3, 11]]


        tree = TreeUtil.treeOf(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,null,5,1});
        TreeUtil.print(tree);
        System.out.println("Paths count: "+pathSumCount(tree, 22)); // 3 - [[5, 4, 11, 2], [5, 8, 4, 5], [4,7,11]]

        tree = TreeUtil.treeOf(new Integer[]{-2,null,-3});
        TreeUtil.print(tree);
        System.out.println("Paths count: "+pathSumCount(tree, -5)); // 1 - [[-2, -3]]

        tree = TreeUtil.treeOf(new Integer[]{1,2,null,3,null,4,null,5});
        TreeUtil.print(tree);
        System.out.println("Paths count: "+pathSumCount(tree, 6)); // 1 - [[1,2,3]]


        tree = TreeUtil.treeOf(new Integer[]{1,-2,-3,1,3,-2,null,-1,null,null,null,null,null,null,null});
        TreeUtil.print(tree);
        System.out.println("Paths count: "+pathSumCount(tree, -1)); // 4 - [[-1], [-2, 1], [1, -2], [1, -2, 1, -1]]
    }

    private static List<List<Integer>> pathSumCount(final IBTree<Integer> tree, int sum) {
        if (null == tree) return Collections.emptyList();
        return pathSums(tree.root(), sum);
    }

    private static List<List<Integer>> pathSums(final BTreeNode<Integer> node, int sum) {
        if(null == node) return Collections.emptyList();
        final List<List<Integer>> paths = new LinkedList<>();
        paths.addAll(pathSums(node.left, sum)); // paths that add to sum in left sub tree
        paths.addAll(pathSumCountHelper(node, sum, new LinkedList<>())); // paths that add to sum from parent
        paths.addAll(pathSums(node.right, sum)); // paths that add to sum in right sub tree
        return paths;
    }

    private static List<List<Integer>> pathSumCountHelper(final BTreeNode<Integer> node, final int sum, final List<Integer> pathSoFar) {
        if(null == node) return Collections.emptyList();
        final List<List<Integer>> paths = new LinkedList<>();
        pathSoFar.add(node.data);
        if(sum - node.data == 0) paths.add(new ArrayList<>(pathSoFar));
        paths.addAll(pathSumCountHelper(node.left, sum-node.data, pathSoFar));
        paths.addAll(pathSumCountHelper(node.right, sum-node.data, pathSoFar));
        pathSoFar.remove(pathSoFar.size()-1);
        return paths;
    }


}
