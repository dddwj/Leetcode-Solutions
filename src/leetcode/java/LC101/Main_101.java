package leetcode.java.LC101;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

// GREAT Tutorial: https://youtu.be/F85boSPtfKg

public class Main_101 {
    public static void main(String[] args) {
        Main_101 main_101 = new Main_101();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(3);
        t1.left = t2; t1.right = t3; t2.left = t4; t2.right = t5; t3.left = t6; t3.right = t7;
        System.out.println(main_101.isSymmetric(t1));
    }

    // Solution1: DFS  (Recursive)
    // Time: O(N)  Space(Data Structure): O(1)  Space(Recursive Call): O(N)
    public boolean isSymmetric(TreeNode root) {
        if (root == null)   return true;
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }


    // Solution2: BFS - Queue  (Iterative)
    // Time: O(N)     // TODO: leetcode上说Space: O(N)? 是不是应该远小于O(N)?
    public boolean isSymmetric_solution2(TreeNode root) {
        if (root == null)   return true;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;

            // Note: 插入queue的顺序是破题点！
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }


}
