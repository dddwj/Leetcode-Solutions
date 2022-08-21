# Reference: https://leetcode.com/problems/walls-and-gates/discuss/72745/Java-BFS-Solution-O(mn)-Time/75176

class Solution:
    def wallsAndGates(self, rooms: List[List[int]]) -> None:
        """
        Do not return anything, modify rooms in-place instead.
        """
        row = len(rooms)
        col = len(rooms[0])
        inf = 2147483647
        queue = []

        for i in range(row):
            for j in range(col):
                if rooms[i][j] == 0:
                    queue.append((i, j))

        while queue:
            i, j = queue.pop(0)
            if i - 1 >= 0 and rooms[i - 1][j] == inf:
                queue.append((i - 1, j))
                rooms[i - 1][j] = rooms[i][j] + 1
            if i + 1 < row and rooms[i + 1][j] == inf:
                queue.append((i + 1, j))
                rooms[i + 1][j] = rooms[i][j] + 1
            if j - 1 >= 0 and rooms[i][j - 1] == inf:
                queue.append((i, j - 1))
                rooms[i][j - 1] = rooms[i][j] + 1
            if j + 1 < col and rooms[i][j + 1] == inf:
                queue.append((i, j + 1))
                rooms[i][j + 1] = rooms[i][j] + 1


