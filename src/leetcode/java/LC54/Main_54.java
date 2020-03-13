package leetcode.java.LC54;

import java.util.*;

public class Main_54 {

    // Reference: 剑指offer P161
    // GREAT Tutorial: https://youtu.be/uYgoo8BdUAA

    public static void main(String[] args) {
        Main_54 main_54 = new Main_54();
        System.out.println(main_54.spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        }));
    }


    // Solution: Layer by Layer
    // Time: O(N)  Space: O(1)
    // Ref: https://youtu.be/uYgoo8BdUAA
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<Integer>();
        }
        int n = matrix.length, m = matrix[0].length;
        int top = 0, bottom = n - 1, left = 0, right = m - 1;
        List<Integer> result = new ArrayList<>();

        while (result.size() < n * m) {
            for (int j = left; j <= right && result.size() < n * m; j++) {
                result.add(matrix[top][j]);
//                System.out.println(matrix[top][j]);
            }
            top++;

            for (int i = top; i <= bottom && result.size() < n * m; i++) {
                result.add(matrix[i][right]);
//                System.out.println(matrix[i][right]);
            }
            right--;

            for (int j = right; j >= left && result.size() < n * m; j--) {
                result.add(matrix[bottom][j]);
//                System.out.println(matrix[bottom][j]);
            }
            bottom--;

            for (int i = bottom; i >= top && result.size() < n * m; i--) {
                result.add(matrix[i][left]);
//                System.out.println(matrix[i][left]);
            }
            left++;

//            System.out.println("");
        }
        return result;
    }
}
