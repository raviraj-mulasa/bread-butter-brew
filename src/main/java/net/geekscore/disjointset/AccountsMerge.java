package net.geekscore.disjointset;

import java.util.*;

/**
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element
 * accounts[i][0] is a name, and the rest of the elements are emails re`presenting emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there
 * is some email that is common to both accounts. Note that even if two accounts have the same name,
 * they may belong to different people as people could have the same name. A person can have any number
 * of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each
 * account is the name, and the rest of the elements are emails in sorted order.
 *
 * The accounts themselves can be returned in any order.
 *Example 1:
 * Input:
 * accounts = [
 *  ["John", "johnsmith@mail.com", "john00@mail.com"],
 *  ["John", "johnnybravo@mail.com"],
 *  ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 *  ["Mary", "mary@mail.com"]
 * ]
 *
 * Output: [
 *  ["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
 *  ["John", "johnnybravo@mail.com"],
 *  ["Mary", "mary@mail.com"]
 * ]
 *
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer
 * [
 *  ['Mary', 'mary@mail.com'],
 *  ['John', 'johnnybravo@mail.com'],
 *  ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']
 * ]
 * would still be accepted.
 *
 * Note:
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].
 */
public class AccountsMerge {

    private static class Account {
        int index = -1;
        String name;
        Set<String> emails;
        Account(final String name, final  Set<String> emails, int index) {
            this.name = name;
            this.emails = emails;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        System.out.println(accountsMerge(Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com")
                ,Arrays.asList("John", "johnnybravo@mail.com")
                ,Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com")
                ,Arrays.asList("John", "john_newyork@mail.com" , "john01@mail.com")
                ,Arrays.asList("Mary", "mary@mail.com")
        )));
    }

    private static List<List<String>> accountsMerge(List<List<String>> accounts) {

        final List<Account> accountList = new LinkedList<>();
        for (int i = 0; i < accounts.size(); i++) {
            final List<String> account = accounts.get(i);
            final String name = account.get(0);
            final Set<String> emails = new HashSet<>(account.subList(1, account.size()));
            accountList.add(new Account(name, emails, i));
        }

        Account prev = accountList.get(0);
        for (int i = 1; i < accountList.size(); i++) {
            Account curr = accountList.get(i);
            Set<String> emailIntersection = new HashSet<>(prev.emails);
            emailIntersection.retainAll(curr.emails);
            if(!emailIntersection.isEmpty()) {
                curr.index = prev.index;
            }
        }


        final List<List<String>> mergedAccounts = new LinkedList<>();
//        for (final Map.Entry<String, List<Account>> entry: parent.entrySet()) {
//            final List<String> mergedAccount = new LinkedList<>();
//            mergedAccount.add(entry.getValue().get(0).name); // name
//            if(entry.getValue().size() > 1) {
//                final Set<String> emails = new HashSet<>();
//
//                for (final Account account: entry.getValue()) {
//                    emails.addAll(account.emails);
//                }
//                mergedAccount.addAll(emails); // emails
//                mergedAccounts.add(mergedAccount);
//            }
//
//        }

        return mergedAccounts;

    }




}
