package leetcode.java.LC993;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main_993 {
    public static void main(String[] args) {
        Main_993 main_993 = new Main_993();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;  n1.right = n3;
        n2.right = n4;  n3.right = n5;
        System.out.println(main_993.isCousins(n1, 4, 5));
    }



    // Solution: DFS
    // Time: O(N)
    // Space: O(N)
    // Ref: https://leetcode-cn.com/problems/cousins-in-binary-tree/solution/er-cha-shu-de-tang-xiong-di-jie-dian-by-leetcode/
    public boolean isCousins(TreeNode root, int x, int y) {
        depth = new HashMap<>();
        parent = new HashMap<>();
        dfs(root, null);
        return (depth.get(x) == depth.get(y) && parent.get(x) != parent.get(y));
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            depth.put(node.val, par != null ? 1 + depth.get(par.val) : 0);
            parent.put(node.val, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    Map<Integer, Integer> depth;
    Map<Integer, TreeNode> parent;

}
