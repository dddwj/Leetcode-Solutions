package leetcode.java.LC1457;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main_1457 {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(1);
        TreeNode n6 = new TreeNode(1);
        n1.left = n2; n1.right = n3;
        n2.left = n4; n2.right = n5;
        n3.right = n6;

        Main_1457 main_1457 = new Main_1457();
        System.out.println(main_1457.pseudoPalindromicPaths_solution1(n1));
    }



    // My Solution: DFS(Recursion) + Array Alphabet (with List to store paths)
    // Time: O(N)  N = count(nodes)
    // Space: O(lgN + N),  lgN for recursion stack,
    //                      N for storing paths  (every node has an alphabet table of size 10)
    List<int[]> paths;   // list to store paths
    public int pseudoPalindromicPaths_mysolution(TreeNode root) {
        if (root == null)
            return 0;
        paths = new ArrayList<>();

        helper(root, new int[10]);

        int count = 0;
        for (int[] path : paths) {
            // System.out.println(Arrays.toString(path));
            if (isPseudoPalindrome(path)) {
                count++;
            }
        }
        return count;
    }

    private boolean isPseudoPalindrome(int[] path) {
        int oddRemain = 1;
        for (int freq : path) {
            if (freq % 2 == 0)
                continue;
            if (oddRemain == 0)
                return false;
            oddRemain--;
        }
        return true;
    }

    private void helper(TreeNode root, int[] table) {
        table[root.val]++;
        if (root.left == null && root.right == null) {
            paths.add(table);
            return;
        }
        if (root.left != null) {
            helper(root.left, Arrays.copyOf(table, table.length));      // Space: O(10 * N) = O(N)
        }
        if (root.right != null)
            helper(root.right, Arrays.copyOf(table, table.length));     // Space: O(10 * N) = O(N)
    }




    // Solution1: DFS(Recursion) + Hashset Alphabet (without list to store paths)
    // Time: O(N), N = count(nodes)
    // Space: O(lgN) for recursion stack
    // Ref: https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/discuss/648345/Java-DFS-+-Set-+-Recursion
    public int pseudoPalindromicPaths_solution1(TreeNode root) {
        return canBePalindrome(root, new HashSet());
    }

    private int canBePalindrome(TreeNode node, Set<Integer> numbers) {
        if (node == null)
            return 0;

        if (numbers.contains(node.val)) {
            numbers.remove(node.val);
        } else {
            numbers.add(node.val);
        }

        if (node.left == null && node.right == null) {
            return (numbers.size() == 0 || numbers.size() == 1) ? 1 : 0;
        }

        int left = canBePalindrome(node.left, new HashSet(numbers));
        int right = canBePalindrome(node.right, new HashSet(numbers));
        return left + right;
    }
}
