package net.geekscore.disjointset;

public class DisjointSetExample {

    public static void main(String[] args) {

        final IDisjointSet<Integer> disjointSet = new DisjointSet<>();

        disjointSet.makeSet(1);
        disjointSet.makeSet(2);
        disjointSet.makeSet(3);
        disjointSet.makeSet(4);
        disjointSet.makeSet(5);
        disjointSet.makeSet(6);
        disjointSet.makeSet(7);

        System.out.println(disjointSet.findSet(1));
        System.out.println(disjointSet.findSet(2));
        System.out.println(disjointSet.findSet(3));
        System.out.println(disjointSet.findSet(4));
        System.out.println(disjointSet.findSet(5));
        System.out.println(disjointSet.findSet(6));
        System.out.println(disjointSet.findSet(7));

        disjointSet.union(1, 2);
        disjointSet.union(2, 3);
        disjointSet.union(4, 5);
        disjointSet.union(6, 7);
        disjointSet.union(5, 6);
        disjointSet.union(3, 7);

        System.out.println("After Union");

        System.out.println(disjointSet.findSet(1));
        System.out.println(disjointSet.findSet(2));
        System.out.println(disjointSet.findSet(3));
        System.out.println(disjointSet.findSet(4));
        System.out.println(disjointSet.findSet(5));
        System.out.println(disjointSet.findSet(6));
        System.out.println(disjointSet.findSet(7));
    }
}
