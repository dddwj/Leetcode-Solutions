package leetcode.java.LC1448;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main_1448 {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n3_2 = new TreeNode(3);
        TreeNode n1_2 = new TreeNode(1);
        n3.left = n1;  n3.right = n4;
        n1.left = n3_2;  n4.left = n1_2; n4.right = n5;

        Main_1448 main_1448 = new Main_1448();
        System.out.println(main_1448.goodNodes(n3));    // Example 1 on leetcode.com
    }


    // My Solution: DFS
    // Time: O(N)
    // Space: O(lgN) for recursion
    private int res;
    public int goodNodes(TreeNode root) {
        if (root == null)
            return 0;

        res = 0;
        helper(root, Integer.MIN_VALUE);
        return res;
    }

    private void helper(TreeNode node, int maxAbove) {
        if (node == null)
            return;

        if (node.val >= maxAbove) {
            res++;
            maxAbove = node.val;
        }
        helper(node.left, maxAbove);
        helper(node.right, maxAbove);
    }
}
