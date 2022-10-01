# Reference: https://leetcode.com/problems/valid-sudoku/solution/

# Space: O(N)
class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        N = 9
        rows = [0] * N
        cols = [0] * N
        boxes = [0] * N

        for r in range(N):
            for c in range(N):
                if board[r][c] == ".":
                    continue

                pos = int(board[r][c]) - 1

                # Check rows
                if rows[r] & (1 << pos):
                    return False
                rows[r] |= (1 << pos)

                # Check columns
                if cols[c] & (1 << pos):
                    return False
                cols[c] |= (1 << pos)

                # Check boxes
                idx = (r // 3) * 3 + c // 3
                if boxes[idx] & (1 << pos):
                    return False
                boxes[idx] |= (1 << pos)

        return True

# Space: O(N^2)
class Solution_naive:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        if not board or len(board) != 9 or len(board[0]) != 9:
            return False
        ROW = len(board)
        COL = len(board[0])

        row_valid = self.check_row(board, ROW, COL)
        col_valid = self.check_col(board, ROW, COL)
        box_valid = self.check_box(board, ROW, COL)
        # print(row_valid, col_valid, box_valid)
        return row_valid and col_valid and box_valid



    def check_row(self, board, ROW, COL):
        for row in range(ROW):
            seen = set()
            for col in range(COL):
                if board[row][col] == '.':
                    continue
                if board[row][col] in seen:
                    return False
                seen.add(board[row][col])
        return True

    def check_col(self, board, ROW, COL):
        for col in range(COL):
            seen = set()
            for row in range(ROW):
                if board[row][col] == '.':
                    continue
                if board[row][col] in seen:
                    return False
                seen.add(board[row][col])
        return True

    def check_box(self, board, ROW, COL):
        boxes = [[0] * 9 for _ in range(9)]
        for row in range(ROW):
            for col in range(COL):
                if board[row][col] == '.':
                    continue
                idx = (row // 3) * 3 + col // 3
                number = int(board[row][col]) - 1
                if boxes[idx][number]:
                    return False
                boxes[idx][number] = 1
        return True
