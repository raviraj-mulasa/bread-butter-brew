package net.geekscore.string.matching;

import java.util.Objects;

/**
 * Created by ravirajmulasa on 9/17/16.
 */
public final class NaiveAlgorithm implements IStringMatch {

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

        boolean isMatched   = false;
        int matchingIndex   = 0;

        while (!isMatched && matchingIndex <= N - M) {

            if(Objects.equals(text.substring(matchingIndex, matchingIndex + M), pattern)) {
                isMatched = true;
                break;
            }
            matchingIndex +=1;
        }

        return isMatched ? matchingIndex : -1;

    }
}
