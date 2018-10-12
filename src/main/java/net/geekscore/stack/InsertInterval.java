package net.geekscore.stack;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

 */
public class InsertInterval {

    public static void main(String[] args) {

        Instant start = Instant.now();
        List<Interval> intervals = Arrays.asList(
                new Interval(1,3)
                , new Interval(6,9)
        );
        // [[1,5],[6,9]]
        System.out.println(insert(intervals, new Interval(2,5)));

        List<Interval> intervals1 = Arrays.asList(
                new Interval(1,2)
                , new Interval(3,5)
                , new Interval(6,7)
                , new Interval(8,10)
                , new Interval(12,16)
        );
        // [[1,2],[3,10],[12,16]]
        System.out.println(insert(intervals1, new Interval(4,8)));

        List<Interval> intervals11 = Arrays.asList(
                new Interval(1,2)
                , new Interval(3,5)
                , new Interval(6,7)
                , new Interval(8,10)
                , new Interval(12,16)
        );
        // [[0,2],[3,5],[6,7],[8,10],[12,16]]
        System.out.println(insert(intervals11, new Interval(0,1)));

        List<Interval> intervals2 = Arrays.asList(
                new Interval(1,3)
                , new Interval(6,9)
        );
        // [[0,5],[6,9]]
        System.out.println(insert(intervals2, new Interval(0,5)));
        // [[5,7]]
        System.out.println(insert(Collections.emptyList(), new Interval(5,7)));

        System.out.println("Time taken in nanos: "+ Duration.between(start, Instant.now()).toNanos());

        System.out.println("-----------------------");

        start = Instant.now();
        intervals = Arrays.asList(
                new Interval(1,3)
                , new Interval(6,9)
        );
        // [[1,5],[6,9]]
        System.out.println(insertEfficient(intervals, new Interval(2,5)));

        intervals1 = Arrays.asList(
                new Interval(1,2)
                , new Interval(3,5)
                , new Interval(6,7)
                , new Interval(8,10)
                , new Interval(12,16)
        );
        // [[1,2],[3,10],[12,16]]
        System.out.println(insertEfficient(intervals1, new Interval(4,8)));

        intervals11 = Arrays.asList(
                new Interval(1,2)
                , new Interval(3,5)
                , new Interval(6,7)
                , new Interval(8,10)
                , new Interval(12,16)
        );
        // [[0,2],[3,5],[6,7],[8,10],[12,16]]
        System.out.println(insertEfficient(intervals11, new Interval(0,1)));

        intervals2 = Arrays.asList(
                new Interval(1,3)
                , new Interval(6,9)
        );
        // [[0,5],[6,9]]
        System.out.println(insertEfficient(intervals2, new Interval(0,5)));

        // [[5,7]]
        System.out.println(insertEfficient(Collections.emptyList(), new Interval(5,7)));
        System.out.println("Time taken in nanos: "+ Duration.between(start, Instant.now()).toNanos());


    }

    private static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if(intervals == null || (intervals.isEmpty() && null == newInterval)) return Collections.emptyList();
        final Deque<Interval> stack = new LinkedList<>();
        if(null != newInterval) stack.push(newInterval);
        for (Interval interval : intervals) {
            if(stack.isEmpty()) {
                stack.push(interval);
                continue;
            }
            Interval top = stack.peek();
            if(interval.start < top.start) {
                // Current interval starts earlier than top of the stack
                top = stack.pop();
                stack.push(interval);
                interval = top;
                top = stack.peek();
            }
            if(top.end >= interval.start) {
                // merge if top and interval overlap
                top.start = Math.min(interval.start, top.start);
                top.end = Math.max(interval.end, top.end);
                continue;
            }
            stack.push(interval);
        }
//        System.out.println(stack);
        Collections.reverse((List)stack);
        return (List)stack;
    }


    private static List<Interval> insertEfficient(List<Interval> intervals, Interval newInterval) {
        if(intervals == null || (intervals.isEmpty() && null == newInterval)) return Collections.emptyList();
        final Deque<Interval> stack = new LinkedList<>();
        stack.push(newInterval);
        for (int i = intervals.size()-1; i > -1 ; i--) {
            Interval interval = intervals.get(i);
            Interval top = stack.peek();
            if(interval.start > top.start) {
                top = stack.pop();
                stack.push(interval);
                interval = top;
                top = stack.peek();
            }
            if(top.start <= interval.end) {
                // merge if top and interval overlap
                top.start = Math.min(interval.start, top.start);
                top.end = Math.max(interval.end, top.end);
                continue;
            }
            stack.push(interval);
        }
        return (List)stack;
    }

    private static class Interval {
        int start;
        int end;
        private Interval() { this(0,0); }
        private Interval(int s, int e) { start = s; end = e; }
        @Override
        public String toString() { return "["+this.start+","+this.end+"]";}

    }
}
