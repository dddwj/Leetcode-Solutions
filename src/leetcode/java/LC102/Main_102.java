package leetcode.java.LC102;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {val = x;}
}

public class Main_102 {
    public static void main(String[] args) {
        Main_102 main_102 = new Main_102();

        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
//        TreeNode t4 = new TreeNode();
//        TreeNode t5 = new TreeNode();
        TreeNode t6 = new TreeNode(15);
        TreeNode t7 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t3.left = t6;
        t3.right = t7;

        // Store result to 'rst'
        List<List<Integer>> rst = main_102.levelOrder_solution1(t1);

        // Print 'rst' out
        for (List<Integer> line : rst) {
            System.out.println(Arrays.toString(line.toArray()));
        }
    }

    // Solution1: DFS - Recursive
    // Time: O(N)  Space: O(N)
    public List<List<Integer>> levelOrder_solution1(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null)   return levels;
        levelOrder(root, 0, levels);
        return levels;
    }

    public void levelOrder(TreeNode node, int level, List<List<Integer>> levels) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

        // fulfil the current level
        levels.get(level).add(node.val);

        // process child nodes for the next level   (Pre-order Traverse here. In-order or Post-order also can!)
        if (node.left != null)
            levelOrder(node.left, level + 1, levels);
        if (node.right != null)
            levelOrder(node.right, level + 1, levels);
    }



    // Solution2: BFS - Iteration
    // Time: O(N)  Space: O(N)
    public List<List<Integer>> levelOrder_solution2(TreeNode root) {
        if (root == null)
            return new LinkedList<List<Integer>>();

        List<List<Integer>> rst = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> line = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.remove();
                line.add(node.val);

                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }

            rst.add(line);
        }
        return rst;
    }



}
