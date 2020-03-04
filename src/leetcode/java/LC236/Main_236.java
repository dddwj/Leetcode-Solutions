package leetcode.java.LC236;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main_236 {

    /*
        SAME AS: Leetcode 235
     */


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode l5 = new TreeNode(5);
        TreeNode l1 = new TreeNode(1);
        TreeNode l6 = new TreeNode(6);
        TreeNode l2 = new TreeNode(2);
        TreeNode l0 = new TreeNode(0);
        TreeNode l8 = new TreeNode(8);
        TreeNode l7 = new TreeNode(7);
        TreeNode l4 = new TreeNode(4);
        root.left = l5; root.right = l1;
        l5.left = l6;   l5.right = l2;
        l2.left = l7;   l2.right = l4;
        l1.left = l0;   l1.right = l8;

        Main_236 main_236 = new Main_236();
        System.out.println(main_236.lowestCommonAncestor(root, l5, l4).val);
    }



    // Solution1: Recursive Approach
    // Time(worst): O(N)
    // Space(worst): O(N)
    private TreeNode ans;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Traverse the tree
        recurseTree(root, p, q);
        return this.ans;
    }

    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {

        // If reached the end of a branch, return false.
        if (currentNode == null) {
            return false;
        }

        // Left Recursion. If left recursion returns true, set left = 1 else 0
        int left = recurseTree(currentNode.left, p, q) ? 1 : 0;

        // Right Recursion
        int right = recurseTree(currentNode.right, p, q) ? 1 : 0;

        // If the current node is one of p or q
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;


        // If any two of the flags left, right or mid become True
        if (mid + left + right >= 2) {
            this.ans = currentNode;
        }

        // Return true if any one of the three bool values is True.
        return (mid + left + right > 0);
    }



    // Solution3:  Iterative without parent pointers
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/



    // My Solution (Solution2 On Leetcode.com) : Naive  (Iterative using parent pointers)    --- Refer to Leetcode 236.
    // Time: O(N)
    // Space: O(N)
    public TreeNode lowestCommonAncestor_mysolution(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)   return null;

        // Traverse the tree unitl both nodes are found
        int nodeFound = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, TreeNode> map = new HashMap<>();
        queue.offer(root);
        map.put(root, null);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                if (curr == p || curr == q)
                    if (++nodeFound == 2)
                        break;
                if (curr.left != null) {
                    queue.offer(curr.left);
                    map.put(curr.left, curr);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                    map.put(curr.right, curr);
                }
            }
        }

        // Find ancestors of p into a set
        Set<TreeNode> set = new HashSet<TreeNode>();
        set.add(p);
        TreeNode ancestorP = map.get(p);
        while (ancestorP != null) {
            set.add(ancestorP);
            ancestorP = map.get(ancestorP);
        }

        // Find q's ancestors in the set
        if (set.contains(q))
            return q;
        TreeNode ancestorQ = map.get(q);
        while (ancestorQ != null) {
            if (set.contains(ancestorQ))
                return ancestorQ;
            else
                ancestorQ = map.get(ancestorQ);
        }

        return null;
    }
}
