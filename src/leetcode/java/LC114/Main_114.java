package leetcode.java.LC114;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


public class Main_114 {

    public static void main(String[] args) {
        TreeNode t0 = new TreeNode(0);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        t1.left = t2;   t1.right = t5;  t5.right = t6;
        t2.left = t3;   t2.right = t4;
        Main_114 main_114 = new Main_114();

        main_114.flatten_solution1(t1);
        main_114.printList(t1);

        main_114.flatten_solution1(t0);
        main_114.printList(t0);


    }

    private void printList(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            System.out.println(cur.val);
            if (cur.left != null) {
                System.out.println("error: T" + cur.val);
            }
            cur = cur.right;
        }
    }


    // Solution1: Naive     (from top to bottom)
    // Time: O(N)
    // Space: O(1)
    // Ref: https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/
    public void flatten_solution1(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                TreeNode rightMost = root.left;
                while (rightMost.right != null) {
                    rightMost = rightMost.right;
                }
                rightMost.right = root.right;

                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }



    // Solution2: Naive, use 'pre' pointer to track right child (from bottom to top)
    // Time: O(N)
    // Space: O(logN)
    // Ref: https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/
    private TreeNode pre = null;
    public void flatten_solution2(TreeNode root) {
        if (root == null)
            return;
        flatten_solution2(root.right);        // 后序遍历：root.right -> root.left -> root。 使用后序遍历+自底向上，达到自顶向下正序遍历的效果。
        flatten_solution2(root.left);
        root.right = pre;
        root.left = null;
        pre = root;     // Note: 使用pre指针来保存右孩子, 好方法！
    }



    // Solution3: Stack, use stack to track right child (from top to bottom)
    // Time: O(N)
    // Space: O(N)
    // Ref: https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/
    public void flatten(TreeNode root) {
        if (root == null){
            return;
        }
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        TreeNode pre = null;
        while (!s.isEmpty()) {
            TreeNode temp = s.pop();
            /* **********修改的地方*************/
            if(pre!=null){
                pre.right = temp;
                pre.left = null;
            }
            /* *******************************/
            if (temp.right != null){
                s.push(temp.right);
            }
            if (temp.left != null){
                s.push(temp.left);
            }
            /* **********修改的地方*************/
            pre = temp;
            /* *******************************/
        }
    }


    // My Solution: Preorder + LinkedList  (Not 'in-place')
    // Time: O(N)
    // Space: O(N)
    public void flatten_mysolution(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        preorder(root, queue);

        TreeNode cur = new TreeNode(-1);
        while (!queue.isEmpty()) {
            cur.left = null;
            cur.right = queue.poll();
            cur = cur.right;
        }
    }

    private void preorder(TreeNode root, Queue<TreeNode> queue) {
        if (root == null) {
            return;
        }
        queue.offer(root);
        preorder(root.left, queue);
        preorder(root.right, queue);
    }
}
