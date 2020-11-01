package leetcode.java.LC105;

// See also: LC106, LC654
// Great Tutorial: https://mp.weixin.qq.com/s/OlpaDhPDTJlQ5MJ8tsARlA

import leetcode.java.Utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Main_105 {

    public static void main(String[] args) {
        Main_105 main_105 = new Main_105();
        main_105.buildTree_solution1(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        main_105.buildTree_solution1_optimized(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
    }

    // Solution1: Recursive  (Search for root everytime)
    // Time: average: O(NlgN) ~ worst: O(N*N)
    // Space: O(lgN) for recursion
    // Ref: https://mp.weixin.qq.com/s/OlpaDhPDTJlQ5MJ8tsARlA
    public TreeNode buildTree_solution1(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length)
            return null;
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart < 0 || inEnd >= inorder.length) {
            return null;
        }

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int inorder_root_idx = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                inorder_root_idx = i;
                break;
            }
        }

        int leftSize = inorder_root_idx - inStart;

        root.left = build(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, inorder_root_idx - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                inorder, inorder_root_idx + 1, inEnd);

        return root;
    }


    // Solution1: Recursive (Optimized) (Use hashmap to cache)  (Solution1 on leetcode-cn.com)
    // Time: O(N)
    // Space: O(lgN) for recursion + O(N) for cache
    // Ref: https://www.cxyxiaowu.com/8321.html
    Map<Integer, Integer> cache;

    public TreeNode buildTree_solution1_optimized(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length)
            return null;

        cache = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            cache.put(inorder[i], i);
        }

        return build_optimized(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }


    private TreeNode build_optimized(int[] preorder, int preStart, int preEnd,
                                     int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart < 0 || inEnd >= inorder.length) {
            return null;
        }

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int inorder_root_idx = cache.get(rootVal);

        int leftSize = inorder_root_idx - inStart;

        root.left = build_optimized(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, inorder_root_idx - 1);
        root.right = build_optimized(preorder, preStart + leftSize + 1, preEnd,
                inorder, inorder_root_idx + 1, inEnd);

        return root;
    }


    // Solution2: Iterative  (To be understood...)
    // Time: O(N)
    // Space: O(N)
    // Ref: https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/
    public TreeNode buildTree_solution2(int[] preorder, int[] inorder) {
        return null;
    }
}