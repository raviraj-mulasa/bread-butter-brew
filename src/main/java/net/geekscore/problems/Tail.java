package net.geekscore.problems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Tail {

    public static void main(String[] args) throws Exception{

        System.out.println(tail(new File(Paths.get(".")+File.separator+"books/dummy.txt"), 20));
        System.out.println("-------------");
        System.out.println(tail(new File(Paths.get(".")+File.separator+"books/newline.txt"), 20));
        System.out.println("-------------");
        System.out.println(tail(new File(Paths.get(".")+File.separator+"src/main/java/net/geekscore/problems/Tail.java")));
        System.out.println("-------------");
        System.out.println(tail(new File(Paths.get(".")+File.separator+"src/main/java/net/geekscore/problems/Tail.java"), 20));

    }

    private static List<String> tail(final File file) throws IOException {
        return tail(file, 10);
    }

    private static List<String> tail(final File file, int lines) throws IOException {
        if(!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath()+" does not exists");
        }
        final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        final  List<String> tail = new LinkedList<>();
        long pointer = randomAccessFile.length() - 1;
        int line = 0;
        final StringBuilder lineBuilder = new StringBuilder();
        while (line < lines && pointer >= 0) {
            randomAccessFile.seek(pointer--);
            final char ch = (char)randomAccessFile.readByte();
            lineBuilder.append(ch);
            if(ch == '\n' || ch == '\r') { // new line character or carriage return
                tail.add(lineBuilder.reverse().toString());
                lineBuilder.setLength(0);
                line += 1;
            }
        }
        Collections.reverse(tail);
        return tail;
    }
}