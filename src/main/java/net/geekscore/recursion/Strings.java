package net.geekscore.recursion;

public class Strings {

    public static void main(String[] args) {
        String para = "Roses are red,\nViolets are blue\nAll my base\nAre belong to you";
        System.out.println("===== Para =====");
        System.out.println(para);
        System.out.println("===== Reversed Lines =====");
        reverseLines(para);
        para = "\n";
        System.out.println("===== Para =====");
        System.out.println(para);
        System.out.println("===== Reversed Lines =====");
        reverseLines(para);
        para = "\nAre belong to you";
        System.out.println("===== Para =====");
        System.out.println(para);
        System.out.println("===== Reversed Lines =====");
        reverseLines(para);
    }

    private static void reverseLines(final String para) {
        if(para !=null && para.length() >= 1){
            final int newLineIdx = para.indexOf("\n");
            if(newLineIdx != -1) {
                reverseLines(para.substring(newLineIdx+1));
                System.out.println(para.substring(0, newLineIdx));
            } else {
                System.out.println(para);
            }
        }
    }
}
