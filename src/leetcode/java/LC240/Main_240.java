package leetcode.java.LC240;

public class Main_240 {

    // Reference: LC74
    //            剑指offer P44

    public static void main(String[] args) {
        Main_240 main_240 = new Main_240();
        System.out.println(main_240.searchMatrix_solution3(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 5));
        System.out.println(main_240.searchMatrix_solution3(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 27));

    }

    // Solution1: Brute Force
    // Time: O(M*N)
    // Space: O(1)
    // ...




    // Solution2: 沿对角线元素（按行、按列）二分查找
    // Time:    O(lg(n!))
    // Space:   O(1)
    public boolean searchMatrix_solution2(int[][] matrix, int target) {
        // an empty matrix obviously does not contain `target`
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        // iterate over matrix diagonals
        int shorterDim = Math.min(matrix.length, matrix[0].length);
        for (int i = 0; i < shorterDim; i++) {
            boolean verticalFound = binarySearch(matrix, target, i, true);
            boolean horizontalFound = binarySearch(matrix, target, i, false);
            if (verticalFound || horizontalFound) {
                return true;
            }
        }

        return false;
    }

    private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
        int lo = start;
        int hi = vertical ? matrix[0].length-1 : matrix.length-1;

        while (hi >= lo) {
            int mid = (lo + hi)/2;
            if (vertical) { // searching a column
                if (matrix[start][mid] < target) {
                    lo = mid + 1;
                } else if (matrix[start][mid] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            } else { // searching a row
                if (matrix[mid][start] < target) {
                    lo = mid + 1;
                } else if (matrix[mid][start] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            }
        }

        return false;
    }






    // Solution3: 逐列扫描，按行二分
    // Time: O(NlogN)
    // Space: O(logN) (for recursion)
    public boolean searchMatrix_solution3(int[][] mat, int targ) {
        // cache input values in object to avoid passing them unnecessarily
        // to `searchRec`
        matrix_s3 = mat;
        target_s3 = targ;

        // an empty matrix_s3 obviously does not contain `target_s3`
        if (matrix_s3 == null || matrix_s3.length == 0) {
            return false;
        }

        return searchRec(0, 0, matrix_s3[0].length-1, matrix_s3.length-1);
    }
    private int[][] matrix_s3;
    private int target_s3;
    private boolean searchRec(int left, int up, int right, int down) {
        // this submatrix has no height or no width.
        if (left > right || up > down) {
            return false;
            // `target_s3` is already larger than the largest element or smaller
            // than the smallest element in this submatrix.
        } else if (target_s3 < matrix_s3[up][left] || target_s3 > matrix_s3[down][right]) {
            return false;
        }

        int mid = left + (right-left)/2;

        // Locate `row` such that matrix_s3[row-1][mid] < target_s3 < matrix_s3[row][mid]
        int row = up;
        while (row <= down && matrix_s3[row][mid] <= target_s3) {
            if (matrix_s3[row][mid] == target_s3) {
                return true;
            }
            row++;
        }

        return searchRec(left, row, mid-1, down) || searchRec(mid+1, up, right, row-1);
    }




    // Solution4: Two Boundaries            // Reference: 剑指offer P44
    // Time: O(N+M)
    // Space: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length, n = matrix[0].length;

        int right = n - 1, top = 0;

        while (right >= 0 && top <= m - 1) {
            // System.out.println(matrix_s3[top][right]);
            if (matrix[top][right] == target) {
                return true;
            } else if (matrix[top][right] > target) {
                right--;
            } else if (matrix[top][right] < target) {
                top++;
            }
        }

        return false;
    }


}
