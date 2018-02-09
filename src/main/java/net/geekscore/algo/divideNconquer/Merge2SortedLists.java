package net.geekscore.algo.divideNconquer;

import net.geekscore.list.ListNode;
import net.geekscore.list.SingleLinkedList;
import net.geekscore.list.SingleListUtil;

import java.util.function.Function;

public class Merge2SortedLists {

    public static void main(String[] args) {

        final SingleLinkedList list1 = createSingleLinkedList(10, Function.identity());
        System.out.print("List 1 : ");
        SingleListUtil.printSingleList(list1);


        final SingleLinkedList list2 = createSingleLinkedList(3, (i -> i * i + 3));
        System.out.print("List 2 : ");
        SingleListUtil.printSingleList(list2);

        final SingleLinkedList list3 = createSingleLinkedList(13, (i -> i * i + 5));
        System.out.print("List 3 : ");
        SingleListUtil.printSingleList(list3);


        final SingleLinkedList list4 = createSingleLinkedList(5, (i -> i * i + 7));
        System.out.print("List 4 : ");
        SingleListUtil.printSingleList(list4);


        System.out.println("-------- D&N : 2 SortedLists (null, null) --------");
        SingleListUtil.printSingleList(merge2SortedLists(null, null));

        System.out.println("-------- D&N : 2 SortedLists (null, list2) --------");
        SingleListUtil.printSingleList(merge2SortedLists(null, list2));

        System.out.println("-------- D&N : 2 SortedLists (list1, null) --------");
        SingleListUtil.printSingleList(merge2SortedLists(list1, null));

        System.out.println("-------- D&N : 2 SortedLists (list1, list2) --------");
        SingleListUtil.printSingleList(merge2SortedLists(list1, list2));

        System.out.println("-------- D&N : 2 SortedLists (list1, list4) --------");
        SingleListUtil.printSingleList(merge2SortedLists(list1, list4));

        System.out.println("-------- D&N : 2 SortedLists (list3, list4) --------");
        SingleListUtil.printSingleList(merge2SortedLists(list3, list4));

        System.out.println("-------- D&N : 2 SortedLists (list3, list2) --------");
        SingleListUtil.printSingleList(merge2SortedLists(list3, list2));

    }

    private static final SingleLinkedList createSingleLinkedList(final int nodeCount, final Function<Integer, Integer> function) {
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<Integer>();
        for(int i=1 ; i <= nodeCount; i++) {
            singleLinkedList.add(function.apply(i));
        }
        return singleLinkedList;
    }


    public static final SingleLinkedList merge2SortedLists(final SingleLinkedList<Integer> list1, final SingleLinkedList<Integer> list2) {
        if(list1 == null && list2 != null) return list2;
        if(list1 != null && list2 == null) return list1;
        if(list1 == list2) return list1;
        ListNode<Integer> curr1 = list1.getHead();
        ListNode<Integer> curr2 = list2.getHead();
        SingleLinkedList<Integer> mergedList = new SingleLinkedList<Integer>();
        while (curr1 != null && curr2 != null) {
            if(curr1.getData().compareTo(curr2.getData()) < 0) {
                mergedList.add(curr1.getData());
                curr1 = curr1.getNext();
            }
            else if(curr1.getData().compareTo(curr2.getData()) > 0) {
                mergedList.add(curr2.getData());
                curr2 = curr2.getNext();
            }
            else {
                mergedList.add(curr1.getData());
                mergedList.add(curr2.getData());
                curr1 = curr1.getNext();
                curr2 = curr2.getNext();
            }
        }
        while (curr2 != null) {
            mergedList.add(curr2.getData());
            curr2 = curr2.getNext();
        }
        while (curr1 != null) {
            mergedList.add(curr1.getData());
            curr1 = curr1.getNext();
        }
        return mergedList;
    }
}
