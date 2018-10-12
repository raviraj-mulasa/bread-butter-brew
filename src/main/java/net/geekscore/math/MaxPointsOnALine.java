package net.geekscore.math;


import java.util.*;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * Example 1:
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 *
 *
 * Example 2:
 * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * Explanation:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 */
public class MaxPointsOnALine {
    
    private static  class  Point {
        int x;
        int y;
        Point() { this(0,0); }
        Point(int a, int b) { x = a; y = b; }
        @Override
        public String toString() {
            return "("+this.x+","+this.y+")";
        }
    }

    public static void main(String[] args) {
        // 3
        System.out.println(maxPoints(new Point[]{
                new Point(1,1)
                ,new Point(2,2)
                , new Point(3,3)
        }));
        // 4
        System.out.println(maxPoints(new Point[]{
                new Point(1,1)
                ,new Point(3,2)
                ,new Point(5,3)
                ,new Point(4,1)
                ,new Point(2,3)
                ,new Point(1,4)
        }));

        // 1
        System.out.println(maxPoints(new Point[]{
                new Point(0,0)
        }));

        // 2
        System.out.println(maxPoints(new Point[]{
                new Point(0,0)
                ,new Point(1,0)
        }));

        // 3
        System.out.println(maxPoints(new Point[]{
                new Point(-1,-2)
                ,new Point(3,5)
                ,new Point(-5,-9)
        }));

        // 2
        System.out.println(maxPoints(new Point[]{
                new Point(0,0)
                ,new Point(1,1)
                ,new Point(1,-1)
        }));

        // 3
        System.out.println(maxPoints(new Point[]{
                new Point(0,0)
                ,new Point(1,1)
                ,new Point(0,0)
        }));

        // 4
        System.out.println(maxPoints(new Point[]{
                new Point(1,1)
                ,new Point(1,1)
                ,new Point(2,2)
                ,new Point(2,2)
        }));

        // 3
        System.out.println(maxPoints(new Point[]{
                new Point(2,3)
                ,new Point(3,3)
                ,new Point(-5,3)
        }));
    }

    private static int maxPoints(Point[] points) {

        if(points == null || points.length == 0) return 0;
        int maxPointsOnALine = 0;

        for (int i = 0; i < points.length; i++) {
            final Map<String, Integer> slopePointsMap = new HashMap<>();
            int overlap = 1 , localMax = 0;
            for (int j = 0; j < points.length; j++) {
                if (j == i) continue;
                int deltaX = points[j].x - points[i].x;
                int deltaY = points[j].y - points[i].y;
                if (deltaX == 0 && deltaY == 0) {
                    overlap++;
                    continue;
                }
                int gcd = gcd(deltaX, deltaY); // gcd will never be zero.
                deltaX /= gcd;
                deltaY /= gcd;
                final String key = deltaX + ":" + deltaY;
                slopePointsMap.put(key, slopePointsMap.getOrDefault(key, 0) + 1);
                localMax = Math.max(localMax, slopePointsMap.get(key));
            }
            maxPointsOnALine = Math.max(maxPointsOnALine, localMax+overlap);
            if(maxPointsOnALine == points.length) break; // max cannot be greater than no.of points
        }
        return maxPointsOnALine;
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
}
