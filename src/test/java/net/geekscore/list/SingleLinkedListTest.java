package net.geekscore.list;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ravirajmulasa on 8/23/16.
 */
public class SingleLinkedListTest {

    @Test
    public void testPrint() {
        final SingleLinkedList singleLinkedList = createSingleLinkedList(11);
        singleLinkedList.print();
    }

    @Test
    public void testReverse() {
        final SingleLinkedList singleLinkedList = createSingleLinkedList(11);
        singleLinkedList.print();
        SingleListUtil.reverse(singleLinkedList);
        singleLinkedList.print();
    }

    @Test
    public void testSize() {
        final SingleLinkedList singleLinkedList = createSingleLinkedList(0);
        Assert.assertEquals(0, singleLinkedList.size());
    }

    @Test
    public void testGet() {
        final SingleLinkedList singleLinkedList = createSingleLinkedList(100);
        Assert.assertEquals(100, singleLinkedList.get(99));
    }

    @Test
    public void testDelete() {
        final SingleLinkedList singleLinkedList = createSingleLinkedList(100);
        Assert.assertEquals(100, singleLinkedList.get(99));
        Assert.assertEquals(100, singleLinkedList.delete(99));
        Assert.assertEquals(null, singleLinkedList.get(99));
        Assert.assertEquals(99, singleLinkedList.get(98));
        Assert.assertEquals(47, singleLinkedList.get(46));
    }

    @Test
    public void testFind() {
        final SingleLinkedList singleLinkedList = createSingleLinkedList(100);
        Assert.assertEquals(99, singleLinkedList.find(new Integer(100)));
    }


    @Test
    public void testSet() {
        final SingleLinkedList singleLinkedList = createSingleLinkedList(100);
        Assert.assertEquals(100, singleLinkedList.get(99));
        singleLinkedList.set(99, 1234);
        Assert.assertEquals(1234, singleLinkedList.get(99));
    }


    private final SingleLinkedList createSingleLinkedList(final int nodeCount) {
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<Integer>();
        for(int i=1 ; i <= nodeCount; i++) {
            singleLinkedList.add(i);
        }
        return singleLinkedList;
    }
}
