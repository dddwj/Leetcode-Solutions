package leetcode.java.LC230;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main_230 {

    // Answer to Follow Up:   https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63743/Java-divide-and-conquer-solution-considering-augmenting-tree-structure-for-the-follow-up
    // Tutorial: https://youtu.be/QxFOR8sQuB4


    public static void main(String[] args) {
        Main_230 main_230 = new Main_230();

    }



    // My Solution:  In-order DFS - Recursion  (Solution1 on leetcode.com)
    // Time: O(N)
    // Space: O(lgN)
    public int kthSmallest_mysolution(TreeNode root, int k) {
        if (root == null)
            return -1;
        remain = k;
        return findSmaller(root);
    }

    private int remain;

    private Integer findSmaller(TreeNode root) {
        Integer res;

        if (root.left != null) {
            res = findSmaller(root.left);
            if (res != null)
                return res;
        }

        remain--;
        if (remain == 0)
            return root.val;

        if (root.right != null) {
            res = findSmaller(root.right);
            if (res != null)
                return res;
        }

        return null;
    }



    // Solution2:  In-order DFS - Recursion
    // Time: O(H + K)   Space: O(H + k), where H max = N, avg = lgN
    // Ref: https://leetcode.wang/leetcode-230-Kth-Smallest-Element-in-a-BST.html
    //      Template Below
    public int kthSmallest_solution2(TreeNode root, int k) {
        if (root == null)
            return -1;

        Stack<TreeNode> stack = new Stack<>();
        int remain = k;
        TreeNode cur = root;
        stack.push(cur);
        while (!stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            remain--;
            if (remain == 0)
                return cur.val;

            cur = cur.right;
        }
        return -1;
    }




    // Solution3: Divide and Conquer
    // Ref: https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63743/Java-divide-and-conquer-solution-considering-augmenting-tree-structure-for-the-follow-up
    public int kthSmallest_solution3(TreeNode root, int k) {
        int left = nodeCount(root.left);  // this value can be saved in the root node
        if(left + 1 == k) {
            return root.val;
        } else if (left + 1 < k) {
            return kthSmallest_solution3(root.right, k - left - 1);
        } else {   // left + 1 > k
            return kthSmallest_solution3(root.left, k);
        }
    }

    private int nodeCount(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + nodeCount(root.left) + nodeCount(root.right);
    }






    // Template: In-order traversal  -  Iteration
    // Ref: LC94: https://leetcode.wang/leetCode-94-Binary-Tree-Inorder-Traversal.html
    // Tutorial: https://youtu.be/QxFOR8sQuB4
    // Time: O(N)   Space: O(lgN)
    // Note: 记忆模版！
    public List<Integer> inorderTraversal_iteration(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || root != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            ans.add(cur.val);
            cur = cur.right;
        }
        return ans;
    }
}
