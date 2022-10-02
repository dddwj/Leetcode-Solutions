# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


# Ref:  https://mp.weixin.qq.com/s/z44hk0MW14_mAQd7988mfw
class Solution_recursion:
    def rob(self, root: Optional[TreeNode]) -> int:
        self.memo = {}
        return self.dfs(root)

    def dfs(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0
        if root in self.memo:
            return self.memo[root]

        # Do rob
        left_amount = right_amount = 0
        if root.left:   # Cannot rob root.left
            left_amount = self.dfs(root.left.left) + self.dfs(root.left.right)
        if root.right:
            right_amount = self.dfs(root.right.left) + self.dfs(root.right.right)
        rob_it = root.val + left_amount + right_amount

        # Do not rob
        not_rob = self.dfs(root.left) + self.dfs(root.right)

        res = max(rob_it, not_rob)
        self.memo[root] = res
        return res


# Ref: https://leetcode.com/problems/house-robber-iii/solutions/873813/house-robber-iii/
class Solution_recursion_concise:
    def rob(self, root: TreeNode) -> int:
        def helper(node):
            if not node:
                return [0, 0]

            left = helper(node.left)
            right = helper(node.right)

            rob = node.val + left[1] + right[1]
            not_rob = max(left) + max(right)
            return [rob, not_rob]

        return max(helper(root))


# Ref: https://leetcode.com/problems/house-robber-iii/solutions/873813/house-robber-iii/
class Solution_Recursion_with_Memoization:
    def rob(self, root: TreeNode) -> int:
        rob_saved = {}
        not_rob_saved = {}

        def helper(node, parent_robbed):
            if not node:
                return 0

            if parent_robbed:
                if node in rob_saved:
                    return rob_saved[node]
                result = helper(node.left, False) + helper(node.right, False)
                rob_saved[node] = result
                return result
            else:
                if node in not_rob_saved:
                    return not_rob_saved[node]
                rob = node.val + helper(node.left, True) + helper(node.right, True)
                not_rob = helper(node.left, False) + helper(node.right, False)
                result = max(rob, not_rob)
                not_rob_saved[node] = result
                return result

        return helper(root, False)