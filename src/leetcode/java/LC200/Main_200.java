package leetcode.java.LC200;

import java.util.*;

public class Main_200 {

    // Reference: LC695

    public static void main(String[] args) {
        Main_200 main_200 = new Main_200();
        System.out.println(main_200.numIslands_solution2(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        }));
        System.out.println(main_200.numIslands_solution2(new char[][]{
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'}
        }));
        System.out.println(main_200.numIslands_solution2(new char[][]{}));
    }


    // Solution1: DFS (Recursive)
    // Time: O(M*N)
    // Space: O(M*N + H),  where H: worst=M*N（全为陆地，递归栈深度为M*N）
    public int numIslands_solution1(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int count = 0;
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (seen[row][col] || grid[row][col] == '0') {
                    seen[row][col] = true;
                    continue;
                }
                dfs(row, col, seen, grid);
                count++;
            }
        }
        return count;
    }

    private void dfs(int row, int col, boolean[][] seen, char[][] grid) {
        if (row >= grid.length || col >= grid[0].length
                || row < 0 || col < 0
                || seen[row][col]) {
            return;
        }
        seen[row][col] = true;
        if (grid[row][col] == '0') {
            return;
        } else {
            dfs(row+1, col, seen, grid);
            dfs(row-1, col, seen, grid);
            dfs(row, col+1, seen, grid);
            dfs(row, col-1, seen, grid);
        }
        return;
    }



    // Solution2: (Iterative)    DFS(with stack)   ===   BFS (with queue)
    // Time: O(M*N)
    // Space: O(M*N)
    public int numIslands_solution2(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int count = 0;
        boolean[][] seen = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (seen[i][j] || grid[i][j] == '0') {
                    seen[i][j] = true;
                    continue;
                }

                Stack<int[]> stack = new Stack<>();
                stack.push(new int[]{i, j});
                seen[i][j] = true;
                count++;

                while (!stack.isEmpty()) {
                    int[] pair = stack.pop();
                    int r = pair[0], c = pair[1];
                    if (grid[r][c] == '1') {
                        if (r + 1 < grid.length && !seen[r + 1][c]) {
                            stack.push(new int[]{r + 1, c});
                            seen[r + 1][c] = true;
                        }
                        if (r - 1 >= 0 && !seen[r - 1][c]) {
                            stack.push(new int[]{r - 1, c});
                            seen[r - 1][c] = true;
                        }
                        if (c + 1 < grid[0].length && !seen[r][c + 1]) {
                            stack.push(new int[]{r, c + 1});
                            seen[r][c + 1] = true;
                        }
                        if (c - 1 >= 0 && !seen[r][c - 1]) {
                            stack.push(new int[]{r, c - 1});
                            seen[r][c - 1] = true;
                        }
                    }
                }
            }
        }

        return count;
    }





    // Solution3: Union Find                // TO Be Understood...
    // Time: O(M*N) Space: O(M*N)
    // Ref: https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-shu-liang-by-leetcode/
    public int numIslands_solution3(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r-1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }


    class UnionFind {
        int count; // # of connected components
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) { // for problem 200
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int i) { // path compression
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) { // union with rank
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx; rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

}
