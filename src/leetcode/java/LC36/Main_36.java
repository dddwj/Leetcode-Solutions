package leetcode.java.LC36;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main_36 {
    public static void main(String[] args) {
        Main_36 main_36 = new Main_36();
        char[][] input = {
                {'.','.','4','.','.','.','6','3','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'5','.','.','.','.','.','.','9','.'},
                {'.','.','.','5','6','.','.','.','.'},
                {'4','.','3','.','.','.','.','.','1'},
                {'.','.','.','7','.','.','.','.','.'},
                {'.','.','.','5','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
        };
        System.out.println(main_36.isValidSudoku(input));
    }


    // Solution1: check row -> column -> box
    public boolean isValidSudoku_solution1(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9)
            throw new IllegalArgumentException("Illegal Input");

        return isValidRow(board) && isValidColumn(board) && isValidBox(board);
    }

    public boolean isValidRow(char[][] board) {
        for (int i = 0; i < 9; i++) {
            HashSet<Character> set = new HashSet<>();         // 由于只有九个数，所以可以不用HashSet/HashTable，可以用数组: boolean[] taken = new boolean[9];
            for (Character c : board[i]) {
                if (c == '.')
                    continue;
                if (set.contains(c))
                    return false;
                else
                    set.add(c);
            }
        }

        return true;
    }

    public boolean isValidColumn(char[][] board) {
        for (int i = 0; i < 9; i++) {
            HashSet<Character> set = new HashSet<>();    // 问题：在迭代过程中临时变量开辟的空间会被'回收再利用'吗？如果不是，那么该迭代的空间复杂度会达到:9N
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.')
                    continue;
                if (set.contains(board[j][i]))
                    return false;
                else
                    set.add(board[j][i]);
            }
        }

        return true;
    }

    public boolean isValidBox(char[][] board) {

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (checkBox(i, j, board))
                    continue;
                else
                    return false;
            }
        }

        return true;
    }

    public boolean checkBox(int startRowIdx, int startColIdx, char[][] board) {
        HashSet<Character> set = new HashSet<Character>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRowIdx + i][startColIdx + j] == '.')
                    continue;
                if (set.contains(board[startRowIdx + i][startColIdx + j]))
                    return false;
                else
                    set.add(board[startRowIdx + i][startColIdx + j]);
            }
        }
        return true;
    }



    // Solution2: check
    public boolean isValidSudoku(char[][] board) {
        // init data
        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer>[] columns = new HashMap[9];
        HashMap<Integer, Integer>[] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<Integer, Integer>();
            columns[i] = new HashMap<Integer, Integer>();
            boxes[i] = new HashMap<Integer, Integer>();
        }

        // validate a board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int n = Integer.valueOf(""+num);
                    int box_index = (i / 3) * 3 + j / 3;   // 可记忆：二维数组划片的方法

                    // keep the current cell value
                    rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
                    columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
                    boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);

                    // check if this value has been already seen before
                    if (rows[i].getOrDefault(n, 0) > 1 || columns[j].getOrDefault(n, 0) > 1 || boxes[box_index].getOrDefault(n, 0) > 1)
                        return false;
                }
            }
        }
        return true;
    }
}

