package net.geekscore.disjointset;

import java.util.HashMap;
import java.util.Map;

/**
 * Disjoint sets using path compression and union by rank
 * Supports 3 operations
 * 1) makeSet
 * 2) union
 * 3) findSet
 *
 * For m operations and total n elements time complexity is O(m*f(n))
 * where f(n) is very slowly growing function.
 * For most cases f(n) <= 4 so effectively total time will be O(m).
 *
 * @param <T>
 */
public class DisjointSet<T extends Comparable> implements IDisjointSet<T> {

    private class Node {
        Node parent;
        T value;
        int rank;

        private Node(final T value) {
            this.value = value;
            this.parent = this;
            this.rank = 0;
        }
    }

    private final Map<T, Node> elements;

    public DisjointSet() {
        elements = new HashMap<>();
    }

    @Override
    public void makeSet(T value) {
        if(!this.elements.containsKey(value)) {
            this.elements.put(value, new Node(value));
        }
    }

    @Override
    public void union(T value1, T value2) {
        if(!this.elements.containsKey(value1)) {
            makeSet(value1);
        }
        if(value1.compareTo(value2) != 0 && !this.elements.containsKey(value2)) {
            makeSet(value2);
        }
        final Node parent1 = this.findSet(this.elements.get(value1));
        final Node parent2 = this.findSet(this.elements.get(value2));
        final int rank1 = parent1.rank;
        final int rank2 = parent2.rank;
        if(rank1 >= rank2) { // Union by rank;
            if(rank1 == rank2) {
                parent1.rank = rank1+1;
            }
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
    }

    @Override
    public T findSet(T value) {
        if(this.elements.containsKey(value)) {
            return findSet(this.elements.get(value)).value;
        }
        return null;
    }

    private Node findSet(Node node) {
        final Node parent = node.parent;
        if(node == parent) return parent;
        node.parent = findSet(node.parent); // path compression
        return node.parent;
    }
}
