package leetcode.java.LC37;

import java.util.Arrays;

// See also: *LC51*, *LC46*, LC39, LC77, LC78, LC22
// Great Tutorial: https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/sudoku

public class Main_37 {

    public static void main(String[] args) {
        Main_37 main_37 = new Main_37();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        main_37.solveSudoku(board);
        Arrays.stream(board).forEach(line -> System.out.println(Arrays.toString(line)));
    }


    // Solution1: Backtrack
    // Ref: https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/sudoku
    //      LC51
    public void solveSudoku(char[][] board) {
        int i = 0, j = 0;
        backtrack(board, i, j);
        return;
    }

    private boolean backtrack(char[][] board, int i, int j) {
        int n = 9;
        if (j == n) {
            j = 0;
            i = i + 1;
            // return backtrack(board, i, j);
        }
        if (i == n) {       // base case
            return true;
        }
        if (board[i][j] != '.')
            return backtrack(board, i, j + 1);

        for (char ch = '1'; ch <= '9'; ch++) {
            if (!isValid(board, i, j, ch))
                continue;

            board[i][j] = ch;
            if (backtrack(board, i, j + 1))     // 如果找到一个可行解，立即结束
                return true;
            board[i][j] = '.';
        }

        return false;       // 穷举完 1~9，依然没有找到可行解，此路不通
    }

    private boolean isValid(char[][] board, int r, int c, char ch) {
        for (int i = 0; i < 9; i++) {
            if (board[i][c] == ch)
                return false;
            if (board[r][i] == ch)
                return false;
            if (board[(r/3)*3 + i/3][(c/3)*3 + i%3] == ch)      // 判断 3 x 3 方框是否存在重复
                return false;
        }
        return true;
    }

}
