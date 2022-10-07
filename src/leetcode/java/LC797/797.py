class Solution_backtracking:
    # Time: O(2^N * N)
    # Space: O(N) for recursion stack
    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        target = len(graph) - 1
        results = []

        def backtrack(curr_node, path):
            if curr_node == target:
                results.append(list(path))      # O(N) Time
                return
            for next_node in graph[curr_node]:
                path.append(next_node)
                backtrack(next_node, path)
                path.pop()

        path = [0]
        backtrack(0, path)
        return results


class Solution_dynamic_programming:
    def allPathsSourceTarget(self, graph: List[List[int]]) -> List[List[int]]:
        target = len(graph) - 1
        results = []

#         @lru_cache(maxsize=None)
        def all_paths_to_target(curr_node):
            if curr_node == target:
                return [[target]]

            results = []
            for next_node in graph[curr_node]:
                for path in all_paths_to_target(next_node):
                    results.append([curr_node] + path)

            return results

        return all_paths_to_target(0)