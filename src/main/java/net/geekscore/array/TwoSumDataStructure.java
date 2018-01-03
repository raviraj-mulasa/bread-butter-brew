package net.geekscore.array;

import java.util.Arrays;

/**
 *
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 *  add - Add the number to an internal data structure.
 *  find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 *  For example,
 *  add(1); add(3); add(5);
 *  find(4) -> true
 *  find(7) -> false
 *
 */
public class TwoSumDataStructure {

    public static void main(String[] args) {
        TwoSum obj = new TwoSum();
        obj.add(2);
        obj.add(11);
        obj.add(7);
        obj.add(15);
        System.out.println("Found 9 :"+obj.find(9));
        obj.add(1);
        obj.add(3);
        obj.add(5);
        obj.add(4);
        System.out.println("Found 5 :"+obj.find(5));
        System.out.println("Found 7 :"+obj.find(7));
        System.out.println("Found 77 :"+obj.find(77));
    }

    static class TwoSum {

        private static final int SIZE = 1024;
        private int[] nums;
        private int idx = 0;
        private boolean sorted = Boolean.FALSE;

        /** Initialize your data structure here. */
        public TwoSum() {
            nums = new int[SIZE];
            Arrays.fill(this.nums, Integer.MAX_VALUE);
            idx  = 0;
            this.sorted = Boolean.FALSE;
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            if(this.idx % SIZE == 0) {
                final int[] currNums = this.nums;
                this.nums = new int[2 * currNums.length];
                Arrays.fill(this.nums, Integer.MAX_VALUE);
                System.arraycopy(currNums, 0, this.nums, 0, currNums.length);
            }
            this.nums[this.idx++] = number;
            this.sorted = Boolean.FALSE;
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            if(!this.sorted){
                Arrays.sort(this.nums);
                this.sorted = Boolean.TRUE;
            }
            int left = 0, right = this.idx - 1;
            while (left < right) {
                final int sum = this.nums[left] + this.nums[right];
                if(sum == value) {
                    return true;
                }
                if(sum > value){
                    right--;
                }else {
                    left++;
                }
            }
            return false;
        }

    }


}
