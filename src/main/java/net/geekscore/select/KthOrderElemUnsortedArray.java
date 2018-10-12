package net.geekscore.select;

import java.util.Arrays;

/**
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element
 * in the sorted order, not the kth distinct element.
 *
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthOrderElemUnsortedArray {

    public static void main(String[] args) {
        System.out.println(kthElem(new int[]{3,2,1,5,6,4}, 2, true)); // 5
        System.out.println(kthElem(new int[]{3,2,1,5,6,4}, 1, true)); // 6
        System.out.println(kthElem(new int[]{3,2,1,5,6,4,7,89,90}, 2, true)); // 2

        System.out.println("----------------------------");

        System.out.println(kthElem1(new int[]{3,2,1,5,6,4}, 2, false)); // 2
        System.out.println(kthElem1(new int[]{3,2,1,5,6,4}, 3, false)); // 3
        System.out.println(kthElem1(new int[]{3,2,1,5,6,4,7,89,90}, 8, false)); // 89
    }

    private static final int kthElem(final int[] nums, final int k, boolean largest){
        System.out.println(Arrays.toString(nums));
        return largest ?
                kthHelper(nums, nums.length-k+1, 0, nums.length-1)
                :
                kthHelper(nums, k, 0, nums.length-1);
    }

    private static final int kthElem1(final int[] nums, final int k, boolean largest){
        System.out.println(Arrays.toString(nums));
        return largest ?
                kthHelper1(nums, nums.length-k+1, 0, nums.length-1)
                :
                kthHelper1(nums, k, 0, nums.length-1);
    }

    private static final int kthHelper(final int[] nums, final int k, int start, int end){
        int pivot = nums[end]; // Pivot is end
        int left = start;
        int right = end;
        System.out.println(nums[left]+" "+pivot+"  "+nums[end]);
        while (true) {
            while (nums[left] < pivot) left++;
            while (right > left && nums[right] >= pivot) right--;
            if (right <= left) break;
            swap(nums, left, right);
        }
        // left is final position for the pivot element
        swap(nums, left, end);
        if(k == left+1) return pivot;
        else if(k < left+1) return kthHelper(nums, k, start, left-1);
        else return kthHelper(nums, k, left+1, end);

    }

    private static final int kthHelper1(final int[] nums, final int k, int start, int end){
        int pivot = nums[start]; // Pivot is start
        int left = start;
        int right = end;
        while (true) {
            while (left < right && nums[left] <= pivot) left++;
            while (nums[right] > pivot) right--;
            if (right <= left) break;
            swap(nums, left, right);
        }
        // right is final position for the pivot element
        swap(nums, start, right);
        if(k == right+1) return pivot;
        else if(k > right+1) return kthHelper1(nums, k, right+1, end);
        else return kthHelper1(nums, k, start, right-1);

    }

    private static final void swap(final int[] nums, int i, int j) {
        if(nums[i] != nums[j]) {
            final int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
