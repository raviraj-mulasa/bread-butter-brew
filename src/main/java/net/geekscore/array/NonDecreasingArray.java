package net.geekscore.array;

/**
 *  Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

 We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

 Example 1:

 Input: [4,2,3]
 Output: True
 Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

 Example 2:

 Input: [4,2,1]
 Output: False
 Explanation: You can't get a non-decreasing array by modify at most one element.

 Note: The n belongs to [1, 10,000].
 */
public class NonDecreasingArray {

    public static void main(String[] args) {
        System.out.println(checkPossibility(new int[]{4,2,3})); // true
        System.out.println(checkPossibility(new int[]{4,2,1})); // false
        System.out.println(checkPossibility(new int[]{3,4,2,5})); // true
        System.out.println(checkPossibility(new int[]{3,4,2,3})); // false
    }

    private static boolean checkPossibility(int[] nums) {
        if(null == nums || nums.length == 0) return true;
        int modifications = 0;
        int curr =  nums[0];
        for (int i = 0; i < nums.length-1; i++) {
            if (curr > nums[i+1]) {
                modifications++;
                if(modifications == 2) return false;
                if (!(i > 0 && nums[i - 1] > nums[i + 1])) {
                    curr = nums[i + 1];
                }
            }else {
                curr = nums[i+1];
            }
        }
        return modifications <= 1;
    }
}
