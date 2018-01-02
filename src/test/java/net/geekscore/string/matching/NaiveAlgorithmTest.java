package net.geekscore.string.matching;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by ravirajmulasa on 9/17/16.
 */
public final class NaiveAlgorithmTest {

    @Test
    public void testStringMatch() {

        final IStringMatch naive    = new NaiveAlgorithm();

        int matchedIndex      = naive.search("abcabade", "aba");
        Assert.assertTrue(matchedIndex == 3);

        matchedIndex      = naive.search("abcabade", "abawwrer244545");
        Assert.assertTrue(matchedIndex == -1);


        matchedIndex      = naive.search("abcabade", "2");
        Assert.assertTrue(matchedIndex == -1);

        matchedIndex      = naive.search("abcabade", "e");
        Assert.assertTrue(matchedIndex == 7);

        matchedIndex      = naive.search("1abcabade", "1");
        Assert.assertTrue(matchedIndex == 0);

        matchedIndex      = naive.search("cababa", "aba");
        Assert.assertTrue(matchedIndex == 1);


    }
}
