package leetcode.java.LC102;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {val = x;}
}

public class Main_102 {
    public static void main(String[] args) {
        Main_102 main_102 = new Main_102();

        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
//        TreeNode t4 = new TreeNode();
//        TreeNode t5 = new TreeNode();
        TreeNode t6 = new TreeNode(15);
        TreeNode t7 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t3.left = t6;
        t3.right = t7;

        List<List<Integer>> rst = main_102.levelOrder(t1);
        for (List<Integer> line : rst) {
            System.out.println(Arrays.toString(line.toArray()));
        }
    }



    // Solution2: BFS - Iteration
    // Time: O(N)  Space: O(N)
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new LinkedList<List<Integer>>();

        List<List<Integer>> rst = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> line = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.remove();
                line.add(node.val);

                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }

            rst.add(line);
        }
        return rst;
    }



}
