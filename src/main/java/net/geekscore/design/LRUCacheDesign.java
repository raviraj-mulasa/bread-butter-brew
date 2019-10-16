package net.geekscore.design;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 *  Design and implement a data structure for Least Recently Used (LRU) cache.
 *  It should support the following operations: get and put.
 *
 *  get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
 *  otherwise return -1.
 *
 *  put(key, value) - Set or insert the value if the key is not already present.
 *  When the cache reached its capacity, it should invalidate the least recently used item before
 *  inserting a new item.
 *
 *  Follow up: Could you do both operations in O(1) time complexity?
 *
 *  Example:
 *  LRUCache cache = new LRUCache( 2 ); // capacity
 *  cache.put(1, 1);
 *  cache.put(2, 2);
 *  cache.get(1);       // returns 1
 *  cache.put(3, 3);    // evicts key 2
 *  cache.get(2);       // returns -1 (not found)
 *  cache.put(4, 4);    // evicts key 1
 *  cache.get(1);       // returns -1 (not found)
 *  cache.get(3);       // returns 3
 *  cache.get(4);       // returns 4
 */

public class LRUCacheDesign {

    /* Approach 1 */
    private static class LRUCache {

        private final Deque<Integer> queue;

        private final Map<Integer, Integer> cache;

        private final int capacity;

        private LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>(this.capacity);
            this.queue = new ArrayDeque<>(this.capacity);
        }

        private int get(int key) {
            if(!this.cache.containsKey(key)) return -1;
            Integer result = this.cache.get(key);
            bringToFrontOfQueue(key);
            return result;
        }

        private void put(int key, int value) {
            if(this.queue.size() == this.capacity) {
                // Evict operation
                this.cache.remove(this.queue.removeLast());
            }
            this.cache.put(key, value);
            bringToFrontOfQueue(key);

        }

        private void bringToFrontOfQueue(int key) {
            /*
               Removes the first occurrence of the specified element from this deque.
               If the deque does not contain the element, it is unchanged.
            */
            this.queue.remove(key);
            this.queue.offerFirst(key);
        }

        private void display() {
            System.out.println("Cache:"+ this.cache);
            System.out.println("Queue:"+ this.queue);
        }
    }

    /* Approach 2 */
    private static class LRUCache1 {

        private static class LinkedCappedHashMap<K,V> extends  LinkedHashMap<K,V> {

            private final int capacity;

            private LinkedCappedHashMap(int capacity) {
                super(2, 0.75f, true);
                this.capacity = capacity;
            }

            @Override
            protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
                return this.size() > this.capacity;
            }
        }

        private final LinkedHashMap<Integer, Integer> cache;

        private LRUCache1(int capacity) {
            this.cache = new LinkedCappedHashMap<>(capacity);
        }

        private int get(int key) {
            if(!this.cache.containsKey(key)) return -1;
            return this.cache.get(key);
        }

        private void put(int key, int value) {
            this.cache.put(key, value);
        }
    }


    /* Approach 3 */
    private static class LRUCache2 {

        private static class LinkedCappedHashMap<K,V> extends  LinkedHashMap<K,V> {

            private final int capacity;

            private LinkedCappedHashMap(int capacity) {
                super(2, 0.75f, true);
                this.capacity = capacity;
            }

            @Override
            protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
                return this.size() > this.capacity;
            }
        }

        private final LinkedHashMap<Integer, Integer> cache;

        private LRUCache2(int capacity) {
            this.cache = new LinkedCappedHashMap<>(capacity);
        }

        private int get(int key) {
            if(!this.cache.containsKey(key)) return -1;
            return this.cache.get(key);
        }

        private void put(int key, int value) {
            this.cache.put(key, value);
        }
    }




    public static void main(String[] args) {


        final LRUCache cache = new LRUCache( 2 ); // capacity - 2
        Instant start = Instant.now();
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);                        // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);                        // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
        cache.display();
        System.out.println("Time to execute(nanos): "+ Duration.between(start, Instant.now()).toNanos());

        System.out.println("-----------");

        final LRUCache1 cache1 = new LRUCache1( 2 ); // capacity - 2
        start = Instant.now();
        cache1.put(1, 1);
        cache1.put(2, 2);
        System.out.println(cache1.get(1));       // returns 1
        cache1.put(3, 3);                        // evicts key 2
        System.out.println(cache1.get(2));       // returns -1 (not found)
        cache1.put(4, 4);                        // evicts key 1
        System.out.println(cache1.get(1));       // returns -1 (not found)
        System.out.println(cache1.get(3));       // returns 3
        System.out.println(cache1.get(4));       // returns 4
        System.out.println("Time to execute(nanos): "+ Duration.between(start, Instant.now()).toNanos());


        System.out.println("-----------");

        final LRUCache2 cache2 = new LRUCache2( 2 ); // capacity - 2
        start = Instant.now();
        cache2.put(1, 1);
        cache2.put(2, 2);
        System.out.println(cache2.get(1));       // returns 1
        cache2.put(3, 3);                        // evicts key 2
        System.out.println(cache2.get(2));       // returns -1 (not found)
        cache2.put(4, 4);                        // evicts key 1
        System.out.println(cache2.get(1));       // returns -1 (not found)
        System.out.println(cache2.get(3));       // returns 3
        System.out.println(cache2.get(4));       // returns 4
        System.out.println("Time to execute(nanos): "+ Duration.between(start, Instant.now()).toNanos());
    }

}
