package net.geekscore.array;

import java.util.Arrays;

/**
 * TODO
 *
 * Reverse Words in a String II
 *
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 *
 * Note: Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 */
public class RotateArray {

    public static void main(String[] args) {


        System.out.println(Arrays.toString(rotateLeftWithAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 1))); // [2, 3, 4, 5, 6, 7, 1]
        System.out.println(Arrays.toString(rotateLeftWithAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 2))); // [3, 4, 5, 6, 7, 1, 2]
        System.out.println(Arrays.toString(rotateLeftWithAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 3))); // [4, 5, 6, 7, 1, 2, 3]
        System.out.println(Arrays.toString(rotateLeftWithAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 4))); // [5, 6, 7, 1, 2, 3, 4]

        System.out.println(Arrays.toString(rotateLeftWithAdditionalMemory(new int[]{1,2,3}, 4))); // [2, 3, 1]
        System.out.println(Arrays.toString(rotateLeftWithAdditionalMemory(new int[]{1,2}, 3))); // [2, 1]
        System.out.println(Arrays.toString(rotateLeftWithAdditionalMemory(new int[]{1,2,3}, 3))); // [1, 2, 3]

        System.out.println("----------------------------------------");

        System.out.println(Arrays.toString(rotateRightWithAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 1))); // [7, 1, 2, 3, 4, 5, 6]
        System.out.println(Arrays.toString(rotateRightWithAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 2))); // [6, 7, 1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(rotateRightWithAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 3))); // [5, 6, 7, 1, 2, 3, 4]
        System.out.println(Arrays.toString(rotateRightWithAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 4))); // [4, 5, 6, 7, 1, 2, 3]

        System.out.println(Arrays.toString(rotateRightWithAdditionalMemory(new int[]{1,2,3}, 4))); // [3, 1, 2]
        System.out.println(Arrays.toString(rotateRightWithAdditionalMemory(new int[]{1,2}, 3))); // [2, 1]
        System.out.println(Arrays.toString(rotateRightWithAdditionalMemory(new int[]{1,2,3}, 3))); // [1, 2, 3]


        System.out.println("----------------------------------------");

//        System.out.println(Arrays.toString(rotateLeftWithAdditionalMemoryC(new int[]{1,2,3,4,5,6,7}, 1, 1))); // [2, 3, 4, 5, 6, 7, 1]
//        System.out.println(Arrays.toString(rotateLeftWithAdditionalMemoryC(new int[]{1,2,3,4,5,6,7}, 2, 1))); // [3, 4, 5, 6, 7, 1, 2]
//        System.out.println(Arrays.toString(rotateLeftWithAdditionalMemoryC(new int[]{1,2,3,4,5,6,7}, 3, 2))); // [4, 5, 6, 7, 1, 2, 3]
//        System.out.println(Arrays.toString(rotateLeftWithAdditionalMemoryC(new int[]{1,2,3,4,5,6,7}, 4, 3))); // [5, 6, 7, 1, 2, 3, 4]

        System.out.println("----------------------------------------");

//        System.out.println(Arrays.toString(rotateRightWithAdditionalMemoryC(new int[]{1,2,3,4,5,6,7}, 1, 1))); // [7, 1, 2, 3, 4, 5, 6]
//        System.out.println(Arrays.toString(rotateRightWithAdditionalMemoryC(new int[]{1,2,3,4,5,6,7}, 2, 1))); // [6, 7, 1, 2, 3, 4, 5]
//        System.out.println(Arrays.toString(rotateRightWithAdditionalMemoryC(new int[]{1,2,3,4,5,6,7}, 3, 3))); // [5, 6, 7, 1, 2, 3, 4]
//        System.out.println(Arrays.toString(rotateRightWithAdditionalMemoryC(new int[]{1,2,3,4,5,6,7}, 4, 4))); // [4, 5, 6, 7, 1, 2, 3]

        System.out.println("----------------------------------------");

        System.out.println(Arrays.toString(rotateLeftNoAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 1))); // [2, 3, 4, 5, 6, 7, 1]
        System.out.println(Arrays.toString(rotateLeftNoAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 2))); // [3, 4, 5, 6, 7, 1, 2]
        System.out.println(Arrays.toString(rotateLeftNoAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 3))); // [4, 5, 6, 7, 1, 2, 3]
        System.out.println(Arrays.toString(rotateLeftNoAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 4))); // [5, 6, 7, 1, 2, 3, 4]\

        System.out.println(Arrays.toString(rotateLeftNoAdditionalMemory(new int[]{1,2,3}, 4))); // [2, 3, 1]
        System.out.println(Arrays.toString(rotateLeftNoAdditionalMemory(new int[]{1,2}, 3))); // [2, 1]
        System.out.println(Arrays.toString(rotateLeftNoAdditionalMemory(new int[]{1,2,3}, 3))); // [1, 2, 3]

        System.out.println("----------------------------------------");

        System.out.println(Arrays.toString(rotateRightNoAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 1))); // [7, 1, 2, 3, 4, 5, 6]
        System.out.println(Arrays.toString(rotateRightNoAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 2))); // [6, 7, 1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(rotateRightNoAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 3))); // [5, 6, 7, 1, 2, 3, 4]
        System.out.println(Arrays.toString(rotateRightNoAdditionalMemory(new int[]{1,2,3,4,5,6,7}, 4))); // [4, 5, 6, 7, 1, 2, 3]

        System.out.println(Arrays.toString(rotateRightNoAdditionalMemory(new int[]{1,2,3}, 4))); // [3, 1, 2]
        System.out.println(Arrays.toString(rotateRightNoAdditionalMemory(new int[]{1,2}, 3))); // [2, 1]
        System.out.println(Arrays.toString(rotateRightNoAdditionalMemory(new int[]{1,2,3}, 3))); // [1, 2, 3]
    }

    // Additional memory is equal to size of the array
    private static int[] rotateLeftWithAdditionalMemory(int[] array, int k) {
        if(null == array || array.length == 0) return array;
        k = k % array.length;
        if(k < 0) k += array.length;
        final int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[(i + k) % array.length];
        }
        return copy;
    }

    private static int[] rotateRightWithAdditionalMemory(int[] array, int k) {
        if(null == array || array.length == 0) return array;
        k = k % array.length;
        if(k < 0) k += array.length;
        final int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[(i + k)%array.length] = array[i];
        }
        return copy;
    }

    // Additional memory  with size c where c less than  size of the array
    private static int[] rotateLeftWithAdditionalMemoryC(int[] array, int k, int c) {
        //todo
        if(null == array || array.length == 0) return array;
        k = k % array.length;
        if(k < 0) k += array.length;

        final int[] buffer = Arrays.copyOf(array, c);
        for (int i = 0; i < array.length / c; i++) {
            array[i] = array[(i + k)%array.length];
//            for (int m = c-1, j = array.length - 1; m >= 0; m--, j--) {
//                array[j] = buffer[m];
//            }
        }
        System.out.println(Arrays.toString(buffer));
        System.out.println(Arrays.toString(array));

        return array;
    }


    // Additional memory with size c where c less than  size of the array
    private static int[] rotateRightWithAdditionalMemoryC(int[] array, int k, int c) {
        // todo
        if(null == array || array.length == 0) return array;
        k = k % array.length;
        if(k < 0) k += array.length;

        final int[] buffer = Arrays.copyOfRange(array, array.length - c, array.length);
        for (int i = 0; i < array.length / c; i++) {
            for (int j = 0; j < array.length; j++) {
                array[(j + k)%array.length] = array[j];
            }
            System.out.println(Arrays.toString(array));
        }
        System.out.println(Arrays.toString(buffer));
//        for (int i = 0, j = array.length - 1; i < c; i++, j--) {
//            array[j] = buffer[i];
//        }
        return array;
    }



    private static int[] rotateLeftNoAdditionalMemory(final int[] array, int k) {
        if(null == array || array.length == 0) return array;
        k = k % array.length;
        if(k < 0) k += array.length;
        reverse(array);
        reverse(array, array.length - k, array.length - 1);
        reverse(array, 0, array.length - k - 1);
        return array;
    }

    private static int[] rotateRightNoAdditionalMemory(final int[] array, int k) {
        if(null == array || array.length == 0) return array;
        k = k % array.length;
        if(k < 0) k += array.length;
        reverse(array);
        reverse(array, 0, k - 1);
        reverse(array, k, array.length - 1);
        return array;
    }

    private static void reverse(final int[] array) {
        reverse(array, 0, array.length - 1);
    }


    private static void reverse(final int[] array, int i, int j) {
        for (; i < j ; i++, j--) {
            if(array[i] != array[j]) {
                final int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
    }
}
