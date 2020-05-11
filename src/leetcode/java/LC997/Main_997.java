package leetcode.java.LC997;

import java.util.*;

public class Main_997 {

    public static void main(String[] args) {
        Main_997 main_997 = new Main_997();
        System.out.println(main_997.findJudge(4, new int[][]{
                {1, 3},
                {1, 4},
                {2, 4},
                {2, 3},
                {4, 3},
                // {3, 1}
        }));
    }


    // My Solution: Graph (Map)  (Similar to Solution1)
    // Time: O(N) ?
    // Space: O(N * N)
    public int findJudge_mysolution(int N, int[][] trust) {
        Set<Integer> noJudge = new HashSet<>(N + 1);
        Set<Integer>[] beTrustedMap = new HashSet[N + 1];
        Arrays.fill(beTrustedMap, new HashSet<Integer>(N + 1));
        for (int[] line : trust) {
            int from = line[0], to = line[1];
            beTrustedMap[to].add(from);
            noJudge.add(from);
        }
        for (int i = 1; i < N + 1; i++) {
            if (beTrustedMap[i].size() == N - 1 && !noJudge.contains(i)) {
                return i;
            }
        }
        return -1;
    }



    // Solution1: Graph (Map)
    // Time: O(N)
    // Space: O(N)
    // Ref: https://leetcode.com/problems/find-the-town-judge/discuss/624428/JAVA-Easy-to-understand-solution-by-using-arrays-only.
    public static int findJudge(int N, int trust[][]) {
        int i;
        int givetrust[] = new int[N + 1];           // Note: 采用这个数据结构，省空间，是比我改进的地方。
        int taketrust[] = new int[N + 1];
        for(i = 0; i < trust.length; i++) {
            int t[] = trust[i];
            givetrust[t[0]]++;
            taketrust[t[1]]++;

        }
        for(i = 1; i < givetrust.length; i++) {
            if(givetrust[i]==0) {
                if(taketrust[i] == N-1)
                    return i;
            }
        }
        return -1;
    }
}
