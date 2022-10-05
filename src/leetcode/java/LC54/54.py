# Reference: https://leetcode.com/problems/spiral-matrix/solution/
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        ROW = len(matrix)
        COL = len(matrix[0])

        left = 0
        right = COL - 1
        up = 0
        down = ROW - 1

        res = []
        while len(res) < ROW * COL:
            # Traverse from left to right.
            for i in range(left, right+1, 1):
                res.append(matrix[up][i])

            # Traverse downwards.
            for i in range(up+1, down+1, 1):
                res.append(matrix[i][right])

            # Make sure we are now on a different row.
            if up != down:
                # Traverse from right to left.
                for i in range(right-1, left-1, -1):
                    res.append(matrix[down][i])

            # Make sure we are now on a different column.
            if left != right:
                # Traverse upwards.
                for i in range(down-1, up, -1):
                    res.append(matrix[i][left])

            up += 1
            down -= 1
            left += 1
            right -= 1

        return res