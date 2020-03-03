package leetcode.java.LC235;


import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


public class Main_235 {

    /*
        SAME AS: Leetcode 236
     */



    public static void main(String[] args) {
        Main_235 main_235 = new Main_235();
        TreeNode root = new TreeNode(6);
        TreeNode l1 = new TreeNode(2);
        TreeNode l2 = new TreeNode(8);
        TreeNode l3 = new TreeNode(0);
        TreeNode l4 = new TreeNode(4);
        TreeNode l5 = new TreeNode(7);
        TreeNode l6 = new TreeNode(9);
        TreeNode l7 = new TreeNode(3);
        TreeNode l8 = new TreeNode(5);
        root.left = l1; root.right = l2;
        l1.left = l3;   l1.right = l4;
        l2.left = l5;   l2.right = l6;
        l4.left = l7;   l4.right = l8;

        System.out.println(main_235.lowestCommonAncestor(root, l4, l1).val);
    }


    // Solution1:  Utilizing BST features  -- Recursive Method
    // Time(worst): O(N)
    // Space(worst): O(N)
    public TreeNode lowestCommonAncestor_solution1(TreeNode root, TreeNode p, TreeNode q) {
        int parentVal = root.val;   // value of current node or parent node
        int pVal = p.val;   // value of p
        int qVal = q.val;   // value of q

        if (pVal > parentVal && qVal > parentVal) {
            return lowestCommonAncestor_solution1(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            return lowestCommonAncestor_solution1(root.left, p, q);
        } else   //  i.e.:    p <= parentVal <= q   or   q <= parentVal <= p
            // parentVal must be the lowest Common Ancestor
            return root;
    }


    // Solution2:  Utilizing BST features  -- Iterative Method
    // Time: O(N)
    // Space: O(1)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val;   // value of p
        int qVal = q.val;   // value of q

        TreeNode node = root;

        while (node != null) {
            int parentVal = node.val;

            if (pVal > parentVal && qVal > parentVal) {
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                node = node.left;
            } else
                return node;
        }

        return null;
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
