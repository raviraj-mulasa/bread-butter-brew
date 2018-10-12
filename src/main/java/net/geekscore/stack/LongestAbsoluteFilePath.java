package net.geekscore.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Suppose we abstract our file system by a string in the following manner:
 *
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 * dir
 *  subdir1
 *  subdir2
 *      file.ext
 *
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing
 * a file file.ext.
 *
 * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
 * represents:
 * dir
 *  subdir1
 *      file1.ext
 *      subsubdir1
 *  subdir2
 *      subsubdir2
 *          file2.ext
 *
 * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and
 * an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2
 * containing a file file2.ext.
 *
 * We are interested in finding the longest (number of characters) absolute path to a file within our file system.
 * For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and
 * its length is 32 (not including the double quotes).
 *
 * Given a string representing the file system in the above format, return the length of the longest absolute path
 * to file in the abstracted file system. If there is no file in the system, return 0.
 *
 * Note:
 * The name of a file contains at least a . and an extension.
 * The name of a directory or sub-directory will not contain a ..
 *
 * Time complexity required: O(n) where n is the size of the input string.
 *
 * Notice that a/aa/aaa/file1.txt is not the longest file path,
 * if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 */
public class LongestAbsoluteFilePath {

    public static void main(String[] args) {
        System.out.println(longestFilePath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        System.out.println(longestFilePath("aaaaaaaaaaaaaaaaaaaaa\n\tsth.png\na\n\taa\n\t\taaa\n\t\t\tfile1.txt"));
        System.out.println(longestFilePath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    }

    private static String longestFilePath(final String fileSystem) {
        if(null == fileSystem || fileSystem.length() == 0) return "";
        Deque<String> dirStack = new LinkedList<>();
        Deque<Integer> lvlStack = new LinkedList<>();
        for (final String dir: fileSystem.split("\n")) {
            final int level = dir.lastIndexOf("\t"); // number of "\t"
            final String tabsRemovedDir = dir.substring(level+1);
            System.out.println(tabsRemovedDir+" @ level "+(level+1));
            if(isFile(tabsRemovedDir)) {
//                System.out.println(dirSoFar.toString());
            } else {
                dirStack.offer(tabsRemovedDir);
                lvlStack.offer(level+1);
            }
        }
        return "";
    }


    private static void longestFilePathRec(final String fileSystem, StringBuilder dirSoFar) {
        if(null == fileSystem || fileSystem.length() == 0) return ;
        System.out.println("Before "+dirSoFar);
        final int newLineIdx = fileSystem.indexOf('\n');
        if(newLineIdx != -1) {
            final String dir = fileSystem.substring(0, newLineIdx);

            int tabsIdx = 0;
            while(dir.indexOf('\t', tabsIdx) != -1) tabsIdx = dir.indexOf('\t', tabsIdx)+1;
            final String tabsRemovedDir = dir.substring(tabsIdx);
            final int lengthBefore = dirSoFar.length();
            dirSoFar.append("/").append(tabsRemovedDir);
            if (isFile(dirSoFar.toString())) {
                System.out.println(dirSoFar.toString());
            }
            longestFilePathRec(fileSystem.substring(newLineIdx+1), dirSoFar);
            if(lengthBefore > 0) {
                dirSoFar.delete(lengthBefore,dirSoFar.length());
                System.out.println("After "+dirSoFar);
            }
        }
    }

    private static boolean isFile(final String dirName) {
        return dirName.contains(".");
    }
}
