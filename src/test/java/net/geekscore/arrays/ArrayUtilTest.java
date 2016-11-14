package net.geekscore.arrays;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ravirajmulasa on 9/6/16.
 */
public final class ArrayUtilTest {

    @Test
    public void testSpiralDisplay() {

        final Integer[][] matrix      = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        final List<Integer> expected  = Arrays.asList(1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10);
        Assert.assertThat(ArrayUtil.spiralDisplay(matrix), CoreMatchers.is(expected));

        final Integer[][] matrix1     = {{1,2,3,4,5,6}, {7,8,9,10,11,12},{13,14,15,16,17,18}};
        final List<Integer> expected1 = Arrays.asList(1, 2, 3, 4, 5, 6, 12, 18, 17, 16, 15, 14, 13, 7, 8, 9, 10, 11);
        Assert.assertThat(ArrayUtil.spiralDisplay(matrix1), CoreMatchers.is(expected1));

    }
}
