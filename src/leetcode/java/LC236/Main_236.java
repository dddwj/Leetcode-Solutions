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

    }




    // My Solution: Naive    --- Refer to Leetcode 236.
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
