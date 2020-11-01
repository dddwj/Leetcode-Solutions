package leetcode.java.LC106;

// See also: LC105, LC654
// Great Tutorial: https://mp.weixin.qq.com/s/OlpaDhPDTJlQ5MJ8tsARlA

import leetcode.java.Utils.TreeNode;

import java.util.HashMap;
import java.util.Map;


public class Main_106 {

    public static void main(String[] args) {
        Main_106 main_106 = new Main_106();
        main_106.buildTree(new int[] {9,3,15,20,7}, new int[]{9,15,7,20,3});
    }


    // Solution1: Recursive (Optimized) (Use hashmap to cache)  (Solution1 on leetcode-cn.com)
    // Time: O(N)
    // Space: O(lgN) for recursion + O(N) for cache
    // Ref: LC105
    private Map<Integer, Integer> cache;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;

        cache = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            cache.put(inorder[i], i);
        }

        return buildTree(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd)
            return null;

        TreeNode root = new TreeNode(postorder[postEnd]);

        int root_idx = cache.get(root.val);
        int rightSize = inEnd - root_idx;

        root.left = buildTree(inorder, inStart, root_idx - 1, postorder, postStart, postEnd - rightSize - 1);
        root.right = buildTree(inorder, root_idx + 1, inEnd, postorder, postEnd - rightSize, postEnd - 1);

        return root;
    }

}
