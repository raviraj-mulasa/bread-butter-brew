package edu.learn.me.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ravirajmulasa on 8/23/16.
 */
public class SinglyLinkedListTest {

    private SinglyLinkedList singlyLinkedList = null;

    @Before
    public void setUp() {

        singlyLinkedList = createSinglyLinkedList(1);
        singlyLinkedList.print();
        System.out.println();
        System.out.println(singlyLinkedList.get(20) + "");
        System.out.println(singlyLinkedList.delete(20) + "");
        System.out.println(singlyLinkedList.get(20) + "");
        System.out.println(singlyLinkedList.find(22) + "");
        System.out.println(singlyLinkedList.get(0) + "");
        System.out.println(singlyLinkedList.delete(0) + "");
        System.out.println(singlyLinkedList.get(0) + "");
    }


    @Test
    public void testLoopExistenceFloyds() {
        ;
    }



    private final SinglyLinkedList createSinglyLinkedList(final int nodeCount) {
        SinglyLinkedList<Integer> singlyLinkedList    = new SinglyLinkedList<Integer>();
        for(int i=1 ; i <= nodeCount; i++) {
            singlyLinkedList.add(i);
        }
        return  singlyLinkedList;
    }
}
