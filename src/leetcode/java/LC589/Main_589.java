package leetcode.java.LC589;

import java.util.*;

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}



public class Main_589 {

    public static void main(String[] args) {

    }


    // Solution1: Recursive
    // Time: O(N)
    // Space: O(logN)
    public List<Integer> preorder_solution1(Node root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }

        List<Integer> res = new ArrayList<>();

        dfs(root, res);

        return res;
    }
    private void dfs(Node root, List<Integer> res) {
        res.add(root.val);

        if (root.children == null) {
            return;
        }

        List<Node> children = root.children;
        for (Node child : children) {
            dfs(child, res);
        }
    }



    // Solution2: Iterative
    // Time: O(N)
    // Space: O(N) (worst)
    public List<Integer> preorder_solution2(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> rst = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            rst.add(node.val);

            if (node.children == null) {
                continue;
            }

            Collections.reverse(node.children);     // Reverse 是 迭代成功的关键
            for (Node child : node.children) {
                stack.push(child);
            }
        }

        return rst;
    }

}
