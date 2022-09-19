# Reference: https://leetcode.com/problems/valid-sudoku/solution/

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