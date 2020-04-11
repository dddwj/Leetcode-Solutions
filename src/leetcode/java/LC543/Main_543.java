package leetcode.java.LC543;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main_543 {

    public static void main(String[] args) {
        Main_543 main_543 = new Main_543();

    }

    // My Solution (Solution1: DFS)
    // Time: O(N)
    // Space: O(logN)
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return res;
    }

    private int helper(TreeNode root) {
        int len_l = 0, len_r = 0;
        if (root.left != null) {
            len_l += helper(root.left);
            len_l ++;
        }
        if (root.right != null) {
            len_r += helper(root.right);
            len_r ++;
        }

        res = Math.max(res, len_l + len_r);

        return len_l > len_r ? len_l : len_r;
    }
}
