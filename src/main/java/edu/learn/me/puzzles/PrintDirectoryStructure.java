package edu.learn.me.puzzles;

import java.io.File;
import java.util.*;

/**
 * Created by ravirajmulasa on 9/1/16.
 */
public class PrintDirectoryStructure {

    public static final List<String> printDirectoryRec(final String path) {

        if(path == null || path.length() ==0) {
            return Collections.emptyList();
        }

        File dir            = new File(path);
        File[] filesInDir   = dir.listFiles();

        if(null == filesInDir) {
            return Collections.emptyList();
        }

        List<String> dirChildren = new LinkedList<>();
        for(File fileInDir: filesInDir) {
            dirChildren.add(fileInDir.getName());
            if(fileInDir.isDirectory()) {
                dirChildren.addAll(printDirectoryRec(fileInDir.getAbsolutePath()));
            }
        }
        return dirChildren;

    }


    public static final List<String> printDirectory(final String path) {

        if(path == null || path.length() == 0) {
            return Collections.emptyList();
        }

        List<String> dirChildren    = new LinkedList<>();
        File currDir                = new File(path);;
        Stack<File> stack           = new Stack<File>();
        stack.push(currDir);

        while(!stack.isEmpty()) {

            File fileOnStackTop = stack.pop();

            if(null != fileOnStackTop) {
                if(fileOnStackTop.isFile()) {
                    dirChildren.add(fileOnStackTop.getName());
                }
                else {
                    dirChildren.add(fileOnStackTop.getName());
                    File []  filesInDirOnStackTop = fileOnStackTop.listFiles();
                    if(null != filesInDirOnStackTop) {
                        for(File fileInDirOnStackTop: filesInDirOnStackTop) {
                            stack.push(fileInDirOnStackTop);
                        }
                    }
                }
            }
        }
        return dirChildren;
    }


    public static void main(String args[]) {
        System.out.println(printDirectoryRec("/Users/ravirajmulasa/Documents/personal_workspace/Bread-Butter/src/main/java/edu/learn/me/algo"));
        System.out.println(printDirectory("/Users/ravirajmulasa/Documents/personal_workspace/Bread-Butter/src/main/java/edu/learn/me/algo"));
    }
}
