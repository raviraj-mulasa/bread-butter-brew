package edu.learn.me.list;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by ravirajmulasa on 8/24/16.
 */
public final class SinglyLinkedList {

    private AtomicInteger size  = new AtomicInteger(0);

    private SingleListNode head = null;


    public SinglyLinkedList() {
        this.head = null;
        this.size.set(0);
    }

    public final SingleListNode getHead() {
        return head;
    }

//    @Override
//    public final String toString() {
//        SingleListNode curr = head;
//        while (curr != null) {
//            System.out.print(String.format("%d-->", curr.getData()));
//            final SingleListNode next = curr.getNext();
//            if(next == head) {
//                System.out.print(String.format("%d", next.getData()));
//                break;
//            }
//            curr = next;
//        }
//    }
}
