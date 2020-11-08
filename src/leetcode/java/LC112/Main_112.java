package leetcode.java.LC112;

// See also: [Series] LC112, LC113, LC437
// Ref: https://leetcode.wang/leetcode-112-Path-Sum.html

import leetcode.java.Utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main_112 {
    public static void main(String[] args) {
        Main_112 main_112 = new Main_112();
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node8 = new TreeNode(8);
        TreeNode node11 = new TreeNode(11);
        TreeNode node13 = new TreeNode(13);
        TreeNode node4_2 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        node5.left = node4;  node5.right = node8;
        node4.left = node11; node8.left = node13; node8.right = node4_2;
        node11.left = node7;  node11.right = node2;  node4_2.right = node1;
        System.out.println(main_112.hasPathSum_iteration(node5, 22));
    }


    // Solution1: Recursion  (DFS)
    // Time: O(N)
    // Space: O(H), where H: lgN ~ N,  for recursion stack
    public boolean hasPathSum_recursion(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }

        return hasPathSum_recursion(root.left, sum - root.val)
                || hasPathSum_recursion(root.right, sum - root.val);
    }


    // Solution2: Iteration (Queue) (BFS)
    // Ref: https://leetcode.wang/leetcode-112-Path-Sum.html
    // Time: O(N)
    // Space: O(N)  for  queue
    public boolean hasPathSum_iteration(TreeNode root, int sum) {
        if (root == null)
            return false;

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> queueSum = new LinkedList<>();
        queue.offer(root);
        queueSum.offer(sum);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                int remain = queueSum.poll();
                if (node.left == null && node.right == null) {
                    if (remain == node.val)
                        return true;
                    continue;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                    queueSum.offer(remain - node.val);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    queueSum.offer(remain - node.val);
                }
            }
        }
        return false;
    }



    // Solution3:  DFS (Simulation of recursion)
    // Ref: https://leetcode.wang/leetcode-112-Path-Sum.html
    // Time: O(N)
    // Space: O(N)
    public boolean hasPathSum_dfs(TreeNode root, int sum) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> stackSum = new Stack<>();
        TreeNode cur = root;
        int curSum = 0;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                curSum += cur.val;
                stackSum.push(curSum);
                cur = cur.left;
            }
            cur = stack.pop();
            curSum = stackSum.pop();
            if (curSum == sum && cur.left == null && cur.right == null) {
                return true;
            }
            cur = cur.right;
        }
        return false;
    }


    // Solution3 (Optimized): DFS (Simulation of recursion)
    // Ref: https://leetcode.wang/leetcode-112-Path-Sum.html
    // To be understood...
    public boolean hasPathSum_dfs_optimized(TreeNode root, int sum) {
        Stack<TreeNode> toVisit = new Stack<>();
        TreeNode cur = root, pre = null;
        int curSum = 0;
        while (cur != null || !toVisit.isEmpty()) {
            while (cur != null) {
                toVisit.push(cur);
                curSum += cur.val;
                cur = cur.left;
            }
            cur = toVisit.peek();
            if (curSum == sum && cur.left == null && cur.right == null) {
                return true;
            }
            if (cur.right == null || cur.right == pre) {
                TreeNode pop = toVisit.pop();
                curSum -= pop.val;
                pre = cur;
                cur = null;
            } else {
                cur = cur.right;
            }
        }
        return false;
    }
}
