package edu.learn.me.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ravirajmulasa on 9/6/16.
 */
public final class ArrayUtil {

    private ArrayUtil(){}

    public static final <T>  List<T> spiralDisplay(final T[][] matrix) {

        if(null == matrix) {
            return Collections.emptyList();
        }

        final int rows    = Math.max(0, matrix.length);
        int columns = 0;
        if(null != matrix[0]) {
            columns = Math.max(0, matrix[0].length);
        }
        int left  = 0;
        int right = columns - 1;
        int top   = 0;
        int bottom= rows -1;

        final List<T> result = new ArrayList<>(rows * columns);

        while ( top <= bottom && left <= right) {

//            left to right for top most row
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top += 1;
//            top to bottom for right most column
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right -= 1;
//            right to left for bottom  most row
            for (int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]);
            }
            bottom -= 1;
//            bottom to top for left most column
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left += 1;
        }
        return result;

    }
}
