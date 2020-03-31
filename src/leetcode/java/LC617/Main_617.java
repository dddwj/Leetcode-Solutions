package leetcode.java.LC617;

import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main_617 {

    public static void main(String[] args) {
        Main_617 main_617 = new Main_617();
        TreeNode t0 = new TreeNode(0);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode s0 = new TreeNode(0);
        TreeNode s1 = new TreeNode(1);
        TreeNode s2 = new TreeNode(2);
        TreeNode s3 = new TreeNode(3);
        TreeNode s4 = new TreeNode(4);
        TreeNode s5 = new TreeNode(5);
        TreeNode s6 = new TreeNode(6);
        TreeNode s7 = new TreeNode(7);
        t1.left = t3; t3.left = t5; t1.right = t2;
        s2.left = s1; s1.right = s4; s2.right = s3; s3.right = s7;
        main_617.mergeTrees_solution2(t1, s2);
    }



    // My Solution (Solution1): Recursive
    // Time: O(N),  where N = min( len(t1), len(t2) )
    // Space: O(log(N)),  where N = min( len(t1), len(t2) )
    public TreeNode mergeTrees_mysolution(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return t1;
        }
        if (t1 == null) {
            return t2;
        }
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees_mysolution(t1.left, t2.left);
        t1.right = mergeTrees_mysolution(t1.right, t2.right);
        return t1;
    }




    // Solution2: Iterative
    // Time: O(N)
    // Space: O(h), where h min: logN    max: N
    public TreeNode mergeTrees_solution2(TreeNode t1, TreeNode t2) {
        if (t2 == null || t1 == null) {
            return t1 == null ? t2 : t1;
        }

        Stack< TreeNode[] > stack = new Stack<>();
        stack.push(new TreeNode[]{t1, t2});

        while (!stack.isEmpty()) {
            TreeNode[] nodes = stack.pop();
            if (nodes[1] == null) {
                continue;
            }

            nodes[0].val += nodes[1].val;

            if (nodes[0].left == null) {
                nodes[0].left = nodes[1].left;
            } else {
                stack.push(new TreeNode[]{nodes[0].left, nodes[1].left});
            }

            if (nodes[0].right == null) {
                nodes[0].right = nodes[1].right;
            } else {
                stack.push(new TreeNode[]{nodes[0].right, nodes[1].right});
            }
        }

        return t1;
    }
}
