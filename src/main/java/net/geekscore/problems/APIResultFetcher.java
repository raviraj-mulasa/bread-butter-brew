package net.geekscore.problems;

import java.util.*;

/*
 * A third-party API that we're using has a paginated API. It returns results in chunks
 * of 10. Assume that we've got the following API created for us by that third party
 * API:
 *
 * def fetch_page(placeholder: str=None):
 *  '''Return the next page of results.  If placeholder == None, starts from the
 *     beginning.  Otherwise, fetches the next 10 records after the last
 *     placeholder.
 *
 *     returns:
 *       {
 *         "results": [...],
 *         "placeholder": "abc123"
 *       }
 *   '''
 * We don't think that API is very useful, and would prefer the following:
 *
 * class ResultFetcher(object):
 *   def fetch(n):
 * Your task will be to create a new class that encapsulates the calls to fetch_page
 * into a new function that presents an API that allows fetching N results at a time,  *
 * keeping it's place.
 */

public class APIResultFetcher {

    public static void main(String[] args) {

        final ResultFetcher  resultFetcher = new ResultFetcher();

        for(final Integer[] result: resultFetcher.fetch(100)) {
            for(final Integer _result: result) {
                System.out.print(_result+", ");
            }
        }
        System.out.println();

        for(final Integer[] result: resultFetcher.fetch(100)) {
            for(final Integer _result: result) {
                System.out.print(_result+", ");
            }
        }
        System.out.println();
        System.out.println("second test");

        final ResultFetcher  resultFetcher2 = new ResultFetcher();

        for(final Integer[] result: resultFetcher2.fetch(5)) {
            for(final Integer _result: result) {
                System.out.print(_result+", ");
            }
        }
        System.out.println();

        for(final Integer[] result: resultFetcher2.fetch(5)) {
            for(final Integer _result: result) {
                System.out.print(_result +", ");
            }
        }
    }

    public static class Result {
        String nextPlaceholder;
        Integer[] results;

        public Result(String nextPlaceholder, Integer[] results) {
            this.nextPlaceholder = nextPlaceholder;
            this.results = results;
        }

        public String getPlaceholder() {
            return this.nextPlaceholder;
        }
    }

    public static Result fetchPage(String placeholder) {
        int max_results = 105;
        if (placeholder == null) {
            placeholder = "0";
        }
        String nextPlaceholder = String.valueOf(Integer.valueOf(placeholder) + 1);
        if (Integer.valueOf(placeholder) * 10 > 103) {
            Integer[] array = new Integer[0];
            return new Result(null, array);
        }
        int size = Math.min(max_results, (Integer.valueOf(nextPlaceholder)) * 10);
        if (size == max_results) {
            nextPlaceholder = null;
        }
        Integer[] array = new Integer[size - Integer.valueOf(placeholder) * 10];
        for (int i=0; i<array.length; i++) {
            array[i] = Integer.valueOf(placeholder) * 10 + i;
        }
        return new Result(nextPlaceholder, array);
    }



    public static class ResultFetcher {

        private String placeHolder;

        private int indexToReadFrom;

        ResultFetcher() { this(null); }

        ResultFetcher(final String placeHolder) { this.placeHolder = placeHolder; this.indexToReadFrom=0; }

        final List<Integer[]> fetch(int n) {
            if(n < 1) return Collections.emptyList();
            final List<Integer[]> results = new LinkedList<>();
            do {
                final Result result = fetchPage(this.placeHolder);
                if(result.results.length > 0) {
                    final Integer[] finalResults = new Integer[Math.min(n, result.results.length)];
                    for (int i = 0; i < finalResults.length; i++) {
                        finalResults[indexToReadFrom % finalResults.length] = result.results[indexToReadFrom++];
                    }
                    results.add(finalResults);
                    if(n >= result.results.length) {
                        this.indexToReadFrom = 0;
                        this.placeHolder = result.getPlaceholder();
                    }
                    n = n - finalResults.length;
                }
            }while(n > 0 && this.placeHolder != null);
            return results;
        }

    }
}
