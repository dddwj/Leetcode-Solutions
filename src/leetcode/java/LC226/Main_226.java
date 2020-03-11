package leetcode.java.LC226;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main_226 {

    public static void main(String[] args) {

    }

    // Solution1 (My Solution): Recursive
    // Time: O(N)
    // Space: O(H), where H: worst = N, best = logN
    public TreeNode invertTree_solution1(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }

        TreeNode temp = invertTree_solution1(root.left);
        root.left = invertTree_solution1(root.right);
        root.right = temp;
        return root;
    }

    // Solution2: Iterative
    // Time: O(N)
    // Space: O(N)  Because: for the worst: the queue will contain all nodes in one level of the binary tree. For a full binary tree, the leaf level has ceil(N/2) = O(N) leaves
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
