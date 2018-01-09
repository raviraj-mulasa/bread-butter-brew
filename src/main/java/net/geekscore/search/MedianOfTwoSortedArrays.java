package net.geekscore.search;

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
//        System.out.println(medianRec(new int[]{1,3,5,11,17}, new int[]{9,10,11,13,14})); // 6.0
//        System.out.println(medianRec(new int[]{1,2,5,11,15}, new int[]{3,4,13,17,18})); // 2.5
//        System.out.println(medianRec(new int[]{3,7,9,15,18,21,25}, new int[]{4,6,8,10,11,18})); // 5.0
//        System.out.println(medianRec(new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 5, 6, 8, 8, 9})); // 5.0
//        System.out.println(medianRec(new int[]{-3,4}, new int[]{5,6})); // 4.5
//        System.out.println(medianRec(new int[]{23,26,31,35}, new int[]{3,5,7,9,11,16})); // 13.5
//        System.out.println(medianRec(new int[]{1,2}, new int[]{3,4})); // 2.5
//        System.out.println(medianRec(new int[]{1, 3}, new int[]{2})); // 2.0
//        System.out.println(medianRec(new int[]{1}, new int[]{2,3})); // 2.0
//        System.out.println(medianRec(new int[]{1}, new int[]{3})); // 2.0

        System.out.println("------------------------------------");

        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4})); // 2.5
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2})); // 2.0
        System.out.println(findMedianSortedArrays(new int[]{1}, new int[]{2,3})); // 2.0
        System.out.println(findMedianSortedArrays(new int[]{1}, new int[]{3})); // 2.0


    }


    private static final double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        }

        int x = nums1.length;
        int y = nums2.length;

        int low = 0;
        int high = x;
        while (low <= high) {
            int partitionX = (low + high)/2;
            int partitionY = (x + y + 1)/2 - partitionX;

            //if partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
            //if partitionX is length of input then there is nothing on right side. Use +INF for minRightX
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums2[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums1[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                //We have partitioned array at correct place
                // Now get max of left elements and min of right elements to get the median in case of even length combined array size
                // or get max of left for odd length combined array size.
                if ((x + y) % 2 == 0) {
                    return ((double)Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2;
                } else {
                    return (double)Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) { //we are too far on right side for partitionX. Go on left side.
                high = partitionX - 1;
            } else { //we are too far on left side for partitionX. Go on right side.
                low = partitionX + 1;
            }
        }

        //Only we we can come here is if input arrays were not sorted. Throw in that scenario.
        throw new IllegalArgumentException();
    }


    private static final double medianRec(final int[] a, final  int [] b){
        if(a == null || a.length < 1 && (b != null && b.length > 0)){
            return median(b);
        }
        if(b == null || b.length < 1 && (a != null && a.length > 0)){
            return median(a);
        }
        return medianRecHelper(a, b, 0, a.length - 1, 0, b.length - 1);
    }

    private static final double medianRecHelper(final int[] a, final  int [] b, int startA, int endA, int startB, int endB){

        if ((endA - startA == 0) && ((endB - startB == 0))) {
//            Each array has 1 element each
            return (a[0] + b[0])/2.0;
        }
        if((endA - startA) == 1 && (endB - startB) == 1) {
//            Each array has 2 elements each
            return (Math.max(a[0], b[0]) + Math.min(a[1], b[1])) / 2.0;
        }

        int medianIdxA = (startA + endA) / 2;
        int medianIdxB = (startB + endB) / 2;

        final int medianA = a[medianIdxA];
        final int medianB = b[medianIdxB];

        if(medianA == medianB){
            return medianA;
        }
        if(medianA < medianB) {
            startA = medianIdxA;
            endB = medianIdxB;
        } else {
            startB = medianIdxB;
            endA = medianIdxA;
        }
        return medianRecHelper(a, b, startA, endA, startB, endB);
    }


//    private static final double median(final int[] a, final  int [] b){
//        if(a == null || a.length < 1 && (b != null && b.length > 0)){
//            return median(b);
//        }
//        if(b == null || b.length < 1 && (a != null && a.length > 0)){
//            return median(a);
//        }
//        int start = 0, end = Math.max(a.length, b.length);
//        int medianRec
//        int medianA = medianRec(a);
//        int medianB = medianRec(b);
//        while (start < end) {
//            if(medianA == medianB) {
//                return medianB;
//            }
//            if(medianA < medianB) {
//
//            }
//
//        }
//
//
//    }

    private static final double median(final int[] a){
        return median(a, 0 ,a.length - 1);
    }


    private static final double median(final int[] a, final int start, final int end){
        double median = a[(end - start)/2] * 1.0;
        if(a.length % 2 == 0) return (median + a[(end - start)/2 - 1])/2.0;
        else return median;
    }

//    private static final double medianIdx(final int start, final int end){
//        double median = a[(end - start)/2] * 1.0;
//        if(a.length % 2 == 0) return (median + a[(end - start)/2 - 1])/2.0;
//        else return median;
//    }





}
