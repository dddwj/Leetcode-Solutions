package leetcode.java.LC74;

public class Main_74 {

    // Reference: LC240
    //            剑指offer P44

    public static void main(String[] args) {
        Main_74 main_74 = new Main_74();
        System.out.println(main_74.searchMatrix_solution1(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 11));
    }



    // My Solution: Brute Force
    // Time: O(M*N)
    // Space: O(1)
    public boolean searchMatrix_mysolution(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length, n = matrix[0].length;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == target) {
                    return true;
                }
            }
        }
        return false;
    }



    // Solution1: Binary Search
    // Time: O(log(M*N))
    // Space: O(1)
    // Note: 标准的二分查找，需要牢记！
    public boolean searchMatrix_solution1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length, n = matrix[0].length;

        int left = 0, right = m * n - 1;
        while (left <= right) {
            int pivot = (left + right) / 2;
            int r = pivot / n, c = pivot % n;    // Note: Key Point Here!
            if (target == matrix[r][c]) {
                return true;
            } else if (target < matrix[r][c]) {
                right = pivot - 1;
            } else if (target > matrix[r][c]) {
                left = pivot + 1;
            }
        }
        return false;
    }

}
