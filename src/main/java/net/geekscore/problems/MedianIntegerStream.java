package net.geekscore.problems;

import java.math.BigInteger;
import java.util.*;

public class MedianIntegerStream {



    public static void main(String[] args) {

        final MedianFinderHeap medianFinderHeap = new MedianFinderHeap();
        for (int num: new int[]{2,3,4}) {
            medianFinderHeap.add(num);
        }
        System.out.println(medianFinderHeap.median()); // 3

        medianFinderHeap.clear();
        for (int num: new int[]{2,3,4}) {
            medianFinderHeap.add(num);
        }
        System.out.println(medianFinderHeap.median()); // 3

        medianFinderHeap.clear();
        for (int num: new int[]{2,3}) {
            medianFinderHeap.add(num);
        }
        System.out.println(medianFinderHeap.median()); // 2.5

        medianFinderHeap.clear();
        for (int num: new int[]{1,2}) {
            medianFinderHeap.add(num);
        }
        System.out.println(medianFinderHeap.median()); // 1.5

        medianFinderHeap.clear();
        for (int num: new int[]{1}) {
            medianFinderHeap.add(num);
        }
        System.out.println(medianFinderHeap.median()); // 1

        medianFinderHeap.clear();
        for (int num: new int[]{7,8,9,6,10,13,14,15,11}) {
            medianFinderHeap.add(num);
        }
        System.out.println(medianFinderHeap.median()); // 10

        medianFinderHeap.clear();
        for (int num: new int[]{7,8,9,6,10,13,14,15,11,12}) {
            medianFinderHeap.add(num);
        }
        System.out.println(medianFinderHeap.median()); // 10.5

        System.out.println("-----------------------");

        final MedianFinderSet medianFinderSet = new MedianFinderSet();
        for (int num: new int[]{2,3,4}) {
            medianFinderSet.add(num);
        }
        System.out.println(medianFinderSet.median()); // 3

        medianFinderSet.clear();
        for (int num: new int[]{2,3,4}) {
            medianFinderSet.add(num);
        }
        System.out.println(medianFinderSet.median()); // 3

        medianFinderSet.clear();
        for (int num: new int[]{2,3}) {
            medianFinderSet.add(num);
        }
        System.out.println(medianFinderSet.median()); // 2.5

        medianFinderSet.clear();
        for (int num: new int[]{1,2}) {
            medianFinderSet.add(num);
        }
        System.out.println(medianFinderSet.median()); // 1.5

        medianFinderSet.clear();
        for (int num: new int[]{1}) {
            medianFinderSet.add(num);
        }
        System.out.println(medianFinderSet.median()); // 1

        medianFinderSet.clear();
        for (int num: new int[]{7,8,9,6,10,13,14,15,11}) {
            medianFinderSet.add(num);
        }
        System.out.println(medianFinderSet.median()); // 10

        medianFinderSet.clear();
        for (int num: new int[]{7,8,9,6,10,13,14,15,11,12}) {
            medianFinderSet.add(num);
        }
        System.out.println(medianFinderSet.median()); // 10.5



    }

    public static class MedianFinderHeap {

        private final Queue<Integer> minHeap; // holds elems > running median

        private final Queue<Integer> maxHeap; // holds elems < running median

        /** initialize your data structure here. */
        MedianFinderHeap() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        }

        void add(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            while (maxHeap.size() < minHeap.size()) maxHeap.offer(minHeap.poll()); // always maxHeap size > minHeap size
        }

        double median() {
            return maxHeap.size() == minHeap.size() ? (maxHeap.peek()+minHeap.peek())/2.0 : maxHeap.peek();

        }

        void clear() {
            minHeap.clear();
            maxHeap.clear();
        }
    }

    public static class MedianFinderSet {

        private final SortedSet<Integer> minSet; // holds elems > running median

        private final SortedSet<Integer> maxSet; // holds elems < running median

        MedianFinderSet() {
            minSet = new TreeSet<>(Comparator.reverseOrder());
            maxSet = new TreeSet<>();
        }

        void add(int num) {
            minSet.add(num);
            final int minOfMinSet = minSet.last();
            maxSet.add(minOfMinSet);
            minSet.remove(minOfMinSet);
            while (maxSet.size() > minSet.size()) {
                // minSet size > maxSet size always
                final int maxOfMaxSet = maxSet.last();
                minSet.add(maxOfMaxSet);
                maxSet.remove(maxOfMaxSet);
            }
        }

        double median() {
            return maxSet.size() == minSet.size()
                    ?
                    BigInteger.valueOf(minSet.last()).add(BigInteger.valueOf(maxSet.last())).doubleValue()/2.0
                    :
                    minSet.last();
        }

        void clear() {
            this.maxSet.clear();
            this.minSet.clear();
        }
    }
}

