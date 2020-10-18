package leetcode.java.LC51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// See also: *LC37*, *LC46*, LC39, LC77, LC78, LC22

// Solution1: Classic Backtrack
// Ref: https://leetcode-cn.com/problems/n-queens/solution/hui-su-suan-fa-xiang-jie-by-labuladong/216150
//      LC46, LC37
public class Main_51_solution1 {

    public static void main(String[] args) {
        Main_51_solution1 main_51 = new Main_51_solution1();
        main_51.solveNQueens(4).forEach(System.out::println);
//        System.out.println(main_51.isValid(new char[][]{
//                {'Q', '.', '.', '.'},
//                {'.', '.', '.', '.'},
//                {'.', '.', '.', '.'},
//                {'.', '.', '.', '.'}
//        }, 1, 1));
    }


    public List<List<String>> solveNQueens(int n) {
        if (n < 0)
            return null;

        List<List<String>> res = new ArrayList<>();

        char[][] board = new char[n][n];
        for (char[] line : board) {
            Arrays.fill(line, '.');
        }

        backtrack(0, board, res);
        return res;
    }

    private void backtrack(int row, char[][] board, List<List<String>> res) {
        if (row == board.length) {
            res.add(chartoString(board));
            return;
        }

        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col))
                continue;
            board[row][col] = 'Q';
            backtrack(row + 1, board, res);
            board[row][col] = '.';
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        int rows = board.length;
        // check is valid in col
        for (char[] chars : board)
            if (chars[col] == 'Q')
                return false;
        // check is valide upright
        for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }
        // check is valide upleft
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }
        return true;
    }

    private List<String> chartoString(char[][] board) {
        List<String> boardLine = new ArrayList<>();
        for (char[] line :  board) {
            boardLine.add(new String(line));
        }
        return boardLine;
    }

}
