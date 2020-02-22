package leetcode.java.LC104;

import javafx.util.Pair;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {val = x;}
}

public class Main_104 {

    public static void main(String[] args) {
        Main_104 main_104 = new Main_104();
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(20);
        TreeNode t3 = new TreeNode(30);
        TreeNode t4 = new TreeNode(40);
        t1.left = t2;
        t2.left = t3;
        t2.right = t4;
        System.out.println(main_104.maxDepth_Solution22(t1));
        System.out.println(main_104.maxDepth(t1));
    }

    // Solution1: Recursive (DFS)
    // Worst Time: O(N)， 所有节点都只有左孩子
    // Best Time: O(logN)， 完全二叉树
    // Space: O(1)
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // Solution2 (Opt1): Iterative (BFS)
    // Time: O(N) Space: O(N)
    public int maxDepth_Solution21(TreeNode root) {
        Queue<SimpleEntry<TreeNode, Integer>> queue = new LinkedList<>();
        if (root != null) {
            queue.add(new SimpleEntry<>(root, 1));
        }

        int depth = 0;
        while (!queue.isEmpty()) {
            SimpleEntry<TreeNode, Integer> current = queue.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                queue.add(new SimpleEntry<>(root.left, current_depth + 1));
                queue.add(new SimpleEntry<>(root.right, current_depth + 1));
            }
        }
        return depth;
    }

    // Solution2 (Opt2): Iterative (BFS)
    // Time: O(N) Space: O(N)
    public int maxDepth_Solution22(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // bfs
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
        return depth;
    }

}
