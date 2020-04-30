package leetcode.java.UnknownNumber.CheckValidSequence;

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

public class CheckValidSequenceSolution {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(0);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(0);
        TreeNode n5 = new TreeNode(15);
        TreeNode n6 = new TreeNode(16);
        n1.left = n2; n2.left = n3; n3.right = n4;
        n1.right = n5; n2.right = n6;

        CheckValidSequenceSolution solution = new CheckValidSequenceSolution();
        System.out.println(solution.isValidSequence(n1, new int[]{1, 0, 1, 0, 10, 11}));
    }

    public boolean isValidSequence(TreeNode root, int[] arr) {
        if (root == null || arr == null || arr.length == 0)
            return false;
        return isValidNode(root, arr, 0);
    }

    private boolean isValidNode(TreeNode root, int[] arr, int depth) {System.out.println(depth);
        if (depth >= arr.length || root.val != arr[depth])
            return false;
        if (root.left == null && root.right == null) {
            return depth == arr.length - 1;
        }
        return (root.left != null && isValidNode(root.left, arr, depth + 1))
                || (root.right != null && isValidNode(root.right, arr, depth + 1));
    }
}
