package leetcode.java.LC207;

import java.util.*;

public class Main_207 {

    public static void main(String[] args) {
//         Main_207_solution1 main_207 = new Main_207_solution1();
        Main_207_solution2 main_207 = new Main_207_solution2();
        System.out.println(main_207.canFinish(2, new int[][]{
                {1, 0},
                // {0, 1}
        }));
    }
}


// My Solution: DFS - Naive Way  (TLE,  44/46 test cases passed)
// Time: O(N * E),  N = num of nodes,  E = num of edges
// Space: O(N * N)
class Main_207_mysolution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(i);
        }
        for (int[] prerequisite : prerequisites) {
            courses[prerequisite[0]].addCourse(courses[prerequisite[1]]);
        }
        for (Course course : courses) {
            if (hasCycle(course, new HashSet<Course>()))
                return false;
        }
        return true;
    }

    private boolean hasCycle(Course course, HashSet<Course> visited) {
        if (visited.contains(course))
            return true;
        visited.add(course);

        List<Course> pres = course.pres;
        for (Course pre : pres) {
            if (hasCycle(pre, new HashSet<Course>(visited)))
                return true;
        }
        return false;
    }


    class Course {
        int id;
        List<Course> pres;      // list of prerequisites.  Space: O(N)

        Course(int id) {
            this.id = id;
            this.pres = new ArrayList<>();
        }

        void addCourse(Course course) {
            this.pres.add(course);
        }
    }
}




// Solution1: BFS (Indegree Table)    Topological Sort
// Time: O(N + E),    N = num of nodes,  E = num of edges
// Space: O(N + E),
// Ref: https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
class Main_207_solution1 {
    public boolean canFinish(int numCourse, int[][] prerequisites) {
        int[] indegrees = new int[numCourse];
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        // 形成邻接表
        for (int i = 0; i < numCourse; i++)
            adjacency.add(new ArrayList<>());
        for (int[] cp : prerequisites) {
            indegrees[cp[0]]++;                 // 注意箭头方向(与mysolution方向相反)：前置课程 --> 后置课程。 indegree：后置课程的入度。
            adjacency.get(cp[1]).add(cp[0]);    // List of 前置课程。  eg: 前置课程[n] == [后置课程1, 后置课程2, 后称课程3...]
        }

        // 从入度为0开始遍历
        for (int i = 0; i < numCourse; i++)
            if (indegrees[i] == 0)
                queue.add(i);
        // BFS
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            numCourse--;
            for (int cur : adjacency.get(pre)) {
                indegrees[cur]--;
                if (indegrees[cur] == 0)
                    queue.add(cur);
            }
        }

        return numCourse == 0;
    }
}





// Solution2: DFS
// Time: O(N + E),    N = num of nodes,  E = num of edges
// Space: O(N + E),
// Ref: https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
class Main_207_solution2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        int[] visited = new int[numCourses];

        // 形成邻接表
        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        for (int[] cp : prerequisites)
            adjacency.get(cp[1]).add(cp[0]);

        // DFS搜索每一条路径、检测环
        for (int i = 0; i < numCourses; i++)
            if (!dfs(adjacency, visited, i))
                return false;

        return true;
    }

    private boolean dfs(List<List<Integer>> adjacency, int[] visited, int i) {
        if (visited[i] == 1)     // `i` is being visited, but a cycle detected!
            return false;
        if (visited[i] == -1)    // `i` has been visited normally
            return true;
        visited[i] = 1;
        for (int j : adjacency.get(i))
            if (!dfs(adjacency, visited, j))
                return false;
        visited[i] = -1;
        return true;
    }
}