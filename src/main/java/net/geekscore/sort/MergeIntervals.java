package net.geekscore.sort;

import java.util.*;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */
public class MergeIntervals {

    public static void main(String[] args) {

        System.out.println(merge(
                Arrays.asList(
                        new Interval(1,3)
                        , new Interval(2,6)
                        , new Interval(8,10)
                        , new Interval(15,18)
                )
            )
        ); // [1,6],[8,10],[15,18].

        System.out.println(merge(
                Arrays.asList(
                        new Interval(1,3)
                        , new Interval(2,6)
                        , new Interval(5,10)
                        , new Interval(9,18)
                )
                )
        ); // [1,18].

        System.out.println(merge(
                Arrays.asList(
                        new Interval(1,4)
                        , new Interval(4,5)
                    )
                )
        ); // [1,5].
    }

    private static final List<Interval> merge(List<Interval> intervals) {
        if(null == intervals || intervals.size() == 0) return Collections.emptyList();
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        final List<Interval> mergedIntervals = new LinkedList<>();
        mergedIntervals.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            final Interval curr = intervals.get(i);
            final Interval merged = mergedIntervals.get(mergedIntervals.size()-1);
            if(curr.start <= merged.end) {
                merged.end = Math.max(curr.end, merged.end);
            } else {
                mergedIntervals.add(curr);
            }
        }
        return mergedIntervals;
    }

    static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }

        @Override
        public String toString(){
            return String.format("[%d, %d]", this.start, this.end);
        }
    }
}


