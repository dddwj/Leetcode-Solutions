# Great Tutorial: https://leetcode.com/problems/graph-valid-tree/solution/
# See also: LC1136, LC207, https://leetcode.com/discuss/interview-question/353830/Google-or-Phone-Screen-or-Parallel-Job-Scheduling

# Solution1: BFS or DFS
# Time: O(V + E)
# Space: O(V + E)
class Solution_BFS_OR_DFS:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        graph = defaultdict(list)
        for node1, node2 in edges:
            graph[node1].append(node2)
            graph[node2].append(node1)

        parent = {0: -1}
        q = deque([0])

        while q:
            p_node = q.popleft()    # q.pop() for DFS  //     q.popleft() for BFS
            for child in graph[p_node]:
                if child == parent[p_node]:
                    continue
                if child in parent:
                    return False
                parent[child] = p_node
                q.append(child)

        return len(parent) == n

# Solution2.1: Graph Theory + Recursive Depth-First Search
# Time: O(N + E) => O(N) where E bounded by N - 1
# Space: O(N + E) => O(N)
class Solution_21:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        if len(edges) != n - 1:
            return False

        adj_list = defaultdict(list)
        for A, B in edges:
            adj_list[A].append(B)
            adj_list[B].append(A)

        seen = set()
        def dfs(node):
            if node in seen:
                return
            seen.add(node)
            for neighbor in adj_list[node]:
                dfs(neighbor)


        dfs(0)
        return len(seen) == n


# Solution2.2: Graph Theory + Iterative Depth-First Search
class Solution_22:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        if len(edges) != n - 1:
            return False

        adj_list = defaultdict(list)
        for A, B in edges:
            adj_list[A].append(B)
            adj_list[B].append(A)

        seen = {0}
        stack = [0]

        while stack:
            node = stack.pop()
            for neighbour in adj_list[node]:
                if neighbour in seen:
                    continue
                seen.add(neighbour)
                stack.append(neighbour)

        return len(seen) == n


# Solution 3: Union Find
