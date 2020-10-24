package leetcode.java.LC337;

// See also (Series):  LC213, LC198
// Ref: https://mp.weixin.qq.com/s/z44hk0MW14_mAQd7988mfw

import java.util.HashMap;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Main_337 {

    public static void main(String[] args) {
        Main_337 main_337 = new Main_337();
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(1);
        n1.left = n2; n1.right = n3;  n2.left = n4; n2.right = n5; n3.right = n6;

        System.out.println(main_337.rob(null));
        System.out.println(main_337.rob(n1));
    }


    // Solution1: Dynamic Programming
    // Ref: https://mp.weixin.qq.com/s/z44hk0MW14_mAQd7988mfw
    // Time: O(N)
    // Space: O(N)
    private HashMap<TreeNode, Integer> memo = new HashMap<>();
    public int rob(TreeNode root) {
        if (root == null)
            return 0;
        if (memo.containsKey(root))
            return memo.get(root);

        int do_it = root.val
                + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
        int not_do = rob(root.left) + rob(root.right);
        int max_income = Math.max(do_it, not_do);
        memo.put(root, max_income);
        return max_income;
    }
}
