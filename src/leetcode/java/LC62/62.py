# Dynamic Programming
# Time: O(N*M)
# Space: O(N*M)
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        paths = [[0] * n for _ in range(m)]
        paths[0] = [1] * n
        for i in range(m):
            paths[i][0] = 1

        for row in range(1, m):
            for col in range(1, n):
                paths[row][col] = paths[row-1][col] + paths[row][col-1]

        return paths[m-1][n-1]