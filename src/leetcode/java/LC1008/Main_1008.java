package leetcode.java.LC1008;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main_1008 {

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.bstFromPreorder(new int[]{8,5,1,7,10,12});

        Solution2 solution2 = new Solution2();
        solution2.bstFromPreorder(new int[]{8,5,1,7,10,12});
    }



    // Solution1: In-order & Pre-order traverse
    // Time: O(NlogN)
    // Space: O(N) to store in-order array
    // Ref: https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/solution/jian-kong-er-cha-shu-by-leetcode/
    public static class Solution1 {
        int pre_idx = 0;
        int[] preorder;
        HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

        public TreeNode helper(int in_left, int in_right) {
            // if there is no elements to construct subtrees
            if (in_left == in_right)
                return null;

            // pick up pre_idx element as a root
            int root_val = preorder[pre_idx];
            TreeNode root = new TreeNode(root_val);

            // root splits inorder list into left and right subtrees
            int index = idx_map.get(root_val);

            // recursion
            pre_idx++;
            root.left = helper(in_left, index);
            root.right = helper(index + 1, in_right);
            return root;
        }

        public TreeNode bstFromPreorder(int[] preorder) {
            this.preorder = preorder;
            int[] inorder = Arrays.copyOf(preorder, preorder.length);
            Arrays.sort(inorder);

            // build a hashmap value -> its index
            int idx = 0;
            for (Integer val : inorder)
                idx_map.put(val, idx++);
            return helper(0, inorder.length);
        }
    }



    // Solution2: Recursion
    // Time: O(N)
    // Space: O(logN)  for recursion stack
    // Ref: https://leetcode-cn.com/problems/construct-binary-search-tree-from-preorder-traversal/solution/jian-kong-er-cha-shu-by-leetcode/
    public static class Solution2 {
        int idx = 0;
        int[] preorder;
        int n;

        public TreeNode helper(int lower, int upper) {
            // if all elements from preorder are used, then tree is constructed
            if (idx == n)
                return null;

            int val = preorder[idx];
            // if the current element could not be placed here to meet BST requirements
            if (val < lower || val > upper)
                return null;

            // place the current element and recursively construct subtrees
            idx++;
            TreeNode root = new TreeNode(val);
            root.left = helper(lower, val);
            root.right = helper(val, upper);
            return root;
        }

        public TreeNode bstFromPreorder(int[] preorder) {
            this.preorder = preorder;
            n = preorder.length;
            return helper(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }


    // Solution3: Iteration
    // Time: O(N)
    // Space: O(N)
    public static class Solution3 {
        public TreeNode bstFromPreorder(int[] preorder) {
            int n = preorder.length;
            if (n == 0)
                return null;

            TreeNode root = new TreeNode(preorder[0]);
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.push(root);

            for (int i = 1; i < n; i++) {
                TreeNode node = deque.peek();
                TreeNode child = new TreeNode(preorder[i]);

                // adjust the parent
                while (!deque.isEmpty() && deque.peek().val < child.val)
                    node = deque.pop();

                // follow the BST rule to create a parent-child link
                if (node.val < child.val)
                    node.right = child;
                else
                    node.left = child;

                // add the child into deque
                deque.push(child);
            }
            return root;
        }
    }


    // My Solution: Find the spot + place the node  (Similar to Solution3 on leetcode.com)
    // Time: O(N*logN)
    // Space: O(1)
    public TreeNode bstFromPreorder_mysolution(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            addNode(root, preorder[i]);
        }
        return root;
    }

    private void addNode(TreeNode root, int val) {
        TreeNode curr = root;
        TreeNode prev = null;
        while (curr != null) {
            if (val == curr.val)  {   // invalid
                return;
            } else if (val > curr.val) {
                prev = curr;
                curr = curr.right;
            } else if (val < curr.val) {
                prev = curr;
                curr = curr.left;
            }
        }
        if (prev.val < val)
            prev.right = new TreeNode(val);
        else if (prev.val > val)
            prev.left = new TreeNode(val);
        return;
    }
}
