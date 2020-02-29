package leetcode.java.LC695;

import java.util.Arrays;
import java.util.Stack;

public class Main_695 {
    public static void main(String[] args) {
        Main_695 main_695 = new Main_695();
//        int[][] grid = {
//                {1, 1, 0, 0},
//                {1, 1, 0, 0},
//                {0, 0, 1, 1},
//                {0, 0, 1, 1}
//        };
//        int[][] grid = {
//                {0, 0},
//                {1, 1}
//        };
        int[][] grid = {
                {1, 0, 1},
                {1, 1, 1},
        };
        System.out.println(main_695.maxAreaOfIsland_solution2(grid));
    }


    // My Solution (Solution1 on Leetcode.com): Depth-First Search (Recursive)
    // Time: O(Row * Column)
    // Space: O(Row * Column)
    public int maxAreaOfIsland_solution1(int[][] grid) {
        int maxArea = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
    /*
     *  Not necessary to initialize
     *  The boolean array is initialized with all 'false'
     *
        for (boolean[] row : visited) {
            System.out.println(Arrays.toString(row));
            Arrays.fill(row, false);
        }
    */
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (visited[i][j] || grid[i][j] == 0) {
                    continue;
                }

                // DFS from grid(i, j)
                int area = countLinked(grid, visited, i, j);

                // Store
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }

    private int countLinked(int[][] grid, boolean[][] visited, int i, int j) {
        int count;
        if (i >= grid.length || j >= grid[0].length || j < 0 || i < 0)   return 0;
        if (grid[i][j] == 0)    return 0;
        if (visited[i][j])  return 0;

//        System.out.println("Counting: ( " +i+", "+j+")");

        // Linked & not counted
        visited[i][j] = true;
        count = 1 + countLinked(grid, visited, i, j + 1)
                + countLinked(grid, visited, i + 1, j)
                + countLinked(grid, visited, i, j - 1)
                + countLinked(grid, visited, i - 1, j);

        return count;
    }



    // Solution2: Depth-First Search (Iterative)
    // Time: O(Row * Column)
    // Space: O(Row * Column)
    public int maxAreaOfIsland_solution2(int[][] grid) {
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        int maxArea = 0;
        for (int r0 = 0; r0 < grid.length; r0++) {
            for (int c0 = 0; c0 < grid[0].length; c0++) {
                if (grid[r0][c0] == 1 && !seen[r0][c0]) {
                    // Prepare
                    int area = 0;
                    Stack<int[]> stack = new Stack();
                    stack.push(new int[]{r0, c0});
                    seen[r0][c0] = true;

                    // DFS - Iterative, Start from grid(r0, c0)
                    while (!stack.empty()) {
                        int[] node = stack.pop();
                        int r = node[0], c = node[1];
                        area++;
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if (0 <= nr && nr < grid.length &&
                                    0 <= nc && nc < grid[0].length &&
                                    grid[nr][nc] == 1 && !seen[nr][nc]) {
                                stack.push(new int[]{nr, nc});
                                seen[nr][nc] = true;
                            }
                        }
                    }
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
}
