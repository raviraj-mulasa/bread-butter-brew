package net.geekscore.graph;

import java.util.Comparator;

public class EdgeWeightComparator implements Comparator<Edge>{

    @Override
    public int compare(Edge e1, Edge e2) {

        if(e1 == e2) return 0; // Both e1 and e2 refers to the same instance

        final Comparable e1Weight = e1.getWeight();
        final Comparable e2Weight = e2.getWeight();

        if(e1Weight == null && e2Weight != null) return -1;
        if(e1Weight != null && e2Weight == null) return  1;

        final int compareWeights = e1Weight.compareTo(e2Weight);
        if(compareWeights == 0){
            return e1.to().getValue().compareTo(e2.to().getValue()); // Resolve tie based on the value of neighbors
        }
        return compareWeights;
    }
}
