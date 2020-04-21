package leetcode.java.UnknownNumber;

public class LeftMostColumnWithOne {
    public static void main(String[] args) {
        LeftMostColumnWithOne solution = new LeftMostColumnWithOne();
      /*  System.out.println(solution.leftMostColumnWithOne_solution2(new int[][]{
//            {0,0,0,1,1},
//            {1,1,1,1,1},
//            {0,0,1,1,1},
//            {0,0,0,0,1},
            {0,0,0,0,0}
        }));
        System.out.println(solution.leftMostColumnWithOne_solution2(new int[][] {
                {0, 0},
                {0, 1},
        }));
*/
        System.out.println(solution.leftMostColumnWithOne_solution2(new int[][] {
                {0,0,0,1},
                {0,0,1,1},
                {0,1,1,1}
        }));
    }

    // Solution1: Binary Search
    public int leftMostColumnWithOne_solution1(int[][] mat) {
    /*
    public int leftMostColumnWithOne_solution1(BinaryMatrix binaryMatrix) {
        List<Integer> dims = binaryMatrix.dimensions();
        int m = dims.get(1), n = dims.get(0);
    */
        int m = mat[0].length, n = mat.length;
        int minLen = Integer.MAX_VALUE;
        boolean found = false;
        for (int i = 0; i < n; i++) {
    //      len = binarySearch(i, m, binaryMatrix);
            int len = binarySearch(i, m, mat);
            if (len >= 0) {
                minLen = Math.min(len, minLen);
                found = true;
            }
        }
        return found ? minLen : -1;
    }

    private int binarySearch(int row, int col, int[][] mat) {
//  private int binarySearch(int row, int col, BinaryMatrix binaryMatrix) {
        int lo = 0, hi = col - 1;
        int mid = (hi + lo) / 2;

        while (lo <= hi) {
//            int num = binaryMatrix.get(row, mid);
            int num = mat[row][mid];
            if (num == 0) {
                lo = mid + 1;
            } else if (num == 1) {   // 1 starts from [lo, mid]     //TODO: 如何快速找到0和1的分界点？（是否可以参考LC33？）
                for (int i = lo; i <= mid; i++) {
//                    if (binaryMatrix.get(row, i) == 1)
                    if (mat[row][i] == 1)
                        return i;
                }
            }
            mid = (hi + lo) / 2;
        }
        return -1;
    }



    // Solution2: Search from the upper right
    public int leftMostColumnWithOne_solution2(int[][] mat) {
        boolean found = false;
        int m = mat[0].length, n = mat.length;
        int j = m - 1;
        for (int i = 0; i < n; i++) {
            if (mat[i][j] == 0)
                continue;
            else
                found = true;
            while (j - 1 >= 0 && mat[i][j-1] == 1) {
                j--;
            }
            if (j == 0)
                return 0;
        }
        return found ? j : -1;
    }

}
