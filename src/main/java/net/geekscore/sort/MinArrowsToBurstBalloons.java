package net.geekscore.sort;

import java.util.*;

/**
 *
 *
 * There are a number of spherical balloons spread in two-dimensional space.
 * For each balloon, provided input is the start and end coordinates of the horizontal diameter.
 * Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of
 * the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.
 *
 * An arrow can be shot up exactly vertically from different points along the x-axis.
 * A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend.
 * There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up
 * infinitely. The problem is to find the minimum number of arrows that must be shot to burst all
 * balloons.
 *
 * Example:
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 * Output:
 * 2
 *
 * Explanation:
 * One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6])
 * and another arrow at x = 11 (bursting the other two balloons).
 */
public class MinArrowsToBurstBalloons {

    public static void main(String[] args) {
        System.out.println(minArrows(Arrays.asList(
                    new Balloon(10, 16)
                    ,new Balloon(2, 8)
                    ,new Balloon(1, 6)
                    ,new Balloon(7, 12)
                )
            )
        );
    }

    private static final int minArrows(final List<Balloon> ballons) {
        if(null == ballons || ballons.size() == 0) return 0;
        Collections.sort(ballons, new Comparator<Balloon>() {
            @Override
            public int compare(Balloon o1, Balloon o2) {
                return o1.xStart - o2.xStart;
            }
        });
        final List<Balloon> merged = new LinkedList<Balloon>();
        merged.add(ballons.get(0));
        for (int i = 1; i < ballons.size(); i++) {
            final Balloon prev = merged.get(merged.size()-1);
            final Balloon curr = ballons.get(i);
            if(curr.xStart <= prev.xEnd) prev.xEnd = Math.min(curr.xEnd, prev.xEnd);
            else merged.add(curr);
        }
        return merged.size();
    }

    static class Balloon {
        int xStart;
        int xEnd;
        Balloon(int xStart, int xEnd) {
            this.xStart = xStart;
            this.xEnd = xEnd;
        }
        @Override
        public String toString(){
            return String.format("[%d, %d]", xStart, xEnd);
        }
    }
}
