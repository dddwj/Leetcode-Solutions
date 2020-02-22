package leetcode.java.LC48;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 A GOOD TUTORIAL:
 https://youtu.be/gCciKhaK2v8
*/

public class Main_48 {
    public static void main(String[] args) {
        Main_48 main_48 = new Main_48();
//        int[][] input = {
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9},
//        };
        int[][] input = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        main_48.rotate_solution2(input);
    }



    // Solution1: transpose + reverse
    // Time: O(N*N)  Space: O(1)
    public void rotate_solution1(int[][] matrix) {
        int cnt = matrix.length;

        // Transpose
        for (int i = 0; i < cnt; i++) {
            for (int j = i; j < cnt; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reverse: Swap Elements in Rows
        for (int i = 0; i < cnt; i++) {
            int start = 0, end = cnt - 1;
            while (start < end) {
                int temp = matrix[i][end];
                matrix[i][end] = matrix[i][start];
                matrix[i][start] = temp;

                start++;
                end--;
            }
        }

        // Print
//         for(int[] m : matrix)
//             System.out.println(Arrays.toString(m));
    }



    // Solution2: Rotate four points recursively. More detailed than solution3.
    // Time: O(N*N)  Space: O(1)  (only a int[4] vector was used)
    public void rotate_solution2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2 + n % 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                System.out.println("i = " + i);
                System.out.println("j = " + j);
                // Take out the 'four points' to temps
                int[] tmp = new int[4];
                int row = i;
                int col = j;
                for (int k = 0; k < 4; k++) {
                    tmp[k] = matrix[row][col];
                    int x = row;
                    row = col;
                    col = (n - 1) - x;
                }

                // Assign temps to matrix
                for (int k = 0; k < 4; k++) {
                    matrix[row][col] = tmp[(k + 3) % 4];
                    int x = row;
                    row = col;
                    col = (n - 1) - x;
                }
            }
        }
    }


    // Solution3 (in youtube video): Rotate four points recursively. More concise than solution2.
    // Time: O(N*N)  Space: O(1)
    public void rotate_solution3(int[][] matrix) {
        int cnt = matrix.length;
        /*
        NOTE: Top, right, left, bottom are named as follows.
        [
         [top .....  right]
         [...          ...]
         [left .... bottom]
        ]
         */
        for (int i = 0; i < cnt / 2; i++) {    // Note: The recursive strategy in solution2 is also OK!
            for (int j = i; j < cnt - i - 1; j++) {
                // save the top
                int top = matrix[i][j];

                // move left to top
                matrix[i][j] = matrix[cnt - 1 - j][i];

                // move bottom to left
                matrix[cnt - 1 - j][i] = matrix[cnt - i - 1][cnt - 1 - j];

                // move right to bottom
                matrix[cnt - i - 1][cnt - 1 - j] = matrix[j][cnt - i - 1];

                // move top to right
                matrix[j][cnt - i - 1] = top;

            }
        }

        // Print
//        for(int[] m : matrix)
//            System.out.println(Arrays.toString(m));
    }


    // My Solution: Brute Force
    // Time: O(N*N)  Space: O(N*N) (Need extra space)
    public void rotate_mysolution(int[][] matrix) {
        int cnt = matrix.length;

        // Init data
        List<Integer>[] cols = new ArrayList[cnt];
        for (int i = 0; i < cols.length; i++)    // 此处初始化每个ArrayList别忘记！
            cols[i] = new ArrayList<Integer>();

        // Copy data to ArrayList
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < cnt; j++) {
                cols[j].add(matrix[i][j]);
            }
        }

        // Copy data back to matrix
        for (int i = 0; i < cnt; i++)
            for (int j = 0; j < cnt; j++) {
                matrix[i][j] = cols[i].get(cnt - 1 - j);
            }

        // Print
//        for(int[] m : matrix)
//            System.out.println(Arrays.toString(m));
    }

}