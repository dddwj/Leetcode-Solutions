package leetcode.java.LC886;

import java.util.*;

public class Main_886 {

    public static void main(String[] args) {
        Main_886 main_886 = new Main_886();
        System.out.println(main_886.possibleBipartition(4, new int[][]{
                {1, 2},
                {1, 3},
                {2, 4}
        }));
    }



    // Solution: DFS    (Graph Theory)
    // Time: O(N + E)
    // Space: O(N + E)
    // Ref: https://leetcode-cn.com/problems/possible-bipartition/solution/ke-neng-de-er-fen-fa-by-leetcode/
    public boolean possibleBipartition(int N, int[][] dislikes) {
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<Integer>();

        for (int[] edge : dislikes) {       // 构建图，添加边
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        color = new HashMap<>();
        for (int node = 1; node <= N; node++) {
            if (!color.containsKey(node) && !dfs(node, 0))      // 还未上色, 从node开始遍历
                return false;
        }
        return true;
    }

    public boolean dfs(int node, int c) {
        System.out.println("Visting: " + node);
        if (color.containsKey(node))        // 如果已经上色，判断是否上色正确
            return color.get(node) == c;
        color.put(node, c);     // 上色
        System.out.println("Colored: " + node +"\n");

        for (int nei : graph[node])     // 为node的邻居上另一种色 (1 ^ color)
            if (!dfs(nei, c ^ 1))    // 如果上色不正确，冲突产生。
                return false;
        return true;                    // 遍历完成，上色无冲突。
    }

    ArrayList<Integer>[] graph;
    Map<Integer, Integer> color;    // <node, nodes's color>
}
