package net.geekscore.design;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HitCounterMain {

    private static class HitCounter {

        private final Map<Integer, Integer>  timestampHitMap;
        private final SortedSet<Integer> timestamps;

        /** Initialize your data structure here. */
        private HitCounter() {
            this.timestampHitMap = new ConcurrentHashMap<>();
            this.timestamps= new TreeSet<>();
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        private void hit(int timestamp) {
            this.timestampHitMap.put(timestamp, this.timestampHitMap.getOrDefault(timestamp, 0)+1);
            this.timestamps.add(timestamp);
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        private int getHits(int timestamp) {
            int hits = 0;
            this.removeHitsLessThan5Min(timestamp);
            for (final int _timestamp: this.timestamps.headSet(timestamp+1)) {
                hits += this.timestampHitMap.getOrDefault(_timestamp, 0);
            }
            return hits;
        }

        private void removeHitsLessThan5Min(int timestamp) {
            if(timestamp - 5*60 > 0) {
                final Set<Integer> lessThan5mins = new HashSet<>(this.timestamps.headSet(timestamp-5*60+1));
                for (final int _timestamp: lessThan5mins) {
                    this.timestampHitMap.remove(_timestamp);
                }
                if(this.timestamps.removeAll(lessThan5mins)) {
                    System.out.println("Removed < 5 mis hits: "+lessThan5mins);
                }
            }
        }
    }

    public static void main(String[] args) {

        HitCounter counter = new HitCounter();
        // hit at timestamp 1.
        counter.hit(1);
        // hit at timestamp 2.
        counter.hit(2);
        // hit at timestamp 3.
        counter.hit(3);
        // get hits at timestamp 4, should
        System.out.println(counter.getHits(4)); // return 3.
        // hit at timestamp 300.
        counter.hit(300);
        // get hits at timestamp 300, should return 4.
        System.out.println(counter.getHits(300)); // return 4.
        // get hits at timestamp 301, should return 3.
        System.out.println(counter.getHits(301)); // return 3.
        // hit at timestamp 300.
        counter.hit(300);
        // hit at timestamp 301.
        counter.hit(301);
        System.out.println(counter.getHits(301)); // return 5.
        counter.hit(302);
        counter.hit(303);
        System.out.println(counter.getHits(303)); // return 5.


    }
}
