package net.geekscore.search;

import java.security.SecureRandom;
import java.util.Random;

/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version
 * are also bad.
 * are also bad.

 Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

 You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 */
public class FirstBadVersion {

    private static class VersionControl {
        protected final int versions;
        private final int badVersion;
        private final Random random;

        private VersionControl(int n){
            this.versions = n;
            this.random = new SecureRandom();
            this.badVersion = this.random.nextInt(n-1+1)+1;
            System.out.println("Bad Version "+this.badVersion);
        }
        protected boolean isBadVersion(int version) {
            return this.badVersion <= version;
        }
    }

    private static class BadVersion extends  VersionControl {

        private BadVersion(int n) {
            super(n);
        }
        private int firstBadVersion() {
            int low = 1;
            int high = this.versions;
            while (low <= high) {
                final int mid = low+(high-low)/2;
                if(!this.isBadVersion(mid)) low = mid+1;
                else high = mid-1;
            }
            return low;
        }
    }

    public static void main(String[] args) {
        final BadVersion badVersion = new BadVersion(10);
        System.out.println("First Bad Version "+badVersion.firstBadVersion());
    }
}
