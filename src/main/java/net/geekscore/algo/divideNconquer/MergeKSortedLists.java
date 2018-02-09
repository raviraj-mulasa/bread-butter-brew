package net.geekscore.algo.divideNconquer;

import net.geekscore.list.ListNode;
import net.geekscore.list.SingleLinkedList;
import net.geekscore.list.SingleListUtil;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.function.Function;

public class MergeKSortedLists {

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

        System.out.println("-------- Heap : K SortedLists(list1, list2, list3) --------");
        SingleListUtil.printSingleList(mergeKListsHeap(new SingleLinkedList[]{list1, list2, list3}));

        System.out.println("-------- Heap : K SortedLists(list1, list2, list3, list4) --------");
        SingleListUtil.printSingleList(mergeKListsHeap(new SingleLinkedList[]{list1, list2, list3,list4}));

        System.out.println("-------- Heap : K SortedLists(null, null, null) --------");
        SingleListUtil.printSingleList(mergeKListsHeap(new SingleLinkedList[]{null, null, null}));

        System.out.println("-------- Heap : K SortedLists(null, list2, list3) --------");
        SingleListUtil.printSingleList(mergeKListsHeap(new SingleLinkedList[]{null, list2, list3}));

        System.out.println("-------- Heap : K SortedLists(list1, null, list3) --------");
        SingleListUtil.printSingleList(mergeKListsHeap(new SingleLinkedList[]{list1, null, list3}));

        System.out.println("-------- Heap : K SortedLists(list1, list2, null) --------");
        SingleListUtil.printSingleList(mergeKListsHeap(new SingleLinkedList[]{list1, list2, null}));


        System.out.println("-------- D&N : K SortedLists(list1, list2, list3) --------");
        SingleListUtil.printSingleList(mergeKSortedLists(new SingleLinkedList[]{list1, list2, list3}));

        System.out.println("-------- D&N : K SortedLists(list1, list2, list3, list4) --------");
        SingleListUtil.printSingleList(mergeKSortedLists(new SingleLinkedList[]{list1, list2, list3, list4}));

        System.out.println("-------- D&N : K SortedLists(null, null, null) --------");
        SingleListUtil.printSingleList(mergeKSortedLists(new SingleLinkedList[]{null, null, null}));

        System.out.println("-------- D&N : K SortedLists(null, list2, list3) --------");
        SingleListUtil.printSingleList(mergeKSortedLists(new SingleLinkedList[]{null, list2, list3}));

        System.out.println("-------- D&N : K SortedLists(list1, null, list3) --------");
        SingleListUtil.printSingleList(mergeKSortedLists(new SingleLinkedList[]{list1, null, list3}));

        System.out.println("-------- D&N : K SortedLists(list1, list2, null) --------");
        SingleListUtil.printSingleList(mergeKSortedLists(new SingleLinkedList[]{list1, list2, null}));

    }

    private static final SingleLinkedList createSingleLinkedList(final int nodeCount, final Function<Integer, Integer> function) {
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<Integer>();
        for(int i=1 ; i <= nodeCount; i++) {
            singleLinkedList.add(function.apply(i));
        }
        return singleLinkedList;
    }


    private static final SingleLinkedList mergeKListsHeap(final SingleLinkedList[] lists) {

        final AbstractQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, (o1, o2) -> {
            final int comparison = o1.getData().compareTo(o2.getData());
            if(comparison == 0) return o1.hashCode() - o2.hashCode();
            else return comparison;
        });

        final ListNode[] currs = new ListNode[lists.length];
        for (int i = 0; i < lists.length; i++) { /* Insert list heads */
            if (lists[i] !=null && lists[i].getHead() != null) {
                currs[i] = lists[i].getHead();
                minHeap.offer(currs[i]);
            }
        }
        SingleLinkedList<Integer> mergedList = new SingleLinkedList<Integer>();
        while (!minHeap.isEmpty()) {
            final ListNode<Integer> heapTop = minHeap.poll();
            if(null != heapTop) mergedList.add(heapTop.getData());
            if(null != heapTop && null != heapTop.getNext()) minHeap.add(heapTop.getNext());
        }
        return mergedList;
    }

    private static final SingleLinkedList mergeKSortedLists(final SingleLinkedList<Integer>[] lists) {
        if(lists == null || lists.length == 0) return null;
        if(lists.length == 1) return lists[0];
        if(lists.length == 2) return Merge2SortedLists.merge2SortedLists(lists[0], lists[1]);
        final int mid = lists.length%2 == 0 ? (lists.length/2) : (lists.length/2+1);
        final SingleLinkedList<Integer>[] left  = Arrays.copyOfRange(lists, 0, mid);
        final SingleLinkedList<Integer>[] right = Arrays.copyOfRange(lists,mid, lists.length);
        return Merge2SortedLists.merge2SortedLists(mergeKSortedLists(left), mergeKSortedLists(right));
    }


}
