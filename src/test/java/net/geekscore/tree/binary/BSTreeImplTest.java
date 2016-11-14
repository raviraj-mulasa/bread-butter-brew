package net.geekscore.tree.binary;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by ravirajmulasa on 8/28/16.
 */
public class BSTreeImplTest {

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
    public void testInsert() {
        Assert.assertTrue(BST_TREE.find(-3).isLeaf());
        Assert.assertTrue(BST_TREE.find(0).isLeaf());
        Assert.assertTrue(BST_TREE.find(2).isLeaf());
        Assert.assertTrue(BST_TREE.find(4).isLeaf());
        Assert.assertTrue(BST_TREE.find(8).isLeaf());
        Assert.assertTrue(BST_TREE.find(20).isLeaf());
        Assert.assertTrue(BST_TREE.find(5).getRight() == null);
    }

    @Test
    public void testFind() {
        final BTreeNode<Integer> nodeFound = BST_TREE.find(5);
        Assert.assertNotNull(nodeFound);
        final BTreeNode<Integer> nodeNotFound = BST_TREE.find(7);
        Assert.assertNull(nodeNotFound);
    }

    @Test
    public void testRoot() {
        BTreeNode<Integer> nodeFound = BST_TREE.find(1);
        Assert.assertNull(nodeFound.getParent());
        nodeFound = BST_TREE.find(4);
        Assert.assertTrue(nodeFound.getParent().getData() == 3);
    }

    @Test
    public void testDelete() {
//        Assert.assertNotNull(BST_TREE.delete(4));
//        Assert.assertNull(BST_TREE.find(4));
    }
}
