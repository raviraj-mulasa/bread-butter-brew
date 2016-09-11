package edu.learn.me.tree;

import edu.learn.me.tree.binary.BSTreeImpl;
import edu.learn.me.tree.binary.BTreeNode;
import edu.learn.me.tree.binary.IBTree;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ravirajmulasa on 9/6/16.
 */
public final class TreeUtilTest {

    private static IBTree<Integer> BST_TREE = null;

    @BeforeClass
    public static void init() {
        BST_TREE = new BSTreeImpl<>();
        BST_TREE.insert(1);
        BST_TREE.insert(6);
        BST_TREE.insert(5);
        BST_TREE.insert(3);
        BST_TREE.insert(2);
        BST_TREE.insert(4);
        BST_TREE.insert(-1);
        BST_TREE.insert(-3);
        BST_TREE.insert(0);
        BST_TREE.insert(17);
        BST_TREE.insert(8);
        BST_TREE.insert(20);
    }

    @Test
    public void testInOrder() {
        final List<Integer> expected = Arrays.asList(-3, -1, 0, 1, 2, 3, 4, 5, 6, 8, 17, 20);
        Assert.assertThat(TreeUtil.inOrder(BST_TREE), CoreMatchers.is(expected));
    }

    @Test
    public void testPreOrder() {
        final List<Integer> expected = Arrays.asList(1, -1, -3, 0, 6, 5, 3, 2, 4, 17, 8, 20);
        Assert.assertThat(TreeUtil.preOrder(BST_TREE), CoreMatchers.is(expected));
    }

    @Test
    public void testPostOrder() {
//        System.out.println(TreeUtil.postOrder(bstTree));
    }

    @Test
    public void testInOrderSuccessor() {
        BTreeNode<Integer> inOrderSuccessor = TreeUtil.inOrderSuccessor(BST_TREE.find(4));
        Assert.assertSame(5, inOrderSuccessor.getData());

        inOrderSuccessor = TreeUtil.inOrderSuccessor(BST_TREE.find(1));
        Assert.assertSame(2, inOrderSuccessor.getData());

        inOrderSuccessor = TreeUtil.inOrderSuccessor(BST_TREE.find(-1));
        Assert.assertSame(0, inOrderSuccessor.getData());

    }

    @Test
    public void testIsBST() {
        final  IBTree<Integer> bstTree = new BSTreeImpl<>();
        bstTree.insert(1);
        bstTree.insert(6);
        bstTree.insert(5);
        bstTree.insert(3);
        bstTree.insert(2);
        bstTree.insert(4);
        bstTree.insert(-1);
        bstTree.insert(-3);
        bstTree.insert(0);
        bstTree.insert(17);
        bstTree.insert(8);
        bstTree.insert(20);

        Assert.assertTrue(TreeUtil.isBST(bstTree, Integer.MIN_VALUE, Integer.MAX_VALUE));

        final BTreeNode<Integer> node5 = bstTree.find(5);
        node5.setData(18);
        Assert.assertFalse(TreeUtil.isBST(bstTree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        node5.setData(5);

        final BTreeNode<Integer> node6 = bstTree.find(6);
        Assert.assertTrue(TreeUtil.isBST(node6, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void testHeightBinaryTree() {

        Assert.assertTrue(4 == TreeUtil.heightBinaryTree(BST_TREE));

        final  IBTree<Integer> bstTree = new BSTreeImpl<>();
        Assert.assertTrue(-1 == TreeUtil.heightBinaryTree(bstTree));

        bstTree.insert(0);
        bstTree.insert(1);
        bstTree.insert(-2);

        Assert.assertTrue(1 == TreeUtil.heightBinaryTree(bstTree));
    }


    @Test
    public void testFindMinInBST() {
        System.out.printf("%s%n", TreeUtil.findMinInBST(BST_TREE.root().getLeft()).getData());
    }

    @Test
    public void testFindMaxInBST() {
        System.out.printf("%s%n", TreeUtil.findMaxInBST(BST_TREE.root().getRight()).getData());
    }
}
