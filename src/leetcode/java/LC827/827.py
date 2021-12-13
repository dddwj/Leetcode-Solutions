# Reference: https://leetcode.com/problems/making-a-large-island/discuss/1375940/Python-dfs-with-connected-components-explained

from itertools import product
from typing import *

class Solution_Brute_Force:
    def largestIsland(self, grid: List[List[int]]) -> int:
        N = len(grid)

        def check(r, c):
            seen = {(r, c)}
            stack = [(r, c)]
            while stack:
                r, c = stack.pop()
                for nr, nc in ((r-1, c), (r, c-1), (r+1, c), (r, c+1)):
                    if (nr, nc) not in seen and 0 <= nr < N and 0 <= nc < N and grid[nr][nc]:
                        stack.append((nr, nc))
                        seen.add((nr, nc))
            return len(seen)

        ans = 0
        has_zero = False
        for r, row in enumerate(grid):
            for c, val in enumerate(row):
                if val == 0:
                    has_zero = True
                    grid[r][c] = 1
                    ans = max(ans, check(r, c))
                    grid[r][c] = 0

        return ans if has_zero else N*N


class Solution:
    def largestIsland(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        neib_list = [[1,0], [-1,0],[0,-1],[0,1]]
        islands, count, ans = {}, 2, 0

        def dfs(t, i, j):
            if not 0 <= i < m or not 0 <= j < n or grid[i][j] != 1:
                return
            if t not in islands:
                islands[t] = 0
            islands[t] += 1
            grid[i][j] = t
            for x, y in neib_list:
                dfs(t, i+x, j+y)

        for x, y in product(range(m), range(n)):
            if grid[x][y] == 1:
                dfs(count, x, y)
                count += 1

        for x, y in product(range(m), range(n)):
            if grid[x][y] != 0:
                continue
            neibs = set()
            for dx, dy in neib_list:
                if 0 <= x + dx < m and 0 <= y + dy < n and grid[x+dx][y+dy] != 0:
                    neibs.add(grid[x+dx][y+dy])
            ans = max(ans, sum(islands[i] for i in neibs) + 1)

        return ans if ans != 0 else m*n

s = Solution()
# print(s.largestIsland([[1,0],[0,1]]))
# print(s.largestIsland([[1,1],[1,0]]))
print(s.largestIsland([[1,1],[1,1]]))