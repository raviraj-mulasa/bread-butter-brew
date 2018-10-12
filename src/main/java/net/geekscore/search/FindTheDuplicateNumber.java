package net.geekscore.search;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 * find the duplicate one.
 *
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindTheDuplicateNumber {


    public static void main(String[] args) {

        Instant start = Instant.now();
        System.out.println(findDuplicateBinarySearch(new int[]{1}));  // -1
        System.out.println(findDuplicateBinarySearch(new int[]{1,1})); // 1
        System.out.println(findDuplicateBinarySearch(new int[]{1,2})); // -1
        System.out.println(findDuplicateBinarySearch(new int[]{1,2,2})); // 2
        System.out.println(findDuplicateBinarySearch(new int[]{1,2,1})); // 1
        System.out.println(findDuplicateBinarySearch(new int[]{2,1,1})); // 1
        System.out.println(findDuplicateBinarySearch(new int[]{2,2,2,2,2})); // 2
        System.out.println("Time take in nanos: "+ Duration.between(start, Instant.now()).toNanos());

        System.out.println("--------------");

        start = Instant.now();
        System.out.println(findDuplicateBinarySearchEfficient(new int[]{1}));  // -1
        System.out.println(findDuplicateBinarySearchEfficient(new int[]{1,1})); // 1
        System.out.println(findDuplicateBinarySearchEfficient(new int[]{1,2})); // -1
        System.out.println(findDuplicateBinarySearchEfficient(new int[]{1,2,2})); // 2
        System.out.println(findDuplicateBinarySearchEfficient(new int[]{1,2,1})); // 1
        System.out.println(findDuplicateBinarySearchEfficient(new int[]{2,1,1})); // 1
        System.out.println(findDuplicateBinarySearchEfficient(new int[]{2,2,2,2,2})); // 2
        System.out.println("Time take in nanos: "+ Duration.between(start, Instant.now()).toNanos());

        System.out.println("--------------");

        start = Instant.now();
        System.out.println(findDuplicateEfficient(new int[]{1}));  // -1
        System.out.println(findDuplicateEfficient(new int[]{1,1})); // 1
        System.out.println(findDuplicateEfficient(new int[]{1,2})); // -1
        System.out.println(findDuplicateEfficient(new int[]{1,2,2})); // 2
        System.out.println(findDuplicateEfficient(new int[]{1,2,1})); // 1
        System.out.println(findDuplicateEfficient(new int[]{2,1,1})); // 1
        System.out.println(findDuplicateEfficient(new int[]{2,2,2,2,2})); // 2
        System.out.println("Time take in nanos: "+ Duration.between(start, Instant.now()).toNanos());

        System.out.println("--------------");

        start = Instant.now();
        System.out.println(findDuplicateEfficient1(new int[]{1}));  // -1
        System.out.println(findDuplicateEfficient1(new int[]{1,1})); // 1
        System.out.println(findDuplicateEfficient1(new int[]{1,2})); // -1
        System.out.println(findDuplicateEfficient1(new int[]{1,2,2})); // 2
        System.out.println(findDuplicateEfficient1(new int[]{1,2,1})); // 1
        System.out.println(findDuplicateEfficient1(new int[]{2,1,1})); // 1
        System.out.println(findDuplicateEfficient1(new int[]{2,2,2,2,2})); // 2
        System.out.println("Time take in nanos: "+ Duration.between(start, Instant.now()).toNanos());

    }

    private static int findDuplicateBinarySearch(int[] nums) {
        if(null == nums || nums.length == 0) return -1;
        Arrays.sort(nums);
        for(int i=0; i < nums.length; i++) {
            final int left = search(nums, i, i+1, nums[i]);
            if(left == -1) {
                i = nums[i];
                continue;
            }
            final int right = search(nums, left+1 , nums.length-1, nums[i]);
            if(right != -1) return nums[i];
        }
        return -1;
    }

    private static int search(int[] nums, int low, int high, int target) {
        while(low <= high) {
            final int mid = low+(high-low)/2;
            if(target <= nums[mid]) high = mid-1;
            else low = mid+1;
        }
        return low >= nums.length || nums[low] != target ? -1 : low;
    }


    private static int findDuplicateBinarySearchEfficient(int[] nums) {
        if(null == nums || nums.length <= 1) return -1;
        Arrays.sort(nums);
        int left = 1, right = nums.length - 1;
        while (left<=right) {
            final int mid = left + (right-left)/2;
            int count = 0;
            for (final int num: nums) {
                if(num <= mid) count++;
            }
            if (count <= mid) left = mid + 1; // there are repeated elements in the right half
            else right = mid - 1; // there are repeated elements in the left half
        }
        return left == nums.length ? -1 : left;
    }

    private static int findDuplicateEfficient(int[] nums) {
        if(null != nums && nums.length > 1) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                if(nums[i] == nums[i-1]) return nums[i];
            }
        }
        return -1;
    }

    private static int findDuplicateEfficient1(int[] nums) {
        if(null != nums && nums.length > 1)  {
            int slow = nums[0];
            int fast = nums[nums[0]];
            while (fast < nums.length && slow != fast) {
                // Detect the loop
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            fast = 0;
            while(slow < nums.length && slow != fast) {
                //find the start of the loop which means at least two integer are the same value;
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow == nums.length || fast == nums.length ? -1 : slow;
        }
        return -1;
    }



}
