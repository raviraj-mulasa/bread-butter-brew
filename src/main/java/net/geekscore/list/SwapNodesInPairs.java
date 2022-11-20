package net.geekscore.list;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 */

public class SwapNodesInPairs {

    public static void main(String[] args) {
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<Integer>();
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(-3);
        singleLinkedList.add(4);
        SingleListUtil.printSingleList(singleLinkedList);

        swapPairs(singleLinkedList.getHead());
        SingleListUtil.printSingleList(singleLinkedList);

        System.out.println("---------------------------");

        SingleLinkedList<Integer> singleLinkedList1 = new SingleLinkedList<>();
        singleLinkedList1.add(11);
        singleLinkedList1.add(21);
        singleLinkedList1.add(-31);
        singleLinkedList1.add(41);
        SingleListUtil.printSingleList(singleLinkedList1);

        swapPairs(singleLinkedList1.getHead());
        SingleListUtil.printSingleList(singleLinkedList1);


        System.out.println("---------------------------");

        SingleLinkedList<Integer> singleLinkedList2 = new SingleLinkedList<>();
        singleLinkedList2.add(1);
        singleLinkedList2.add(21);
        singleLinkedList2.add(-31);
        SingleListUtil.printSingleList(singleLinkedList2);

        swapPairs(singleLinkedList2.getHead());
        SingleListUtil.printSingleList(singleLinkedList2);
    }



    public static void swapPairs(ListNode<Integer> head) {
        if(null == head) return;
        ListNode<Integer> curr = head;
        while(curr != null && curr.getNext() != null) {
            ListNode<Integer> next = curr.getNext();
            Integer tmp = next.getData();
            next.setData(curr.getData());
            curr.setData(tmp);
            curr = next.getNext();
        }
    }


}
