from typing import *

class Solution:
    def findCircleNum(self, isConnected: List[List[int]]) -> int:
        n = len(isConnected)
        if n < 1:
            return 0

        def dfs(i, isConnected, seen):
            for j, connection in enumerate(isConnected[i]):
                if j not in seen and isConnected[i][j] == 1:
                    seen.add(j)
                    dfs(j, isConnected, seen)

        seen = set()
        total = 0

        for i in range(n):
            if i not in seen:
                seen.add(i)
                total += 1
                dfs(i, isConnected, seen)

        return total
