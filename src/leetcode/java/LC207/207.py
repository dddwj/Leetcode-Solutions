# Tutorial: https://leetcode.com/problems/course-schedule/solution/
#           https://leetcode.wang/leetcode-207-Course-Schedule.html


# Solution1: Backtrack  (TLE!)
class Solution_Backtrack:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        self.courseDict = defaultdict(list)

        for relation in prerequisites:
            nextCourse, prevCourse = relation[0], relation[1]
            self.courseDict[prevCourse].append(nextCourse)

        # print(self.courseDict)

        path = [False] * numCourses
        for currCourse in range(numCourses):
            # print(currCourse)
            if self.isCyclic(currCourse, path):
                return False
        return True

    def isCyclic(self, currCourse, path):
        if path[currCourse]:
            # come across a previously visited node, i.e. detect the cycle
            return True

        # before backtracking, mark the node in the path
        path[currCourse] = True

        # backtracking
        cycle_detected = False
        for nextCourse in self.courseDict[currCourse]:
            cycle_detected = self.isCyclic(nextCourse, path)
            if cycle_detected:
                break

        path[currCourse] = False
        return cycle_detected


# Solution2: DFS // Backtrack with Memoization
# Time: O(E + V)
# Space: O(E + V)
class Solution_DFS:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        self.courseDict = defaultdict(list)

        for relation in prerequisites:
            nextCourse, prevCourse = relation[0], relation[1]
            self.courseDict[prevCourse].append(nextCourse)

        print(self.courseDict)

        checked = [False] * numCourses
        path = [False] * numCourses
        for currCourse in range(numCourses):
            print(currCourse)
            if self.isCyclic(currCourse, path, checked):
                return False
        return True


    def isCyclic(self, currCourse, path, checked):
        if checked[currCourse]:
            # this node has been checked, no cycle would be formed with this node.
            return False

        if path[currCourse]:
            # come across a previously visited node, i.e. detect the cycle
            return True

        # before backtracking, mark the node in the path
        path[currCourse] = True

        # backtracking
        cycle_detected = False
        for nextCourse in self.courseDict[currCourse]:
            cycle_detected = self.isCyclic(nextCourse, path, checked)
            if cycle_detected:
                break

        path[currCourse] = False

        # Now that we've visited the nodes in the downstream,
        #   we complete the check of this node.
        checked[currCourse] = True
        return cycle_detected




# Solution3: Topological Sort
# Time: O(E+V)
# Space: O(E+V)
class Solution_TopologicalSort:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        graph = defaultdict(GNode)

        for relation in prerequisites:
            nextCourse, prevCourse = relation[0], relation[1]
            graph[prevCourse].outNodes.append(nextCourse)
            graph[nextCourse].inDegrees += 1


        nodepCourses = deque()
        for index, node in graph.items():
            if node.inDegrees == 0:
                nodepCourses.append(index)

        removedEdges = []
        while nodepCourses:
            course = nodepCourses.pop()

            for nextCourse in graph[course].outNodes:
                graph[nextCourse].inDegrees -= 1
                removedEdges.append([nextCourse, course])
                if graph[nextCourse].inDegrees == 0:
                    nodepCourses.append(nextCourse)

        # print(removedEdges)
        if len(removedEdges) == len(prerequisites):
            return True
        else:
            return False

class GNode(object):
    def __init__(self):
        self.inDegrees = 0
        self.outNodes = []

