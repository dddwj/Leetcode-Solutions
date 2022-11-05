# See also: LC207, LC261
# See also: https://leetcode.com/discuss/interview-question/353830/Google-or-Phone-Screen-or-Parallel-Job-Scheduling

# Reference: https://leetcode.com/problems/parallel-courses/solution/

# Solution1: Graph - Topological Sort
# Time: O(N + E)
# Space: O(N + E)
class Solution_Graph:
    def minimumSemesters(self, n: int, relations: List[List[int]]) -> int:
        graph = defaultdict(Course)
        for relation in relations:
            prev, now = relation[0], relation[1]
            graph[prev].nexts.append(now)
            graph[now].inDegree += 1

        # print(graph)

        q = deque()
        for i in range(1, n+1):
            course = graph[i]
            if course.inDegree == 0:
                q.append(course)

        semester = 0
        visited = set()
        while q:
            semester += 1
            next_q = deque()
            while q:
                course = q.popleft()
                if course in visited:
                    return -1
                visited.add(course)
                for next_course_i in course.nexts:
                    next_course = graph[next_course_i]
                    next_course.inDegree -= 1
                    if next_course.inDegree == 0:
                        next_q.append(next_course)
            q = next_q

        return semester if len(visited) == n else -1

class Course:
    def __init__(self):
        self.nexts = []
        self.inDegree = 0


# Solution2: DFS with memoization
# Time: O(N + E)
# Space: O(N + E)
class Solution_DFS:
    def minimumSemesters(self, n: int, relations: List[List[int]]) -> int:
        graph = {i: [] for i in range(1, n+1)}
        for prev_course, next_course in relations:
            graph[prev_course].append(next_course)

        visited = {}

        def dfs(node: int) -> int:
            if node in visited:     # return the longest path (inclusive)
                return visited[node]
            else:
                # mark as visiting
                visited[node] = -1

            max_length = 1
            for end_node in graph[node]:
                length = dfs(end_node)
                if length == -1:    # Meet a cycle!
                    return -1
                else:
                    max_length = max(length + 1, max_length)

            visited[node] = max_length   # mark as visited
            return max_length

        max_length = -1
        for node in graph.keys():
            length = dfs(node)
            # print(node, length, visited)
            if length == -1:
                return -1
            else:
                max_length = max(length, max_length)

        return max_length