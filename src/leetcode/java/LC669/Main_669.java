package leetcode.java.LC669;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main_669 {

    public static void main(String[] args) {

    }



    // Solution1: Recursive
    // Time: O(logN)
    // Space: O(logN)
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }

        if (root.val < L) {
            root = trimBST(root.right, L, R);
            return root;
        }

        if (root.val > R) {
            root = trimBST(root.left, L, R);
            return root;
        }

        /*
        Optional:

        if (root.val == R) {
            root.right = null;
            root.left = trimBST(root.left, L, R);
            return root;
        }

        if (root.val == L) {
            root.left = null;
            root.right = trimBST(root.right, L, R);
            return root;
        }

         */

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}
