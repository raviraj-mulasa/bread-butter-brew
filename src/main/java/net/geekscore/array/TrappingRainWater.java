package net.geekscore.array;



/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * For example,
 *
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 *
 *
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        System.out.println(trapRainWaterBruteForce(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); // 6

        System.out.println(trapRainWaterBruteForce(new int[]{1, 2})); // 0
        System.out.println(trapRainWaterBruteForce(new int[]{2, 1})); // 0

        System.out.println(trapRainWaterBruteForce(new int[]{2})); // 0

        System.out.println(trapRainWaterBruteForce(new int[]{1, 0, 2})); // 1
        System.out.println(trapRainWaterBruteForce(new int[]{2, 0, 1})); // 1
        System.out.println(trapRainWaterBruteForce(new int[]{0, 1, 2})); // 0
        System.out.println(trapRainWaterBruteForce(new int[]{2, 1, 0})); // 0
        System.out.println(trapRainWaterBruteForce(new int[]{0, 2, 1})); // 0

        System.out.println(trapRainWaterBruteForce(new int[]{2,1,0,2})); // 3


        System.out.println("-----------------------------------------");

        System.out.println(trapRainWaterExtraSpace(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); // 6

        System.out.println(trapRainWaterExtraSpace(new int[]{1, 2})); // 0
        System.out.println(trapRainWaterExtraSpace(new int[]{2, 1})); // 0

        System.out.println(trapRainWaterExtraSpace(new int[]{2})); // 0

        System.out.println(trapRainWaterExtraSpace(new int[]{1, 0, 2})); // 1
        System.out.println(trapRainWaterExtraSpace(new int[]{2, 0, 1})); // 1
        System.out.println(trapRainWaterExtraSpace(new int[]{0, 1, 2})); // 0
        System.out.println(trapRainWaterExtraSpace(new int[]{2, 1, 0})); // 0
        System.out.println(trapRainWaterExtraSpace(new int[]{0, 2, 1})); // 0

        System.out.println(trapRainWaterExtraSpace(new int[]{2,1,0,2})); // 3

        System.out.println("-----------------------------------------");

        System.out.println(trapRainWaterEfficient(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); // 6

        System.out.println(trapRainWaterEfficient(new int[]{1, 2})); // 0
        System.out.println(trapRainWaterEfficient(new int[]{2, 1})); // 0
        System.out.println(trapRainWaterEfficient(new int[]{2})); // 0

        System.out.println(trapRainWaterEfficient(new int[]{1, 0, 2})); // 1
        System.out.println(trapRainWaterEfficient(new int[]{2, 0, 1})); // 1
        System.out.println(trapRainWaterEfficient(new int[]{0, 1, 2})); // 0
        System.out.println(trapRainWaterEfficient(new int[]{2, 1, 0})); // 0
        System.out.println(trapRainWaterEfficient(new int[]{0, 2, 1})); // 0

        System.out.println(trapRainWaterEfficient(new int[]{2,1,0,2})); // 3

        System.out.println("-----------------------------------------");

//        System.out.println(trapRainWaterEfficient1(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); // 6
//
//        System.out.println(trapRainWaterEfficient1(new int[]{1, 2})); // 0
//        System.out.println(trapRainWaterEfficient1(new int[]{2, 1})); // 0
//        System.out.println(trapRainWaterEfficient1(new int[]{2})); // 0

        System.out.println(trapRainWaterEfficient1(new int[]{1, 0, 2})); // 1
        System.out.println(trapRainWaterEfficient1(new int[]{2, 0, 1})); // 1
//        System.out.println(trapRainWaterEfficient1(new int[]{0, 1, 2})); // 0
//        System.out.println(trapRainWaterEfficient1(new int[]{2, 1, 0})); // 0
//        System.out.println(trapRainWaterEfficient1(new int[]{0, 2, 1})); // 0

        System.out.println(trapRainWaterEfficient1(new int[]{2,1,0,2})); // 3


    }


     // O(n2) , No Extra space - Naive
    private static final int trapRainWaterBruteForce(final int[] heights) {
        int waterTrapped = 0;
        for(int i=0; i < heights.length; i++) {
            int maxLeft = heights[i], maxRight = maxLeft;
            for (int left = 0; left < i; left++) {
                maxLeft = Math.max(maxLeft, heights[left]);
            }
            for (int right = heights.length-1; right > i; right--) {
                maxRight = Math.max(maxRight, heights[right]);
            }
            if(maxLeft == heights[i] || heights[i] == maxRight) continue;
            waterTrapped += (Math.min(maxLeft , maxRight) - heights[i]);
        }
        return waterTrapped;
    }


    // O(n) , 2*O(n) Extra space
    private static final int trapRainWaterExtraSpace(final int[] heights) {

        int waterTrapped = 0;
        final int[] maxLeft2Right = new int[heights.length];
        final int[] maxRight2Left = new int[heights.length];

        int max = 0;
        for(int left=0; left < heights.length; left++) {
            max = Math.max(max, heights[left]);
            maxLeft2Right[left] = max;
        }

        max = 0;
        for(int right=heights.length-1; right > -1; right--) {
            max = Math.max(max, heights[right]);
            maxRight2Left[right] = max;
        }
        for(int i=0; i < heights.length; i++) {
            waterTrapped += (Math.min(maxLeft2Right[i] , maxRight2Left[i]) - heights[i]);
        }
        return waterTrapped;
    }


    // O(n) , No Extra space
    private static final int trapRainWaterEfficient(final int[] heights) {
        if(null == heights || heights.length < 3) return 0;
        int waterTrapped = 0;
        int left = 0, right = left + heights.length - 1;
        int maxLeft = heights[left];
        int maxRight = heights[right];
        while (left < right) {
            if(heights[left] < heights[right]) {
                // left is small
                maxLeft = Math.max(maxLeft, heights[left]);
                waterTrapped += (maxLeft - heights[left++]);
            } else {
                // right is small
                maxRight = Math.max(maxRight, heights[right]);
                waterTrapped += (maxRight - heights[right--]);
            }
        }
        return waterTrapped;
    }


    private static final int trapRainWaterEfficient1(final int[] heights) {
        int waterTrapped = 0;
        int left = 0, right = left + heights.length - 1;
        int height2Add = 0; // we will a new height between left and right , for now there is no height
        while (left < right) {
            // to keep track smaller of left and right
            int smaller = heights[left] < heights[right] ? heights[left++] : heights[right--];
            // set height to add  smaller than smaller of right and left
            height2Add = Math.max(height2Add, smaller);
//            System.out.println("level "+height2Add+" water to be trapped "+(height2Add-smaller));
            waterTrapped += (height2Add - smaller);
        }
        return waterTrapped;
    }

    private static final int trapRainWaterStack(final int[] heights) {
//        {0,1,0,2,1,0,1,3,2,1,2,1})); // 6
//        final Stack<Integer> stack = new Stack<>(); // tracks the indices
//        int i = 0;
//        stack.push(i);
        int waterTrapped = 0;
//        while (!stack.isEmpty() && i < heights.length) {
//            final int maxOnTop = heights[stack.peek()];
//            if(maxOnTop < heights[++i]) { // Current element > top, push on stack
//                stack.push(i);
//            } else {
//
//            }
//        }
        return waterTrapped;
    }
}
