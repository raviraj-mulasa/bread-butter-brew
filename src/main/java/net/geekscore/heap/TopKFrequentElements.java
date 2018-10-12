package net.geekscore.heap;

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 */
public class TopKFrequentElements {

    public static void main(String[] args) {

        System.out.println("------------------");
        // [1, 2]
        System.out.println(topKFreqElems(new int[]{1,1,1,2,2,3}, 2));
        // [1]
        System.out.println(topKFreqElems(new int[]{1,1,1,2,2,3}, 1));
        // [1,2,3,4]
        System.out.println(topKFreqElems(new int[]{1,1,1,2,2,3,3,4,4,5,6,6,7,8}, 4));
        // [9, 10, 11, 12]
        System.out.println(topKFreqElems(new int[]{9, 10, 11, 12, 9, 9, 9, 12, 11, 11}, 4));
        // [-1,2]
        System.out.println(topKFreqElems(new int[]{4,1,-1,2,-1,2,3}, 2));

        System.out.println("------------------");
        // [1, 2]
        System.out.println(topKFreqElems1(new int[]{1,1,1,2,2,3}, 2));
        // [1]
        System.out.println(topKFreqElems1(new int[]{1,1,1,2,2,3}, 1));
        // [1,2,3,4]
        System.out.println(topKFreqElems1(new int[]{1,1,1,2,2,3,3,4,4,5,6,6,7,8}, 4));
        // [9, 10, 11, 12]
        System.out.println(topKFreqElems1(new int[]{9, 10, 11, 12, 9, 9, 9, 12, 11, 11}, 4));
        // [-1,2]
        System.out.println(topKFreqElems1(new int[]{4,1,-1,2,-1,2,3}, 2));


    }

    // O(n + nlogk + klogk) time
    private static final List<Integer> topKFreqElems(final int[] nums, int k) {
        if(nums == null || nums.length == 0 || k<0) return Collections.emptyList();

        final Map<Integer, Integer> frequencyMap = new HashMap<>();

        for(final int num: nums) frequencyMap.put(num, frequencyMap.getOrDefault(num,0)+1);

        final PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue()) == 0 ? o1.getKey().compareTo(o2.getKey()) : o1.getValue().compareTo(o2.getValue());
            }
        });
        for (final Map.Entry<Integer, Integer> entry: frequencyMap.entrySet()) {
            System.out.println(minHeap);
            if(minHeap.size() < k) {
                minHeap.offer(entry);
                continue;
            }
            if (minHeap.peek().getValue() < entry.getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        final List<Integer>  kFreqElems = new LinkedList<>();
        while(minHeap.size() > 0) {
            kFreqElems.add(minHeap.poll().getKey());
        }
        Collections.sort(kFreqElems);
        return kFreqElems;
    }


    // O(n + klogk) time
    private static final List<Integer> topKFreqElems1(final int[] nums, int k) {
        if(nums == null || nums.length == 0 || k<0) return Collections.emptyList();
        final Map<Integer, Integer> frequencyMap = new HashMap<>();
        int highFreq = Integer.MIN_VALUE;
        for(final int num: nums) {
            final int freq = frequencyMap.getOrDefault(num,0)+1;
            frequencyMap.put(num, freq);
            highFreq = Math.max(highFreq, freq);
        }
        final List<Integer>[] bucket = new List[highFreq+1];
        for (final Map.Entry<Integer, Integer> entry: frequencyMap.entrySet()) {
            final int freq   = entry.getValue();
            if (bucket[freq] == null) bucket[freq] = new LinkedList();
            bucket[freq].add(entry.getKey());
        }
        final List<Integer> kFreqElems = new LinkedList<>();
        while (k > 0){
            final List<Integer> elems = bucket[highFreq--];
            if(null != elems) {
                kFreqElems.addAll(elems.subList(0, Math.min(k, elems.size())));
                k -= elems.size();
            }
        }
        Collections.sort(kFreqElems);
        return kFreqElems;
    }
}
