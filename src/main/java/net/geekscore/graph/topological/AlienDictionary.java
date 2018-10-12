package net.geekscore.graph.topological;



import java.util.*;

/**
 *
 * Given a file of strings in some mystery lexicographic order, come up with a way to determine
 * the order the file is in.
 *
 * I always make sure the candidate understands what lexicographic order means.
 * If they're confused I usually motivate it by writing out a sample input file, e.g.:
 *
 * apricot
 * baby
 * bath
 * cab
 * cat
 * frog
 * game
 * zoo
 *
 * A sorted order of characters for the above file could be: a, b, c, t, f, g, z
 * because we only have enough information to have relative ordering between characters.
 *
 * Note that this is a partial ordering (there are several possible partial orderings here).
 * When there is only one possible ordering, we call this total ordering
 * (eg the ordering of the alphabet in our universe).
 */

public class AlienDictionary {

    enum State {
        NOT_DISCOVERED,
        DISCOVERED,
        EXPLORED
    };


    public static void main(String[] args) {

        System.out.println("-------------------");

        System.out.println(ordering(new String[] { "abc", "ace" ,"mbc" ,"mmc"})); // [a, b, m, c]
        System.out.println(ordering(new String[] { "apricot", "baby" ,"bath" ,"cab", "cat", "frog", "game", "zoo"})); // [a, b, c, t, f, g, z]
        System.out.println(ordering(new String[] { "baby" , "bath"})); // [b, t]
        System.out.println(ordering(new String[] { "wrt", "wrf", "er", "ett", "rftt"})); // [w, e, r, t, f]
        System.out.println(ordering(new String[] { "wrtkj", "wrt"})); // []
        System.out.println(ordering(new String[] { "z", "x"})); // [z, x]
        System.out.println(ordering(new String[] { "z", "z"})); // ["z"]
        System.out.println(ordering(new String[] { "wrt", "wrt"})); // ["rtw"]

        System.out.println("-------------------");

        System.out.println(ordering1(new String[] { "abc", "ace" ,"mbc" ,"mmc"})); // [a, b, m, c]
        System.out.println(ordering1(new String[] { "apricot", "baby" ,"bath" ,"cab", "cat", "frog", "game", "zoo"})); // [a, b, c, t, f, g, z]
        System.out.println(ordering1(new String[] { "baby" , "bath"})); // [b, t]
        System.out.println(ordering1(new String[] { "wrt", "wrf", "er", "ett", "rftt"})); // [w, e, r, t, f]
        System.out.println(ordering1(new String[] { "wrtkj", "wrt"})); // []
        System.out.println(ordering1(new String[] { "z", "x"})); // [z, x]
        System.out.println(ordering1(new String[] { "z", "z"})); // ["z"]
        System.out.println(ordering1(new String[] { "wrt", "wrt"})); // ["rtw"]
    }

    private static Collection<Character> ordering(final String[] words) {
        if(words == null || 0 == words.length) return Collections.emptyList();
        final Deque<Character> topologicalSort = new LinkedList<>();
        if(words.length == 1) {
            for (char ch: words[0].toCharArray()) {
                topologicalSort.offer(ch);
            }
            return topologicalSort;
        }

        final Map<Character, Set<Character>> adjList = new LinkedHashMap<>();
        final Map<Character, State> states = new HashMap<>();
        buildGraph(words, adjList);
        System.out.println(adjList);

        Set<Character> alphabet = new HashSet<>(adjList.keySet());
        for (Collection<Character> values: adjList.values()) {
            alphabet.addAll(values);
        }

        for (final Character ch: alphabet) states.put(ch,State.NOT_DISCOVERED);

        for (char ch: alphabet) {
            if(dfs(ch, adjList, states, topologicalSort)) return Collections.emptyList();
        }
        Collections.reverse((List<?>) topologicalSort);
        return topologicalSort;

    }

    private static boolean dfs(final char ch, final Map<Character, Set<Character>> adjList , final Map<Character, State> states, final Deque<Character> topologicalSort) {
        if(states.get(ch) == State.NOT_DISCOVERED) {
            states.put(ch,State.DISCOVERED);
            if(adjList.containsKey(ch)) {
                for (final char neighbor: adjList.get(ch)) {
                    if(states.get(neighbor) == State.DISCOVERED) return true;
                    if(dfs(neighbor, adjList, states, topologicalSort)) return true;
                }
            }
            states.put(ch,State.EXPLORED);
            topologicalSort.offer(ch);
        }
        return false;
    }

    private static void buildGraph(final String[] words, final Map<Character, Set<Character>> adjList ) {
        for (int i = 0; i < words.length-1; i++) {
            int j = i+1;
            for (int k = 0; k < Math.min(words[i].length(), words[j].length()); k++) {
                if(words[i].charAt(k) != words[j].charAt(k)) {
                    final Set<Character> neighbors = adjList.getOrDefault(words[i].charAt(k), new LinkedHashSet<>());
                    neighbors.add(words[j].charAt(k));
                    adjList.put(words[i].charAt(k), neighbors);
                    break;
                }
            }
        }
    }

    private static Collection<Character> ordering1(final String[] words) {
        if (words == null || 0 == words.length) return Collections.emptyList();

        final Set<Character> alphabet = new LinkedHashSet<>();

        for(String word: words) {
            for (char ch: word.toLowerCase().toCharArray()) alphabet.add(ch);
        }
        final boolean[][] adjMatrix  = new boolean[26][26];
        buildGraph(words, adjMatrix);

        final Map<Character, State> states = new HashMap<>();
        for (char ch : alphabet) states.put(ch, State.NOT_DISCOVERED);

        final Set<Character> visited = new HashSet<>();

        final Deque<Character> topologicalSort = new LinkedList<>();
        for (char ch= 'a'; ch <= 'z'; ch++) {
            if(dfs(ch, adjMatrix, states, topologicalSort, visited)) return Collections.emptyList();
        }
        return topologicalSort;

    }

    private static void buildGraph(final String[] words, final boolean[][] adjMatrix) {
        for (int i = 0; i < words.length-1; i++) {
            for (int k = 0; k < words[i].length() &&  k < words[i+1].length(); k++) {
                if(words[i].charAt(k) != words[i+1].charAt(k)) {
                    adjMatrix[words[i].charAt(k) - 'a'][words[i+1].charAt(k) - 'a'] = true;
                    break;
                }
            }
        }
    }

    private static boolean dfs(final char ch, final boolean[][] adjMatrix ,final Map<Character, State> states, final Deque<Character> topologicalSort, final Set<Character> visited) {
        if(states.get(ch) == State.NOT_DISCOVERED) {
            states.put(ch,State.DISCOVERED);
            for (int i = 0; i < adjMatrix[ch-'a'].length; i++) {
                if(adjMatrix[ch-'a'][i]) {
                    if(states.get((char)('a'+i)) == State.DISCOVERED) return true;
                    if(dfs((char)(i+'a'), adjMatrix, states, topologicalSort, visited)) return true;
                }
            }
            states.put(ch,State.EXPLORED);
            topologicalSort.offerFirst(ch);
        }
        return false;
    }

}
