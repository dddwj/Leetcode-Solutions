package leetcode.java.LC113;

// See also: [Series] LC112, LC113, LC437

import leetcode.java.Utils.TreeNode;

import java.util.*;

public class Main_113 {

    public static void main(String[] args) {
        Main_113 main_113 = new Main_113();
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node8 = new TreeNode(8);
        TreeNode node11 = new TreeNode(11);
        TreeNode node13 = new TreeNode(13);
        TreeNode node4_2 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node2 = new TreeNode(2);
        TreeNode node5_2 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        node5.left = node4;
        node5.right = node8;
        node4.left = node11;
        node8.left = node13;
        node8.right = node4_2;
        node11.left = node7;
        node11.right = node2;
        node4_2.right = node1;
        node4_2.left = node5_2;
        System.out.println(main_113.pathSum_solution2(node5, 22));
    }


    // Solution1: Recursion (Backtrack) (DFS)
    // Ref: LC46
    // Time: O(N)
    // Space: O(H), where H: lgN ~ N,  for recursion stack
    // Ref: https://leetcode.wang/leetcode-113-Path-SumII.html
    public List<List<Integer>> pathSum_solution1(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        computeSum(root, sum, new ArrayList<>(), res);
        return res;
    }

    private void computeSum(TreeNode root, int remain, List<Integer> board, List<List<Integer>> res) {
        if (root.left == null && root.right == null) {
            if (root.val == remain) {
                List<Integer> list = new ArrayList<>(board);
                list.add(root.val);
                res.add(list);
            }
        }

        // Backtrack:
        //          Traverse all choices
        //          1. make choice
        //          2. backtrack
        //          3. undo choice
        if (root.left != null) {
            board.add(root.val);
            computeSum(root.left, remain - root.val, board, res);
            board.remove(board.lastIndexOf(root.val));
        }
        if (root.right != null) {
            board.add(root.val);
            computeSum(root.right, remain - root.val, board, res);
            board.remove(board.lastIndexOf(root.val));
        }
    }



    // Solution2: Iteration (Queue) (BFS)
    // Time: O(N)
    // Space: O(N) for queue and map
    // Ref: https://leetcode-cn.com/problems/path-sum-ii/solution/lu-jing-zong-he-ii-by-leetcode-solution/
    public List<List<Integer>> pathSum_solution2(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        Map<TreeNode, TreeNode> map = new HashMap<>();
        if (root == null)
            return res;

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> queueSum = new LinkedList<>();
        queue.offer(root);
        queueSum.offer(sum);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                int remain = queueSum.poll();
                if (node.left == null && node.right == null && node.val == remain) {
                    addResult(node, res, map);
                }
                if (node.left != null) {
                    map.put(node.left, node);
                    queue.offer(node.left);
                    queueSum.offer(remain - node.val);
                }
                if (node.right != null) {
                    map.put(node.right, node);
                    queue.offer(node.right);
                    queueSum.offer(remain - node.val);
                }
            }
        }
        return res;
    }

    private void addResult(TreeNode node, List<List<Integer>> res, Map<TreeNode, TreeNode> map) {
        List<Integer> list = new LinkedList<>();
        list.add(node.val);
        while (map.containsKey(node)) {
            node = map.get(node);
            list.add(node.val);
        }
        Collections.reverse(list);
        res.add(list);
    }
}
