package leetcode.java.LC654;


import leetcode.java.Utils.TreeNode;


public class Main_654 {

    public static void main(String[] args) {
        Main_654 main_654 = new Main_654();
        main_654.constructMaximumBinaryTree(new int[] {3,2,1,6,0,5});
    }


    // Solution: Recursive
    // Ref: https://mp.weixin.qq.com/s/OlpaDhPDTJlQ5MJ8tsARlA
    // Time: O(NlgN)  --> worst: O(N*N)
    // Space: O(lgN) ~ O(N) for recursion stack
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;

        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int start, int end) {
        if (start < 0 || end > nums.length - 1 || start > end) {
            return null;
        }
        int max_idx = findMax(nums, start, end);
        TreeNode root = new TreeNode(nums[max_idx]);
        root.left = construct(nums, start, max_idx - 1);
        root.right = construct(nums, max_idx + 1, end);
        return root;
    }

    private int findMax(int[] nums, int start, int end) {
        int max = Integer.MIN_VALUE;
        int max_idx = -1;
        for (int i = start; i <= end; i++) {
            if (nums[i] > max) {
                max = nums[i];
                max_idx = i;
            }
        }
        return max_idx;
    }
}
