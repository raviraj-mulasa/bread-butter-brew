package net.geekscore.stack;

import java.util.*;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 * Given the list [[1,1],2,[1,1]],
 *
 * By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,1,2,1,1].
 *
 * Example 2:
 * Given the list [1,[4,[6]]],
 *
 * By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,4,6].
 */
public class FlattenNestedListIterator {


    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    private interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    private static class NestedIntegerImpl implements NestedInteger {

        private final Integer value;
        private final List<NestedInteger> list;

        NestedIntegerImpl(final Integer value) {
            this(value, null);
        }

        NestedIntegerImpl(final List<NestedInteger> list) {
            this(null, list);
        }

        NestedIntegerImpl(final Integer value, final List<NestedInteger> list) {
            this.value = value;
            this.list = list;
        }

        @Override
        public boolean isInteger() {
            return this.value != null;
        }

        @Override
        public Integer getInteger() {
            return this.value;
        }

        @Override
        public List<NestedInteger> getList() {
            return this.list;
        }

        @Override
        public String toString() {
            final StringBuilder stringBuilder = new StringBuilder();
            this.toStringHelper(stringBuilder, this);
            return stringBuilder.toString().trim();
        }

        private void toStringHelper(final StringBuilder stringBuilder, NestedIntegerImpl node) {
            if (null != node) {
                if(node.value != null) stringBuilder.append(node.value);
                if (node.list != null) {
                    stringBuilder.append("[");
                    for (final NestedInteger nestedInteger : node.list) {
                        this.toStringHelper(stringBuilder, (NestedIntegerImpl) nestedInteger);
                        stringBuilder.append(",");
                    }
                    if(stringBuilder.length() > 1) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    stringBuilder.append("]");
                }
            }
        }
    }


    public static void main(String[] args) {

        final NestedInteger one = new NestedIntegerImpl(1);
        final NestedInteger ones = new NestedIntegerImpl(Arrays.asList(one, one));
        final NestedInteger two = new NestedIntegerImpl(2);
        final NestedInteger nine = new NestedIntegerImpl(9);
        final NestedInteger ten = new NestedIntegerImpl(10);
        final NestedInteger nineTen = new NestedIntegerImpl(Arrays.asList(nine, ten));

        final List<NestedInteger> list1 = new ArrayList<>();
        list1.add(ones);
        list1.add(two);
        list1.add(nineTen);
        System.out.println("Nested List: "+list1); // [[1,1],2,[9,10]]

        Iterator<Integer> iterator = new NestedIterator0(list1);
        List<Integer> flattenList = new LinkedList<>();
        while (iterator.hasNext()){
            flattenList.add(iterator.next());
        }
        System.out.println("Flattened List 0: "+flattenList); // [1,1,2,9,10]

        iterator = new NestedIterator1(list1);
        flattenList = new LinkedList<>();
        while (iterator.hasNext()){
            flattenList.add(iterator.next());
        }
        System.out.println("Flattened List 1: "+flattenList); // [1,1,2,9,10]

        iterator = new NestedIterator2(list1);
        flattenList = new LinkedList<>();
        while (iterator.hasNext()){
            flattenList.add(iterator.next());
        }
        System.out.println("Flattened List 2: "+flattenList); // [1,1,2,9,10]

        System.out.println("---------------------------");

        final NestedInteger six = new NestedIntegerImpl(6);
        final NestedInteger four = new NestedIntegerImpl(4, Collections.singletonList(six));
        final NestedInteger nestedOne = new NestedIntegerImpl(1, Collections.singletonList(four));
        final List<NestedInteger> list2 = Collections.singletonList(nestedOne);
        System.out.println("Nested List: "+list2); //  [1,[4,[6]]]

        iterator = new NestedIterator0(list2);
        flattenList = new LinkedList<>();
        while (iterator.hasNext()){
            flattenList.add(iterator.next());
        }
        System.out.println("Flattened List 0: "+flattenList); // [1,4,6]

        iterator = new NestedIterator1(list2);
        flattenList = new LinkedList<>();
        while (iterator.hasNext()){
            flattenList.add(iterator.next());
        }
        System.out.println("Flattened List 1: "+flattenList); // [1,4,6]


        iterator = new NestedIterator2(list2);
        flattenList = new LinkedList<>();
        while (iterator.hasNext()){
            flattenList.add(iterator.next());
        }
        System.out.println("Flattened List 2: "+flattenList); // [1,4,6]

        System.out.println("---------------------------");

        final NestedInteger empty = new NestedIntegerImpl(null, Collections.emptyList());
        final List<NestedInteger> list3 = Collections.singletonList(empty);
        System.out.println("Nested List: "+list3); //  [[]]

        iterator = new NestedIterator0(list3);
        flattenList = new LinkedList<>();
        while (iterator.hasNext()){
            flattenList.add(iterator.next());
        }
        System.out.println("Flattened List 0: "+flattenList); // []

        iterator = new NestedIterator1(list3);
        flattenList = new LinkedList<>();
        while (iterator.hasNext()){
            flattenList.add(iterator.next());
        }
        System.out.println("Flattened List 1: "+flattenList); // []

        iterator = new NestedIterator2(list3);
        flattenList = new LinkedList<>();
        while (iterator.hasNext()){
            flattenList.add(iterator.next());
        }
        System.out.println("Flattened List 2: "+flattenList); // []

    }

    // Approach 1 - Tracking all elements via DFS
    private static class NestedIterator0 implements Iterator<Integer> {
        int pointer = -1;
        List<Integer> elems = new LinkedList<>();

        NestedIterator0(List<NestedInteger> nestedList) {
            process(nestedList);
        }

        private void process(List<NestedInteger> nestedList) {
            if(null != nestedList) {
                for (final NestedInteger list : nestedList) {
                    if(list.isInteger()) elems.add(list.getInteger());
                    process(list.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return elems.get(++pointer);
        }

        @Override
        public boolean hasNext() {
            return pointer < elems.size() - 1;
        }
    }


    // Approach 2 - Using stack of NestNestedInteger
    private static class NestedIterator1 implements Iterator<Integer> {

        Deque<NestedInteger> stack = new LinkedList<>();

        NestedIterator1(List<NestedInteger> nestedList) {
            pushNestedList2Stack(nestedList);
        }

        @Override
        public Integer next() {
            return hasNext() ? stack.pop().getInteger() : null;
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                if (stack.peek().isInteger()) return true;
                pushNestedList2Stack(stack.pop().getList());
            }
            return false;
        }

        private void pushNestedList2Stack(List<NestedInteger> nestedList) {
            if(null != nestedList) {
                for (int i = nestedList.size()-1; i > -1 ; i--) {
                    stack.push(nestedList.get(i));
                }
            }
        }
    }

    // Approach 2 - Using stack of NestNestedInteger Iterators
    private static class NestedIterator2 implements Iterator<Integer> {

        Deque<Iterator<NestedInteger>> stack = new LinkedList<>();
        Integer next;

        NestedIterator2(List<NestedInteger> nestedList) {
            this.next = null;
            if(nestedList != null && !nestedList.isEmpty()) {
                this.stack.push(nestedList.iterator());
            }
        }

        @Override
        public Integer next() {
            final Integer result = this.next;
            this.next = null;
            return result;
        }


        @Override
        public boolean hasNext() {
            while (!this.stack.isEmpty() && this.next == null) {
                if (this.stack.peek().hasNext()) {
                    final NestedInteger nestedInteger = this.stack.peek().next();
                    if (nestedInteger.isInteger()) this.next = nestedInteger.getInteger();
                    else this.stack.push(nestedInteger.getList().iterator());
                } else {
                    this.stack.pop();
                }
            }
            return this.next != null;
        }
    }
}
