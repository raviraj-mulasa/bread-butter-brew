package net.geekscore.algo.backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address
 * combinations.
 *
 * For example:
 * Given "25525511135",
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
public class RestoreIPAddresses {

    private static final int IPV4_OCTETS = 4;

    public static void main(String[] args) {
        System.out.println(restoreIPAddress("0000"));
        System.out.println(restoreIPAddress("127001"));
        System.out.println(restoreIPAddress("25525511135"));
    }

    private static List<String> restoreIPAddress(final String str) {
        if(null == str || str.length() < 4 || str.length() > 12) return Collections.emptyList();
        final List<List<String>> restoredIPAddressesOctets = new LinkedList<>();
        restoreIPAddressHelper(str, 0, restoredIPAddressesOctets, new LinkedList<>());

        List<String> result = new LinkedList<>();
        for (final List<String> validIPAddress: restoredIPAddressesOctets) {
            final StringBuilder stringBuilder = new StringBuilder();
            for (final String octet: validIPAddress) {
                final int lastIdxZero = octet.lastIndexOf("0");
                if(lastIdxZero > -1) {
                    if(lastIdxZero == octet.length() - 1) stringBuilder.append("0");
                    else  {
                        stringBuilder.append(octet.substring(lastIdxZero+1, octet.length()));
                        stringBuilder.append(octet.substring(lastIdxZero+1, octet.length()));
                    }
                } else {
                    stringBuilder.append(octet);
                }
                stringBuilder.append(".");
            }
            result.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        System.out.println(restoredIPAddressesOctets);
        System.out.println(result);
        return Collections.emptyList();
    }

    private static void restoreIPAddressHelper(final String str, final int start, final List<List<String>> restoredIPAddressesOctets, List<String> ipAddressOctetsSoFar) {

        // if already get 4 numbers, but s is not consumed, return
        if(ipAddressOctetsSoFar.size() >= IPV4_OCTETS && start < str.length()) return;

        if(ipAddressOctetsSoFar.size() == IPV4_OCTETS && start == str.length()) {
            restoredIPAddressesOctets.add(new ArrayList<>(ipAddressOctetsSoFar));
            return;
        }

        for (int i = 1; i <=3 ; i++) {
            if(start+i <= str.length() && Integer.valueOf(str.substring(start, start+i)) < 256) {
                ipAddressOctetsSoFar.add(str.substring(start, start+i));
                restoreIPAddressHelper(str, start+i, restoredIPAddressesOctets, ipAddressOctetsSoFar);
                ipAddressOctetsSoFar.remove(ipAddressOctetsSoFar.size()-1);
            }
        }
    }
}
