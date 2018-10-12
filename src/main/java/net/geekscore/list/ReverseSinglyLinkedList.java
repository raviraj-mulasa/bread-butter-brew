package net.geekscore.list;

public class ReverseSinglyLinkedList {

    public static void main(String[] args) {
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<Integer>();
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(-3);
        singleLinkedList.add(4);
        SingleListUtil.printSingleList(singleLinkedList);

        SingleLinkedList<Integer> reversedList = new SingleLinkedList<>();
        reversedList.setHead(reverseRec(singleLinkedList.getHead()));
        SingleListUtil.printSingleList(reversedList);

        System.out.println("---------------------------");

        SingleLinkedList<Integer> singleLinkedList1 = new SingleLinkedList<>();
        singleLinkedList1.add(11);
        singleLinkedList1.add(21);
        singleLinkedList1.add(-31);
        singleLinkedList1.add(41);
        SingleListUtil.printSingleList(singleLinkedList1);

        SingleLinkedList<Integer> reversedList1 = new SingleLinkedList<>();
        reversedList1.setHead(reverse(singleLinkedList1.getHead()));
        SingleListUtil.printSingleList(reversedList1);
    }

    private static ListNode<Integer> reverseRec(ListNode<Integer> node) {
        // if head is null or only one node, it's reverse of itself.
        if(null == node || null == node.getNext()) return node;
        // reverse the sub-list leaving the head node.
        ListNode<Integer> reverse = reverseRec(node.getNext());
        // head.next still points to the last element of reversed sub-list.
        node.getNext().setNext(node);
        // point last node to nil, (get rid of cycles)
        node.setNext(null);
        return reverse;
    }

    private static ListNode<Integer> reverse(ListNode<Integer> node) {
        ListNode<Integer> result = null, curr = node,  next = result;
        while (curr != null) {
            next = curr.getNext();
            curr.setNext(result);
            result = curr;
            curr = next;
        }
        return result;

    }
}
