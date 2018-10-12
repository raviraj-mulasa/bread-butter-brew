package net.geekscore.design;

import java.util.*;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 * It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a
 * new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency),
 * the least recently used key would be evicted.
 *
 * Follow up: Could you do both operations in O(1) time complexity?
 *
 * Example:
 * LFUCache cache = new LFUCache( 2); // capacity
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.get(3);       // returns 3.
 * cache.put(4, 4);    // evicts key 1.
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */




public class LFUCacheDesign {

    private static class CacheItem {
        int key;
        int value;
        int times;
        long nanos;

        private CacheItem(int key, int value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
            this.nanos = System.nanoTime();
        }

        @Override
        public boolean equals(Object other) {
            if(this == other) return true;
            if(other == null || other.getClass()!= this.getClass()) return false;
            CacheItem otherItem = (CacheItem) other;
            return this.key == otherItem.key  && this.value == otherItem.value;
        }

        @Override
        public int hashCode() {
            return this.key;
        }
    }


    private static class LFUCache {

        private final Map<Integer, CacheItem> cache;
        private final SortedSet<CacheItem> freqSet;
        private final int capacity;

        private LFUCache(int capacity) {
            this.cache = new HashMap<>(capacity);
            this.freqSet = new TreeSet<>(new Comparator<CacheItem>() {
                @Override
                public int compare(CacheItem o1, CacheItem o2) {
                    if(o1.times == o2.times) {
                        return Long.compare(o1.nanos, o2.nanos);
                    }
                    return o1.times - o2.times;
                }
            });
            this.capacity = capacity;
        }

        private int get(int key) {
            if(this.cache.containsKey(key)) {
                this.incrementFreq(this.cache.get(key));
                return this.cache.get(key).value;
            }
            return -1;
        }

        private void put(int key, int value) {
            CacheItem inCache = null;
            if(this.cache.containsKey(key)) {
                inCache = this.cache.get(key);
                inCache.value = value;
                this.incrementFreq(inCache);
            } else {
                if(this.capacity > 0) {
                    if(this.cache.size() == this.capacity) evict();
                    inCache = new CacheItem(key, value, 1);
                    this.cache.put(key, inCache);
                    this.freqSet.add(inCache);
                }
            }
        }

        private void incrementFreq(final CacheItem inCache) {
            if(this.freqSet.remove(inCache)){
                this.freqSet.add(new CacheItem(inCache.key, inCache.value, inCache.times+1));
            }
        }

        private void evict() {
            if(!this.freqSet.isEmpty()) {
                final CacheItem leastFreqItem = this.freqSet.first();
                if(this.freqSet.remove(leastFreqItem)) {
                    final int key2Evict = leastFreqItem.key;
                    System.out.println("Evicting "+key2Evict);
                    this.cache.remove(key2Evict);
                }
            }
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache( 2); // capacity
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);                        // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.put(4, 4);                        // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4

        System.out.println("----------");

        LFUCache cache1 = new LFUCache( 2); // capacity
        cache1.put(2, 1);
        cache1.put(3, 2);
        System.out.println(cache1.get(3));       // returns 2
        System.out.println(cache1.get(2));       // returns 1
        cache1.put(4, 3);                        // evicts key 3
        System.out.println(cache1.get(2));       // returns 1
        System.out.println(cache1.get(3));       // returns -1 (not found)
        System.out.println(cache1.get(4));       // returns 3

        /**
         * ["LFUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"]
         [[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]
         [null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,14,null,null,18,null,null,11,null,null,null,null,null,18,null,null,24,null,4,29,30,null,12,-1,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,24,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null]
         */
    }
}
