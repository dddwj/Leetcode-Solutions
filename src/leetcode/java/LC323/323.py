# Solution 1: DFS
# Time: O(E + N)  E = len(edges)  N = number of nodes
class Solution:
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        visited = set()
        adjacent = defaultdict(set)
        for edge in edges:
            n1, n2 = edge[0], edge[1]
            adjacent[n1].add(n2)
            adjacent[n2].add(n1)

        count = 0
        for node in range(n):
            if node in visited:
                continue
            count += 1
            self.dfs(node, visited, adjacent)
        return count

    def dfs(self, node, visited, adjacent):
        if node in visited:
            return
        visited.add(node)
        for neighbor in adjacent[node]:
            self.dfs(neighbor, visited, adjacent)



# Solution 2: Union Find
# Reference: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/discuss/1483077/python-or-DFS-sol-or-Union-Find-sol
class UnionFind:
    def __init__(self,size):
        self.root = [i for i in range(size)]
        self.rank = [1] * size

    def find(self,x):
        if x != self.root[x]:
            self.root[x] = self.find(self.root[x])
        return self.root[x]

    def union(self,x,y):
        rootX = self.find(x)
        rootY = self.find(y)
        if rootX != rootY:
            if self.rank[rootX] > self.rank[rootY]:
                self.root[rootY] = rootX
            elif self.rank[rootX] < self.rank[rootY]:
                self.root[rootX] = rootY
            else:
                self.root[rootY] = rootX
                self.rank[rootX] += 1

class Solution_Union_Find:
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        uf = UnionFind(n)
        for A,B in edges:
            uf.union(A,B)

        count = 0
        for i in range(n):
            if i == uf.root[i]:
                count += 1
        return count


