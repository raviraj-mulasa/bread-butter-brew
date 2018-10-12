package net.geekscore.graph.bipartite;

/**
 *
 * https://uva.onlinejudge.org/external/110/p11080.pdf
 *
 * In the country of Ajabdesh there are some streets and junctions. Each street connects 2 junctions.
 * The king of Ajabdesh wants to place some guards in some junctions so that all the junctions and streets
 * can be guarded by them. A guard in a junction can guard all the junctions and streets adjacent to it.
 * But the guards themselves are not gentle. If a street is guarded by multiple guards then they start fighting.
 * So the king does not want the scenario where a street may be guarded by two guards.
 *
 * Given the information about the streets and junctions of Ajabdesh, help the king to find the minimum
 * number of guards needed to guard all the junctions and streets of his country.
 *
 * Input
 The first line of the input contains a single integer T (T < 80) indicating the number of test cases.
 Each test case begins with 2 integers v (1 ≤ v ≤ 200) and e (0 ≤ e ≤ 10000.). v is the number of
 junctions and e is the number of streets. Each of the next e line contains 2 integer f and t denoting
 that there is a street between f and t. All the junctions are numbered from 0 to v − 1.
 Output
 For each test case output in a single line an integer m denoting the minimum number of guards needed
 to guard all the junctions and streets. Set the value of m as ‘-1’ if it is impossible to place the guards
 without fighting.
 Sample Input
 2
 4 2
 0 1
 2 3
 5 5
 0 1
 1 2
 2 3
 0 4
 3 4
 Sample Output
 2
 -1
 */
public class KingOfAjabdesh {


}
