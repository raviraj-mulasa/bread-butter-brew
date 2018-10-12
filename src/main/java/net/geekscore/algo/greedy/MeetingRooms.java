package net.geekscore.algo.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * (si < ei), determine if a person could attend all meetings.
 *
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return false.
 */
public class MeetingRooms {

    public static void main(String[] args) {

        System.out.println(canAttendAllMeetings(
                Arrays.asList(
                        new Interval(0,30)
                        , new Interval(5,10)
                        , new Interval(15,20)
                )
                )
        ); // false

        System.out.println(canAttendAllMeetings(
                Arrays.asList(
                        new Interval(5,10)
                        , new Interval(15,20)
                        , new Interval(21,30)
                        , new Interval(31,35)
                )
                )
        ); // true.

        System.out.println(canAttendAllMeetings(
                Arrays.asList(
                        new Interval(5,10)
                        , new Interval(15,20)
                        , new Interval(21,30)
                        , new Interval(31,35)
                        , new Interval(35,38)
                )
                )
        ); // true.

        System.out.println(canAttendAllMeetings(
                Arrays.asList(
                        new Interval(13,15)
                        , new Interval(1,13)
                    )
                )
        ); // true.

    }

    private static final boolean canAttendAllMeetings(List<Interval> intervals) {
        if(null == intervals || intervals.size() == 0) return true;
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        for (int i = 1; i < intervals.size(); i++) {
            final Interval curr = intervals.get(i);
            final Interval prev = intervals.get(i - 1);
            if (curr.start < prev.end) return false;
        }
        return true;
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

