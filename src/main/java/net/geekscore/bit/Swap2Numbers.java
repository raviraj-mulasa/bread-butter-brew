package net.geekscore.bit;

public class Swap2Numbers {

    public static void main(String[] args) {
        int x = 523, y = 769;
        System.out.println(String.format("x: %d, y: %d", x, y));
        x = x ^ y;
        y = y ^ x;
        x = x ^ y;
        System.out.println(String.format("x: %d, y: %d", x, y));
    }
}
