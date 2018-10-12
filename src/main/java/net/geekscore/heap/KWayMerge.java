package net.geekscore.heap;

import java.util.*;

/**
 *
 * Imagine you have any number of servers (1 to 1000+) that generate log files for your distributed app.
 * Each log file can range from 100MB - 512GB in size.
 * They are copied to our machine which contains only 16GB of RAM.
 *
 * The local directory would look like this:
 * /temp/server-ac329xbv.log
 * /temp/server-buyew12x.log
 * /temp/server-cnw293z2.log
 *
 * Our goal is to print the individual lines all out to your screen, sorted by timestamp.
 * .....
 * A log file stuctured as a `CSV` with the date in ISO 8601 format in the first column
 * and an event in the second column.
 *
 * Each individual file is already in time order.
 *
 * As an example,
 * if file /temp/server-bc329xbv.log looks like:
 * 2016-12-20T19:00:45Z, Server A started.
 * 2016-12-20T19:01:25Z, Server A completed job.
 * 2016-12-20T19:02:48Z, Server A terminated.
 *
 * ,file /temp/server-cuyew12x.log looks like:
 * 2016-12-20T19:01:16Z, Server B started.
 * 2016-12-20T19:03:25Z, Server B completed job.
 * 2016-12-20T19:04:50Z, Server B terminated.
 *
 * And file /temp/server-cuyew12x.log looks like:
 * 2016-12-20T19:01:06Z, Server C started.
 * 2016-12-20T19:02:25Z, Server C completed job.
 * 2016-12-20T19:02:30Z, Server C terminated.
 *
 * Then our output would be:
 *
 * 2016-12-20T19:00:45Z, Server A started.
 * 2016-12-20T19:01:06Z, Server C started.
 * 2016-12-20T19:01:16Z, Server B started.
 * 2016-12-20T19:01:25Z, Server A completed job.
 * 2016-12-20T19:02:25Z, Server C completed job.
 * 2016-12-20T19:02:30Z, Server C terminated.
 * 2016-12-20T19:02:48Z, Server A terminated.
 * 2016-12-20T19:03:25Z, Server B completed job.
 * 2016-12-20T19:04:50Z, Server B terminated.

 */
public class KWayMerge {

    public static void main(String[] args) {
        final List[] files = new List[] {
                Arrays.asList(
                        "2016-12-20T19:00:45Z, Server A started."
                        ,"2016-12-20T19:01:25Z, Server A completed job."
                        ,"2016-12-20T19:02:48Z, Server A terminated."
                )
                ,Arrays.asList(
                        "2016-12-20T19:01:16Z, Server B started."
                        ,"2016-12-20T19:03:25Z, Server B completed job."
                        ,"2016-12-20T19:04:50Z, Server B terminated."
                )
                ,Arrays.asList(
                        "2016-12-20T19:01:06Z, Server C started."
                        ,"2016-12-20T19:02:25Z, Server C completed job."
                        ,"2016-12-20T19:02:30Z, Server C terminated."
                )
        };

        kWayMerge(files);
    }


    private static  void kWayMerge(final List[] files) {
        if(null == files || files.length == 0) return;
        final PriorityQueue<LogLineFile> minHeap = new PriorityQueue<>(files.length, new Comparator<LogLineFile>() {
            @Override
            public int compare(LogLineFile o1, LogLineFile o2) {
                return o1.logLine.split(" ")[0].compareTo(o2.logLine.split(" ")[0]);
            }
        });

        final ListIterator[] iterators = new ListIterator[files.length];
        for (int i = 0; i < files.length; i++) {
            iterators[i] = files[i].listIterator();
            if(iterators[i].hasNext()) minHeap.add(new LogLineFile((String)iterators[i].next(), i));
        }

        while (!minHeap.isEmpty()) {
            final LogLineFile top = minHeap.poll();
            System.out.println(top.logLine);
            if(iterators[top.fileIdx].hasNext()) minHeap.add(new LogLineFile((String)iterators[top.fileIdx].next(), top.fileIdx));
        }
    }

    private static class LogLineFile {
        String logLine;
        int fileIdx;
        private LogLineFile(String logLine, int index) {
            this.logLine = logLine;
            this.fileIdx = index;
        }

    }
}
