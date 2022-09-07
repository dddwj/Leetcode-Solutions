# Reference: https://leetcode.com/problems/candy-crush/discuss/113914/15-ms-Short-Java-Solution-Mark-crush-with-negative-value

class Solution:
    def candyCrush(self, board: List[List[int]]) -> List[List[int]]:
        N = len(board)
        M = len(board[0])

        found = True
        while found:
            found = False
            for i in range(N):
                for j in range(M):
                    element = abs(board[i][j])
                    if element == 0:
                        continue
                    if j < M - 2 and element == abs(board[i][j+1]) and element == abs(board[i][j+2]):
                        found = True
                        ptr = j
                        while ptr < M and abs(board[i][ptr]) == element:
                            board[i][ptr] = -element
                            ptr += 1
                    if i < N - 2 and element == abs(board[i+1][j]) and element == abs(board[i+2][j]):
                        found = True
                        ptr = i
                        while ptr < N and abs(board[ptr][j]) == element:
                            board[ptr][j] = -element
                            ptr += 1
            if found:
                for j in range(M):
                    i = N-1
                    ptr = i
                    while i >= 0:
                        if board[i][j] > 0:
                            board[ptr][j] = board[i][j]
                            ptr -= 1
                            i -= 1
                        else:
                            i -= 1
                    while ptr >= 0:
                        board[ptr][j] = 0
                        ptr -= 1

        return board