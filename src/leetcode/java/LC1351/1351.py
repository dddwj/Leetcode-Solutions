class Solution:
    def countNegatives(self, grid: List[List[int]]) -> int:
        ROW = len(grid)
        COL = len(grid[0])

        row = ROW - 1
        col = 0

        pos = 0   # non-negative number count
        while row >= 0:
            if col >= COL:
                break
            while col < COL:
                if grid[row][col] >= 0:
                    col += 1
                else:
                    break
            pos += col
            # print(row, pos)
            row -= 1

        while row >= 0:
            pos += COL
            row -= 1

        return ROW * COL - pos