from typing import List

class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        if not grid or len(grid) == 0 or len(grid[0]) == 0:
            return 0

        result = 0
        visited = set()

        r = len(grid)
        c = len(grid[0])

        for i in range(r):
            for j in range(c):
                if self.search(grid, i, j, visited, r, c):
                    result += 1

        return result

    def search(self, grid, i, j, visited, r, c) -> int:
        if i < 0 or j < 0 or i >= r or j >= c:
            return False

        if (i, j) in visited:
            return False
        visited.add((i, j))

        if grid[i][j] == "1":
            self.search(grid, i + 1, j, visited, r, c)
            self.search(grid, i, j + 1, visited, r, c)
            self.search(grid, i - 1, j, visited, r, c)
            self.search(grid, i, j - 1, visited, r, c)
            return True

        return False



if __name__ == '__main__':
    # grid = [
    #     ["1","1","0","0","0"],
    #     ["1","1","0","0","0"],
    #     ["0","0","1","0","0"],
    #     ["0","0","0","1","1"]
    # ]
    grid = [
        ["1","1","1","1","0"],
        ["1","1","0","1","0"],
        ["1","1","0","0","0"],
        ["0","0","0","0","0"]
    ]
    s = Solution()
    print(s.numIslands(grid))
