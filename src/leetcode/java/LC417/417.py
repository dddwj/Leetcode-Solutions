class Solution:
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        m = len(heights)
        n = len(heights[0])

        pacific_stack = deque()
        atlantic_stack = deque()
        for i in range(m):
            pacific_stack.append((i, 0))
            atlantic_stack.append((i, n - 1))
        for i in range(n):
            pacific_stack.append((0, i))
            atlantic_stack.append((m - 1, i))

        pacific_reachable = self.bfs(pacific_stack, heights)
        atlantic_reachable = self.bfs(atlantic_stack, heights)

        return list(pacific_reachable.intersection(atlantic_reachable))

    def bfs(self, queue, heights):
        m = len(heights)
        n = len(heights[0])
        reachable = set()
        while queue:
            (row, col) = queue.popleft()
            reachable.add((row, col))
            for (dx, dy) in [(1, 0), (0, 1), (-1, 0), (0, -1)]:
                new_row = row + dx
                new_col = col + dy
                if new_row < 0 or new_row >= m or new_col < 0 or new_col >= n:
                    continue
                if (new_row, new_col) in reachable:
                    continue
                if heights[new_row][new_col] < heights[row][col]:
                    continue
                queue.append((new_row, new_col))
        return reachable