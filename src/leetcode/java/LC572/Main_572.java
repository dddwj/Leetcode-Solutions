package leetcode.java.LC572;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main_572 {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t4 = new TreeNode(4);
        TreeNode t0 = new TreeNode(0);
        TreeNode s1 = new TreeNode(1);
        TreeNode s2 = new TreeNode(2);
        TreeNode s3 = new TreeNode(3);
        TreeNode s4 = new TreeNode(4);
        TreeNode s5 = new TreeNode(5);
        TreeNode s0 = new TreeNode(0);

        s3.left = s4; s3.right = s5; s4.left = s1; s4.right = s2;
        t4.left = t1; t4.right = t2;
        // t2.right = t0;  // s2.right = s0;
        Main_572 main_572 = new Main_572();
        System.out.println(main_572.isSubtree_solution1(s3, t4));
        System.out.println(main_572.isSubtree_solution1(null, t4));
        System.out.println(main_572.isSubtree_solution1(s3, null));
    }



    // My Solution: Iteration(Queue) + Recursive   (Similar to Solution2 on Leetcode.com)
    // Time: O(M*N)
    // Space: O(N)   (The depth of the recursion tree can go up to n)
    public boolean isSubtree_mysolution(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(s);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.val == t.val) {   // Check equivalent
                if (isEqual(cur, t)) {
                    return true;
                }
            }
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        return false;
    }

    private boolean isEqual(TreeNode cur, TreeNode t) {
        if (cur == null && t == null) {
            return true;
        }
        if (cur == null || t == null) {
            return false;
        }
        return (cur.val == t.val) &&
                isEqual(cur.left, t.left) &&
                isEqual(cur.right, t.right);
    }




    // Solution1: Using Preorder Traversal + String Comparison
    // Time: O(m^2 + n^2 + m*n)
    // Space: O(max(m,n))
    // Ref: https://leetcode.com/problems/subtree-of-another-tree/solution/
    public boolean isSubtree_solution1(TreeNode s, TreeNode t) {
        String tree1 = preorder(s);
        String tree2 = preorder(t);
//        System.out.println(tree1);
//        System.out.println(tree2);
        return tree1.contains(tree2);
    }

    public String preorder(TreeNode t) {
        if (t == null) {
            return "null";
        }
        return "#" + t.val + " " + preorder(t.left) + " " + preorder(t.right);
    }




    // Solution2: Recursive + Recursive
    // Time: O(M*N)
    // Space: O(N)  (The depth of the recursion tree can go up to N)
    public boolean isSubtree_solution2(TreeNode s, TreeNode t) {
        return traverse(s, t);
    }

    public boolean equals(TreeNode x, TreeNode y) {
        if(x==null && y==null)
            return true;
        if(x==null || y==null)
            return false;
        return x.val == y.val &&
                equals(x.left, y.left) &&
                equals(x.right, y.right);
    }

    public boolean traverse(TreeNode s, TreeNode t) {
        return  s != null &&
                ( equals(s, t) || traverse(s.left, t) || traverse(s.right, t));
    }

}
