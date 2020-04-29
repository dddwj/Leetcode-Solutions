package leetcode.java.LC124;

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

public class Main_124 {

    public static void main(String[] args) {
        Main_124 main_124 = new Main_124();
        TreeNode n_3 = new TreeNode(-3);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        TreeNode n1 = new TreeNode(1);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        TreeNode n6 = new TreeNode(6);
        n8.left = n_3; n8.right = n7;
        n_3.left = n1; n_3.right = n4;
        n1.right = n3; n4.left = n2; n4.right = n6;
        /** Tree
         *            8
         *          /  \
         *         -3  7
         *        /  \
         *       1   4
         *       \  / \
         *       3 2  6
         */
        System.out.println(main_124.maxPathSum(n8));
    }


    // Solution: DFS
    // Time: O(N)
    // Space: O(logN)
    // Ref: https://leetcode.wang/leetcode-124-Binary-Tree-Maximum-Path-Sum.html
    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    private int helper(TreeNode root) {
        if (root == null)
            return 0;
        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);

        // Note: Key point! 求的过程中考虑包含当前根节点的最大路径。（在递归过程中就可以进行求解本题）
        max = Math.max(max, root.val + left + right);

        // 只返回包含当前根节点和左子树或者右子树的路径
        return root.val + Math.max(left, right);
    }

    private int max = Integer.MIN_VALUE;        // 利用一个全局变量，在考虑 helper 函数中当前 root 的时候，同时去判断一下'包含当前root的路径'的最大值。

}
