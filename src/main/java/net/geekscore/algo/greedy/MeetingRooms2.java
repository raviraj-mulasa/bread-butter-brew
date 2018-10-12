package net.geekscore.algo.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 *
 */
public class MeetingRooms2 {

    public static void main(String[] args) {

        System.out.println(" ----- Using sorts ----");
        System.out.println(minMeetingRooms(new Interval[]{
                new Interval(0,30)
                , new Interval(5,10)
                , new Interval(15,20)
        })); // 2

        System.out.println(minMeetingRooms(new Interval[]{
                new Interval(0,30)
        })); // 1

        System.out.println(minMeetingRooms(new Interval[]{
                new Interval(0,30)
                , new Interval(5,21)
                , new Interval(15,20)
        })); // 3


        System.out.println(minMeetingRooms(new Interval[]{
                new Interval(9,10)
                , new Interval(4,9)
                , new Interval(4,17)
        })); // 2

        System.out.println(minMeetingRooms(new Interval[]{
                new Interval(4,30)
                , new Interval(4,9)
                , new Interval(4,17)
        })); // 3

        System.out.println(minMeetingRooms(new Interval[]{
                new Interval(2,11)
                , new Interval(6,16)
                , new Interval(11,16)
        })); // 2

        System.out.println(minMeetingRooms(new Interval[]{
                new Interval(1,5)
                , new Interval(8,9)
                , new Interval(8,9)
        })); // 2


        System.out.println(" ----- Using Greedy ----");

    }



    private static final int minMeetingRooms(final Interval[] intervals) {

        if(intervals == null || intervals.length == 0) return 0;

        final int[] starts = new int[intervals.length];
        final int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsPointer = 0;
        for (int start : starts) {
            if (start < ends[endsPointer]) rooms++;
            else endsPointer++;
        }
        return rooms;
    }


    private static final int minMeetingRoomsGreedy(final Interval[] intervals) {

        if(intervals == null || intervals.length == 0) return 0;

        final int[] starts = new int[intervals.length];
        final int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for (int start : starts) {
            if (start < ends[endsItr]) rooms++;
            else endsItr++;
        }
        return rooms;
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
