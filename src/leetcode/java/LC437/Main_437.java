package leetcode.java.LC437;

// See also: [Series] LC112, LC113, LC437

import leetcode.java.Utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Main_437 {

    public static void main(String[] args) {
        Main_437 main_437 = new Main_437();
        TreeNode root = TreeNode.buildFromList("[10,5,-3,3,2,null,11,3,-2,null,1]");
        System.out.println(main_437.pathSum_solution1(root, 8));
        System.out.println(main_437.pathSum_solution2(root, 8));
    }


    // Solution1:  Recursive  "双递归"
    // Time: O(NlgN) ~ O(N*N)   (I guess so...)
    // Space: recursion stack
    // Ref: https://leetcode-cn.com/problems/path-sum-iii/solution/liang-chong-fang-fa-jian-dan-yi-dong-ban-ben-by-a3/
    public int pathSum_solution1(TreeNode root, int sum) {
        if (root == null)
            return 0;
        return pathSum_solution1(root.left, sum)
                + pathSum_solution1(root.right, sum)
                + countFromRoot(root, sum);
    }

    private int countFromRoot(TreeNode root, int sum) {
        if (root == null)
            return 0;

        int result = 0;
        if (sum - root.val == 0)
            result++;
        result += countFromRoot(root.left, sum - root.val) + countFromRoot(root.right, sum - root.val);

        return result;
    }



    // Solution2:  Recursive (Use 'preSum' to cache)  prefix:"前缀和"
    // Time: O(N)
    // Space: O(N)
    // Ref: https://leetcode.com/problems/path-sum-iii/discuss/91878/17-ms-O(n)-java-Prefix-sum-method
    public int pathSum_solution2(TreeNode root, int sum) {
        Map<Integer, Integer> preSum = new HashMap<>();        // map 记录的是 "从根节点到当前节点的路径上，以根节点为起点，长为 key 的子序列的数量"
                                                               //    ( key : the prefix sum, value : how many ways get to this prefix sum)
        preSum.put(0, 1);
        return helper(root, 0, sum, preSum);
    }

    private int helper(TreeNode root, int currSum, int target, Map<Integer, Integer> preSum) {
        if (root == null)
            return 0;

        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        res += helper(root.left, currSum, target, preSum)
                + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }
}















