package leetcode.java.LC98;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {val = x;}
}


public class Main_98 {

    public static void main(String[] args) {
        Main_98 main_98 = new Main_98();

        TreeNode root = new TreeNode(5);
        TreeNode t1 = new TreeNode(0);
        TreeNode t2 = new TreeNode(10);
        TreeNode t3 = new TreeNode(-1);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(6);
        TreeNode t6 = new TreeNode(13);
        root.left = t1;  root.right = t2;  t1.left = t3;  t1.right = t4;  t2.right = t6;   t2.left = t5;
//        root.left = t1;
        System.out.println(main_98.isValidBST_mysolution2(root));
    }


    // My Solution: Brute Force  -- DFS
    // Time: worst: O(N*N), when the depth h = N
    // Space: O(h)  where h is the depth of this tree (range: logN ~ N)
    public boolean isValidBST_mysolution(TreeNode root) {
        /*
         * Process:
         * 1. if left!=null
         *        left.right.right... < root  ? valid : notValid
         * 2. if right!=null
         *        root < right.left.left....  ? valid : notValid
         */
        if (root == null)
            return true;

        TreeNode biggestOnTheLeft;
        TreeNode smallestOnTheRight;

        if (root.left == null && root.right == null)   // i.e. This 'root' node is the leaf node
            return true;

        if (root.left == null) {   // i.e. root.right is not null
            smallestOnTheRight = findSmallest(root.right);
            if (root.val < smallestOnTheRight.val)    // Confirms root is valid
                return isValidBST_mysolution(root.right);   // Finds whether root.right is valid
            else
                return false;
        }
        else if (root.right == null) {  // i.e. root.left is not null
            biggestOnTheLeft = findBiggest(root.left);
            if (biggestOnTheLeft.val < root.val)   // Confirms root is valid
                return isValidBST_mysolution(root.left);   // Finds whether root.left is valid
            else
                return false;
        } else {                        // i.e. root.left & root.right are both not null
            biggestOnTheLeft = findBiggest(root.left);
            smallestOnTheRight = findSmallest(root.right);
            if (biggestOnTheLeft.val < root.val && root.val < smallestOnTheRight.val)    // Confirms root is valid
                return isValidBST_mysolution(root.left) && isValidBST_mysolution(root.right);           // Finds whether root.left & root.right are valid
            else
                return false;
        }
    }

    private TreeNode findSmallest(TreeNode root) {
        TreeNode curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    private TreeNode findBiggest(TreeNode root) {
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }


    // Solution1: Recursion  -- DFS
    // Time: O(N), we visit each node exactly once.
    // Space: O(h)  where h is the depth of this tree (range: logN ~ N)
    public boolean isValidBST_solution1(TreeNode root) {
        return helper(root, null, null);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null)
            return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (! helper(node.right, val, upper)) return false;
        if (! helper(node.left, lower, val)) return false;

        return true;
    }


    // Solution2: Iteration -- BFS
    // Time: O(N), we visit each node exactly once.
    // Space: O(h)  where h is the depth of this tree (range: logN ~ N)
    LinkedList<TreeNode> stack = new LinkedList();
    LinkedList<Integer> uppers = new LinkedList(),
            lowers = new LinkedList();

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }

    public boolean isValidBST_solution2(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) continue;
            val = root.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }


    // Solution3: Inorder traversal (Check whether the values are in order)
    // Time: O(N)  Space: O(N) (To keep stack)
    public boolean isValidBST_solution3(TreeNode node) {
        Stack<TreeNode> stack = new Stack();
        double inorder = - Double.MAX_VALUE;

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (node.val <= inorder) return false;
            inorder = node.val;
            node = node.right;
        }
        return true;
    }



    // My Solution2: Inorder traversal  (Check whether the values are in order)
    // Time: O(N+N)
    // Space: O(N+h)  where h is the depth of this tree (range: logN ~ N)
    List<TreeNode> tree = new ArrayList<>();
    public boolean isValidBST_mysolution2(TreeNode root) {
        if (root == null)
            return true;

        // store the tree into the arraylist  (In-order traverse)  Time: O(N)
        helper4(root);

        // check if the arraylist is ascending. If not, then the tree is not a valid BST.  Time: O(N)
        for (int i = 0; i < tree.size() - 1; i++) {
            if (! (tree.get(i).val < tree.get(i + 1).val))
                return false;
        }

        return true;
    }

    public void helper4(TreeNode root) {
        if (root.left != null) {
            helper4(root.left);
        }
        tree.add(root);
        if (root.right != null) {
            helper4(root.right);
        }
    }
}
