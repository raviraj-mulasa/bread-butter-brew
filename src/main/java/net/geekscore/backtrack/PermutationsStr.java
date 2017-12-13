package net.geekscore.backtrack;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class PermutationsStr {


    public static void main(String[] args) {
        permutations("ABC");
        permutations("ravi");
        permutations("GOOGLE");
    }

    private static final void permutations(String str) {
        if(str != null) {
            System.out.println("Dups Allowed");
            Instant start = Instant.now();
            permutationsHelper(str, "");
            System.out.println(Duration.between(start, Instant.now()).toMillis());
            start = Instant.now();
            System.out.println("Dups NOT Allowed");
            permutationsHelperNoDup(str, "", new HashSet<>());
            System.out.println(Duration.between(start, Instant.now()).toMillis());
        }
    }

    private static final void permutationsHelper(String str, String permutationSoFar) {
//        System.out.println("permutationsHelper("+str+","+permutationSoFar+")");
        final int strLen = str.length();
        if (strLen == 0) {
            System.out.println(permutationSoFar);
        } else {
            for (int i = 0; i < strLen; i++) {
                // Choose
                final char chosen = str.charAt(i); // choose char at i
                permutationSoFar = permutationSoFar + chosen; // append the chosen char to the permutation
                // Explore
                permutationsHelper(str.substring(0,i) + str.substring(i+1) , permutationSoFar); // explore by deleting the last chosen char from the string
                // Un-choose
                str = str.substring(0, i) + chosen + str.substring(i+1, strLen); // insert the last chosen char back in the string
                permutationSoFar = permutationSoFar.substring(0, permutationSoFar.length() - 1); // remove last chosen char from the permutation
            }

        }

    }

    private static final void permutationsHelperNoDup(String str, String permutationSoFar, Set<String> permutationsSeen) {
//        System.out.println("permutationsHelperNoDup("+str+","+permutationSoFar+","+permutationsSeen+")");
        final int strLen = str.length();
        if (strLen == 0) {
            System.out.println(permutationSoFar);
            permutationsSeen.add(permutationSoFar);
        } else {
            for (int i = 0; i < strLen; i++) {
                // Choose
                final char chosen = str.charAt(i); // choose char at i
                if(!permutationsSeen.contains(permutationSoFar + chosen)) {
                    permutationSoFar = permutationSoFar + chosen; // append the chosen char to the permutation
                    permutationsSeen.add(permutationSoFar);
                    // Explore
                    permutationsHelperNoDup(str.substring(0,i) + str.substring(i+1) , permutationSoFar, permutationsSeen); // explore by deleting the last chosen char from the string
                    // Un-choose
                    str = str.substring(0, i) + chosen + str.substring(i+1, strLen); // insert the last chosen char back in the string
                    permutationSoFar = permutationSoFar.substring(0, permutationSoFar.length() - 1); // remove last chosen char from the permutation
                }
            }
        }
    }

}
