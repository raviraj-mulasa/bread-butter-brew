package net.geekscore.string.matching;

/**
 *
 * https://www.youtube.com/watch?v=iZ93Unvxwtw
 *
 * KMP Substring search access no more than M+N chars
 * to search a pattern of length M in a text of length N
 *
 * Created by ravirajmulasa on 9/17/16.
 */
public final class KnuthMorrisPratt implements IStringMatch {

    @Override
    public int search(String text, String pattern) {

        if(null == text) {
            return -1;
        }

        if(pattern.length() > text.length()) {
            return -1;
        }

        final int N = text.length();
        final int M = pattern.length();

        return 0;
    }

    /***
     *
     * H
     *
     * @param pattern
     * @return shift array a.k.a array H
     */
    private final int[] computeShiftArray(final String pattern) {

        final int[] shiftArray = new int[pattern.length()];

        return shiftArray;
    }
}
