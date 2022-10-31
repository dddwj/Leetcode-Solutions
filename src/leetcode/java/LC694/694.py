# Reference: https://leetcode.com/problems/number-of-distinct-islands/solution/

# Time: O((MN)^2) where M = len(row) N = len(col)
# Space: O(MN)
class Solution_BruteForce_Comparison:
    def numDistinctIslands(self, grid: List[List[int]]) -> int:
        seen = set()
        num = 0
        ROW = len(grid)
        COL = len(grid[0])
        unique_islands = []

        def island_is_equal(island1, island2):
            for cell_1, cell_2 in zip(island1, island2):
                if cell_1 != cell_2:
                    return False
            return True

        def current_island_is_unique():
            for other_island in unique_islands:
                if len(other_island) != len(current_island):
                    continue
                if island_is_equal(current_island, other_island):
                    return False
            return True

        def dfs(i, j):
            if grid[i][j] == 1:
                seen.add((i, j))
                current_island.append((i - base_r, j - base_c))
                for dx, dy in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
                    x, y = i + dx, j + dy
                    if 0 <= x < ROW and 0 <= y < COL and (x, y) not in seen:
                        dfs(x, y)
            else:
                return

        for r in range(ROW):
            for c in range(COL):
                if grid[r][c] == 1 and (r, c) not in seen:
                    current_island = []
                    base_r, base_c = r, c
                    dfs(r, c)
                    if current_island and current_island_is_unique():
                        unique_islands.append(current_island)
        print(unique_islands)
        return len(unique_islands)


# Time: O(MN)
# Space: O(MN)
class Solution_Set_with_FrozenSet:
    def numDistinctIslands(self, grid: List[List[int]]) -> int:
        seen = set()
        num = 0
        ROW = len(grid)
        COL = len(grid[0])
        unique_islands = set()

        def dfs(i, j):
            if grid[i][j] == 1:
                seen.add((i, j))
                current_island.append((i - base_r, j - base_c))
                for dx, dy in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
                    x, y = i + dx, j + dy
                    if 0 <= x < ROW and 0 <= y < COL and (x, y) not in seen:
                        dfs(x, y)
            else:
                return

        for r in range(ROW):
            for c in range(COL):
                if grid[r][c] == 1 and (r, c) not in seen:
                    current_island = []
                    base_r, base_c = r, c
                    dfs(r, c)
                    if current_island:
                        unique_islands.add(frozenset(current_island))
        # print(unique_islands)
        return len(unique_islands)

"""

[[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
[[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]

"""


