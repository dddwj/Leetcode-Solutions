package leetcode.java.LC449;

// See also: LC105, LC106
// Great Tutorial: 剑指offer P194

import leetcode.java.Utils.TreeNode;

import java.util.*;


// Solution1: Naive, with BST features
// Ref: https://leetcode-cn.com/problems/serialize-and-deserialize-bst/solution/xu-lie-hua-he-fan-xu-lie-hua-er-cha-sou-suo-shu-2/
//      LC105, LC106
// Time: O(N)
// Space: O(N) 编码序列：需要 (N - 1)(N−1) 个分隔符和 NN 个节点的值。树的结构和顺序不占用空间。
public class Main_449_solution1 {

    public static void main(String[] args) {

        Main_449_solution1 main_449_solution1 = new Main_449_solution1();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n3.left = n2; n2.left = n1; n3.right = n5; n5.left = n4;
        /*
            3
          2    5
         1    4
         */

        String str = main_449_solution1.serialize(n3);
        System.out.println(str);
        main_449_solution1.deserialize(str);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder sb = new StringBuilder();
        traverse_preOrder(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void traverse_preOrder(TreeNode node, StringBuilder sb) {
        if (node == null)
            return;
        sb.append(node.val).append(",");
        traverse_preOrder(node.left, sb);
        traverse_preOrder(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0)
            return null;
        LinkedList<Integer> preOrder = new LinkedList<>();
        String[] nodeValStrings = data.split(",");
        for (String nodeValString : nodeValStrings) {
            preOrder.add(Integer.valueOf(nodeValString));
        }
        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, preOrder);
    }

    private TreeNode helper(int minValue, int maxValue, LinkedList<Integer> preOrder) {
        if (preOrder.isEmpty())
            return null;

        int val = preOrder.getFirst();
        if (val < minValue || val > maxValue)
            return null;

        TreeNode root = new TreeNode(val);
        preOrder.removeFirst();

        root.left = helper(minValue, val, preOrder);
        root.right = helper(val, maxValue, preOrder);
        return root;
    }

}
