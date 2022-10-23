# Reference: https://leetcode.com/problems/sum-of-distances-in-tree/solution/
# Met at TuSimple Interview
# Time: O(N)
# Space: O(N)
class Solution:
    def sumOfDistancesInTree(self, n: int, edges: List[List[int]]) -> List[int]:
        graph = collections.defaultdict(set)
        for u, v in edges:
            graph[u].add(v)
            graph[v].add(u)

        count = [1] * n
        distances = [0] * n

        def dfs(node, parent):
            for child in graph[node]:
                if child != parent:
                    dfs(child, node)
                    count[node] += count[child]
                    distances[node] += distances[child] + count[child]

        def dfs2(node, parent):
            for child in graph[node]:
                if child != parent:
                    distances[child] = distances[node] - count[child] + (n - count[child])
                    dfs2(child, node)


        dfs(0, None)    # Initialize graph & distance info
        dfs2(0, None)   # Build distances from bottom to up
        return distances
