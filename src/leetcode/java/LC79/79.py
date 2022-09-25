# Reference: https://leetcode.wang/leetCode-79-Word-Search.html
#            https://leetcode.com/problems/word-search/solution/
class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        N = len(board)
        M = len(board[0])

        def dfs(i, j, index, visited):
            if i >= N or j >= M or i < 0 or j < 0:
                return False
            if (i, j) in visited:
                return False
            if board[i][j] != word[index]:
                return False
            visited.add((i, j))
            if index == len(word) - 1:
                return True

            if dfs(i+1, j, index+1, visited) or dfs(i, j+1, index+1, visited) or dfs(i-1, j, index+1, visited) or dfs(i, j-1, index+1, visited):
                return True
            else:
                visited.remove((i, j))
                return False

        for i in range(N):
            for j in range(M):
                visited = set()
                if dfs(i, j, 0, visited):
                    return True
        return False
